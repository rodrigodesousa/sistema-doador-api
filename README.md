# sistema-doador-api
## Demo para testes
```
https://sistema-doador-backend.herokuapp.com/
```
## Requisitos:
```
Java 11
Spring Boot 2.4
Maven 3
PostgreSQL
```
## Criar Database manualmente no seu postgresql e no arquivo aplication-dev.properties alterar valores de acordo com seu banco:
```
spring.datasource.url=jdbc:postgresql://[HOST_DO_BANCO]:[PORTA_DO_BANCO]/[NOME_DO_DATABASE]
spring.datasource.username=[USUARIO_DO_BANCO]
spring.datasource.password=[SENHA_DO_BANCO]
```
## Utilizar IDE Eclipse ou Intellij para executar projeto ou as linhas de comando abaixo

## Instalação de dependencias
```
mvn install
```
## Executar Projeto
```
mvn spring-boot:run
```
