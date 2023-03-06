package com.doccms.adapter.repository.document;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ValueDocument(
    String stringValue,
    Integer integerValue,
    Float floatValue,
    Double doubleValue,
    LocalDate dateValue) {


}
