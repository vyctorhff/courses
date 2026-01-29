package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

/*
Write a program to sort an array of integers.
The program should partition the array into 4 parts, each of which is sorted by a different goroutine.

Each partition should be of approximately equal size.
Then the main goroutine should merge the 4 sorted subarrays into one large sorted array.

The program should prompt the user to input a series of integers.
Each goroutine which sorts ¼ of the array should print the subarray that it will sort.

When sorting is complete, the main goroutine should print the entire sorted list
*/

func main() {
	list := GetUserInput()
	
	part1, part2, part3, part4 := Partition(list)

	ch1 := make(chan []int)
	ch2 := make(chan []int)
	ch3 := make(chan []int)
	ch4 := make(chan []int)

	go DoSort(part1, ch1)
	go DoSort(part2, ch2)
	go DoSort(part3, ch3)
	go DoSort(part4, ch4)

	sorted1 := <-ch1
	sorted2 := <-ch2
	sorted3 := <-ch3
	sorted4 := <-ch4

	result := Merge(sorted1, sorted2, sorted3, sorted4)
	fmt.Printf("Sorted: %v", result)
}

func Merge(sorted1, sorted2, sorted3, sorted4 []int) []int {
	result := []int{}

	result = append(result, sorted1...)
	result = append(result, sorted2...)
	result = append(result, sorted3...)
	result = append(result, sorted4...)

	sort.Ints(result)
	return result
}

func DoSort(list []int, channel chan []int) {
	fmt.Printf("Part: %v\n", list)

	sort.Ints(list)
	channel <- list

	close(channel)
}

func Partition(list []int) ([]int, []int, []int, []int) {
	var part1 []int
	var part2 []int
	var part3 []int
	var part4 []int

	b1, b2, b3, b4 := true, true, true, true

	for _, value := range list {
		if AddIf(&part1, value, &b1) {
			continue
		}

		if AddIf(&part2, value, &b2) {
			continue
		}

		if AddIf(&part3, value, &b3) {
			continue
		}

		AddIf(&part4, value, &b4)

		b1, b2, b3, b4 = true, true, true, true
	}

	return part1, part2, part3, part4
}

func AddIf(list *[]int, value int, isAdd *bool) bool {
	if !*isAdd {
		return false
	}

	*list = append(*list, value)
	*isAdd = false

	return true
}

func GetUserInput() []int {
	fmt.Println("Enter a list of integers separate by space.")
	fmt.Print("List: ")
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()

	return ConvertInputToIntegerArray(scanner.Text())
}

func ConvertInputToIntegerArray(input string) []int {
	strList := strings.Split(input, " ")
	intList := []int{}

	for _, value := range strList {
		num, _ := strconv.Atoi(value)
		intList = append(intList, num)
	}
	return intList
}
