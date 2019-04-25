package com.ngocnv.springtemplate.entity.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocnv.springtemplate.entity.Address;
import com.ngocnv.springtemplate.entity.Student;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDto implements Serializable {


  @NotBlank
  private String studentId;

  @NotBlank
  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private String birthDate;

  private Address address;

  private String courseId;

  public Student toStudent() {
    return new Student(studentId, name, birthDate, address);
  }
}
