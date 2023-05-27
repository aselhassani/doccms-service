package com.doccms.adapter.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.doccms.adapter.repository.document.SchemaDocument;
import com.doccms.domain.model.Schema;
import com.doccms.helpers.DocumentTestHelper;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SchemaMongoRepositoryTest {

    private final String SCHEMA_SEQ_NAME = "SCHEMA_SEQ";
    @InjectMocks
    private SchemaMongoRepository underTest;
    @Mock
    private SchemaDocumentMongoRepository schemaDocumentMongoRepository;
    @Mock
    private SequenceGenerator sequenceGenerator;
    private Schema schema;
    private SchemaDocument schemaDocument;

    private String schemaName;

    @BeforeEach
    void setup() {
        schemaName = TestHelper.getRandomId("sch");
        schema = DomainTestHelper.getRandomSchema(schemaName);
        schemaDocument = DocumentTestHelper.getRandomSchemaDocument(schemaName);
    }

    @Test
    void createSchemaShouldCreateSchema() {

        var schemaId = TestHelper.getRandomId();

        when(schemaDocumentMongoRepository.save(any())).thenReturn(schemaDocument);
        when(sequenceGenerator.generateSequence(any())).thenReturn(schemaId);

        var result = underTest.save(schema);

        ArgumentCaptor<SchemaDocument> captor = ArgumentCaptor.forClass(SchemaDocument.class);

        verify(sequenceGenerator).generateSequence(SCHEMA_SEQ_NAME);
        verify(schemaDocumentMongoRepository).save(captor.capture());

        var iSchemaDocument = captor.getValue();

        assertThat(iSchemaDocument.id()).isEqualTo(schemaId);
        assertThat(iSchemaDocument).usingRecursiveComparison().ignoringFields("id").isEqualTo(schema);
        assertThat(result).usingRecursiveComparison().isEqualTo(schemaDocument);
    }

    @Test
    void findByNameShouldRetrieveSchemaByName() {

        when(schemaDocumentMongoRepository.findByName(any())).thenReturn(Optional.of(schemaDocument));

        var result = underTest.findByName(schemaName);

        verify(schemaDocumentMongoRepository).findByName(schemaName);

        assertThat(result).usingRecursiveComparison().isEqualTo(Optional.of(schemaDocument));
    }

    @Test
    void findByNameShouldReturnEmptyIfNoSchemaWasFound() {

        when(schemaDocumentMongoRepository.findByName(any())).thenReturn(Optional.empty());

        var result = underTest.findByName(schemaName);

        verify(schemaDocumentMongoRepository).findByName(schemaName);

        assertThat(result).isEmpty();
    }
}
