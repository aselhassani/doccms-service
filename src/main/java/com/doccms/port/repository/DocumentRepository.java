package com.doccms.port.repository;

import java.util.List;
import java.util.Optional;

import com.doccms.adapter.repository.document.NodeDocument;

public interface DocumentRepository {
    Optional<NodeDocument> createDocument(NodeDocument post);

    List<NodeDocument> findDocuments();
}
