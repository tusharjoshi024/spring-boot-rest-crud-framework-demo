package com.example.demo.dto;

import com.example.demo.model.Resource;
import com.example.demo.service.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.core.GenericTypeResolver;

public interface DataTransportObject<R extends Resource> {
    ModelMapper modelMapper = new ModelMapper();
    default R toModelResourceObject(){
        return modelMapper.map(this,((Class<R>)GenericTypeResolver.resolveTypeArgument(getClass(), DataTransportObject.class)));
    }
}
