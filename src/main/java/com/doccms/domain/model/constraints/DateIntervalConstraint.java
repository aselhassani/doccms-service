package com.doccms.domain.model.constraints;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DateIntervalConstraint implements Constraint {
    private static final String NAME = "DateIntervalConstraint";

    private LocalDate start;
    private LocalDate end;

    @Override
    public String getName() {
        return NAME;
    }
}
