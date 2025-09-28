package main

import "fmt"

func main() {
	PrintPresentation()

	for {
		command := GetInput()
		if IsExit(command) {
			break
		}

		Separador()
		HandleCommand(command)
		Separador()
	}
}

func IsExit(command int) bool {
	return command == 3
}

func Separador() {
	fmt.Println("-----------------------------------")
}
