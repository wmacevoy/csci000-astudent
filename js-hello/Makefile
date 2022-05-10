NPM ?= npm

.PHONY: all
all:
	yarn install --check-files

.PHONY: server
server:
	npm run server

.PHONY: test
test : all
	npm run test
