package com.doccms.ws.dto;

import com.doccms.adapter.repository.document.NodeDocument;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DocumentResponseV1DTO(
    @JsonProperty("doc_id")
    Long documentId,
    String title,
    String description,
    String content
) {

    public static DocumentResponseV1DTO fromDomain(NodeDocument domain) {
        return DocumentResponseV1DTO.builder()
                                    // .postId(domain.postId())
                                    .title(domain.title())
                                    .description(domain.description())
                                    //.content(domain.content())
                                    .build();
    }

}
