package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@Valid  @RequestBody Student student) {
        return studentRepository.save(student);
    }
}
