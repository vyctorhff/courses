package main

import (
	"fmt"
	"time"
)

/*
Write two goroutines which have a race condition when executed concurrently.
Explain what the race condition is and how it can occur.

Explanation about race condition:

Race condition is when two process or threads depends on the same resource.
Depending who reach first the resouce, the result will change.

*/

var shareResource bool = true

func g1() {
	if shareResource {
		fmt.Println("Passed by g1")
		shareResource = !shareResource
	}
}

func g2() {
	if shareResource {
		fmt.Println("Passed by g2")
		shareResource = !shareResource
	}
}

func main() {
	count := 0
	for count < 10 {
		go g1()
		go g2()

		count++
	}

	fmt.Println("Main thread sleeping!")
	time.Sleep(time.Second * 3)
	fmt.Println("Main thread over!")
}