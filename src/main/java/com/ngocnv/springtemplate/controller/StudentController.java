package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.Course;
import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.entity.dto.StudentDto;
import com.ngocnv.springtemplate.repo.CourseRepository;
import com.ngocnv.springtemplate.repo.StudentRepository;
import com.ngocnv.springtemplate.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        studentRepository.countActiveStudent();
        return studentService.getStudent(id);
    }

    @PostMapping
    @Transactional
    public Student createStudent(@Valid  @RequestBody StudentDto studentDto) {
        Student student = studentDto.toStudent();
        Course course = courseRepository.findByCourseId(studentDto.getCourseId()).orElse(null);
        student.setCourse(course);
        return studentRepository.save(student);
    }

    private Student buildAStudent() {
        Student student = new Student();
        student.setName("Ngocnv");
        student.setStudentId("1");
        student.setBirthDate("01-10-1988");
        return student;
    }
}
