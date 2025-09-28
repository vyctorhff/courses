package controller

import (
	"html/template"
)

const FILES_TEMPLATE = "templates/*.html"

var templates *template.Template

func GetTemplates() *template.Template {
	ll, err := template.ParseGlob(FILES_TEMPLATE)
	return template.Must(ll, err)
}
