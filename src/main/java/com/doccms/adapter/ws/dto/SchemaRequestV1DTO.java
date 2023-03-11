package com.doccms.adapter.ws.dto;

import java.util.List;

import com.doccms.domain.model.Schema;
import lombok.Builder;

@Builder
@io.swagger.v3.oas.annotations.media.Schema(name = "SchemaRequestV1DTO", description = "Schema request DTO")
public record SchemaRequestV1DTO(
    String name,
    String description,
    List<FieldV1DTO> fields
) {

    public static SchemaRequestV1DTO fromDomain(Schema domain) {
        return SchemaRequestV1DTO.builder()
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
