# Hello

Make a greet function class that can return greeting messages.  The makefile is fairly generic, and, if you structure your project as

```
src/*.cpp
include/*.h
tmp/*.o - temporary object files
lib/*.so - external shared libraries
bin/XXXX - programs built from src/main_XXXX.cpp
bin/test_XXXX - tests built from src/test_XXXX.cpp
```

You can use make clean/all without modifying Makefile and Makefile.googletest


## Build

```bash
make clean
make all
```

## Test

```bash
bin/test_hello
```

## Run

```bash
bin/hello
```

## Clean-Build-Test Screen Capture

![clean-test-build.png](clean-build-test.png)

## References

1. [GNU make tutorial](https://linuxhint.com/gnu-make-tutorial/)
1. [C++ Google Test](https://github.com/google/googletest)
1. [Google Test Primer](https://www.learncpp.com/cpp-tutorial/89-class-code-and-header-files/)
1. [src/include](https://www.learncpp.com/cpp-tutorial/89-class-code-and-header-files/)

