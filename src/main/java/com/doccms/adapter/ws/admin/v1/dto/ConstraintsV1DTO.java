package com.doccms.adapter.ws.admin.v1.dto;


import com.doccms.domain.model.constraint.Constraints;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.util.Optional;

@Builder
@Schema(name = "ConstraintsV1DTO", description = "Constraints DTO")
public record ConstraintsV1DTO(
        @NotNull
        @With
        Boolean nullable,
        @With
        String pattern,
        @With
        DateConstraintV1DTO dateConstraint,
        @With
        NumberConstraintV1DTO numberConstraint,
        @With
        SizeConstraintV1DTO sizeConstraint,
        @With
        ListConstraintV1DTO listConstraint
) {

    public static ConstraintsV1DTO fromDomain(Constraints domain) {
        return ConstraintsV1DTO.builder()
                .nullable(domain.nullable())
                .pattern(domain.pattern())
                .dateConstraint(Optional.ofNullable(domain.dateConstraint())
                        .map(DateConstraintV1DTO::fromDomain)
                        .orElse(null))
                .numberConstraint(Optional.ofNullable(domain.numberConstraint())
                        .map(NumberConstraintV1DTO::fromDomain)
                        .orElse(null))
                .listConstraint(Optional.ofNullable(domain.listConstraint())
                        .map(ListConstraintV1DTO::fromDomain)
                        .orElse(null))
                .sizeConstraint(Optional.ofNullable(domain.sizeConstraint())
                        .map(SizeConstraintV1DTO::fromDomain)
                        .orElse(null))
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
                        Optional.ofNullable(this.listConstraint()).map(ListConstraintV1DTO::toDomain)
                                .orElse(null))
                .build();
    }
}
