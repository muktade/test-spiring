package com.idb.tasks.controller;

import com.idb.tasks.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface BaseController<ENTITY, ID> {

    @PostMapping(value = "save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<ENTITY> save(ENTITY entity);

    @PatchMapping(value = "update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(ENTITY entity);

    @GetMapping(value = "get-by-id/{id}", produces = APPLICATION_JSON_VALUE)
    <T> ResponseEntity<T> getById(@PathVariable("id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = "list/{ids}", produces = APPLICATION_JSON_VALUE)
    <T> ResponseEntity<Page<T>> getByIds(ID... ids);

    @DeleteMapping(value = "delete/{ids}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteByIds(ID... ids);

    @GetMapping(value = "page/{pageNumber}/{pageSize}/{sortDirection}", produces = APPLICATION_JSON_VALUE)
    <T> ResponseEntity<Page<T>> getAll(Integer pageNumber, Integer pageSize, String sortDirection);

}
