package pe.ask.university.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.ask.university.api.dto.request.CourseRequest;
import pe.ask.university.api.dto.response.CourseResponse;
import pe.ask.university.model.course.Course;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICourseMapper {
    Course toDomain(CourseRequest courseRequest);
    CourseResponse toResponse(Course course);
}
