MAVEN ?= $(shell if which mvn > /dev/null ; then which mvn ; else echo echo to run in container: ./run mvn ; fi)

.PHONY: all
all :
	$(MAVEN) compile

.PHONY: test
test :
	$(MAVEN) test

.PHONY: clean
	$(MAVEN) clean
