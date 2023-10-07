package com.idb.tasks.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<ENTITY, ID> {

    ENTITY save(ENTITY entity);

    ENTITY update(ENTITY entity) throws Exception;

    <T> Page<T> getByIds(Pageable pageable, ID... ids);

    void deleteByIds(ID... ids);

    <T> Page<T> getAll(Pageable pageable);

}
