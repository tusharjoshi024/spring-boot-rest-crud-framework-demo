package com.example.demo.dto;

import com.example.demo.model.Person;
import lombok.Data;

@Data
public class PersonDTO implements DataTransportObject<Person>{
    private Long id;
    private String name;
    private String address;
}
