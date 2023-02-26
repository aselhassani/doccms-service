package com.doccms.domain.model.constraints;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NonNullConstraint implements Constraint {

    private static final String NAME = "NonNullConstraint";

    private static NonNullConstraint instance;

    static {
        instance = new NonNullConstraint();
    }

    public static NonNullConstraint getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
