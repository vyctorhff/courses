package main

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestGetDateTime(t *testing.T) {
	datetime := GetDateTime()
	assert.NotNil(t, datetime)
}

func TestGetFormatDateTimePtBR(t *testing.T) {
	datetime := GetFormatDateTime()
	assert.NotNil(t, datetime)
}

func TestReadFile(t *testing.T) {
	content := ReadFile("sites.txt")
	assert.NotNil(t, content)
	assert.True(t, len(content) >= 2)
}
