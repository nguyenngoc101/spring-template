package com.ngocnv.springtemplate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
public class Course extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "course_id")
    private String courseId;

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, targetEntity = Student.class)
    @JsonManagedReference
    private List<Student> students;

    public Course() {
    }

    public Course(@NotBlank String courseId, @NotBlank String name) {
        this.courseId = courseId;
        this.name = name;
    }
}
