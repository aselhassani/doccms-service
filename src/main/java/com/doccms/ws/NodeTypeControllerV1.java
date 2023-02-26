package com.doccms.ws;

import java.util.Optional;

import com.doccms.domain.service.NodeTypeService;
import com.doccms.ws.dto.NodeTypeV1DTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class NodeTypeControllerV1 {

    private final NodeTypeService nodeTypeService;

    @PostMapping
    ResponseEntity<NodeTypeV1DTO> createNodeType(NodeTypeV1DTO nodeTypeV1DTO) {
        Optional.of(nodeTypeV1DTO)
            .map(nodeTypeService::createNodeType)
            .map(NodeTypeV1DTO::fromDomain)
            .map(ResponseEntity::ok).
            orElse(null);
    }





}
