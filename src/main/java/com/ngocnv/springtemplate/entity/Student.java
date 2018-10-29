package com.ngocnv.springtemplate.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Student implements Serializable {

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

    public Student(@NotBlank String studentId, @NotBlank String name, String birthDate, Address address) {
        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
    }
}
