package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"os"
)

/*
Write a program which prompts the user to first enter a name, and then enter an address.
Your program should create a map and add the name and address to the map using the keys “name” and “address”, respectively.
Your program should use Marshal() to create a JSON object from the map, and then your program should print the JSON object.
*/

func main() {
	person := GetInput()
	
	bdata, _ := json.Marshal(person)
	fmt.Print(string(bdata))
}

func GetInput() map[string]string {
	person := map[string]string{
		"name": "",
		"address": "",
	}

	scanner := bufio.NewScanner(os.Stdin)

	fmt.Print("Enter a name: ")
	scanner.Scan()
	person["name"] = scanner.Text()

	fmt.Print("Enter a address: ")
	scanner.Scan()
	person["address"] = scanner.Text()

	return person
}