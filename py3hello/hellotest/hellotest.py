#!/usr/bin/env python

import inspect,os,sys,unittest

currentdir = os.path.dirname(os.path.abspath(inspect.getfile(inspect.currentframe())))
parentdir = os.path.dirname(currentdir)

sys.path.insert(0,parentdir+"/hello")

from hello import helloMessage

class HelloTest(unittest.TestCase):
    def testHelloWorld(self):
        name = "World"
        expect = "Hello, World!"
        result = helloMessage(name)
        assert expect == result

    def testHelloAlice(self):
        name = "Alice"
        expect = "Hello, Alice!"
        result = helloMessage(name)
        assert expect == result

if __name__ == '__main__':
    unittest.main()
