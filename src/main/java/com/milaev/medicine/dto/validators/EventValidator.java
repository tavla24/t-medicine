package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.model.enums.EventStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EventValidator implements Validator {

    private static Logger log = LoggerFactory.getLogger(EventValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        //return EventDTO.class.equals(clazz);
        return EventDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventDTO dto = (EventDTO) target;

        //if ((dto.getStatus() != null) && !dto.getStatus().isEmpty())
            if(dto.getStatus().equals(EventStatus.CANCEL.name()))
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "info", "event.info.empty");
    }

}
