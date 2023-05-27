package com.doccms.port.repository;


import com.doccms.domain.model.Node;

import java.util.List;
import java.util.Optional;

public interface NodeRepository {

    Node save(Node node);
    Optional<Node> findByName(String name);
    Optional<Node>  delete(String name);
    Node  update(Node node);
    List<Node> findAll();

}
