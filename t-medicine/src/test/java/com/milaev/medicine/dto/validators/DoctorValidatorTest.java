package com.milaev.medicine.dto.validators;

import com.milaev.medicine.config.TestConfig;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class DoctorValidatorTest {

    @Autowired
    private DoctorValidator doctorValidator;

    @Autowired
    private AccountService accountService;

    private static final DoctorDTO dto = mock(DoctorDTO.class);

    private static final String login = "doctor";
    private static final String email = "email@mail.com";
    private static final String phone = "0-123-1234567";


    @BeforeAll
    public static void setup() {
        when(dto.getLogin()).thenReturn(login);
        when(dto.getEmail()).thenReturn(email);
        when(dto.getPhone()).thenReturn(email);
        when(login.isEmpty()).thenReturn(true);
    }

    @Test
    public void validateAccountWithNewLogin() {
//        AccountDTO accDTO = new AccountDTO();
//        RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setType("DOCTOR");
//        accDTO.setRole(roleDTO);
//
//        when(accountService.getByLogin(any())).thenReturn(accDTO);
//
//        Errors errors = mock(Errors.class);
//        doctorValidator.validate(dto, errors);
//        verify(errors, never()).rejectValue(eq("login"), any(), any());
    }
}
