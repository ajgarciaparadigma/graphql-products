# Example Microservice GraphQl

This microservice has been developed using the company archetype and starters. The principle followed was using hexagonal architecture (Ports & Adapters). This application is a demo which show how implement a graphql interface.

## Requirements.
- Java 17
- [Maven installed and configured with Nexus repository.](https://paradigma.atlassian.net/wiki/spaces/PQA/pages/816185424/Configuraci+n+settings.xml+y+pom.xml+de+Maven)

## Technical principles
- Spring Boot 3.2.0
- Hexagonal architecture
- H2 embedded database.
- Api First approach.
- [graphql-maven-plugin](https://graphql-maven-plugin-project.graphql-java-generator.com/graphql-maven-plugin/plugin-info.html) is used to generate pojo and server code from graphql schema
- Extended scalars using the library [graphql-java-extended-scalars](https://github.com/graphql-java/graphql-java-extended-scalars)


## How to start the project
1. Build project ```mvn clean package -DwithHistory test-compile org.pitest:pitest-maven:mutationCoverage```
2. Execute locally. ``` mvn clean spring-boot:run -Dspring-boot.run.profiles=local ```.

There is a postman collection ir order to test queries and mutation which could be imported (is under the folder postman).

The api manage products with reviews
