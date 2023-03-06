package com.doccms.adapter.ws.dto;


import java.util.Optional;

import com.doccms.domain.model.contraint.Constraints;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "ConstraintsV1DTO", description = "Constraints DTO")
public record ConstraintsV1DTO(
    Boolean nullable,
    String pattern,
    DateConstraintV1DTO dateConstraint,
    NumberConstraintV1DTO numberConstraint,
    SizeConstraintV1DTO sizeConstraint,

    ListConstraintV1DTO listConstraint
) {

    public static ConstraintsV1DTO fromDomain(Constraints domain) {
        return ConstraintsV1DTO.builder()
                               .nullable(domain.nullable())
                               .pattern(domain.pattern())
                               .dateConstraint(DateConstraintV1DTO.fromDomain(domain.dateConstraint()))
                               .numberConstraint(NumberConstraintV1DTO.fromDomain(domain.numberConstraint()))
                               .listConstraint(ListConstraintV1DTO.fromDomain(domain.listConstraint()))
                               .sizeConstraint(SizeConstraintV1DTO.fromDomain(domain.sizeConstraint()))
                               .build();
    }

    public Constraints toDomain() {
        return Constraints.builder()
                          .nullable(this.nullable())
                          .pattern(this.pattern())
                          .dateConstraint(
                              Optional.ofNullable(this.dateConstraint()).map(DateConstraintV1DTO::toDomain)
                                      .orElse(null))
                          .numberConstraint(
                              Optional.ofNullable(this.numberConstraint()).map(NumberConstraintV1DTO::toDomain)
                                      .orElse(null))
                          .sizeConstraint(
                              Optional.ofNullable(this.sizeConstraint()).map(SizeConstraintV1DTO::toDomain)
                                      .orElse(null))
                          .listConstraint(
                              Optional.ofNullable(this.listConstraint).map(ListConstraintV1DTO::toDomain).orElse(null))
                          .build();
    }
}
