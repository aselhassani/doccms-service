package com.doccms.adapter.repository.document.contraint;

import com.doccms.domain.model.contraint.SizeConstraint;
import lombok.Builder;

@Builder
public record SizeConstraintDocument(
    Integer minSize,
    Integer maxSize) {

    public static SizeConstraintDocument fromDomain(SizeConstraint domain) {
        return SizeConstraintDocument.builder()
                                     .minSize(domain.minSize())
                                     .maxSize(domain.maxSize())
                                     .build();
    }

    public SizeConstraint toDomain() {
        return SizeConstraint.builder()
                             .minSize(this.minSize())
                             .maxSize(this.maxSize())
                             .build();
    }
}
