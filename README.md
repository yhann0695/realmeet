# Realmeet

A API realmeet tem o papel de criar salas de reuniões para devidos horários e reservar.

## Pré-requisitos

- Java 11
- MySQL
- Docker

## Usando

```
Crie a base de dados: docker run --name mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest

A API tem um arquivo "api.yml" dentro de resource que pode ser exportada para o Postman e criar uma Collection.

É possível também ter acesso aos endpoints do projeto via http://localhost:8080/v1/swagger-ui/index.html?configUrl=/v1/v3/api-docs/swagger-config#/

Todos os endpoints são protegidos, para acessar, a chave é: xpto

```

## Construído com

 - Spring Boot
 - Spring Data JPA (com Hibernate)
 - Flyway
 - MapStruct
 - Maven
 - Logback
 - JUnit
 - Mockito
 - Testcontainers
 - OpenAPI (Swagger)
 - AWS
 - Banco de dados MySQL
 - Docker
