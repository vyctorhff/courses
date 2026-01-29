package route

import (
	"ecommerce/controller"
	"net/http"
)

const FILES_STATIC = "templates/static"

func Init() {
	ServingStatic()

	Routes()
}

func ServingStatic() {
	fs := http.FileServer(http.Dir(FILES_STATIC))
	http.Handle("/static/", http.StripPrefix("/static/", fs))
}

func Routes() {
	http.HandleFunc("/", controller.Index)
	http.HandleFunc("/product/prepare-new", controller.PreparNew)
	http.HandleFunc("/product/new", controller.New)
	http.HandleFunc("/product/delete", controller.Delete)
	http.HandleFunc("/product/prepare-edit", controller.PreparEdit)
	http.HandleFunc("/product/edit", controller.Edit)
}
