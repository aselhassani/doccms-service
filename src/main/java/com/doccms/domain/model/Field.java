package com.doccms.domain.model;

import com.doccms.domain.model.contraint.Constraints;
import com.doccms.domain.model.enums.FieldMode;
import com.doccms.domain.model.enums.FieldType;
import lombok.Builder;

@Builder
public record Field(
    String name,
    FieldType type,
    FieldMode mode,
    Object defaultValue,
    Constraints constraints
) {

}
