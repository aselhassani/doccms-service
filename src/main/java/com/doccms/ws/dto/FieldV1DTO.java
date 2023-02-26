package com.doccms.ws.dto;

import java.util.List;

import com.doccms.domain.model.constraints.Constraint;
import com.doccms.domain.model.enums.FieldType;

public record FieldV1DTO(
    String id,
    String name,
    FieldType type,
    String defaultValue,
    List<Constraint> constraints
) {
}
