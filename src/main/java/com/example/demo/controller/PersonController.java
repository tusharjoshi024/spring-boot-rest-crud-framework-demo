package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class PersonController implements ScaffoldingTemplate<Person, PersonDTO> {
    private final PersonService service;
}