# CVDB

A project for a 1) REST API and 2) Resume Browser for managaging and browsing Resumes (CVs).

The project is based on Spring Boot using Spring MVC, Spring JPA.

Implementation notes
-----------------------

** Database
The database is currently a in-memory H2 database initialized with data at startup.
Spring JPA with entities annotation with @Entity and interfaces extending CrudRepository are used.

** Domain model
The domain mata model was created in JDL-studio, generting the following iagram:

[Domain model diagram](jhipster.png)

** REST End-points

GET /api/resumes gets a list of all resumes

GET /api/resumes?firstName&lastName&freeText gets a filtered list of resumes matching firstName, lastName on owning
    person and freeText on name of owner and embedded fields (i.e. assignments, educations). The filtering is made by
    a JPQL query in api.repositories.ResumeRepository.

PUT /api/resumes updates resume properties and honors upate of the person id of owning person. But it does not update person properties (e.g. name, adress).

POST /api/resumes adds a resume

POST /

Test Driven Development using AssertJ, Mockito and Springs mock classes (e.g. MockMVC). Assertions are written in a BDD-style (given..when..then). The overall test coverage is about 80% (90% for the API).

SonalLint was used to scan for code smells.

REST endpoints are implemented in controllers annotated with Spring's @RestControllers. JSON-marshalling from POJOS are done MappingJackson2HttpConverter (autoconfigured by Spring Boot)

Data Transfer Objects (a.k.a command objects) are used consistently instead of Domain objects at the rest-endpoints.

MapStruct is used to generate mappers for conversion between DTO and domain objects. Conversion to Domain Objects are performed in the controllers so that the Services handle only Domain Objects

Lombok @Data, @Builder annotations was used for generated getters/setters/equals/hashcode and buildres on domainobjects and DTOs.

Custom runtime exceptions annotated with suitable Http status codes are thrown for exceptional events (for example for a POST when a resource already exists)

Thrown exceptions in the API are handled a class annotated with @ControllerAdvice. It returns a custom response object with status code, message and date.

The API end-point "resumes" provides a searching functionality of resumes. The searching can be done by a combination of first tname, last name and freetext. The free text phrase is search for in the nearly all resume embedded entities (by a JPQL query in the ResumeRepository).


Some things left to do
------

API: Authorization/antentication (a person can create/edit their resumes, other can only view)
API: Add a picture property of persons.

Browser: Views to Add/edit a resume
Browser: Add Pagination in the search view.
Browser: Increase test coverage of the browser





