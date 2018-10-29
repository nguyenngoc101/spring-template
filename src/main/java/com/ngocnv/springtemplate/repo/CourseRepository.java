package com.ngocnv.springtemplate.repo;

import com.ngocnv.springtemplate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    public Optional<Course> findByCourseId(String courseId);
}
