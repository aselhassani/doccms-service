package com.doccms.adapter.ws.admin.dto;

import com.doccms.domain.model.constraint.SizeConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "SizeConstraintV1DTO", description = "Data size constraint DTO")
public record SizeConstraintDTO(
        Integer minSize,
        Integer maxSize
) {
    public static SizeConstraintDTO fromDomain(SizeConstraint domain) {
        return SizeConstraintDTO.builder()
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
