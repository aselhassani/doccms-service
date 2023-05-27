package com.doccms.domain.model;

import com.doccms.domain.model.constraint.Constraints;
import com.doccms.domain.model.enums.FieldMode;
import com.doccms.domain.model.enums.FieldType;
import lombok.Builder;

@Builder
public record Status(
        Long id,
        String name,
        String label
) {


}
