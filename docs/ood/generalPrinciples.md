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

