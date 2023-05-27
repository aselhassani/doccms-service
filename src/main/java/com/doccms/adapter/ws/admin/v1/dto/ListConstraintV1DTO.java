package com.doccms.adapter.ws.admin.v1.dto;

import com.doccms.domain.model.constraint.ListConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(name = "ListConstraintV1DTO", description = "List constraint DTO")
public record ListConstraintV1DTO(
        Integer minSize,
        Integer maxSize,
        List<Object> possibleValues) {

    public static ListConstraintV1DTO fromDomain(ListConstraint domain) {
        return ListConstraintV1DTO.builder()
                .minSize(domain.minSize())
                .maxSize(domain.maxSize())
                .possibleValues(domain.possibleValues())
                .build();
    }

    public ListConstraint toDomain() {
        return ListConstraint.builder()
                .minSize(this.minSize())
                .maxSize(this.maxSize())
                .possibleValues(this.possibleValues())
                .build();
    }
}
