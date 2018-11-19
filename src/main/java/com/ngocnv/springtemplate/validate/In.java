package com.ngocnv.springtemplate.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { InValidator.class })
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface In {
  String message() default "value must be in valid values";
  String[] list() default {};
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
