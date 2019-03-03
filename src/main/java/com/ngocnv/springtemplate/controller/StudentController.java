package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.entity.dto.StudentDto;
import com.ngocnv.springtemplate.repo.CourseRepository;
import com.ngocnv.springtemplate.repo.StudentRepository;
import com.ngocnv.springtemplate.service.StudentService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  @Transactional
  public Student findById(@PathVariable Long id) {
    return studentService.getStudent(id);
  }

  @PostMapping
  @Transactional
  public Student createStudent(@Valid @RequestBody StudentDto studentDto) {
    Student student = studentDto.toStudent();
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
