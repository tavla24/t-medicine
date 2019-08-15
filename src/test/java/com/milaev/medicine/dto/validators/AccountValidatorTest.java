package com.milaev.medicine.dto.validators;

import com.milaev.medicine.config.TestConfig;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
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

    // mocked dependencies
    @Autowired
    private AccountServiceInterface accountService;

    private static final String login = "admin";
    private static final AccountDTO acc = mock(AccountDTO.class);

    @BeforeAll
    public static void setup() {
        when(acc.getLogin()).thenReturn(login);
    }

    @Test
    public void validateAccountWithNewLogin() {
        when(accountService.getByLogin(login)).thenReturn(null);
        Errors errors = mock(Errors.class);
        accountValidator.validate(acc, errors);
        verify(errors, never()).rejectValue(eq("login"), any(), any());
    }

    @Test
    public void validateAccountWithAlreadyUsedLogin() {
        when(accountService.getByLogin(login)).thenReturn(acc);
        Errors errors = mock(Errors.class);
        accountValidator.validate(acc, errors);
        verify(errors, times(1))
                .rejectValue(eq("login"), any(), any(), any());
    }

}
