package com.ngocnv.springtemplate.repo;

import com.ngocnv.springtemplate.entity.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

  public Optional<Course> findByCourseId(String courseId);
}
