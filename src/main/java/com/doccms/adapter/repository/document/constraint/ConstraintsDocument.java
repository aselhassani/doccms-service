package com.doccms.adapter.repository.document.constraint;

import java.util.Optional;

import com.doccms.domain.model.constraint.Constraints;
import lombok.Builder;

@Builder
public record ConstraintsDocument(
    Boolean nullable,
    String pattern,
    DateConstraintDocument dateConstraint,
    NumberConstraintDocument numberConstraint,
    SizeConstraintDocument sizeConstraint,
    ListConstraintDocument listConstraint
) {
    public static ConstraintsDocument fromDomain(Constraints domain) {
        return ConstraintsDocument.builder()
                                  .nullable(domain.nullable())
                                  .pattern(domain.pattern())
                                  .dateConstraint(
                                      Optional.ofNullable(domain.dateConstraint())
                                              .map(DateConstraintDocument::fromDomain)
                                              .orElse(null))
                                  .numberConstraint(
                                      Optional.ofNullable(domain.numberConstraint())
                                              .map(NumberConstraintDocument::fromDomain)
                                              .orElse(null))
                                  .sizeConstraint(Optional.ofNullable(domain.sizeConstraint())
                                                          .map(SizeConstraintDocument::fromDomain).orElse(null))
                                  .listConstraint(Optional.ofNullable(domain.listConstraint())
                                                          .map(ListConstraintDocument::fromDomain).orElse(null))
                                  .build();
    }

    public Constraints toDomain() {
        return Constraints.builder()
                          .nullable(this.nullable())
                          .pattern(this.pattern())
                          .dateConstraint(
                              Optional.ofNullable(this.dateConstraint())
                                      .map(DateConstraintDocument::toDomain)
                                      .orElse(null))
                          .numberConstraint(
                              Optional.ofNullable(this.numberConstraint())
                                      .map(NumberConstraintDocument::toDomain)
                                      .orElse(null))
                          .sizeConstraint(Optional.ofNullable(this.sizeConstraint())
                                                  .map(SizeConstraintDocument::toDomain)
                                                  .orElse(null))
                          .listConstraint(Optional.ofNullable(this.listConstraint())
                                                  .map(ListConstraintDocument::toDomain)
                                                  .orElse(null))
                          .build();
    }
}
