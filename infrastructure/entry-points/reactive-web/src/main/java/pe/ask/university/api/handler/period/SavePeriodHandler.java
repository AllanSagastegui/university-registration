package pe.ask.university.api.handler.period;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.request.PeriodRequest;
import pe.ask.university.api.mapper.IPeriodMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.api.utils.validator.CustomValidator;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.period.ISavePeriodUseCase;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Handles the HTTP POST request for creating a new period.
 * <p>
 * This handler is responsible for processing incoming requests to save a period.
 * It validates the request body, maps it to a domain model, invokes the corresponding
 * use case, and returns the appropriate server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class SavePeriodHandler {

    private final ISavePeriodUseCase useCase;
    private final IPeriodMapper mapper;
    private final CustomValidator validator;

    /**
     * Listens for and processes the POST request to save a new period.
     * <p>
     * This method orchestrates the flow for creating a period:
     * <ol>
     *     <li>Extracts the {@link PeriodRequest} from the request body.</li>
     *     <li>Validates the request object using {@link CustomValidator}.</li>
     *     <li>Maps the DTO to the domain model using {@link IPeriodMapper}.</li>
     *     <li>Invokes the {@link ISavePeriodUseCase} to persist the period.</li>
     *     <li>Maps the resulting domain model back to a response DTO.</li>
     *     <li>Builds a {@link ServerResponse} with status 201 (Created) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the period data.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenPOSTSavePeriodUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(PeriodRequest.class)
                .flatMap(validator::validate)
                .map(mapper::toDomain)
                .flatMap(useCase::savePeriod)
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
