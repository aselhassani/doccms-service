package com.doccms.domain.model;

import java.util.List;

import lombok.Builder;

@Builder
public record Schema(
        String name,
        String label,
        String description,
        List<Field> fields
) {


}
