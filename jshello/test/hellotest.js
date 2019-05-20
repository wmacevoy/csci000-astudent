var assert = require('assert');
var chai = require('chai');
var hello = require('../hello');

var expect = chai.expect;

describe('Hello', function() {
  describe('#message()', function() {
    it('should be "Hello, World!" when with World', function(){
	    let name = "World";
	    let expect = "Hello, World!";
	    let result = hello.helloMessage(name);
	    assert.equal(expect,result);
    });
    it('should be "Hello, Alice!" when with Alice', function(){
	    let name = "Alice";
	    let expect = "Hello, Alice!";
	    let result = hello.helloMessage(name);
	    assert.equal(expect,result);
    });
  });
});
