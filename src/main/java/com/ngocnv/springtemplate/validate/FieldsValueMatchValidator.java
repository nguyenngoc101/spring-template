package com.ngocnv.springtemplate.validate;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {
  private String field;
  private String fieldMatch;

  public void initialize(FieldsValueMatch constraint) {
    this.field = constraint.field();
    this.fieldMatch = constraint.fieldMatch();
  }

  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    Object fieldValue = new BeanWrapperImpl(obj).getPropertyValue(field);
    Object fieldMatchValue = new BeanWrapperImpl(obj).getPropertyValue(fieldMatch);
    if (fieldValue != null) {
      return fieldValue.equals(fieldMatchValue);
    } else {
      return fieldMatchValue == null;
    }
  }
}
