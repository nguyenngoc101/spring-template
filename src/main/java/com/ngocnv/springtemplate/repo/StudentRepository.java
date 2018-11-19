package com.ngocnv.springtemplate.repo;

import com.ngocnv.springtemplate.entity.Student;
import com.ngocnv.springtemplate.repo.custom.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

  List<Student> findByStudentIdAndName(String studentId, String name);
}
