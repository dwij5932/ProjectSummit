package com.dis.ms.productms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractController<T, ID> {

    protected abstract List<T> getAll(int offset, int limit);

    protected abstract T getById(ID id);

    protected abstract void create(T entity);

    protected abstract void update(ID id, T entity);

    protected abstract void delete(ID id);

    @GetMapping("/all")
    public List<T> getAllEntities(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", defaultValue = "20") int limit){
        return getAll(offset, limit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getEntityById(@PathVariable("id") ID id){
        T entity = getById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void createEntity(@RequestBody T entity) {
        create(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable("id") ID id, @RequestBody T entity) {
        update(id, entity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") ID id) {
        delete(id);
        return ResponseEntity.noContent().build();
    }
}
