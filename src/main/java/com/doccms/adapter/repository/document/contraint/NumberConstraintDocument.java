package com.doccms.adapter.repository.document.contraint;

import com.doccms.domain.model.contraint.NumberConstraint;
import lombok.Builder;

@Builder
public record NumberConstraintDocument(
    Double minValue,
    Double maxValue

) {
    public static NumberConstraintDocument fromDomain(NumberConstraint domain) {
        return NumberConstraintDocument.builder()
                                       .minValue(domain.minValue())
                                       .maxValue(domain.maxValue())
                                       .build();
    }

    public NumberConstraint toDomain() {
        return NumberConstraint.builder()
                               .minValue(this.minValue())
                               .maxValue(this.maxValue())
                               .build();
    }
}
