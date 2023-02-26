package com.doccms.domain.service;


import com.doccms.domain.model.NodeType;
import org.springframework.stereotype.Service;

@Service
public class NodeTypeService {

    public NodeType createNodeType(NodeType nodeType) {
        return NodeType.builder()
            .build();
    }
}
