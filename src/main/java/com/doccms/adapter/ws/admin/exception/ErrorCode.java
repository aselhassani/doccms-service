package com.doccms.adapter.ws.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400-00", "The request could not be processed due to malformed syntax"),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "400-01",
                              "The request could not be processed due to missing request parameter"),
    INVALID_NUMBER_RANGE(HttpStatus.BAD_REQUEST, "400-02", "Invalid number range"),
    INVALID_DATE_RANGE(HttpStatus.BAD_REQUEST, "400-03", "Invalid date range"),
    IDENTIFIERS_MISMATCH(HttpStatus.BAD_REQUEST, "400-04",
                         "Identifiers mismatch. Request body doesn't contain the expected identifier"),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "400-05", "The request could not be processed due to type mismatch"),
    VALIDATION_FAILURE(HttpStatus.BAD_REQUEST, "400-06",
                       "The request could not be processed due to invalid request parameters"),
    PARSE_ERROR(HttpStatus.BAD_REQUEST, "400-07", "The request could not be processed due to request parsing error"),
    MESSAGE_NOT_READABLE(HttpStatus.BAD_REQUEST, "400-08",
                         "The request could not be processed due to request parsing error"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500-00",
                          "Internal Server Error.An unexpected condition was encountered"),
    SCHEMA_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "409-00",
                               "Schema with the same name already exists");

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;
}

