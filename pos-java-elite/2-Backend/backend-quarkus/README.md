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


## Add libs

```shell script
./mvnw quarkus:add-extension -Dextensions="quarkus-rest-client"
```

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

## Quarkus SmallRye

SmallRye é a implementação da especificação do microprofile e outras implementações alé do microprofile

## Panache

Específico para quarkus Facilitar o uso do hibernate(jackarta persistence, antigo jpa)
É recomendado para utilizadas simples

## Opentelemetry

Permitir expor os dados das requisições para uma ferramenta
No caso será usado o Jaeger UI

## Micrometer(quarkus-micrometer)

Framework para coleta de dados

## Quarkus Prometheus(quarkus-micrometer-registry-prometheus)

Habilitando saida das metricas para o prometheus
Tem um endpoint que mostrar todas as metricas em (micrometer metris - prometheus)

## VSCode plugin

REST Client