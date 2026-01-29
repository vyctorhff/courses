package main

import ()

const FILE_SITES = "sites.txt"

func GetSites() []string {
	return ReadFile(FILE_SITES)
}
