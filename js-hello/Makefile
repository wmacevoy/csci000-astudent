NPM ?= $(shell if which npm > /dev/null ; then which npm ; else echo echo to run in container: ./run npm ; fi)

YARN ?= $(shell if which yarn > /dev/null ; then which yarn ; else echo echo to run in container: ./run yarn ; fi)


.PHONY: all
all:
	$(YARN) install --check-files

.PHONY: server
server:
	$(YARN) run server

.PHONY: test
test : all
	$(YARN) run test

.PHONY: clean
	$(YARN) run clean
