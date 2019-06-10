package com.software.rateit.registration;

import com.software.rateit.Entity.User;
import com.software.rateit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    RegistrationValidator validator;

    @GetMapping("/signup")
    public void registration(Model model) {
        model.addAttribute("userForm", new User());

    }

    @PostMapping("/signup")
    void registration(@RequestBody User userForm, BindingResult result) throws Exception{
        validator.validate(userForm, result);
        if (result.hasErrors()){
            throw new Exception();
        }
        else {

            service.registerNewUser(userForm);
        }
    }


    @PostMapping("/userProfile/changePassword")
    String changePassword(@RequestParam("password") String password, @RequestParam("oldpassword") String oldPassword) {
        User user = service.findByNick(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!service.checkIfOldPasswordMatches(user, oldPassword)){
            return "userProfile/changePassword";
        }
        service.changePassword(user,password);
        return ("redirect:http://localhost:8081/dashboard");
    }


}
