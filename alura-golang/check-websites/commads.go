package main

import (
	"fmt"
	"net/http"
)

func HandleCommand(command int) {
	switch command {
	case 1:
		Check()
	case 2:
		ShowLog()
	default:
		fmt.Println("Option not supported")
	}
}

func Check() {
	for _, site := range GetSites() {
		CheckSite(site)
	}
}

func CheckSite(site string) {
	resp, _ := http.Get(site)
	fmt.Print(site)

	if resp.StatusCode == 200 {
		fmt.Println(".......OK")
		LogFile(site, true)
	} else {
		fmt.Println(".......FAIL")
		LogFile(site, false)
	}
}
