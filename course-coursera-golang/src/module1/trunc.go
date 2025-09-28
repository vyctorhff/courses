package main

import (
	"fmt"
)

func main() {
	var floatNumber float32

	fmt.Println("Please, enter a float 32 number")
	fmt.Scan(&floatNumber)

	var integerNumber int32
	integerNumber = int32(floatNumber)

	fmt.Printf("The trunc number in integer is: %d\n", integerNumber)
}
