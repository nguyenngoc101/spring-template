package com.ngocnv.springtemplate.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

  private String field;
  private String fieldMatch;

  @Override
  public void initialize(FieldsValueMatch constraint) {
    this.field = constraint.field();
    this.fieldMatch = constraint.fieldMatch();
  }

  @Override
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
