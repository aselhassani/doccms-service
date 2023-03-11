package com.doccms.adapter.repository.document.constraint;

import java.util.List;

import com.doccms.domain.model.constraint.ListConstraint;
import lombok.Builder;

@Builder
public record ListConstraintDocument(
    Integer minSize,
    Integer maxSize,
    List<Object> possibleValues) {

    public static ListConstraintDocument fromDomain(ListConstraint domain) {
        return ListConstraintDocument.builder()
                                     .minSize(domain.minSize())
                                     .maxSize(domain.maxSize())
                                     .possibleValues(domain.possibleValues())
                                     .build();
    }

    public ListConstraint toDomain() {
        return ListConstraint.builder()
                             .minSize(this.minSize())
                             .maxSize(this.maxSize())
                             .possibleValues(this.possibleValues())
                             .build();
    }
}
