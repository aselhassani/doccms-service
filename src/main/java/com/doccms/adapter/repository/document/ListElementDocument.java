package com.doccms.adapter.repository.document;

import java.util.Date;

import lombok.Builder;

@Builder
public record ListElementDocument(
    String stringValue,
    Integer integerValue,
    Float floatValue,
    Long longValue,
    Double doubleValue,
    Date dateValue
) {
}
