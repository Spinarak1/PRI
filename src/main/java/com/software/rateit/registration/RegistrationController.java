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
        else {
            service.registerNewUser(userForm);
            return ("redirect:http://localhost:8081/dashboard");
        }
    }

    @GetMapping("/signin")
    public boolean login(Model model, String error, String logout, User user) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
            System.out.println("nie udalo sie");
            user.setActive(false);
            return user.isActive();
        }
        else {
            model.addAttribute("message", "You have been logged out successfully.");
            System.out.println("udalo sie");
            user.setActive(true);
            return user.isActive();
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
