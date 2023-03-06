package com.doccms.adapter.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.doccms.adapter.repository.document.SchemaDocument;
import com.doccms.domain.model.Schema;
import com.doccms.helpers.DocumentTestHelper;
import com.doccms.helpers.DomainTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SchemaMongoRepositoryTest {

    @InjectMocks
    private SchemaMongoRepository underTest;

    @Mock
    private SchemaDocumentMongoRepository schemaDocumentMongoRepository;

    private Schema schema;

    private SchemaDocument schemaDocument;

    @BeforeEach
    void setup() {
        schema = DomainTestHelper.getRandomSchema();
        schemaDocument = DocumentTestHelper.getRandomSchemaDocument();
    }

    @Test
    void createSchemaShouldCreateSchema() {

        when(schemaDocumentMongoRepository.save(any())).thenReturn(schemaDocument);

        var result = underTest.save(schema);

        ArgumentCaptor<SchemaDocument> captor = ArgumentCaptor.forClass(SchemaDocument.class);

        verify(schemaDocumentMongoRepository).save(captor.capture());

        var iSchemaDocument = captor.getValue();

        assertThat(iSchemaDocument).usingRecursiveComparison().isEqualTo(schema);
        assertThat(result).usingRecursiveComparison().isEqualTo(schemaDocument);
    }
}
