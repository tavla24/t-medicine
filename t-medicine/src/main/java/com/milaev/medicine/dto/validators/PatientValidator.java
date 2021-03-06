package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.AccountService;
import com.milaev.medicine.service.EventService;
import com.milaev.medicine.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PatientValidator extends PersonValidator {

    private static Logger log = LoggerFactory.getLogger(PatientValidator.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AccountService accountService;

    private static final String INSID_PATTERN = "[A-Z0-9]{10}";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> clazz) {
        return PatientDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        PatientDTO dto = (PatientDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "insuranceId", "patient.insuranceId.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diagnosis", "patient.diagnosis.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "patient.status.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doctor.login", "patient.doctor.login.empty");

        if (!dto.getInsuranceId().isEmpty()) {
            pattern = Pattern.compile(INSID_PATTERN, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(dto.getInsuranceId());
            if (!matcher.matches())
                errors.rejectValue("insuranceId", "patient.insuranceId.invalid");
        }

        if ((dto.getStatus() != null) && (!dto.getStatus().isEmpty()))
            if (dto.getStatus().equals(PatientStatus.HEALTHY.name()))
                if (!eventService.isAllEventsDone(dto.getInsuranceId()))
                    errors.rejectValue("status", "patient.status.ill");


        if (dto.isOldInsuranceIdEmpty() && (patientService.isProfileExist(dto.getInsuranceId())))
            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[]{dto.getInsuranceId()}, null);
        else if (!dto.isInsuranceIdEqualsOld() && (patientService.isProfileExist(dto.getInsuranceId())))
            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[]{dto.getInsuranceId()}, null);

        if (!dto.getDoctor().getLogin().isEmpty()) {
            if (accountService.isLoginUnique(dto.getDoctor().getLogin()) ||
                    !accountService.getByLogin(dto.getDoctor().getLogin()).getRole().getType().equals(RoleType.DOCTOR.name()))
                errors.rejectValue("doctor.login", "doctor.login.nonDoctor", new String[]{dto.getDoctor().getLogin()}, null);
        }
    }
}
