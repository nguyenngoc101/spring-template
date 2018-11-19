package com.ngocnv.springtemplate.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class InValidator implements ConstraintValidator<In, String> {

  String[] validValues;
  public void initialize(In constraint) {
    this.validValues = constraint.list();
  }

  public boolean isValid(String input, ConstraintValidatorContext context) {
    return Arrays.asList(validValues).contains(input);
  }
}
