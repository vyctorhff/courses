package main

import (
	"fmt"
)

type Animal interface {
	Eat()
	Move()
	Speak()

	// Aditional methods to make it easy
	Init(nickName string)
	GetNickName() string
}

type AnimalInfo struct {
	NickName   string
	Name       string
	Food       string
	Locomotion string
	Noise      string
}

/******************************************************
Cow
*******************************************************/
type Cow struct {
	info AnimalInfo
}

func (a Cow) Eat() { fmt.Println(a.info.Food) }

func (a Cow) Move() { fmt.Println(a.info.Locomotion) }

func (a Cow) Speak() { fmt.Println(a.info.Noise) }

func (a *Cow) Init(nickName string) {
	a.info.NickName = nickName
	a.info.Name = "cow"
	a.info.Food = "grass"
	a.info.Locomotion = "walk"
	a.info.Noise = "moo"
}

func (a *Cow) GetNickName() string { return a.info.NickName }

/******************************************************
Bird
*******************************************************/
type Bird struct {
	info AnimalInfo
}

func (a Bird) Eat() { fmt.Println(a.info.Food) }

func (a Bird) Move() { fmt.Println(a.info.Locomotion) }

func (a Bird) Speak() { fmt.Println(a.info.Noise) }

func (a *Bird) Init(nickName string) {
	a.info.NickName = nickName
	a.info.Name = "bird"
	a.info.Food = "worms"
	a.info.Locomotion = "fly"
	a.info.Noise = "peep"
}

func (a *Bird) GetNickName() string { return a.info.NickName }

/******************************************************
Snake
*******************************************************/
type Snake struct {
	info AnimalInfo
}

func (a Snake) Eat() { fmt.Println(a.info.Food) }

func (a Snake) Move() { fmt.Println(a.info.Locomotion) }

func (a Snake) Speak() { fmt.Println(a.info.Noise) }

func (a *Snake) Init(nickName string) {
	a.info.NickName = nickName
	a.info.Name = "snake"
	a.info.Food = "mice"
	a.info.Locomotion = "slither"
	a.info.Noise = "hsss"
}

func (a *Snake) GetNickName() string { return a.info.NickName }

/******************************************************
User Input
*******************************************************/
type UserInput struct {
	Operation string
	Name      string
	Option    string
}

func (u *UserInput) Get() {
	fmt.Print(">")
	fmt.Scanf("%s %s %s", &u.Operation, &u.Name, &u.Option)
}

func (u *UserInput) IsNewAnimal() bool {
	return u.Operation == "newanimal"
}

/******************************************************
List Animais
*******************************************************/
type ListAnimais struct {
	listAnimalsInfo []Animal
}

func (l *ListAnimais) Add(input UserInput) {
	var animal Animal

	switch input.Option {
	case "cow":
		animal = new(Cow)
	case "bird":
		animal = new(Bird)
	case "snake":
		animal = new(Snake)
	}

	animal.Init(input.Name)
	l.listAnimalsInfo = append(l.listAnimalsInfo, animal)

	fmt.Println("Created it!")
}

func (l *ListAnimais) GetAnimalByName(name string) Animal {
	for _, value := range l.listAnimalsInfo {
		if value.GetNickName() == name {
			return value
		}
	}
	return nil
}

/******************************************************
Main
*******************************************************/
func main() {
	list := new(ListAnimais)
	input := new(UserInput)

	fmt.Println("Commands: newanimal or query")

	for {
		input.Get()
		if input.IsNewAnimal() {
			list.Add(*input)
		} else {
			animal := list.GetAnimalByName(input.Name)
			ShowAnimalInfo(animal, input.Option)
		}
	}
}

func ShowAnimalInfo(animal Animal, option string) {
	if animal == nil {
		fmt.Println("Animal not found it")
	} else {
		switch option {
		case "eat":
			animal.Eat()
		case "move":
			animal.Move()
		case "speak":
			animal.Speak()
		}
	}
}
