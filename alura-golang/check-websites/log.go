package main

import (
	"fmt"
	"os"
)

const FILE_LOGS = "logs.txt"

func LogFile(site string, status bool) {
	fileOptions := os.O_RDWR | os.O_CREATE | os.O_APPEND
	file, err := os.OpenFile(FILE_LOGS, fileOptions, 0666)

	if err != nil {
		fmt.Println(err)
		return
	}

	defer file.Close()

	content := fmt.Sprintf("%s - %s - %t\n",
		GetFormatDateTime(),
		site,
		status,
	)
	file.WriteString(content)
}

func ShowLog() {
	for _, line := range ReadFile(FILE_LOGS) {
		fmt.Println(line)
	}
}
