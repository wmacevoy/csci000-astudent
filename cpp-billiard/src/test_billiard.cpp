#include <stdexcept>
#include <sstream>
#include "gtest/gtest.h"

// weird, but kattis wants one source file...
#define TEST_MAIN
#include "main_billiard.cpp"

struct AppTest : ::testing::Test {
  AppPtr ex1() {
    AppPtr app = AppPtr(new App());
    app->a = 100;
    app->b = 100;
    app->s = 1;
    app->m = 1;
    app->n = 1;
    app->velocity = 141.42;
    app->A = 45.00;
    return app;
  }

  AppPtr ex2() {
    AppPtr app = AppPtr(new App());
    app->a = 200;
    app->b = 100;
    app->s = 5;
    app->m = 3;
    app->n = 4;
    app->A = 33.69;
    app->velocity = 144.22;
    return app;
  }
  
  AppPtr ex3() {
    AppPtr app = AppPtr(new App());
    app->a = 201;
    app->b = 132;
    app->s = 48;
    app->m = 1900;
    app->n = 156;
    app->A = 3.09;
    app->velocity = 7967.81;
    return app;
  }
    
  AppPtr ex4() {
    AppPtr app = AppPtr(new App());
    app->a = 10'000;
    app->b = 10'000;
    app->s = 10'000;
    app->m = 10'000;
    app->n = 10'000;
    app->A = 45.00;
    app->velocity = sqrt(2.0)*10'000;
    return app;
        
  }

  AppPtr ex(int k) {
    switch (k) {
    case 1:
      return ex1();
    case 2:
      return ex2();
    case 3:
      return ex3();
    case 4:
      return ex4();
    }
    std::ostringstream msg;
    msg << "k=" << k;
    throw new std::out_of_range(msg.str());
  }

  void assertAppInEquals(AppPtr expect, AppPtr result) {
    ASSERT_EQ(expect->a, result->a);
    ASSERT_EQ(expect->b, result->b);
    ASSERT_EQ(expect->s, result->s);
    ASSERT_EQ(expect->n, result->n);
    ASSERT_EQ(expect->m, result->m);
  }
  
  void assertAppOutEquals(AppPtr expect, AppPtr result, double tolerance) {
    ASSERT_NEAR(expect->A, result->A, tolerance);
    ASSERT_NEAR(expect->velocity, result->velocity, tolerance);
  }

  void testSample(int testcase) {
    AppPtr expected = ex(testcase);
    AppPtr result = ex(testcase);
    result->A = 0;
    result->velocity = 0;
    result->solve();
    assertAppOutEquals(expected, result, 0.01);
  }
  
};


TEST_F(AppTest,Sample1) {
  testSample(1);
}

TEST_F(AppTest,Sample2) {
  testSample(2);
}

TEST_F(AppTest,Sample3) {
  testSample(3);
}

TEST_F(AppTest,Sample4) {
  testSample(4);
}

TEST_F(AppTest,Read3) {
  AppPtr expect = ex3();

  std::istringstream iss("201 132 48 1900 156");
  AppPtr result = AppPtr(new App(iss));
  result->read();
  assertAppInEquals(expect, result);
}

TEST_F(AppTest,Write3) {
  std::ostringstream ossExpect;
  ossExpect << "3.09 7967.81" << std::endl;
  std::string expect = ossExpect.str();

  std::ostringstream ossResult;
  AppPtr app = AppPtr(new App(std::cin,ossResult));
  app->A = 3.09324234;
  app->velocity = 7967.805001;
  app->write();
  std::string result = ossResult.str();
  ASSERT_EQ(expect, result);
}

TEST_F(AppTest,NotFinished) {
  AppPtr app = ex1();
  ASSERT_FALSE(app->finished());
}

TEST_F(AppTest,Finished) {
  AppPtr app = ex1();
  app->a = 0;
  app->b = 0;
  app->s = 0;
  app->n = 0;
  app->m = 0;
  ASSERT_TRUE(app->finished());
}

TEST_F(AppTest,Run) {
  std::ostringstream ossInput;

  ossInput
    << "100 100 1 1 1" << std::endl
    << "200 100 5 3 4" << std::endl
    << "201 132 48 1900 156" << std::endl
    << "0 0 0 0 0" << std::endl;
  std::string input = ossInput.str();
  
  std::ostringstream ossExpect;
  ossExpect
    << "45.00 141.42" << std::endl
    << "33.69 144.22" << std::endl
    << "3.09 7967.81" << std::endl;
  std::string expect = ossExpect.str();
  

  std::istringstream issInput(input);
  std::ostringstream ossOutput;

  AppPtr app = AppPtr(new App(issInput,ossOutput));
  app->run();
  std::string result = ossOutput.str();
  ASSERT_EQ(expect, result);
}

int main(int argc, char** argv) {
  ::testing::InitGoogleTest(&argc, argv);
  return RUN_ALL_TESTS();
}
