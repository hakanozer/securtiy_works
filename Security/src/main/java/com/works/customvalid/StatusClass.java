package com.works.customvalid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusClassValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusClass {


    String message() default "Invalid Status Class";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
