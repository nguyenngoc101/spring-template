package com.ngocnv.springtemplate.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends AuditModel implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  private String studentId;

  @NotBlank
  private String name;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private String birthDate;

  @Embedded
  private Address address;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "course_fk", referencedColumnName = "course_id")
  @JsonBackReference
  private Course course;

  public Student() {
  }

  public Student(@NotBlank String studentId, @NotBlank String name) {
    this.studentId = studentId;
    this.name = name;
  }

  public Student(@NotBlank String studentId, @NotBlank String name, String birthDate,
      Address address) {
    this.studentId = studentId;
    this.name = name;
    this.birthDate = birthDate;
    this.address = address;
  }

  public Student(Course course) {
    this.course = course;
  }
}
