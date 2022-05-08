# Hello

This makes a greet function that can return greeting messages.

The makefile is fairly generic, and, if you structure your project as.
```
src/*.cpp
include/*.h
tmp/<arch>/*.o - temporary object files
lib/<arch>/*.so - external shared libraries
bin/<arch>/XXXX - programs built from src/main_XXXX.cpp
bin/<arch>/test_XXXX - tests built from src/test_XXXX.cpp
```

Here, <arch> is "$(uname -s)-$(uname -m)" like Darwin-x86_64.  This way the
same folder can support multiple architectures.

You can use make clean/all without modifying Makefile and Makefile.googletest


## Build

```bash
make clean
make all
```

## Test

```bash
./test_all
```

## Run

The "uname" business makes things work even if you have muliple architectures (like with docker below).

```bash
./bin/$(uname -s)-$(uname -m)/hello
```
## Clean-Build-Test Screen Capture

![clean-test-build.png](clean-build-test.png)

## Docker

To create a reproducible build (advanced) and you have docker installed, you can do the following (use a git-bash shell in windows, WSL may work):

```bash
./run test_all # test
./run hello # run
```

## References

1. [GNU make tutorial](https://linuxhint.com/gnu-make-tutorial/)
1. [C++ Google Test](https://github.com/google/googletest)
1. [Google Test Primer](https://www.learncpp.com/cpp-tutorial/89-class-code-and-header-files/)
1. [src/include](https://www.learncpp.com/cpp-tutorial/89-class-code-and-header-files/)


