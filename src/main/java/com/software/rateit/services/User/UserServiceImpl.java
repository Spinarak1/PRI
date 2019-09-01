package com.software.rateit.services.User;

import com.software.rateit.DTO.CD.CDMapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Comments.CommentAlbumDTO;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.Comments.CommentsMapper;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.Rate.RateDTO;
import com.software.rateit.DTO.User.*;
import com.software.rateit.Entity.CD;
import com.software.rateit.Entity.Comments;
import com.software.rateit.Entity.Rate;
import com.software.rateit.Entity.User;
import com.software.rateit.exceptions.AuthenticationException;
import com.software.rateit.exceptions.NotFoundException;
import com.software.rateit.repositories.CDRepository;
import com.software.rateit.repositories.CommentsRepository;
import com.software.rateit.repositories.RateRepository;
import com.software.rateit.repositories.UserRepository;
import com.software.rateit.services.Raport;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.mapstruct.IterableMapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl .class);


    @Value("${server-address}")
    private String serverAddress;

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private CDRepository cdRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private CDMapper cdMapper = Mappers.getMapper(CDMapper.class);

    private CommentsMapper commentsMapper = Mappers.getMapper(CommentsMapper.class);

    @Override
    public ResponseEntity<UserResponseWrapper> listOfUsers(Pageable pageable) {

        Page<User> userEntities = repository.findAll(pageable);
        Iterable<UserDTO> users = mapper.mapToUserDTOIterable(userEntities);

        PaginationContext info;

        if(userEntities.getTotalPages() <= pageable.getPageNumber()+1) {
            info = new PaginationContext(pageable.getPageNumber(), userEntities.getTotalPages(), pageable.getPageSize(),
                    userEntities.getTotalElements(), null);
        } else {
            info = new PaginationContext(pageable.getPageNumber(), userEntities.getTotalPages(), pageable.getPageSize(),
                    userEntities.getTotalElements(), serverAddress + "/users?" + "page="
                    + (pageable.getPageNumber()+1) + "&size=" + pageable.getPageSize());
        }

        return new ResponseEntity<>(new UserResponseWrapper(users, info), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getOneById(long id) {
        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return new ResponseEntity<>(mapper.mapToUserDTOWithCollections(user), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDTO> registerNewUser(RegistrationDTO registration) {

        if(registration.getPassword() == null || registration.getConfirmPassword() == null ||
                registration.getEmail() == null || registration.getUsername() == null) {
            throw new AuthenticationException("Can not be empty");
        }
        if(!registration.getPassword().equals(registration.getConfirmPassword())){
            throw new AuthenticationException("Password doesn't match");
        }
        if(!validateEmail(registration.getEmail())){
            throw new AuthenticationException("Email not correct");
        }
        if(repository.findOneByNick(registration.getUsername()) != null) {
            throw new AuthenticationException("Username already taken");
        }
        if(repository.findOneByEmail(registration.getEmail()) != null) {
            throw new AuthenticationException("Email already taken");
        }

        User newUser = new User();
        newUser.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        newUser.setNick(registration.getUsername());
        newUser.setEmail(registration.getEmail());
        newUser.setRole("USER");
        User response = repository.save(newUser);
//        simpleEmail(response.getEmail(), "RateIt: new account", "Hello " + response.getNick()+ "!\n" +
//                "You have successfully registered your account!\n\nBest regards,\nRateIt team!");

        return new ResponseEntity<>(mapper.mapToUserDTO(response), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<UserDTO> changePassword(long id, PasswordChangeDTO password) {

        if(!password.getNewPassword().equals(password.getConfirmPassword()) && password.getNewPassword() != null) {
            throw new AuthenticationException("Password does not match");
        }

        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        if(!bCryptPasswordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new AuthenticationException("Old password incorrect");
        }
        user.setPassword(bCryptPasswordEncoder.encode(password.getNewPassword()));
        User response = repository.save(user);
//        simpleEmail(response.getEmail(), "RateIt: password change", "Hello " + response.getNick()+ "!\n" +
//                "You have successfully changed your password!\n\nBest regards,\nRateIt team!");
        return new ResponseEntity<>(mapper.mapToUserDTOWithCollections(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> addCdToUser(long userId, long cdId) {
        User user = repository.findOneById(userId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        CD cd = cdRepository.findOneById(cdId);
        if(cd == null) {
            throw new NotFoundException("Album not found");
        }
        user.addCd(cd);
        User response = repository.save(user);
        return new ResponseEntity<>(mapper.mapToUserDTOWithCollections(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Iterable<CdDTO>> getUsersCds(long id) {
        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return new ResponseEntity<>(cdMapper.mapToCdDTOIterable(user.getUserscd()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> login(LoginDTO loginDTO) {
        User user = repository.findOneByNick(loginDTO.getUsername());
        if(user == null){
            throw new AuthenticationException("Username not correct");
        }
        if(!bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        user.setActive(true);
        User response = repository.save(user);
        return new ResponseEntity<>(mapper.mapToUserDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> findByEmail(String email) {

        User user =  repository.findOneByEmail(email);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return new ResponseEntity<>(mapper.mapToUserDTO(user), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDTO> findByNick(String nick) {

        User user =  repository.findOneByNick(nick);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        return new ResponseEntity<>(mapper.mapToUserDTO(user), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Void> deleteUser(long id) {
        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("user not found");
        }
        repository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CommentsDTO> commentAlbum(CommentAlbumDTO comment, long id) {
        User user = repository.findOneById(comment.getUserId());
        CD cd = cdRepository.findOneById(id);
        if(user == null){
            throw new NotFoundException("User not found");
        }
        if(cd == null) {
            throw new NotFoundException("Album not found");
        }
        if(comment.getContent() == null){
            throw new NotFoundException("Content cannot be null");
        }
        Comments comments = new Comments();
        comments.setCd(cd);
        comments.setUser(user);
        comments.setContent(comment.getContent());
        Comments response = commentsRepository.save(comments);

        CommentsDTO commentsDTO= new CommentsDTO();
        commentsDTO.setContent(response.getContent());
        commentsDTO.setUser(mapper.mapToUserDTO(response.getUser()));
        commentsDTO.setCd(cdMapper.mapToCdDTO(response.getCd()));

        return new ResponseEntity<>(commentsDTO, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<UserDTO> setAsAdmin(long id) {
        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        user.setRole("ADMIN");
        User response = repository.save(user);
        return new ResponseEntity<>(mapper.mapToUserDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logout(long id) {
        User user = repository.findOneById(id);
        if(user == null){
            throw new NotFoundException("User not found0");
        }
        if(!user.isActive()){
            throw new AuthenticationException("User already logged out");
        }
        user.setActive(false);
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<UserDTO> uploadAvatar(MultipartFile file, long id){
        StringBuilder stb;
        try{
            URL url;
            url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedImage image = null;
            image = ImageIO.read(file.getInputStream());
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArray);
            byte[] byteImage = byteArray.toByteArray();
            String dataImage = Base64.encode(byteImage);
            String data = URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(dataImage, "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID " + "8e65f97cadbba0f");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            conn.connect();
            stb = new StringBuilder();
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                stb.append(line).append("\n");
            }
            wr.close();
            rd.close();

        } catch (IOException e){
            throw new NotFoundException("File not found");
        }
        User user = repository.findOneById(id);
        user.setPhotoURL(getImageUrl(stb.toString()));
        repository.save(user);

        return new ResponseEntity<>(mapper.mapToUserDTOWithCollections(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommentsDTO> editComment(CommentAlbumDTO comment, long id) {
        Comments comments = commentsRepository.findOneById(id);
        if(comments == null){
            throw new NotFoundException("Comment not found");
        }
        if(comment.getUserId() != comments.getId()){
            throw new AuthenticationException("You can not edit");
        }
        comments.setContent(comment.getContent());
        Comments response = commentsRepository.save(comments);

        CommentsDTO commentsDTO= new CommentsDTO();
        commentsDTO.setContent(response.getContent());
        commentsDTO.setUser(mapper.mapToUserDTO(response.getUser()));
        commentsDTO.setCd(cdMapper.mapToCdDTO(response.getCd()));

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteComment(long commentId, long userId) {
        Comments comments = commentsRepository.findOneById(commentId);
        if(comments == null){
            throw new NotFoundException("Comment not found");
        }
        if(userId != comments.getId() || !repository.findOneById(userId).getRole().equals("ADMIN")){
            throw new AuthenticationException("You can not edit");
        }
        commentsRepository.deleteById(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<InputStreamResource> generateRaport(int days) {
        long day = 86400000;
        Iterable<UserDTO> iterable = mapper.mapToUserDTOIterable(repository.findByRegistrationDateGreaterThanOrderByRegistrationDateDesc(System.currentTimeMillis() - days*day));
        List<UserDTO> list = new ArrayList<>();
        iterable.forEach(list::add);

        ByteArrayInputStream inputStream = Raport.pdfDocument(list);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=RateIt_raport.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(new InputStreamResource(inputStream), headers, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<List<RateDTO>> getRatedAlbums(long userId) {
        Iterable<Rate> rates = rateRepository.findAllByUserId(userId);
        List<RateDTO> rated = new ArrayList<>();
        rates.forEach(rate -> {
            RateDTO rateDTO = new RateDTO();
            rateDTO.setAlbum(cdMapper.mapToCdDTO(cdRepository.findOneById(rate.getCd())));
            rateDTO.setNote(rate.getRating());
            rated.add(rateDTO);
        });
        return new ResponseEntity<>(rated, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> changeEmail(ChangeEmailDTO changeEmailDTO, long id) {
        if(!changeEmailDTO.getNewEmail().equals(changeEmailDTO.getConfirmEmail()) && changeEmailDTO.getNewEmail() != null) {
            throw new AuthenticationException("Email does not match");
        }

        User user = repository.findOneById(id);
        if(user == null) {
            throw new NotFoundException("User not found");
        }

        if(!user.getEmail().equals(changeEmailDTO.getOldEmail())) {
            throw new AuthenticationException("Old email incorrect");
        }

        if(!validateEmail(changeEmailDTO.getNewEmail())){
            throw new AuthenticationException("Not a valid email");
        }
        user.setEmail(changeEmailDTO.getNewEmail());
        User response = repository.save(user);
//        simpleEmail(response.getEmail(), "RateIt: password change", "Hello " + response.getNick()+ "!\n" +
//                "You have successfully changed your password!\n\nBest regards,\nRateIt team!");
        return new ResponseEntity<>(mapper.mapToUserDTOWithCollections(response), HttpStatus.OK);
    }

    private Boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        String emailPattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String getImageUrl(String response){
        String stringPattern = "https:\\\\/\\\\/i.imgur.com\\\\/[A-Za-z0-9]+.[a-zA-Z]+";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(response);
        if(matcher.find())
            return matcher.group().replaceAll("\\\\", "");
        else
            return null;
    }

    private void simpleEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            LOG.error("Error while sending out email..{}", e.getMessage());
            return;
        }
        LOG.info("Email to {} sent successfully", to);


    }
}
