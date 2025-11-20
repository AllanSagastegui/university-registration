package pe.ask.university.api.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.request.CourseRequest;
import pe.ask.university.api.mapper.ICourseMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.api.utils.validator.CustomValidator;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.course.ISaveCourseUseCase;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Handles the HTTP POST request for creating a new course.
 * <p>
 * This handler is responsible for processing incoming requests to save a course.
 * It validates the request body, maps it to a domain model, invokes the corresponding
 * use case, and returns the appropriate server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class SaveCourseHandler {

    private final ISaveCourseUseCase useCase;
    private final ICourseMapper mapper;
    private final CustomValidator validator;

    /**
     * Listens for and processes the POST request to save a new course.
     * <p>
     * This method orchestrates the flow for creating a course:
     * <ol>
     *     <li>Extracts the {@link CourseRequest} from the request body.</li>
     *     <li>Validates the request object using {@link CustomValidator}.</li>
     *     <li>Maps the DTO to the domain model using {@link ICourseMapper}.</li>
     *     <li>Invokes the {@link ISaveCourseUseCase} to persist the course.</li>
     *     <li>Maps the resulting domain model back to a response DTO.</li>
     *     <li>Builds a {@link ServerResponse} with status 201 (Created) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the course data.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenPOSTSaveCourseUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CourseRequest.class)
                .flatMap(validator::validate)
                .map(mapper::toDomain)
                .flatMap(useCase::saveCourse)
                .map(mapper::toResponse)
                .flatMap(response ->
                        ServerResponse
                                .created(URI.create(""))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)
                )
                .onErrorResume(ex ->
                        Mono.error(ex instanceof BaseException ? ex : new UnexpectedException(ex))
                );
    }

}
