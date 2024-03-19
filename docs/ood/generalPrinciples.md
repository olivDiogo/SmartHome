## Create entity
- Controller receives primitive types required to construct the value objects
- Controller sends the primitives to the associated service
- Service instantiates value objects
- Service sends value objects to associated factory to instantiate entity
- Factory returns entity to the service
- Service saves the entity to the repository (using auxiliary method)
- Service passes the entity to the assembler to create corresponding DTO
- Service return DTO of added object to the controller
- Controller returns DTO to the UI

#### Sequence Diagram
![Sequence Diagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ood/generalSequenceDiagrams/CreateEntity.png)

## Get all entity's from repo
- Controller receives the request and sends to corresponding service
- Service requests to the repository all the entities
- Repository returns all the entities to the service
- Service passes the entities to the assembler to create corresponding DTOs
- Service returns DTOs to the controller
- Controller returns DTOs to the UI

#### Sequence Diagram
![Sequence Diagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ood/generalSequenceDiagrams/GetAllEntitys.png)

## Get entity by ID
- Controller receives the request with required id's (string format) and sends to corresponding service
- Service creates a identity value object with the given id
- Service requests to the repository the entity with the corresponding id
- Repository returns the entity to the service
- Service passes the entity to the assembler to create corresponding DTO
- Service returns DTO to the controller
- Controller returns DTO to the UI

#### Sequence Diagram
![Sequence Diagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ood/generalSequenceDiagrams/GetEntityByID.png)

## Class Diagram
- Don't need to represent the Value Objects, because they are simple classes with only attributes and getters
- Should represent the DTOs as the attributes will be different from the entity's attributes
- Should represent the Controller, Service, Factory, Repository and Assembler classes
- Should only represent the methods of the classes that are relevant to the use cases
- Should represent all explicit constructors of the classes
- Should show the cardinality of the relationships between classes
- At present the shouldn't represent aggregation or composition relationships between classes
