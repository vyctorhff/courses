package main

import (
	"ecommerce/route"
	"fmt"
	"net/http"
)

func main() {
	route.Init()

	fmt.Println("Server initialize")
	http.ListenAndServe(":8000", nil)
}
