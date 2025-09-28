package main

import "fmt"

func PrintPresentation() {
	nome := "Victor"
	version := 1.0

	fmt.Println("Hello, mr. ", nome)
	fmt.Println("This program is version: ", version)
}

func GetInput() int {
	fmt.Println("1 - Init check")
	fmt.Println("2 - Show logs")
	fmt.Println("3 - Exit")

	var command int
	fmt.Print("Enter the option: ")
	fmt.Scan(&command)

	return command
}
