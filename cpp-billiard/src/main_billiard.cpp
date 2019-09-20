#include <iostream>
#include <iomanip>
#include <string>
#include <math.h>
#include <memory>

// https://open.kattis.com/problems/billiard

struct App;
typedef std::shared_ptr < App > AppPtr;

struct App {
  std::istream &in;
  std::ostream &out;

  int a,b,s,n,m;
  double A,velocity;

  App(std::istream &_in=std::cin, std::ostream &_out=std::cout)
    : in(_in), out(_out), a(0), b(0), s(0), n(0), m(0), A(0.0), velocity(0.0)
  {
  }

    
  void run() {
    while (true) {
      read();
      if (finished()) break;
      solve();
      write();
    }
  }

  void read() {
    in >> a >> b >> s >> m >> n;
  }
    
  void write() {
    out << std::setprecision(2) << std::fixed
	<< A << " " << velocity << std::endl;
  }

  bool finished() {
    return (a == 0 && b == 0 && s == 0 && m == 0 && n == 0);
  }

  void solve() {
    A = (180.0/M_PI)*atan2(n*b,m*a);
    velocity = sqrt(pow(n*b,2)+pow(m*a,2))/((double)s);
  }
};

#if ! defined(TEST_MAIN)
int main(int argc, char *argv[]) {
  AppPtr app = AppPtr (new App() );
  app->run();
  return 0;
}
#endif

