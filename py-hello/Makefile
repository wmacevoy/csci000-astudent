PYTHON3 ?= $(shell if which python3 > /dev/null ; then which python3 ; else echo echo to run in container: ./run python3 ; fi)

.PHONY: all
all :
	echo "Python is a scripting language."

.PHONY: run
run:
	$(PYTHON3) hello/hello.py

.PHONY: test
test :
	$(PYTHON3) hellotest/hellotest.py

.PHONY: clean
	echo "Python is a scripting language."
