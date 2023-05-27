package com.doccms.adapter.ws.admin.v1.dto;

import com.doccms.domain.model.constraint.NumberConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "NumberConstraintV1DTO", description = "Number constraint DTO")
public record NumberConstraintV1DTO(
        Double minValue,
        Double maxValue
) {
    public static NumberConstraintV1DTO fromDomain(NumberConstraint domain) {
        return NumberConstraintV1DTO.builder()
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
