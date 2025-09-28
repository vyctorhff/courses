package main

import (
	"fmt"
	"math"
)

/*
Level 2; Module 2
Let us assume the following formula for displacement s as a function of time t,
acceleration a, initial velocity vo, and initial displacement so.

s = ½ a t2 + vot + so

Write a program which first prompts the user to enter values for acceleration, initial velocity, and initial displacement.
Then the program should prompt the user to enter a value for time and the program should compute the displacement after the entered time.

You will need to define and use a function called GenDisplaceFn() which takes three float64
arguments, acceleration a, initial velocity vo, and initial displacement so.

GenDisplaceFn() should return a function which computes displacement as a function of time,
assuming the given values acceleration, initial velocity, and initial displacement.

The function returned by GenDisplaceFn() should take one float64 argument t, representing time, and return one
float64 argument which is the displacement travelled after time t.

For example, let’s say that I want to assume the following values for acceleration, initial velocity, and initial
displacement: a = 10, vo = 2, so = 1. I can use the following statement to call GenDisplaceFn() to
generate a function fn which will compute displacement as a function of time.

fn := GenDisplaceFn(10, 2, 1)

Then I can use the following statement to print the displacement after 3 seconds.
fmt.Println(fn(3))

And I can use the following statement to print the displacement after 5 seconds.
fmt.Println(fn(5))
*/

type Displacement struct {
	Acceleration float64
	InitVelocity float64
	InitDisplacement float64
	Time float64
}

func main() {
	disp := GetInput()

	fn := GenDisplaceFn(disp.Acceleration, disp.InitVelocity, disp.InitDisplacement)
	fmt.Printf("%0.2f\n", fn(disp.Time))
}

// I let the three float64 args instead of the struct Displacement
// because the question asked to.
// Change after submit
func GenDisplaceFn(acceleration, initVelocity, initDisplacement float64) func (float64) float64 {
	
	return func (time float64) float64 {
		expTime := math.Pow(time, 2)

		part1 := (acceleration * expTime) / 2
		part2 := (initVelocity * time)
		part3 := initDisplacement

		return part1 + part2 + part3
	}
}

func GetInput() Displacement {
	displacement := new(Displacement)

	fmt.Print("Enter the acceleration: ")
	fmt.Scan(&displacement.Acceleration)

	fmt.Print("Enter the initial velocity: ")
	fmt.Scan(&displacement.InitVelocity)

	fmt.Print("Enter the initial displacement: ")
	fmt.Scan(&displacement.InitDisplacement)

	fmt.Print("Enter the time: ")
	fmt.Scan(&displacement.Time)

	return *displacement
}