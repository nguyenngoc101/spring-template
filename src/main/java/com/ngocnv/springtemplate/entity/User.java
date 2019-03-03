package com.ngocnv.springtemplate.entity;

import com.ngocnv.springtemplate.validate.FieldsValueMatch;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@FieldsValueMatch.List({
    @FieldsValueMatch(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Passwords do not match!"
    ),
    @FieldsValueMatch(
        field = "email",
        fieldMatch = "verifyEmail",
        message = "Email addresses do not match!"
    )
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private String email;
  private String verifyEmail;
  private String password;
  private String verifyPassword;
}
