package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.Course;
import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.entity.dto.StudentDto;
import com.ngocnv.springtemplate.repo.CourseRepository;
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

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/students")
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@Valid  @RequestBody StudentDto studentDto) {
        Student student = studentDto.toStudent();
        Course course = courseRepository.findByCourseId(studentDto.getCourseId()).orElse(null);
        student.setCourse(course);
        return studentRepository.save(student);
    }
}
