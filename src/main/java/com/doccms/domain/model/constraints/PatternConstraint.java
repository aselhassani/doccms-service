package com.doccms.domain.model.constraints;

import java.util.regex.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatternConstraint implements Constraint{

    private static final String NAME = "PatternConstraint";

    private Pattern pattern;

    @Override
    public String getName() {
        return NAME;
    }
}
