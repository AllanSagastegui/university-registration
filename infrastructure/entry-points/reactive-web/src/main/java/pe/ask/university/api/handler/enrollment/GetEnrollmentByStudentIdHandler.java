package pe.ask.university.api.handler.enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.response.EnrollmentResponse;
import pe.ask.university.api.mapper.IEnrollmentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.enrollment.IGetEnrollmentByStudentIdUseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handles the HTTP GET request for retrieving enrollments by a student's ID.
 * <p>
 * This handler is responsible for processing requests to fetch all enrollments
 * for a specific student. It extracts the student ID from the request path,
 * invokes the appropriate use case, maps the results to response DTOs, and
 * constructs the server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class GetEnrollmentByStudentIdHandler {

    private final IGetEnrollmentByStudentIdUseCase useCase;
    private final IEnrollmentMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve enrollments by a student's ID.
     * <p>
     * This method orchestrates the flow for fetching enrollments for a student:
     * <ol>
     *     <li>Extracts the student ID from the request's path variable.</li>
     *     <li>Invokes the {@link IGetEnrollmentByStudentIdUseCase} to get the enrollments.</li>
     *     <li>Maps the resulting {@link Pageable} of domain models to a {@link Pageable} of {@link EnrollmentResponse} DTOs.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the paginated response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the student ID in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETEnrollmentByStudentIdUseCase(ServerRequest serverRequest) {
        UUID studentId = UUID.fromString(serverRequest.pathVariable("studentId"));
        return useCase.getEnrollmentByStudentId(studentId)
                .map(pageable -> Pageable.<EnrollmentResponse>builder()
                        .page(pageable.getPage())
                        .size(pageable.getSize())
                        .totalElements(pageable.getTotalElements())
                        .totalPages(pageable.getTotalPages())
                        .content(
                                pageable.getContent().stream()
                                        .map(mapper::toResponse)
                                        .toList()
                        )
                        .build()
                )
                .flatMap(response ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)
                )
                .onErrorResume(ex ->
                        Mono.error(ex instanceof BaseException ? ex : new UnexpectedException(ex))
                );
    }
}
