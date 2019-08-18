package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonValidator implements Validator {
    private static Logger log = LoggerFactory.getLogger(PersonValidator.class);

    private static final String PHONE_PATTERN = "\\d{1}-\\d{3}-\\d{7}";
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO dto = (PersonDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "person.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "person.surname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "person.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "person.phone.empty");

        if (!dto.getEmail().isEmpty()){
            pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(dto.getEmail());
            if (!matcher.matches())
                errors.rejectValue("email","person.email.invalid");
        }

        if (!dto.getPhone().isEmpty()) {
            pattern = Pattern.compile(PHONE_PATTERN);
            matcher = pattern.matcher(dto.getPhone());
            if (!matcher.matches())
                errors.rejectValue("phone", "person.phone.invalid");
        }

//        if (dto.isOldEmailEmpty() && (patientService.isProfileExist(dto.getInsuranceId())))
//            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[] { dto.getInsuranceId() }, null);
//        else if (!dto.isInsuranceIdEqualsOld() && (patientService.isProfileExist(dto.getInsuranceId())))
//            errors.rejectValue("insuranceId", "patient.insuranceId.unique", new String[] { dto.getInsuranceId() }, null);

    }
}

