package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class PatientValidator extends PersonValidator {

    private static Logger log = LoggerFactory.getLogger(PatientValidator.class);

    @Autowired
    private PatientServiceInterface patientService;

    @Autowired
    private AccountServiceInterface accountService;

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

        if (dto.isOldInsuranceIdEmpty() && (patientService.isProfileExist(dto.getInsuranceId())))
            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[] { dto.getInsuranceId() }, null);
        else if (!dto.isInsuranceIdEqualsOld() && (patientService.isProfileExist(dto.getInsuranceId())))
            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[] { dto.getInsuranceId() }, null);

        if (!dto.getDoctor().getLogin().isEmpty()) {
            if (accountService.isLoginUnique(dto.getDoctor().getLogin()) ||
              !accountService.getByLogin(dto.getDoctor().getLogin()).getRole().getType().equals(RoleType.DOCTOR.name()))
                errors.rejectValue("doctor.login", "doctor.login.nonDoctor", new String[]{dto.getDoctor().getLogin()}, null);
        }
    }
}
