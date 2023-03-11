package com.doccms.domain.model.constraint;

import lombok.Builder;

@Builder
public record SizeConstraint(Integer minSize,
                             Integer maxSize) {
}
