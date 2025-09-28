package main

import (
	"fmt"
)

type AnimalAndActions struct {
	name string
	Animal
}

type Animal struct {
	Food string
	Locomotion string
	Noise string
}

func (a Animal) Eat() {
	fmt.Println(a.Food)
}

func (a Animal) Move() {
	fmt.Println(a.Locomotion)
}

func (a Animal) Speak() {
	fmt.Println(a.Noise)
}

var listAnimals []AnimalAndActions

func main() {
	InitAnimals()

	for {
		inputAnimal, inputAction := GetInput()

		animalAndActions := FindAnimalActionByName(inputAnimal)
		action := getActionByName(inputAction, animalAndActions)

		fmt.Println(action)
	}
}

func InitAnimals() {
	listAnimals = []AnimalAndActions{}

	cow := GetAnimal("cow", "grass", "walk", "moo")
	listAnimals = append(listAnimals, cow)

	bird := GetAnimal("bird", "worms", "fly", "peep")
	listAnimals = append(listAnimals, bird)

	snake := GetAnimal("snake", "mice", "slither", "hsss")
	listAnimals = append(listAnimals, snake)
}

func GetAnimal(name, food, locomotion, noise string) AnimalAndActions {
	animal := new(AnimalAndActions)

	animal.name = name
	animal.Food = food
	animal.Locomotion = locomotion
	animal.Noise = noise

	return *animal
}

func FindAnimalActionByName(name string) AnimalAndActions {
	animalAction := new(AnimalAndActions)

	for _, value := range listAnimals {
		if name == value.name {
			*animalAction = value
			break;
		}
	}

	return *animalAction
}

func getActionByName(name string, animalAndActions AnimalAndActions) string {
	switch (name) {
	case "eat":
		return animalAndActions.Food
	case "move":
		return animalAndActions.Locomotion
	case "speak":
		return animalAndActions.Noise
	default:
		return "Not found"
	}
}

func GetInput() (string, string) {
	fmt.Println("------------------------------------------")
	fmt.Println("Enter a (cow, bird, or snake) and a (eat, move, or speak)")

	var animal string
	var action string
	fmt.Print(">")
	fmt.Scanf("%s %s", &animal, &action)

	return animal, action
}