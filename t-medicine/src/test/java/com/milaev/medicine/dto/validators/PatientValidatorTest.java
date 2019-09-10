package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.AccountService;
import com.milaev.medicine.service.EventService;
import com.milaev.medicine.service.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientValidatorTest {

    @InjectMocks
    PatientValidator patientValidator;

    @Mock
    private AccountService accountService;
    @Mock
    private PatientService patientService;
    @Mock
    private EventService eventService;

    private PatientDTO dto;
    private DoctorDTO doc;
    private AccountDTO acc;
    private RoleDTO role;

    private Errors errors;

    private static final String value_str = "string";
    private static final String value_InsuranceId = "XXX1111111";
    private static final String value_email = "address@mail.ru";
    private static final String value_phone = "1-111-1111111";

    @Before
    public void setup() {
        acc = new AccountDTO();
        role = new RoleDTO();
        role.setType(RoleType.DOCTOR.name());
        acc.setRole(role);
        doc = new DoctorDTO();
        doc.setLogin(value_str);
        doc.setEmail(value_email);
        doc.setPhone(value_phone);
        doc.setSpecialization(value_str);
        doc.setName(value_str);
        doc.setSurname(value_str);
        doc.setPatronymic(value_str);

        dto = new PatientDTO();
        dto.setInsuranceId(value_InsuranceId);
        dto.setOldInsuranceId(value_InsuranceId);
        dto.setDiagnosis(value_str);
        dto.setName(value_str);
        dto.setSurname(value_str);
        dto.setPatronymic(value_str);
        dto.setEmail(value_email);
        dto.setPhone(value_phone);
        dto.setStatus(PatientStatus.ILL.name());
        dto.setDoctor(doc);

        errors = new BeanPropertyBindingResult(dto, "dto");

        when(eventService.isAllEventsDone(any())).thenReturn(true);
        when(accountService.isLoginUnique(any())).thenReturn(false);
        when(accountService.getByLogin(any())).thenReturn(acc);
    }

    @Test
    public void validatePatientDTO() {
        patientValidator.validate(dto, errors);
        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validatePatientDTOWithWrongInsuranceId() {
        dto.setInsuranceId(value_str);
        patientValidator.validate(dto, errors);
        Assert.assertNotNull( errors.getFieldError("insuranceId") );
    }

    @Test
    public void validatePatientDTOWithWrongStatusChange() {
        when(eventService.isAllEventsDone(any())).thenReturn(false);
        dto.setStatus(PatientStatus.HEALTHY.name());
        patientValidator.validate(dto, errors);
        Assert.assertNotNull( errors.getFieldError("status") );
    }
}
