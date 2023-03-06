package com.doccms.domain.model.contraint;

import lombok.Builder;

@Builder
public record Constraints(
    Boolean nullable,
    String pattern,
    DateConstraint dateConstraint,
    NumberConstraint numberConstraint,
    SizeConstraint sizeConstraint,
    ListConstraint listConstraint
) {
}
