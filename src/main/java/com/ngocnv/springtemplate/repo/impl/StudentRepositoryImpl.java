package com.ngocnv.springtemplate.repo.impl;

import com.ngocnv.springtemplate.entity.Course;
import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.entity.dto.CourseDto;
import com.ngocnv.springtemplate.repo.custom.StudentRepositoryCustom;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    Predicate lessThanOrEqualTo = criteriaBuilder
        .lessThanOrEqualTo(root.<Date>get("createdAt"), new Date());

    Path<String> courseId = courseJoin.get("name");
    Path<String> studentId = studentJoin.get("name");

    CriteriaQuery<CourseDto> selectQuery = query
        .select(criteriaBuilder.construct(CourseDto.class, courseId, studentId))
        .where(studentIdCond, lessThanOrEqualTo);
    selectQuery.getOrderList();
  }
}
