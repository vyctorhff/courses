package main

import (
	"fmt"
	"strconv"
)

/*
Write a Bubble Sort program in Go.
The program should prompt the user to type in a sequence of up to 10 integers.
The program should print the integers out on one line, in sorted order, from least to greatest.
Use your favorite search tool to find a description of how the bubble sort algorithm works.

As part of this program, you should write a function called BubbleSort() which takes a slice of integers as an argument and returns nothing.
The BubbleSort() function should modify the slice so that the elements are in sorted order.

A recurring operation in the bubble sort algorithm is the Swap operation which swaps the position of two adjacent elements in the slice.
You should write a Swap() function which performs this operation.

Your Swap() function should take two arguments, a slice of integers and an index value i which indicates a position in the slice.
The Swap() function should return nothing, but it should swap the contents of the slice in position i with the contents in position i+1.
*/

const SIZE = 10

func main() {
	var listNumbers []int = LoadUserInput()

	BubbleSort(listNumbers)
	Print(listNumbers)
}

func BubbleSort(listNumbers []int)  {
	var result = listNumbers[SIZE:]
	
	var size int = len(result)
	var next int

	changed := true
	for changed {

		changed = false

		for index, current := range result {
			if index + 1 == size { break }

			next = result[index + 1]

			if (current > next) {
				Swap(result, index)
				changed = true
			}
		}
	}
}

func Swap(list []int, index int) {
	next := list[index + 1]
	current := list[index]

	list[index] = next
	list[index + 1] = current
}

func LoadUserInput() []int {
	var listNumbers []int = make([]int, SIZE)

	for count := 0; count < 10; count++ {
		input := GetInput()

		if isInputExit(input) {
			break;
		} else {
			number,_ := strconv.Atoi(input)
			listNumbers = append(listNumbers, number)
		}
		
	}

	return listNumbers
}

func GetInput() string {
	fmt.Print("Please, input a integer number: ")

	var input string
	fmt.Scan(&input)

	return input
}

func Print(listNumbers []int) {
	fmt.Println(listNumbers[SIZE:])
}

func isInputExit(str string) bool {
	return str == "x" || str == "X"
}