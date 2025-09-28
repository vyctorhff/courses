package util

import (
	"strconv"
)

func StringToFloat64(str string) float64 {
	result, err := strconv.ParseFloat(str, 64)
	if err != nil {
		return 0.0
	} else {
		return result
	}
}

func StringToFloat32(str string) float32 {
	result, err := strconv.ParseFloat(str, 32)
	if err != nil {
		return 0.0
	} else {
		return float32(result)
	}
}

func StringToInt(str string) int {
	result, err := strconv.Atoi(str)
	if err != nil {
		return 0.0
	} else {
		return result
	}
}
