package com.doccms.domain.model.constraint;

import lombok.Builder;

@Builder
public record NumberConstraint(
    Double minValue,
    Double maxValue

) {

}
