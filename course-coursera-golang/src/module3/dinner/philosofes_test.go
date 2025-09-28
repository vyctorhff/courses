package main

import (
	//"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

/*************************************
Util
/************************************/
func TestNextTwo(t *testing.T) {
	list := []int{1, 2, 3, 4, 5, 6, 7, 8}

	next, error := NextTwo(list, 2)
	assert.Equal(t, next[0], 3)
	assert.Equal(t, next[1], 4)

	next, _ = NextTwo(list, 6)
	assert.Equal(t, next[0], 7)
	assert.Equal(t, next[1], 8)

	next, error = NextTwo(list, 8)
	assert.True(t, error, "limited excidity")
}

func TestDeleteNumberRepeted(t *testing.T) {
}

/*************************************
Testing for Inits
/************************************/
func TestInitHost(t *testing.T) {
	host := InitHost()

	if *host.NextIndex != 0 {
		t.Error("Index not zero")
	}

	list := *host.NumberAuthorized

	if list[0] != 1 && list[1] != 3 {
		t.Error("initial authorized number error")
	}

	listOrder := []int{1, 3, 5, 2, 4, 1, 3, 5, 2, 4}
	for index, _ := range listOrder {
		if host.OrderCall[index] != listOrder[index] {
			t.Error("initial order list not set propertily")
		}
	}
}

func TestInitChopsticks(t *testing.T) {
	list := InitChopsticks()

	assert.Equal(t, 5, len(list))

	for key, chopstick := range list {
		assert.Equal(t, chopstick.number, key)
		assert.True(t, chopstick.available)
	}
}

/*************************************
Testing for Host
/************************************/
func TestGetPermission(t *testing.T) {
	host := InitHost()
	assert.True(t, host.GetPermission(1), "Initial permission not granted")
}

func TestNextPair(t *testing.T) {
	host := InitHost()

	list := *host.NextPair()
	assertPair(t, host, list, 2, 5, 2)

	list = *host.NextPair()
	assertPair(t, host, list, 4, 4, 1)

	list = *host.NextPair()
	assertPair(t, host, list, 6, 3, 5)

	list = *host.NextPair()
	assertPair(t, host, list, 8, 2, 4)

	// return to the beginning
	list = *host.NextPair()
	assertPair(t, host, list, 0, 1, 3)

	list = *host.NextPair()
	assertPair(t, host, list, 2, 5, 2)
}

func assertPair(t *testing.T, host *Host, list []int, index, p1, p2 int) {
	assert.Equal(t, index, *host.NextIndex)
	assert.Equal(t, p1, list[0])
	assert.Equal(t, p2, list[1])
}

func TestUpdateNextWhenListEmpty(t *testing.T) {
	host := InitHost()

	*host.NumberAuthorized = []int{}
	*host.NextIndex = 0

	host.UpdateNext(0)
	list := *host.NumberAuthorized

	assert.True(t, len(list) == 2)
	assert.Equal(t, list[0], 5)
	assert.Equal(t, list[1], 2)
}

func TestUpdateNextWhenListIsNotEmptyAndNumberIncluded(t *testing.T) {
	host := InitHost()

	host.UpdateNext(1)
	list := *host.NumberAuthorized

	assert.Equal(t, 1, len(list))
	assert.Equal(t, list[0], 3)

	host.UpdateNext(3)
	list = *host.NumberAuthorized

	assert.Equal(t, 2, len(list))
	assert.Equal(t, list[0], 5)
	assert.Equal(t, list[1], 2)
}

func TestUpdateNextWhenListNotEmptyAndNumberNotIncluded(t *testing.T) {
	host := InitHost()

	host.UpdateNext(2)
	list := *host.NumberAuthorized

	assert.Equal(t, 2, len(list))
	assert.Equal(t, 1, list[0])
	assert.Equal(t, 3, list[1])
}

func TestUpdateNextCompleteCyclo(t *testing.T) {
	host := InitHost()

	host.UpdateNext(1)
	// TODO: continue....
}
