package com.doccms.repository;

import java.util.List;
import java.util.Optional;

import com.doccms.adapter.repository.document.NodeDocument;
import com.doccms.port.repository.DocumentRepository;
import com.doccms.repository.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class DocumentJpaRepository implements DocumentRepository {

    private final DocumentJpaEntityRepository repository;

    @Override
    public Optional<NodeDocument> createDocument(NodeDocument post) {
        return Optional.of(post)
                       .map(DocumentEntity::fromDomain)
                       .map(repository::save)
                       .map(DocumentEntity::toDomain);
    }

    @Override
    public List<NodeDocument> findDocuments() {
        throw new RuntimeException("not yet implemented");
    }
}
