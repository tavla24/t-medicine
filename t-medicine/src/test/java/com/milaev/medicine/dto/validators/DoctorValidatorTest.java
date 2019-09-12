package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
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
public class DoctorValidatorTest {

    @InjectMocks
    private DoctorValidator doctorValidator;

    @Mock
    private AccountService accountService;

    private DoctorDTO doc;
    private AccountDTO acc;
    private RoleDTO role;

    private Errors errors;

    private static final String value_str = "string";
    private static final String value_email = "address@mail.ru";
    private static final String value_phone = "1-111-1111111";

    @Before
    public void setup() {
        acc = new AccountDTO();
        role = new RoleDTO();
        role.setType(RoleType.NURSE.name());
        acc.setRole(role);

        doc = new DoctorDTO();
        doc.setLogin(value_str);
        doc.setEmail(value_email);
        doc.setPhone(value_phone);
        doc.setSpecialization(value_str);
        doc.setName(value_str);
        doc.setSurname(value_str);
        doc.setPatronymic(value_str);

        errors = new BeanPropertyBindingResult(doc, "doc");
    }

    @Test
    public void validateDoctorDTO() {
        when(accountService.isLoginUnique(any())).thenReturn(true);
        when(accountService.getByLogin(any())).thenReturn(acc);

        doctorValidator.validate(doc, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validateDoctorDTOWithWrongEmailPattern() {
        when(accountService.isLoginUnique(any())).thenReturn(true);
        when(accountService.getByLogin(any())).thenReturn(acc);

        doc.setEmail(value_str);
        doctorValidator.validate(doc, errors);

        Assert.assertNotNull( errors.getFieldError("email") );
    }
}
