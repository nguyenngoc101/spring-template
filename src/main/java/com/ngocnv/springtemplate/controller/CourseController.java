package com.ngocnv.springtemplate.controller;

import com.ngocnv.springtemplate.entity.Course;
import com.ngocnv.springtemplate.repo.CourseRepository;
import com.ngocnv.springtemplate.repo.StudentRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CourseController {

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  StudentRepository studentRepository;

  @GetMapping("/courses")
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @PostMapping("/courses")
  public Course createCourse(@Valid @RequestBody Course course) {
    return courseRepository.save(course);
  }
}
