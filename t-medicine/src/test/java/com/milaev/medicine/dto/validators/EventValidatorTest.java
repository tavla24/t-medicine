package com.milaev.medicine.dto.validators;

import com.milaev.medicine.config.TestConfig;
import com.milaev.medicine.dto.EventDTO;
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
public class EventValidatorTest {

    @Autowired
    EventValidator eventValidator;

    private static final EventDTO dto = mock(EventDTO.class);

    @BeforeAll
    public static void setup() {
        when(dto.getStatus()).thenReturn("CANCEL");
    }

    @Test
    public void validateAccountWithNewLogin() {
//        Errors errors = mock(Errors.class);
//        eventValidator.validate(dto, errors);
//        verify(errors, never()).rejectValue(eq("info"), any(), any());
    }
}
