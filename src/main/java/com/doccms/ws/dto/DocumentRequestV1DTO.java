package com.doccms.ws.dto;

import com.doccms.adapter.repository.document.NodeDocument;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DocumentRequestV1DTO(
    @NotBlank
    String title,
    @NotBlank
    String description,
    @NotBlank
    String content
) {

    public NodeDocument toDomain() {
        return NodeDocument.builder()
                           .title(title)
                           .description(description)
                           //.content(content)
                           .build();
    }

}