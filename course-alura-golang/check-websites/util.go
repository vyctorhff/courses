package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
	"time"
)

func GetDateTime() time.Time {
	return time.Now()
}

func GetFormatDateTime() string {
	datetime := GetDateTime()
	return datetime.Format("2006/01/02 15:04:05")
}

func ReadFile(fileName string) []string {
	result := []string{}

	file, err := os.Open(fileName)
	if err != nil {
		fmt.Println(err)
		return result
	}

	defer file.Close()
	reader := bufio.NewReader(file)

	for {
		line, err := reader.ReadString('\n')
		if err == io.EOF {
			break
		}

		line = strings.TrimSpace(line)
		result = append(result, line)
	}

	return result
}
