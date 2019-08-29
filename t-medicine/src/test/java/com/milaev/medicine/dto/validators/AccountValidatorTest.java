package com.milaev.medicine.dto.validators;

import com.milaev.medicine.config.TestConfig;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class AccountValidatorTest {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountService accountService;

    private static final String login = "admin";
    private static final AccountDTO acc = mock(AccountDTO.class);

    @BeforeAll
    public static void setup() {
        when(acc.getOldLogin().isEmpty()).thenReturn(false);

        when(acc.isOldLoginEmpty()).thenReturn(false);
        when(acc.isLoginEqualsOldLogin()).thenReturn(false);
        when(acc.getLogin()).thenReturn(login);
        when(acc.getOldLogin()).thenReturn(login);
    }

    @Test
    public void validateAccountWithNewLogin() {
        when(accountService.isLoginUnique(login)).thenReturn(false);
        Errors errors = mock(Errors.class);
        accountValidator.validate(acc, errors);
        verify(errors, never()).rejectValue(eq("login"), any(), any());
    }

    @Test
    public void validateAccountWithAlreadyUsedLogin() {
        when(accountService.isLoginUnique(login)).thenReturn(true);
        Errors errors = mock(Errors.class);
        accountValidator.validate(acc, errors);
        verify(errors, times(2))
                .rejectValue(eq("login"), any(), any(), any());
    }

}
