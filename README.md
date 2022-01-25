# Graalvm test
This project contains the useful classes which helps to develop unit tests for projects which are using graalvm. 

It consists of three separated libraries:
* [**graalvm-test-mockito**](/graalvm-test-mockito/README.md)
    
   The [mockito](https://site.mockito.org/) matcher which can be used to mock classes and interfaces which has methods accepting graalvm [`Value`](https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html) as a parameter. 
   
* [**graalvm-test-assertj**](/graalvm-test-assertj/README.md)

  The [assertj](https://assertj.github.io/doc/) utilities for fluent assertion of graalvm [`Value`](https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html)

* [**graalvm-test-js**](/graalvm-test-assertj-js/README.md)

  The [assertj](https://assertj.github.io/doc/) utilities for fluent assertion of graalvm [`Value`](https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html) which comes form `javascript` as a hosted language.

## License

See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT). Fell free to fork and contribute.
