import { assert, expect, should } from 'chai';
import {hello} from '../hello.mjs'

describe('Hello', function() {
  describe('#message()', function() {
    it('should be "Hello, World!" when with World', function(){
	    let name = "World";
	    let expect = "Hello, World!";
	    let result = hello(name);
	    assert.equal(expect,result);
    });
    it('should be "Hello, Alice!" when with Alice', function(){
	    let name = "Alice";
	    let expect = "Hello, Alice!";
	    let result = hello(name);
	    assert.equal(expect,result);
    });
  });
});
