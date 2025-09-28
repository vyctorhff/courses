package controller

import (
	"ecommerce/model"
	"ecommerce/util"
	"net/http"
)

func ConvertRequestToProduct(req *http.Request) model.Product {
	p := model.Product{}

	p.Name = req.FormValue("name")
	p.Description = req.FormValue("description")
	p.Price = util.StringToFloat32(req.FormValue("price"))
	p.Amount = util.StringToInt(req.FormValue("amount"))

	return p
}

func PreparNew(resp http.ResponseWriter, req *http.Request) {
	GetTemplates().ExecuteTemplate(resp, "Product-new", nil)
}

func New(resp http.ResponseWriter, req *http.Request) {
	if req.Method != "POST" {
		return
	}

	model.New(ConvertRequestToProduct(req))
	NavHomeSuccess(resp, req)
}

func Delete(resp http.ResponseWriter, req *http.Request) {
	id := req.URL.Query().Get("id")
	model.Delete(util.StringToInt(id))

	NavHomeSuccess(resp, req)
}

func PreparEdit(resp http.ResponseWriter, req *http.Request) {
	id := req.URL.Query().Get("id")

	product := model.SearchById(util.StringToInt(id))
	GetTemplates().ExecuteTemplate(resp, "Product-edit", product)
}

func Edit(resp http.ResponseWriter, req *http.Request) {
	product := ConvertRequestToProduct(req)
	model.Edit(product)

	NavHomeSuccess(resp, req)
}

func NavHomeSuccess(resp http.ResponseWriter, req *http.Request) {
	http.Redirect(resp, req, "/", 301)
}

func NavHome(resp http.ResponseWriter, req *http.Request, status int) {
	http.Redirect(resp, req, "/", status)
}
