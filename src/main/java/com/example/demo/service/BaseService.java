package com.example.demo.service;

import com.example.demo.dto.DataTransportObject;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Resource;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BaseService<R extends Resource> {

    JpaRepository<R,Long> getRepository();

    default List<DataTransportObject<R>> getAll(){
        var result = new ArrayList();
        this.getRepository().findAll().stream()
                .forEach(resource -> result.add(resource.toDTO()));
        return result;
    }

    default DataTransportObject<R> getById(Long id){
        var resource = this.getRepository().findById(id).
                orElseThrow(()-> new ResourceNotFoundException(this.getResourceType(),id));
        return resource.toDTO();
    }

    default DataTransportObject<R> create(DataTransportObject<R> dto){
        return this.getRepository().saveAndFlush(dto.toModelResourceObject()).toDTO();
    }

    default DataTransportObject<R> update(Long id, DataTransportObject<R> dto){
        if(this.getRepository().findById(id).isEmpty())
            throw new ResourceNotFoundException(this.getResourceType(),id);
        R modelResource = dto.toModelResourceObject();
        modelResource.setId(id);
        return this.getRepository().saveAndFlush(modelResource).toDTO();
    }

    default void delete(Long id){
        if(this.getRepository().findById(id).isEmpty())
            throw new ResourceNotFoundException(this.getResourceType(),id);
        this.getRepository().deleteById(id);
    }

    default String getResourceType(){
        return GenericTypeResolver.resolveTypeArgument(getClass(),BaseService.class).getName();
    }
}
