package pe.ask.university.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pe.ask.university.api.dto.request.PeriodRequest;
import pe.ask.university.api.dto.response.PeriodResponse;
import pe.ask.university.model.period.Period;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IPeriodMapper {
    Period toDomain(PeriodRequest periodRequest);
    PeriodResponse toResponse(Period period);
}
