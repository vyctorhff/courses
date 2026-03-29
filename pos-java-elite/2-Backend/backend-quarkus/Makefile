#!/bin/make

## Exemple
##
## make dev
## make add LIBS=quarkus-messaging-kafka

LIBS=''

dev:
	./mvnw quarkus:dev

add:
	./mvnw quarkus:add-extension -Dextensions=$(LIBS)

clean:
	./mvnw clean

test:
	./mvnw quarkus:test
