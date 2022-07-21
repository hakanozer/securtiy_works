package com.works.customvalid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusClassValidator implements ConstraintValidator<StatusClass, String> {

    String[] arr = { "Class -1", "Class -2", "Class -3"  };

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.asList( arr ).contains( value );
    }
}
