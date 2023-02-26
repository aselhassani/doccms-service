package com.doccms.domain.model;

import java.util.List;

import com.doccms.domain.model.constraints.Constraint;
import com.doccms.domain.model.enums.FieldType;
import lombok.Builder;

@Builder
public record Field (
    String id,
    String name,
    FieldType type,
    String defaultValue,
    List<Constraint> constraints
) {
}
