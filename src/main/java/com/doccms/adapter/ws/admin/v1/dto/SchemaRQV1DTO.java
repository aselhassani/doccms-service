package com.doccms.adapter.ws.admin.v1.dto;

import com.doccms.domain.model.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
@io.swagger.v3.oas.annotations.media.Schema(name = "SchemaRQV1DTO", description = "Schema request DTO")
public record SchemaRQV1DTO(
        @NotBlank
        String name,
        @NotBlank
        String description,

        @Valid
        @NotEmpty
        List<FieldV1DTO> fields
) {

    public static SchemaRQV1DTO fromDomain(Schema domain) {
        return SchemaRQV1DTO.builder()
                .name(domain.name())
                .description(domain.description())
                .fields(domain.fields().stream().map(FieldV1DTO::fromDomain).toList())
                .build();
    }

    public Schema toDomain() {
        return Schema.builder()
                .name(this.name())
                .description(this.description())
                .fields(this.fields().stream().map(FieldV1DTO::toDomain).toList())
                .build();
    }

}
