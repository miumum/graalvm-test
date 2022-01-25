# Graalvm test
This project contains the useful classes which helps to develop unit tests for projects which are using graalvm. 

It consists of three separated libraries:
* [**graalvm-test-mockito**](/graalvm-test-mockito/README.md)
    
   The <a href="https://site.mockito.org/" target="_blank">mockito</a> matcher which can be used to mock classes and interfaces which has methods accepting graalvm <a href="https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html" target="_blank">`Value`</a> as a parameter. 
   
* [**graalvm-test-assertj**](/graalvm-test-assertj/README.md)

  The <a href="https://assertj.github.io/doc/" target="_blank">assertj</a> utilities for fluent assertion of graalvm <a href="https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html" target="_blank">`Value`</a>

* [**graalvm-test-js**](/graalvm-test-assertj-js/README.md)

  The <a href="https://assertj.github.io/doc/" target="_blank">assertj</a> utilities for fluent assertion of graalvm <a href="https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html" target="_blank">`Value`</a> which comes form `javascript` as a hosted language.

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT). Fell free to fork and contribute.
