package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountValidatorTest {

    @InjectMocks
    private AccountValidator accountValidator;

    @Mock
    private AccountService accountService;

    private AccountDTO acc;
    private RoleDTO role;

    private Errors errors;

    private static final String value_str = "string";

    @Before
    public void setup() {
        acc = new AccountDTO();
        acc.setLogin(value_str);
        acc.setPassword(value_str);
        acc.setOldLogin("");
        acc.setOldPassword(value_str);
        role = new RoleDTO();
        role.setType(RoleType.ROOT.name());
        acc.setRole(role);

        errors = new BeanPropertyBindingResult(acc, "acc");
    }

    @Test
    public void validateAccountWithNewLogin() {
        when(accountService.isLoginUnique(any())).thenReturn(true);
        accountValidator.validate(acc, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validateAccountWithAlreadyUsedLogin() {
        when(accountService.isLoginUnique(any())).thenReturn(false);
        accountValidator.validate(acc, errors);

        Assert.assertNotNull( errors.getFieldError("login") );
    }

}
