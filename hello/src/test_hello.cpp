#include "hello.h"
#include "gtest/gtest.h"

using namespace std;

TEST(Hello, HelloWorld) {
  string expect = "Hello, World!";
  string result = hello("World");
  ASSERT_EQ(expect,result);
}

int main(int argc, char** argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
