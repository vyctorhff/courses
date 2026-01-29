package model

import (
	"ecommerce/bank"
)

type Product struct {
	Id          int32
	Name        string
	Description string
	Price       float32
	Amount      int
}

func SearchById(id int) Product {
	database := bank.Connect()
	defer database.Close()

	query := "select * from product where id = $1"
	selectProducts, err := database.Query(query, id)
	if err != nil {
		panic(err.Error())
	}

	p := Product{}
	for selectProducts.Next() {
		err := selectProducts.Scan(&p.Id, &p.Name, &p.Description, &p.Price, &p.Amount)
		if err != nil {
			panic(err.Error())
		}
	}

	return p
}

func SearchProducts() []Product {
	database := bank.Connect()
	defer database.Close()

	query := "select * from product"
	selectProducts, err := database.Query(query)
	if err != nil {
		panic(err.Error())
	}

	products := []Product{}

	for selectProducts.Next() {
		p := Product{}

		err := selectProducts.Scan(&p.Id, &p.Name, &p.Description, &p.Price, &p.Amount)
		if err != nil {
			panic(err.Error())
		}

		products = append(products, p)
	}

	return products
}

func New(product Product) {
	database := bank.Connect()
	defer database.Close()

	query := "insert into product (name, description, price, amount) values($1, $2, $3, $4)"
	insert, err := database.Prepare(query)
	if err != nil {
		panic(err.Error())
	}

	insert.Exec(
		product.Name,
		product.Description,
		product.Price,
		product.Amount,
	)
}

func Delete(id int) {
	database := bank.Connect()
	defer database.Close()

	query := "delete from product where id = $1"
	delete, err := database.Prepare(query)
	if err != nil {
		panic(err.Error())
	}

	delete.Exec(id)
}

func Edit(product Product) {
	database := bank.Connect()
	defer database.Close()

	query := "update product set name=$1, description=$2, price=$3, amount=$4 where id=$5"
	update, err := database.Prepare(query)
	if err != nil {
		panic(err.Error())
	}

	update.Exec(
		product.Name,
		product.Description,
		product.Price,
		product.Amount,
		product.Id,
	)
}
