package bank

import (
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
)

func Connect() *sql.DB {
	url := fmt.Sprintf("user=%s dbname=%s password=%d host=%s sslmode=%s",
		"usuario",
		"alura-ecommerce",
		123,
		"localhost",
		"disable",
	)

	db, err := sql.Open("postgres", url)
	if err != nil {
		panic(err.Error())
	}

	return db
}
