package controller

import (
	"ecommerce/model"
	"net/http"
)

func Index(resp http.ResponseWriter, req *http.Request) {
	produtos := model.SearchProducts()
	GetTemplates().ExecuteTemplate(resp, "Index", produtos)
}
