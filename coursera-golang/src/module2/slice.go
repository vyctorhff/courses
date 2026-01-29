package main

import (
	"fmt"
	"strconv"
)

const SIZE = 3
var listNumbers []int = make([]int, SIZE)

func main() {
	for {
		number, valid, exit := GetUserInput()

		if exit {
			break;
		} else if valid {
			Add(number)
			Sort()
			Print()
		}
	}
}

func Sort()  {
	var next int

	var result = listNumbers[SIZE:]
	var size int = len(result)

	changed := true
	for changed {

		changed = false

		for index, current := range result {
			if index + 1 == size { break }

			next = result[index + 1]

			if (current > next) {
				result[index] = next
				result[index + 1] = current

				changed = true
			}
		}
	}
}

func Add(number int) {
	listNumbers = append(listNumbers, number)
}

func Print() {
	fmt.Println(listNumbers[SIZE:])
}

func GetUserInput() (int, bool, bool) {
	fmt.Print("Please, input a integer number: ")

	var strInput string
	_, err := fmt.Scan(&strInput)

	exist := false
	valid := false
	var number int

	if isExist(strInput) {
		exist = true
	}

	if err == nil {
		valid = true
		number, err = strconv.Atoi(strInput)
	}

	return number, valid, exist
}

func isExist(str string) bool {
	return str == "x" || str == "X"
}