package com.doccms.repository;

import com.doccms.repository.entity.DocumentEntity;
import org.springframework.data.repository.CrudRepository;

public interface DocumentJpaEntityRepository extends CrudRepository<DocumentEntity, Long> {
}
