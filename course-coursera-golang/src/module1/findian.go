package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

const (
	FIRST_LETTER     string = "i"
	LAST_LETTER      string = "n"
	CONTAINER_LETTER string = "a"
)

func main() {
	input := GetInput()

	result := HasExpectedCharacters(input)
	AvaliateResult(result)
}

func HasExpectedCharacters(input string) bool {
	first := HasFirstLetter(input)
	last := HasLastLetter(input)
	contains := ContaineLetter(input)

	return first && last && contains
}

func HasFirstLetter(input string) bool {
	var first = string(input[0])
	first = strings.ToLower(first)

	return ComparesIgnoreCase(FIRST_LETTER, first)
}

func HasLastLetter(input string) bool {
	size := len(input)
	last := string(input[size-1])

	return ComparesIgnoreCase(LAST_LETTER, last)
}

func ContaineLetter(input string) bool {
	subStr := input[1 : len(input)-1]

	for count := 0; count < len(subStr); count++ {
		if ComparesIgnoreCase(CONTAINER_LETTER, string(subStr[count])) {
			return true
		}
	}

	return false
}

func ComparesIgnoreCase(str1 string, str2 string) bool {
	str1 = strings.ToLower(str1)
	str2 = strings.ToLower(str2)

	return str1 == str2
}

func AvaliateResult(result bool) {
	if result {
		mach()
	} else {
		notMach()
	}
}

func mach() {
	fmt.Println("Found!")
}

func notMach() {
	fmt.Println("Not found!")
}

func GetInput() string {
	fmt.Printf("Please, enter a string: \n")

	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()

	return scanner.Text()
}
