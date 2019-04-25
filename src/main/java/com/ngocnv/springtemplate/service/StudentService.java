package com.ngocnv.springtemplate.service;

import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.repo.StudentRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StudentService {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  StudentRepository studentRepository;

  @Transactional
  public Student getStudent(@PathVariable Long id) {
    return studentRepository.findById(id).orElse(null);
  }
}
