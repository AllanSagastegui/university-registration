package pe.ask.university.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.ask.university.api.dto.request.EnrollmentRequest;
import pe.ask.university.api.dto.response.EnrollmentResponse;
import pe.ask.university.model.enrollment.Enrollment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IEnrollmentMapper {
    Enrollment toDomain(EnrollmentRequest enrollmentRequest);
    EnrollmentResponse toResponse(Enrollment enrollment);
}
