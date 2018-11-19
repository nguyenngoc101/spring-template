package com.ngocnv.springtemplate.entity.dto;

public class CourseDto {
  private String courseId;
  private String studentId;

  public CourseDto(String courseId, String studentId) {
    this.courseId = courseId;
    this.studentId = studentId;
  }
}
