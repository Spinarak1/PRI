package com.software.rateit.registration;

import com.software.rateit.Entity.User;
import com.software.rateit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class RegistrationValidator implements Validator {

    @Autowired
    UserService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nick", "NotEmpty");
        if(user.getNick().length() < 4 || user.getNick().length() > 32)
            errors.rejectValue("nick","Size.userForm.nick");
        if(service.findByNick(user.getNick()) != null)
            errors.rejectValue("nick","Duplicate.userForm.nick");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        if(user.getPassword().length() < 8 || user.getNick().length() > 32)
            errors.rejectValue("password", "Size.userForm.password");
        if(!user.getPassword().equals(user.getConfirmPasswd()))
            errors.rejectValue("passwordConfirm","Diff.userForm.passwordConfirm");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","NotEmpty");
        if(service.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "Duplicate.userForm.email");
        if(!service.validateEmail(user.getEmail()))
            errors.rejectValue("email", "Size.userForm.email");

    }
}
