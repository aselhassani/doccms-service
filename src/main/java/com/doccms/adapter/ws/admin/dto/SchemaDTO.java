package com.doccms.adapter.ws.admin.dto;

import com.doccms.domain.model.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
@io.swagger.v3.oas.annotations.media.Schema(name = "SchemaRQV1DTO", description = "Schema request DTO")
public record SchemaDTO(
        @NotBlank
        String name,
        @NotBlank
        String description,

        @Valid
        @NotEmpty
        List<FieldDTO> fields
) {

    public static SchemaDTO fromDomain(Schema domain) {
        return SchemaDTO.builder()
                .name(domain.name())
                .description(domain.description())
                .fields(domain.fields().stream().map(FieldDTO::fromDomain).toList())
                .build();
    }

    public Schema toDomain() {
        return Schema.builder()
                .name(this.name())
                .description(this.description())
                .fields(this.fields().stream().map(FieldDTO::toDomain).toList())
                .build();
    }

}
