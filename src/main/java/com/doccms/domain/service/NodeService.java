package com.doccms.domain.service;


import com.doccms.domain.model.Node;
import com.doccms.domain.model.Schema;
import com.doccms.port.repository.NodeRepository;
import com.doccms.port.repository.SchemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NodeService {

    private final NodeRepository nodeRepository;

    public Node save(Node node) {
        return nodeRepository.save(node);
    }

    public Optional<Node> findByName(String name) {
        return nodeRepository.findByName(name);
    }
}
