package com.ngocnv.springtemplate.repo;

import com.ngocnv.springtemplate.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
