package com.doccms.adapter.ws.admin.dto;


import com.doccms.domain.model.constraint.Constraints;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

import java.util.Optional;

@Builder
@Schema(name = "ConstraintsV1DTO", description = "Constraints DTO")
public record ConstraintsDTO(
        @NotNull
        @With
        Boolean nullable,
        @With
        String pattern,
        @With
        DateConstraintDTO dateConstraint,
        @With
        NumberConstraintDTO numberConstraint,
        @With
        SizeConstraintDTO sizeConstraint,
        @With
        ListConstraintDTO listConstraint
) {

    public static ConstraintsDTO fromDomain(Constraints domain) {
        return ConstraintsDTO.builder()
                .nullable(domain.nullable())
                .pattern(domain.pattern())
                .dateConstraint(Optional.ofNullable(domain.dateConstraint())
                        .map(DateConstraintDTO::fromDomain)
                        .orElse(null))
                .numberConstraint(Optional.ofNullable(domain.numberConstraint())
                        .map(NumberConstraintDTO::fromDomain)
                        .orElse(null))
                .listConstraint(Optional.ofNullable(domain.listConstraint())
                        .map(ListConstraintDTO::fromDomain)
                        .orElse(null))
                .sizeConstraint(Optional.ofNullable(domain.sizeConstraint())
                        .map(SizeConstraintDTO::fromDomain)
                        .orElse(null))
                .build();
    }

    public Constraints toDomain() {
        return Constraints.builder()
                .nullable(this.nullable())
                .pattern(this.pattern())
                .dateConstraint(
                        Optional.ofNullable(this.dateConstraint()).map(DateConstraintDTO::toDomain)
                                .orElse(null))
                .numberConstraint(
                        Optional.ofNullable(this.numberConstraint()).map(NumberConstraintDTO::toDomain)
                                .orElse(null))
                .sizeConstraint(
                        Optional.ofNullable(this.sizeConstraint()).map(SizeConstraintDTO::toDomain)
                                .orElse(null))
                .listConstraint(
                        Optional.ofNullable(this.listConstraint()).map(ListConstraintDTO::toDomain)
                                .orElse(null))
                .build();
    }
}
