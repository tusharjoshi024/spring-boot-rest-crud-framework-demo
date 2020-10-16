package com.example.demo.model;

import com.example.demo.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Type;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
public class Person implements Resource{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String somePrivateField = "I don't want to show this!";

    private transient final Class dtoType = PersonDTO.class;
}
