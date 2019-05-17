#include "hello.h"

std::string hello(std::string name)
{
  std::string message;
  message += "Hello, ";
  message += name;
  message += "!";
  return message;
}
