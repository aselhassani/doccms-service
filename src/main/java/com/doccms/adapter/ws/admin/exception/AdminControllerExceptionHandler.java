package com.doccms.adapter.ws.admin.exception;

import com.doccms.adapter.ws.admin.dto.ErrorDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@ControllerAdvice(basePackages = {"com.doccms.adapter.ws.admin"})
@Log4j2
public class AdminControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static ResponseEntity<Object> buildResponseEntity(ErrorCode errorCode, ErrorDTO errorDto) {
        log.info(errorDto);
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        return buildErrorResponse(buildErrorIdAndLog(ex, request), ErrorCode.MISSING_REQUEST_PARAMETER,
                List.of(ex.getParameterName()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        var errorId = buildErrorIdAndLog(ex, request);
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().toUpperCase().concat(": ")
                        .concat(Optional.ofNullable(err.getDefaultMessage()).orElse("")))
                .toList();
        return buildErrorResponse(errorId, ErrorCode.VALIDATION_FAILURE, errors);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        return buildErrorResponse(buildErrorIdAndLog(ex, request), ErrorCode.TYPE_MISMATCH, Collections.emptyList());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        return buildErrorResponse(buildErrorIdAndLog(ex, request), ErrorCode.MESSAGE_NOT_READABLE,
                Collections.emptyList());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
                                                                     WebRequest request) {
        var errorId = buildErrorIdAndLog(ex, request);
        var errors = ex.getConstraintViolations().stream()
                .map(violation -> extractFieldName(violation.getPropertyPath()).concat(": ")
                        .concat(violation.getMessage()))
                .toList();

        return buildErrorResponse(errorId, ErrorCode.VALIDATION_FAILURE, errors);
    }

    @ExceptionHandler({SchemaNameExistsException.class})
    public ResponseEntity<Object> handleSchemaNameExistsException(SchemaNameExistsException ex, WebRequest request) {
        var errorId = buildErrorIdAndLog(ex, request);
        var errors = List.of(MessageFormat.format("Schema name in use was [{0}]", ex.getSchemaName()));
        return buildErrorResponse(errorId, ErrorCode.SCHEMA_NAME_ALREADY_EXISTS, errors);
    }

    private String buildErrorIdAndLog(Exception ex, WebRequest request) {
        String errorId = UUID.randomUUID().toString();
        log.warn(buildLogMessage(ex, request, errorId));
        return errorId;
    }

    private String handleInternalServerError(Exception ex, WebRequest request) {
        String errorId = UUID.randomUUID().toString();
        log.error(buildLogMessage(ex, request, errorId));
        return errorId;
    }

    private ParameterizedMessage buildLogMessage(Exception ex, WebRequest request, String errorId) {
        return new ParameterizedMessage("errorId={} , uri={} , headers={},  params={},",
                errorId, request.getDescription(false),
                getReqHeadersOrParameters(request, WebRequest::getHeaderNames,
                        WebRequest::getHeaderValues),
                getReqHeadersOrParameters(request, WebRequest::getParameterNames,
                        WebRequest::getParameterValues),
                ex);
    }

    private ResponseEntity<Object> buildErrorResponse(String errorId, ErrorCode errorCode, List<String> errors) {
        var errorDto = ErrorDTO.builder()
                .id(errorId)
                .code(errorCode.getCode())
                .message(errorCode.getMessage().concat(buildDetails(errors)))
                .build();
        return buildResponseEntity(errorCode, errorDto);
    }

    private Map<String, String[]> getReqHeadersOrParameters(WebRequest request,
                                                            Function<WebRequest, Iterator<String>> getNamesFunction,
                                                            BiFunction<WebRequest, String, String[]> getValueFunction) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(getNamesFunction.apply(request),
                        Spliterator.ORDERED), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(name -> name, name -> getValueFunction.apply(request, name)));
    }

    private String buildDetails(List<String> errors) {
        return Optional.ofNullable(errors)
                .filter(list -> !list.isEmpty())
                .map(e -> ", Details: " + String.join(". ", e))
                .orElse("");
    }

    private String extractFieldName(Path path) {
        Path.Node node = null;
        for (Path.Node value : path) {
            node = value;
        }
        return node != null ? node.getName().toUpperCase() : "";
    }


}
