package com.ngocnv.springtemplate.repo.impl;

import com.ngocnv.springtemplate.entity.Course;
import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.entity.dto.CourseDto;
import com.ngocnv.springtemplate.repo.custom.StudentRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  // Using DTO to map result from a sql query
  @Override
  public void countActiveStudent() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<CourseDto> query = criteriaBuilder.createQuery(CourseDto.class);
    Root<Student> root = query.from(Student.class);

    Join<Student, Course> courseJoin = root.join("course", JoinType.INNER);
    Join<Student, Student> studentJoin = root.join("parent", JoinType.INNER);

    Predicate studentIdCond = criteriaBuilder.equal(root.get("studentId"), "1");
    Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdAt"), new Date());

    Path<String> courseId = courseJoin.get("name");
    Path<String> studentId = studentJoin.get("name");

    CriteriaQuery<CourseDto> selectQuery = query
        .select(criteriaBuilder.construct(CourseDto.class,courseId, studentId))
        .where(studentIdCond, lessThanOrEqualTo);
    List<CourseDto> resultList = entityManager.createQuery(selectQuery).getResultList();
  }

  // Using List<Tuple> to map result from a sql query
  public void countActiveStudent1() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    //CriteriaQuery<StudentDto> query = criteriaBuilder.createQuery(StudentDto.class);
    CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
    Root<Student> root = query.from(Student.class);

    Join<Student, Course> courseJoin = root.join("course", JoinType.INNER);
    Join<Student, Student> studentJoin = root.join("parent", JoinType.INNER);

    Predicate studentIdCond = criteriaBuilder.equal(root.get("studentId"), "1");
    Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdAt"), new Date());

    Path<String> courseId = courseJoin.get("courseId");
    Path<String> studentId = studentJoin.get("studentId");

    CriteriaQuery<Tuple> selectQuery = query
        .multiselect(courseId, studentId)
        .where(studentIdCond, lessThanOrEqualTo);
    List<Tuple> resultList = entityManager.createQuery(selectQuery).getResultList();
  }
}
