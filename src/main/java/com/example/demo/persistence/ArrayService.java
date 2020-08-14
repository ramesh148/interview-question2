package com.example.demo.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrayService {

    @Autowired
    ArrayRepository repository;

    public ArrayEntity getStoreById(Long id) throws RecordNotFoundException
    {
        Optional<ArrayEntity> store = repository.findById(id);

        if(store.isPresent()) {
            return store.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public ArrayEntity createOrUpdateStore(ArrayEntity entity) throws RecordNotFoundException
    {
        Optional<ArrayEntity> store = repository.findById(entity.getId());

        if(store.isPresent())
        {
            ArrayEntity newEntity = store.get();
            newEntity.setStore(entity.getStore());
            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }
}
