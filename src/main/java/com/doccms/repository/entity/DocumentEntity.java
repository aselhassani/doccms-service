package com.doccms.repository.entity;

import com.doccms.adapter.repository.document.NodeDocument;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String title;
    private String description;
    private String content;

    public static DocumentEntity fromDomain(NodeDocument domain) {
        return DocumentEntity.builder()
                             .title(domain.title())
                             .description(domain.description())
                             .build();
    }

    public NodeDocument toDomain() {
        return NodeDocument.builder()
                           .title(title)
                           .description(description)
                           .build();
    }


}
