package com.doccms.adapter.repository.document;

import java.util.List;

import lombok.Builder;

@Builder
public record AttributeDocument(
    Long schemaId,
    String fieldName,
    ValueDocument value,
    List<ValueDocument> listValues
) {
}
