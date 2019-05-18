package com.software.rateit.registration;


import com.software.rateit.Entity.User;
import com.software.rateit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    RegistrationValidator validator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    String createNewAccount(@RequestBody User userForm, BindingResult result){
        validator.validate(userForm, result);
        if (result.hasErrors()){
            return "/registration";
        }
        service.registerNewUser(userForm);
        return ("redirect:/api/users");
    }


}
