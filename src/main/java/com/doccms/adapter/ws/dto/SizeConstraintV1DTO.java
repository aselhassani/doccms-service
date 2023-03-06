package com.doccms.adapter.ws.dto;

import com.doccms.domain.model.contraint.SizeConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "SizeConstraintV1DTO", description = "Data size constraint DTO")
public record SizeConstraintV1DTO(
    Integer minSize,
    Integer maxSize
) {
    public static SizeConstraintV1DTO fromDomain(SizeConstraint domain) {
        return SizeConstraintV1DTO.builder()
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
