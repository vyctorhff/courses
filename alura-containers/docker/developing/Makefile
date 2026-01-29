
path := $(shell pwd)
network := --network minha-rede

##########################################################
# ALL
upall: upmongo upredis upapp

downall: downapp downredis downmongo

##########################################################
# APP

app_port 	:= 4000:4000
app_volume 	:= $(path)/app:/var/www/app
app_workdir := /var/www/app
app_cmd 	:= npm run dev

preperapp:
	docker run -d -p $(app_port) -v $(app_volume) --name myapp -w $(app_workdir) $(network) node npm run dev

upapp:
	docker start myapp

downapp:
	docker stop myapp

##########################################################
# MONGO
prepermongo:
	docker run -d -p 27017:27017 --name mymongo $(network) mongo

upmongo:
	docker start mymongo

downmongo:
	docker stop mymongo

##########################################################
# REDIS

redis_ports := 6379:6379

upredis:
	docker run -d -p $(redis_ports) --name myredis $(network) redis

downredis:
	docker stop myredis

##########################################################
# DOCKER NETWORK
createNetwork:
	docker network create --driver bridge minha-rede
