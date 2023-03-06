package com.doccms.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.doccms.domain.model.Schema;
import com.doccms.domain.service.SchemaService;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.port.repository.SchemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SchemaServiceTest {


    @InjectMocks
    private SchemaService underTest;

    @Mock
    private SchemaRepository schemaRepository;

    private Schema schema;

    @BeforeEach
    void setup() {
        schema = DomainTestHelper.getRandomSchema();
    }

    @Test
    void saveShouldSaveSchema() {

        when(schemaRepository.save(any())).thenReturn(schema);

        var result = underTest.save(schema);

        verify(schemaRepository).save(schema);

        assertThat(result).isEqualTo(schema);
    }


}
