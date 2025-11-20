package pe.ask.university.api.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import pe.ask.university.api.utils.exception.ErrorResponse;
import pe.ask.university.model.utils.exception.BaseException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Global exception handler for the reactive web application.
 * <p>
 * This class catches all exceptions thrown by the application, with a special focus on
 * instances of {@link BaseException}. It formats these exceptions into a standardized
 * {@link ErrorResponse} and writes it to the HTTP response.
 * </p>
 * <p>
 * It is registered with a high precedence ({@code @Order(-1)}) to ensure it is the first
 * handler to process exceptions.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    /**
     * Handles the given exception and writes a formatted error response to the client.
     * <p>
     * This method processes exceptions that occur during the handling of a web request.
     * If the exception is an instance of {@link BaseException}, it is transformed into a
     * structured JSON error response. Otherwise, the exception is propagated.
     * </p>
     *
     * @param exchange the current server web exchange, providing access to the request and response.
     * @param ex       the exception that was thrown during request processing.
     * @return a {@link Mono<Void>} that signals the completion of the error handling process.
     */
    @Override
    @NonNull
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
        return Mono.just(ex)
                .filter(BaseException.class::isInstance)
                .cast(BaseException.class)
                .flatMap(baseException -> buildErrorResponse(exchange, baseException))
                .switchIfEmpty(Mono.error(ex));
    }

    /**
     * Builds and writes a standardized {@link ErrorResponse} to the {@link ServerWebExchange}.
     *
     * @param exchange      the current server web exchange.
     * @param baseException the custom exception containing error details.
     * @return a {@link Mono<Void>} that completes when the response has been written.
     */
    private Mono<Void> buildErrorResponse(ServerWebExchange exchange, BaseException baseException) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(baseException.getErrorCode())
                .tittle(baseException.getTitle())
                .message(baseException.getMessage())
                .errors(baseException.getErrors())
                .status(baseException.getStatus())
                .timestamp(Optional.ofNullable(baseException.getTimestamp()).orElse(LocalDateTime.now()))
                .build();

        exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(baseException.getStatus()));
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return exchange.getResponse().writeWith(Mono.fromCallable(() -> {
            try {
                return exchange.getResponse().bufferFactory().wrap(objectMapper.writeValueAsBytes(errorResponse));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
