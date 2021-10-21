#include "hello.h"
#include "gtest/gtest.h"

using namespace std;

TEST(Hello, HelloWorld) {
  string expect = "Hello, World!";
  string result = hello("World");
  ASSERT_EQ(expect,result);
}
