package com.ngocnv.springtemplate.service;

import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public Student getStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElse(null);
//        student.setName("ngocnv1");
//        entityManager.detach(student);
//        entityManager.merge(student);
        studentRepository.countActiveStudent();
        return student;
    }
}
