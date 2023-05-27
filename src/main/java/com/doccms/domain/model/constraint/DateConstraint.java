package com.doccms.domain.model.constraint;

import java.time.LocalDate;

import lombok.Builder;


@Builder
public record DateConstraint(LocalDate after,
                             LocalDate before) {

}
