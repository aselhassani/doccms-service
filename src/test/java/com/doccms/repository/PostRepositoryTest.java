package com.doccms.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.doccms.adapter.repository.document.NodeDocument;
import com.doccms.port.repository.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTest {

    private NodeDocument post;

    @InjectMocks
    private DocumentRepository underTest;

    @Mock
    private DocumentJpaRepository postJpaRepository;

    @Test
    void createPostShouldCreatePostAndReturnIt() {


        when(postJpaRepository.createDocument(any())).thenReturn(Optional.of((post)));

        ArgumentCaptor<NodeDocument> captor = ArgumentCaptor.forClass(NodeDocument.class);

        underTest.createDocument(post);

        verify(postJpaRepository).createDocument(captor.capture());

        var iPost = captor.getValue();

        assertThat(iPost).isEqualTo(post);

    }
}
