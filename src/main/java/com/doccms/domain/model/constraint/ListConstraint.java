package com.doccms.domain.model.constraint;

import java.util.List;

import lombok.Builder;

@Builder
public record ListConstraint(
    Integer minSize,
    Integer maxSize,
    List<Object> possibleValues) {
}
