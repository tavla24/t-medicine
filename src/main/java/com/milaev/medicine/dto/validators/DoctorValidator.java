package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class DoctorValidator extends PersonValidator {

    private static Logger log = LoggerFactory.getLogger(DoctorValidator.class);

    @Autowired
    private AccountServiceInterface accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DoctorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        DoctorDTO dto = (DoctorDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "doctor.login.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specialization", "doctor.specialization.empty");

        if (!dto.getLogin().isEmpty())
            if (!accountService.getByLogin(dto.getLogin()).getRole().getType().equals(RoleType.DOCTOR.name()))
                errors.rejectValue("login", "doctor.login.nonDoctor", new String[]{dto.getLogin()}, null);
    }
}
