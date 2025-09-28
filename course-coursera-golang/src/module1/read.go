package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

/*
Write a program which reads information from a file and represents it in a slice of structs. Assume that there is a text file which contains a series of names.
Each line of the text file has a first name and a last name, in that order, separated by a single space on the line.

Your program will define a name struct which has two fields, fname for the first name, and lname for the last name. Each field will be a string of size 20 (characters).

Your program should prompt the user for the name of the text file. Your program will successively read each line of the text file and create a struct which contains the first and last names found in the file.
Each struct created will be added to a slice, and after all lines have been read from the file, your program will have a slice containing one struct for each line in the file.

After reading all lines from the file, your program should iterate through your slice of structs and print the first and last names found in each struct.
*/

type Name struct {
	fname string
	lname string
}

func main() {
	fileName := GetFileName()
	listNames := GetListNames(fileName)

	Print(listNames)
}

func Print(listNames []Name) {
	for key, value := range listNames {
		fmt.Printf("%d: %s, %s\n", key, value.fname, value.lname)
	}
}

func GetListNames(fileName string) []Name {
	var result []Name

	f, _ := os.Open(fileName)
	defer f.Close()

	reader := bufio.NewReader(f)
	for {
		byteLine, _, err := reader.ReadLine()

		if err != nil { break; }

		result = append(result, ConvertLineInName(byteLine))
	}

	return result
}

func ConvertLineInName(byteLine []byte) Name {
	line := string(byteLine)
	names := strings.Split(line, " ")

	return Name{
		fname: names[0],
		lname: names[1],
	}
}

func GetFileName() string {
	var fileName string

	fmt.Print("Enter the file name: ")
	fmt.Scan(&fileName)

	return fileName
	// return "read-file.txt"
}
