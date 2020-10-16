package com.example.demo.controller;

import com.example.demo.dto.DataTransportObject;
import com.example.demo.model.Resource;
import com.example.demo.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScaffoldingTemplate<R extends Resource, DTO extends DataTransportObject<R>> {

    BaseService<R> getService();

    @GetMapping
    default List<DTO> getAll(){
        return (List<DTO>)this.getService().getAll();
    }

    @GetMapping("/{id}")
    default ResponseEntity<DTO> getById(@PathVariable Long id){
        return ResponseEntity.ok((DTO)this.getService().getById(id));
    }

    @PostMapping
    default ResponseEntity<DTO> create(@RequestBody DTO dto){
        return new ResponseEntity(this.getService().create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    default ResponseEntity<DTO> update(@PathVariable Long id, @RequestBody DTO dto){
        return ResponseEntity.ok((DTO)this.getService().update(id,dto));
    }

    @DeleteMapping("/{id}")
    default ResponseEntity delete(@PathVariable Long id){
        this.getService().delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
