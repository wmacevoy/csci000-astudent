# Hello

Make a hello function that can return greeting messages.  The project structure is to have the functionality in the `hello` directory, and the corresponding tests in `hellotest`

```
*.js
```

## Test

```bash
test/*test.js
```
## Clean-Build-Test Screen Capture

![run-test.png](run-test.png)

## Docker

To create a reproducible build (advanced) and you have docker installed, you can do the following:

```bash
docker build -t js-hello .
docker run -i --rm -t js-hello npm test # test
docker run -i --rm -t js-hello node main.js # run
```

## References

1. [Python Unit tests](https://docs.python.org/2/library/unittest.html)


