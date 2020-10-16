# Spring Boot CRUD Framework with Example
This example is about how to convert boiler-plate CRUD code into a prototype. 
We have this framework which allows us to build CRUD API within or less than 5 minutes.

## Steps to use the framework - 

Once you have cloned this example, follow these steps - 

#### 1. Create a Model class, and a DTO class

  ###### Person.java 
    
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
    
        private transient final Class dtoType = PersonDTO.class; // Link this got Model-DTO conversion
    }
  ###### PersonDTO.java
    
    @Data
    public class PersonDTO implements DataTransportObject<Person>{
        private Long id;
        private String name;
        private String address;
    }

#### 2. Once you define your Model, create the repository, service and controller layer

  ###### PersonRepository.java
    
    @Repository
    public interface PersonRepository  extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    }

  ######  PersonService.java
  In this class, all you need to do is to implement the basic service template to include the basic CRUD functionality, without actually coding it!
  
    @Getter
    @Service
    @AllArgsConstructor
    public class PersonService implements BaseService<Person>{
        private final PersonRepository repository;
    }

  ###### PersonController.java
  In this class, you will need to implement the scaffolding template to include the basic CRUD functionality, without actually coding it!
  
    @Getter
    @RestController
    @AllArgsConstructor
    @RequestMapping("/people")
    public class PersonController implements ScaffoldingTemplate<Person, PersonDTO> {
        private final PersonService service;
    }

**And thats it! the API for the resource "_**Person**_" is ready to be used!**

## Some more helpful points -  

**1. Use of Generics**
    
  To use the framework fully, we must understand how we have used generic classes and wild cards here. You can refer to the interfaces we have defined in the framework (i.e. `ScaffoldingTemplate.java` and `BaseService.java` )  

**2. Set up the `/actuator/info` endpoint to use the github run number to be shown as the deployed version.**

    plugins {
        ...
        id 'com.gorylenko.gradle-git-properties' version '2.2.3'
        ...
    }

    ...
    group = 'com.example'
    version = "${System.env.GITHUB_RUN_NUMBER ?: '0-SNAPSHOT'}" //Use GitHub Actions RUN_NUMBER if available
    ...
    
**3. Spring 5 Exception Handling**  
  
  We are using a new Exception implementation introduced in Spring 5, called `ResponseStatusException`, when it is thrown with all the correct properties set, we do not need to implement our own ControllerAdvice or ExceptionHandler.