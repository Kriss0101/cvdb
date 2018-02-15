# cvdb

A REST API and Web client for managaging and browsing Candidates and their Resumes (CVs).

Implementation notes:

Follow TDD (test first) using AssertJ. Assertions are written in a BDD-style (given..when..then)

REST endpoints are implemented in controllers annotated with Spring's @RestControllers. JSON-marshalling from POJOS are done MappingJackson2HttpConverter (autoconfigured by Spring Boot)

Data Transfer Objects (a.k.a DTO/command objects) are used consistently instead of Domain objects at the rest-endpoints.

MapStruct is used to generate mappers for conversion between DTO and domain objects. Conversion to Domain Objects are performed in the controllers so that the Services handle only Domain Objects

Validation of form data is done using the validation annotations in the Data Transfer Objects

Lombok @Data annotation is used for generated getters/setters/equals/hashcode on domainobjects and DTOs.

Custom runtime exceptions annotaed with suitable Http status codes are thrown for exceptional events (for example for a POST when a resource already exists)

Some errors are handled by custom exception handlers in a class annotated with @ControllerAdvice. This includes a validtionEceptionHandler to handle method parameter validation errors. A handler for Exception is used as a fall back.

The API end point "resumes/search" provides a searching functionality of resumes. The searching can be done by a combination of first tname, last name and freetext. The free text phrase is search for in the nearly all resume embedded entities (by a JPQL query in the ResumeRepository).

I created the data model in JDL-studio:

<Link to JDL-Studio diagram here>


TODO
------

Show validation messages in form from the bindingresults

Show resume details of selected resume

Add/edit a resume (for logged in resume user)

Declare mandatory fields for datamodel builders



