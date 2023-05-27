package com.doccms.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.doccms.domain.model.Schema;
import com.doccms.domain.service.SchemaService;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.helpers.TestHelper;
import com.doccms.port.repository.SchemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SchemaServiceTest {


    @InjectMocks
    private SchemaService underTest;

    @Mock
    private SchemaRepository schemaRepository;

    private Schema schema;

    private String schemaName;

    @BeforeEach
    void setup() {
        schemaName = TestHelper.getRandomId("sch");
        schema = DomainTestHelper.getRandomSchema(schemaName);
    }

    @Test
    void saveShouldSaveSchema() {

        when(schemaRepository.save(any())).thenReturn(schema);

        var result = underTest.save(schema);

        verify(schemaRepository).save(schema);

        assertThat(result).isEqualTo(schema);
    }

    @Test
    void findByNameShouldRetrieveSchemaByName() {

        when(schemaRepository.findByName(any())).thenReturn(Optional.of(schema));

        var result = underTest.findByName(schemaName);

        verify(schemaRepository).findByName(schemaName);

        assertThat(result).contains(schema);
    }


}
