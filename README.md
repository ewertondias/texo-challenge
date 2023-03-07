# texo-challenge
Texo challenge project - Golden Raspberry Awards

## Requisitos

[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

[Maven](https://maven.apache.org/download.cgi)

###  Banco de dados da aplicação
H2

url: jdbc:h2:mem:award

database: award

host: http://localhost:8080/h2-console

usuário: sa

senha: sa

### Banco de dados teste (Para testes integrados)
H2

database: award-test

usuário: sa-test

senha: sa-test

## Instalação

### Compilar projeto
```bash
mvn clean install
```

### Executar projeto
```bash
java -jar target/golden-raspberry-awards-0.0.1.jar
```

## Executar Testes

```bash
mvn verify
```

## Documentação

http://localhost:8080/swagger-ui/index.html