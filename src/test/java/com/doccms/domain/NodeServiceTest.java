package com.doccms.domain;

import com.doccms.domain.model.Node;
import com.doccms.domain.service.NodeService;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.helpers.TestHelper;
import com.doccms.port.repository.NodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NodeServiceTest {


    @InjectMocks
    private NodeService underTest;

    @Mock
    private NodeRepository nodeRepository;

    private Node node;



    @BeforeEach
    void setup() {

        node = DomainTestHelper.getRandomNode();
    }

    @Test
    void saveShouldSaveNode() {

        when(nodeRepository.save(any())).thenReturn(node);

        var result = underTest.save(node);
        verify(nodeRepository).save(node);
        assertThat(result).isEqualTo(node);
    }




}
