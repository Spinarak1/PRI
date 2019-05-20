package com.software.rateit.registration;

import com.software.rateit.Entity.User;
import com.software.rateit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8081")
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    RegistrationValidator validator;

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "singup";

    }

    @PostMapping("/signup")
    String registration(@RequestBody User userForm, BindingResult result){
        validator.validate(userForm, result);
        if (result.hasErrors()){
            return "/signup";
        }
        service.registerNewUser(userForm);
        return ("redirect:http://localhost:8081/dashboard");
    }

    @GetMapping("/signin")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "signin";
    }

    @PostMapping("/user/changePassword")
    String changePassword(@RequestParam("password") String password, @RequestParam("oldpassword") String oldPassword) {
        User user = service.findByNick(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!service.checkIfOldPasswordMatches(user, oldPassword)){
            return "user/changePassword";
        }
        service.changePassword(user,password);
        return ("redirect:/dashboard");
    }


}
