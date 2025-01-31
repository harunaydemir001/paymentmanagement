package com.harun.dalcommon.repository.base;

import com.harun.dalcommon.repository.utils.StringBuilderUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface JPABaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID> {

    default E findByIdOrThrowError(ID id) {
        return findById(id).orElseThrow(() ->
                new EntityNotFoundException(StringBuilderUtil.buildMessage("Not found for ID: {}", id)));
    }
}
