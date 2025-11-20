package pe.ask.university.api.handler.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.response.StudentResponse;
import pe.ask.university.api.mapper.IStudentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.student.IGetAllStudentsUseCase;
import reactor.core.publisher.Mono;

/**
 * Handles the HTTP GET request for retrieving all students with pagination.
 * <p>
 * This handler processes requests to fetch a paginated list of students. It extracts
 * pagination parameters from the request, invokes the appropriate use case, maps the
 * results to response DTOs, and constructs the server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class GetAllStudentsHandler {

    private final IGetAllStudentsUseCase useCase;
    private final IStudentMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve all students.
     * <p>
     * This method orchestrates the flow for fetching students:
     * <ol>
     *     <li>Extracts pagination parameters ('page' and 'size') from the request's query parameters.</li>
     *     <li>Invokes the {@link IGetAllStudentsUseCase} to get a paginated list of students.</li>
     *     <li>Maps the resulting {@link Pageable} of domain models to a {@link Pageable} of {@link StudentResponse} DTOs.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the paginated response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request, which may contain pagination query parameters.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETAllStudentsUseCase(ServerRequest serverRequest) {
        int page = Integer.parseInt(serverRequest.queryParam("page").orElse("0"));
        int size = Integer.parseInt(serverRequest.queryParam("size").orElse("10"));
        return useCase.getAllStudents(page, size)
                .map(pageable -> Pageable.<StudentResponse>builder()
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
