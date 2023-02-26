package com.doccms.adapter.repository.document;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public record AttributeDocument(
    @Id
    @MongoId
    String nodeIdFieldId,
    String nodeId,
    String fieldId,
    String stringValue,
    Integer integerValue,
    Float floatValue,
    Double doubleValue,
    LocalDate dateValue,
    List<ListElementDocument> elements
) {
}
