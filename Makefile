version := $(shell mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
hash := $(shell git rev-parse --short HEAD)
snapshot = SNAPSHOT
imageName :=
appName := demo-springboot-retryable

ifeq ($(patsubst %$(snapshot),,$(lastword $(version))),)
	imageName := "$(appName):$(version)-$(hash)"
else
	imageName := "$(appName):$(version)"
endif

.DEFAULT_GOAL := help
.PHONY: help
help: ## Affiche cette aide
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: clean
clean:
	@echo "Image name : $(imageName)"
	mvn clean

.PHONY: install
install: clean
	mvn install

.PHONY: build-image
build-image: pom.xml install
	eval $(minikube docker-env)
	mvn jib:buildTar -Dimage=$(appName)
	docker load -i target/jib-image.tar
	docker tag $(appName) $(imageName)
	docker rmi $(appName)
