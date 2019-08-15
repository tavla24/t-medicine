package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class AccountValidator implements Validator {

    private static Logger log = LoggerFactory.getLogger(AccountValidator.class);

    @Autowired
    private AccountServiceInterface accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDTO dto = (AccountDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "account.empty.login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "account.empty.password");

        AccountDTO acc = accountService.getByLogin(dto.getLogin());
        if (acc != null)
            errors.rejectValue("login", "account.unique.login", new String[] { dto.getLogin() }, null);

    }
}
