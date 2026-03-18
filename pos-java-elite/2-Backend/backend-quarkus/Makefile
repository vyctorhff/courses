LIBS=''

dev:
	./mvnw quarkus:dev

# make add LIBS=quarkus-???
add:
	./mvnw quarkus:add-extension -Dextensions=$(LIBS)

clean:
	./mvnw clean

test:
	./mvnw quarkus:test
