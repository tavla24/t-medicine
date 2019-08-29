package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {

    private static Logger log = LoggerFactory.getLogger(AccountValidator.class);

    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountDTO dto = (AccountDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "account.login.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "account.password.empty");

        if (dto.isOldLoginEmpty() && (!accountService.isLoginUnique(dto.getLogin())))
            errors.rejectValue("login", "account.login.unique", new String[] { dto.getLogin() }, null);
        else if (!dto.isLoginEqualsOldLogin() && (!accountService.isLoginUnique(dto.getLogin())))
            errors.rejectValue("login", "account.login.unique", new String[] { dto.getLogin() }, null);

    }
}
