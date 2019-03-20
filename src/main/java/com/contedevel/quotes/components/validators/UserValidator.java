package com.contedevel.quotes.components.validators;

import com.contedevel.quotes.models.database.UserRepository;
import com.contedevel.quotes.models.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "user-form.email.exists");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "user-form.password.size");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "user-form.password-confirm.diff");
        }
    }
}
