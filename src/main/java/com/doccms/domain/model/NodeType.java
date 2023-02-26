package com.doccms.domain.model;

import java.util.List;

import lombok.Builder;

@Builder
public record NodeType(
    String id,
    String name,
    String description,
    List<Field> fields
) {


}
