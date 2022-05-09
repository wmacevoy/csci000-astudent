#
# Generic gcc/llvm c++ compiler wrapper for make in osx/linux/wsl
# this is a fairly complex makefile, but it is made to
#
#    Be fairly generic, so you probably only want to change flags
#    Allow the use of google test and google mock library in unit tests.
#
#    For your code:
#
#       Your header (.h) files should be in include
#       Your source (.cpp) files should be in src
#       src/main_xxxx.cpp is the main program for bin/xxxx
#       src/test_xxxx.cpp is the test program for bin/test_xxxx
#
#    You may want to change CXXFLAGS for other compile time options
#    Always rebuild everything after a flag change:
#
#       make clean all
#
#    Typical targets:
#
#	make bin/hello
#       make bin/test_hello
#       make clean
#       make all
#       make tests
#       make mains

# system-machine specifications
UNAME_S := $(shell uname -s)
UNAME_M := $(shell uname -m)
OS_ARCH ?= $(UNAME_S)-$(UNAME_M)

# architecture-dependent build tmp/bin/lib directories

BIN := bin/$(OS_ARCH)
TMP := tmp/$(OS_ARCH)
LIB := lib/$(OS_ARCH)

# available standards
CXXSTDS := g++20 c++20 g++2a c++2a g++17 c++17

# choose first CXXSTD that the compiler accepts
CXXSTD := $(shell for std in $(CXXSTDS) ; do if echo "void test();" | $(CXX) -x c++ -E -std=$$std - >/dev/null 2>&1 ; then echo $$std ; break ; fi ; done)

CXXFLAGS:=-pthread -Iinclude -g -std=${CXXSTD} -fPIC

include Makefile.googletest

#
# find options are different on darwin
#
ifeq "$(UNAME_S)" "Darwin"
  FIND_SRC ?= find -E src
else
  FIND_SRC ?= find src -regextype posix-extended
endif

# all not-main and not-test source files in the src folder
CPP_PARTS:=$(shell $(FIND_SRC) -iregex '[a-z0-9].*\.(cpp)' -and -not \( -name '.*' -or -name 'main_*' -or -name 'test_*' \) )
C_PARTS:=$(shell $(FIND_SRC) -iregex '[a-z0-9].*\.(c)' -and -not \( -name '.*' -or -name 'main_*' -or -name 'test_*' \) )

# src/XXXX.cpp -> tmp/XXXX.cpp.o object files
CPP_PARTO:=$(patsubst src/%.cpp,$(TMP)/%.cpp.o,$(CPP_PARTS))
C_PARTO:=$(patsubst src/%.cpp,$(TMP)/%.c.o,$(C_PARTS))
PARTO:=$(CPP_PARTO) $(C_PARTO)

# program main source files in src
CPP_MAINS:=$(shell find src -name 'main_*.cpp')
C_MAINS:=$(shell find src -name 'main_*.c')

# src/main_XXXX.cpp -> bin/XXXX executables
CPP_MAINX:=$(patsubst src/main_%.cpp,$(BIN)/%,$(CPP_MAINS))
C_MAINX:=$(patsubst src/main_%.cpp,$(BIN)/%,$(C_MAINS))
CPP_MAINZ:=$(patsubst src/main_%.cpp,bin/%,$(CPP_MAINS))
C_MAINZ:=$(patsubst src/main_%.cpp,bin/%,$(C_MAINS))

MAINX := $(CPP_MAINX) $(C_MAINX)
MAINZ := $(CPP_MAINZ) $(C_MAINZ)
ALL += $(MAINX) $(MAINZ)

# test source files in src
CPP_TESTS:=$(shell find src -name 'test_*.cpp')
C_TESTS:=$(shell find src -name 'test_*.c')

# src/test_XXXX.cpp -> bin/test_XXXX test executables
CPP_TESTX:=$(patsubst src/test_%.cpp,$(BIN)/test_%,$(CPP_TESTS))
C_TESTX:=$(patsubst src/test_%.c,$(BIN)/test_%,$(C_TESTS))
CPP_TESTZ:=$(patsubst src/test_%.cpp,bin/test_%,$(CPP_TESTS))
C_TESTZ:=$(patsubst src/test_%.c,bin/test_%,$(C_TESTS))

TESTX = $(CPP_TESTX) $(C_TESTX)
TESTZ = $(CPP_TESTZ) $(C_TESTZ)
ALL += $(TESTX) $(TESTZ)

.PHONY: all
all : googletest mains tests

.PHONY: mains
mains : $(MAINX) $(MAINZ)

.PHONY: tests
tests : $(TESTX) $(TESTZ)

.PHONY: test
test:
	./test_all

.PHONY: clean
clean :
	/bin/rm -rf $(TMP)/* $(BIN)/* $(LIB)/*.so
	find . \( -name '*~' -or -name '*#' -or -name '.#*' \) -print0 | xargs -0 /bin/rm -rf

# use compiler to automatically discover #include - dependencies... for test
$(TMP)/test_%.cpp.d: src/test_%.cpp $(GOOGLE_TEST_SRC) $(GOOGLE_MOCK_SRC)
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) $(GOOGLE_TEST_FLAGS) -MM -MT '$(patsubst src/%.cpp,$(TMP)/%.cpp.o,$<)' $< > $@

# use compiler to automatically discover #include - dependencies... for test
$(TMP)/test_%.c.d: src/test_%.c $(GOOGLE_TEST_SRC) $(GOOGLE_MOCK_SRC)
	mkdir -p `dirname $@`
	$(CC) $(CFLAGS) $(GOOGLE_TEST_FLAGS) -MM -MT '$(patsubst src/%.c,$(TMP)/%.c.o,$<)' $< > $@

# use compiler to automatically discover #include - dependencies...
$(TMP)/%.cpp.d: src/%.cpp
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) -MM -MT '$(patsubst src/%.cpp,$(TMP)/%.cpp.o,$<)' $< > $@

# use compiler to automatically discover #include - dependencies...
$(TMP)/%.c.d: src/%.c 
	mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -MM -MT '$(patsubst src/%.c,$(TMP)/%.c.o,$<)' $< > $@

# use (and maybe update) dependency rules unless I am cleaning or making googletests
ifneq (clean,$(MAKECMDGOALS))
ifneq (googletest,$(MAKECMDGOALS))
-include $(patsubst src/%.cpp,$(TMP)/%.cpp.d,$(CPP_PARTS) $(CPP_MAINS) $(CPP_TESTS))
-include $(patsubst src/%.c,$(TMP)/%.c.d,$(C_PARTS) $(C_MAINS) $(C_TESTS))
endif
endif

# generic compile a c++ test file rule
$(TMP)/test_%.cpp.o: src/test_%.cpp $(GOOGLE_TEST_SRC) $(GOOGLE_MOCK_SRC)
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) $(GOOGLE_TEST_FLAGS) -c -o $@ $<

# generic compile a c test file rule
$(TMP)/test_%.c.o: src/test_%.c $(GOOGLE_TEST_SRC) $(GOOGLE_MOCK_SRC)
	mkdir -p `dirname $@`
	$(CC) $(CFLAGS) $(GOOGLE_TEST_FLAGS) -c -o $@ $<

# generic compile a c++ source file rule
$(TMP)/%.cpp.o: src/%.cpp
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) -c -o $@ $<

# generic compile a c source file rule
$(TMP)/%.c.o: src/%.c
	mkdir -p `dirname $@`
	$(CC) $(CFLAGS) -c -o $@ $<

# generic link an executable rule
$(BIN)/% : $(TMP)/main_%.cpp.o $(PARTO) 
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) -o $@ $^ $(LDFLAGS)

# generic link an executable rule
$(BIN)/% : $(TMP)/main_%.c.o $(PARTO)
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) -o $@ $< $(PARTO) $(LDFLAGS)

# generic link a test executable rule
$(BIN)/test_% : $(TMP)/test_%.cpp.o $(PARTO) $(GOOGLE_TEST_LIBS) $(GOOGLE_MAIN_LIBS)
	mkdir -p `dirname $@`
	$(CXX) $(CXXFLAGS) $(GOOGLE_TEST_FLAGS) -o $@ $< $(PARTO) $(LDFLAGS) `nm -g -P $< | egrep "^_?main T" >/dev/null || echo $(GOOGLE_MAIN_LIBS)` $(GOOGLE_TEST_LIBS)

# architecture indepdendent script to run architecture dependent executuable
bin/% : $(BIN)/%
	echo '#!/bin/bash\nexec "$$( cd -- "$$( dirname -- "$${BASH_SOURCE[0]}" )" &> /dev/null && pwd )/$$(uname -s)-$$(uname -m)/$*" "$$@"\n' > bin/"$*"
	chmod +x bin/"$*"

