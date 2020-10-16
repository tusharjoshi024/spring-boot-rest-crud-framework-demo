package com.example.demo.model;

import com.example.demo.dto.DataTransportObject;
import org.modelmapper.ModelMapper;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Type;

public interface Resource {
    ModelMapper modelMapper = new ModelMapper();
    void setId(Long id);
    Type getDtoType();
    default DataTransportObject toDTO(){
        return modelMapper.map(this,this.getDtoType());
    }
}