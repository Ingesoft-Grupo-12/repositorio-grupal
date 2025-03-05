package com.union.unionbackend.mapper;

import com.union.unionbackend.dtos.CourseDto;
import com.union.unionbackend.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface CourseMapper {
  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

  CourseDto courseToCourseDto(Course course);
  List<CourseDto> coursesToCourseDtos(List<Course> courses);
}
