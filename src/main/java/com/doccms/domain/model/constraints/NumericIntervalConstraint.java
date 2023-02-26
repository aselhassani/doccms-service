package com.doccms.domain.model.constraints;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class NumericIntervalConstraint implements Constraint {

    private static final String NAME = "NumericIntervalConstraint";

    private Double min;
    private Double max;

    @Override
    public String getName() {
        return NAME;
    }
}
