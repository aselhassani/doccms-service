package com.doccms.adapter.ws.admin.dto;

import com.doccms.domain.model.constraint.NumberConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "NumberConstraintV1DTO", description = "Number constraint DTO")
public record NumberConstraintDTO(
        Double minValue,
        Double maxValue
) {
    public static NumberConstraintDTO fromDomain(NumberConstraint domain) {
        return NumberConstraintDTO.builder()
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
