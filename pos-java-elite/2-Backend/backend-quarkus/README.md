# backend-quarkus

## dev-ui

Interface que apresenta diversas informações sobre o projeto

### Extensões

Todas as libs utilizadas
Mostra quais recursos de código estão vinculado a cada extensão

### Configuração

Todas as configurações possível para as libs instaladas

### Endpoints

Todos endpoinst separados entre
- endpoints do quarkus
- endpoints da aplicação

## Quarkus Commands

```shell script
./mvnw quarkus:dev
```

```shell script
./mvnw package
```

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

```shell script
./mvnw package -Dnative
```

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```
