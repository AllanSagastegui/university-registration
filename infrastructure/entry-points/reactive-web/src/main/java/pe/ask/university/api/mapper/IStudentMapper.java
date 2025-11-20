package pe.ask.university.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.ask.university.api.dto.request.StudentRequest;
import pe.ask.university.api.dto.response.StudentResponse;
import pe.ask.university.model.student.Student;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IStudentMapper {
    Student toDomain(StudentRequest studentRequest);
    StudentResponse toResponse(Student student);
}
