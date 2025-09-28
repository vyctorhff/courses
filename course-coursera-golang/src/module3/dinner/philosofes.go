package main

/*
Code:
All code should be done in one file.

Question:
Implement the dining philosopher’s problem with the following constraints/modifications.
There should be 5 philosophers sharing chopsticks, with one chopstick between each adjacent pair of philosophers.

Each philosopher should eat only 3 times (not in an infinite loop as we did in lecture)
The philosophers pick up the chopsticks in any order, not lowest-numbered first (which we did in lecture).
In order to eat, a philosopher must get permission from a host which executes in its own goroutine.
The host allows no more than 2 philosophers to eat concurrently.

Each philosopher is numbered, 1 through 5.

When a philosopher starts eating (after it has obtained necessary locks) it prints “starting to eat <number>” on a line by itself,
where <number> is the number of the philosopher.

When a philosopher finishes eating (before it has released its locks) it prints “finishing eating <number>” on a line by itself,
where <number> is the number of the philosopher.
*/

import (
	"fmt"
	"sync"
	"time"
)

const TOTAL_PHILOSOPHERS = 5
const TOTAL_PHILOSOPHERS_CAN_EAT = 3

const TIME_PHILOSOPHER_WAITING = time.Second * 4
const TIME_PHILOSOPHER_EATING = time.Second * 3

var wg sync.WaitGroup
var mt sync.Mutex
var mtp sync.Mutex

/*************************************
Util
/************************************/
func Seperator() {
	fmt.Println("------------------------------------")
}

func contains(list []int, number int) bool {
	for _, value := range list {
		if value == number {
			return true
		}
	}

	return false
}

func NextTwo(list []int, index int) ([]int, bool) {
	if len(list)-2 < index {
		return []int{}, true
	}
	return list[index : index+2], false
}

func index(list []int, number int) (int, bool) {
	for key, value := range list {
		if value == number {
			return key, false
		}
	}

	return 0, true
}

func delete(list []int, number int) *[]int {
	result := []int{}
	for _, value := range list {
		if value == number {
			continue
		}

		result = append(result, value)
	}

	return &result
}

func FinishMeal(philosopher Philosopher) {
	mtp.Lock()

	Seperator()
	fmt.Printf("Finished the meal: %d\n", philosopher.Number)
	Seperator()

	mtp.Unlock()
}

/*************************************
Chopsticks
/************************************/
type Chopsticks struct {
	available bool
	number    int
}

/*************************************
Host
/************************************/
type Host struct {
	OrderCall        []int
	NextIndex        *int
	NumberAuthorized *[]int
}

func (host *Host) GetPermission(number int) bool {
	for !contains(*host.NumberAuthorized, number) {
		host.Wait()
	}
	return true
}

func (host *Host) UpdateNext(number int) {
	mt.Lock()

	if contains(*host.NumberAuthorized, number) {
		*host.NumberAuthorized = *delete(*host.NumberAuthorized, number)
	}

	if len(*host.NumberAuthorized) == 0 {
		*host.NumberAuthorized = *host.NextPair()
	}

	mt.Unlock()
}

func (host *Host) NextPair() *[]int {
	index := *host.NextIndex + 2

	list, error := NextTwo(host.OrderCall, index)

	if error {
		index = 0
		list, _ = NextTwo(host.OrderCall, index)
	}

	*host.NextIndex = index
	return &list
}

func (host *Host) Wait() {
	time.Sleep(TIME_PHILOSOPHER_WAITING)
}

/*************************************
Philosopher
/************************************/
type Philosopher struct {
	Number int
	Right  Chopsticks
	Left   Chopsticks
	Host   Host
}

func (philosopher *Philosopher) eat() {
	number := philosopher.Number
	host := philosopher.Host

	for range [TOTAL_PHILOSOPHERS_CAN_EAT]int{} {

		permisssion := host.GetPermission(number)

		if permisssion {
			philosopher.PrintStarting()
			philosopher.TimeForEating()
			philosopher.PrintFinishing()

			host.UpdateNext(number)
		}
	}

	wg.Done()
	FinishMeal(*philosopher)
}

func (philosopher *Philosopher) TimeForEating() {
	time.Sleep(TIME_PHILOSOPHER_EATING)
}

func (philosopher *Philosopher) PrintStarting() {
	fmt.Printf("Starting to eat %d\n", philosopher.Number)
}

func (philosopher *Philosopher) PrintFinishing() {
	fmt.Printf("Finish eating %d\n\n", philosopher.Number)
}

/*************************************
Inits
/************************************/
func InitHost() *Host {
	index := 0
	listAuthorized := []int{1, 3}

	return &Host{
		OrderCall:        []int{1, 3, 5, 2, 4, 1, 3, 5, 2, 4},
		NextIndex:        &index,
		NumberAuthorized: &listAuthorized,
	}
}

func InitChopsticks() []*Chopsticks {
	list := []*Chopsticks{}

	for count := range [TOTAL_PHILOSOPHERS]int{} {
		list = append(list, &Chopsticks{
			available: true,
			number:    count,
		})
	}

	return list
}

func InitPhilosophers(host *Host, chopsticks []*Chopsticks) []*Philosopher {
	listPhilosopher := []*Philosopher{}

	for count := 0; count < TOTAL_PHILOSOPHERS; count++ {
		ph := Philosopher{Number: count + 1, Host: *host}

		ph.Right = *chopsticks[count]
		ph.Left = *chopsticks[count]

		listPhilosopher = append(listPhilosopher, &ph)
	}

	return listPhilosopher
}

/*************************************
Main
/************************************/
func main() {
	host := InitHost()
	chopsticks := InitChopsticks()
	listPhilosopher := InitPhilosophers(host, chopsticks)

	wg.Add(TOTAL_PHILOSOPHERS)

	for _, ph := range listPhilosopher {
		go ph.eat()
	}

	wg.Wait()
}
