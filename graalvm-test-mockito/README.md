# graalvm-test-mockito

The [mockito](https://site.mockito.org/) matcher which can be used to mock classes and interfaces which has methods
accepting graalvm [`Value`](https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html) as a parameter.

## Set up dependency

```xml
<dependency>
    <groupId>com.github.miumum</groupId>
    <artifactId>graalvm-test-mockito</artifactId>
    <version>1.0</version>
</dependency>
```

## Usage
Imagine you have the class or interface you want to mock with a method which consumes an `org.graalvm.polyglot.Value` as a parameter.
```java
/**
 * The class with method which can be mocked and execution verified using mockito
 */
public class MockedClass {
    void testMethod(Value ignored) {
        //...
    }
}
```

You can mock it or verify the execution using the `org.mockito.graalvm.ValueMatchers`
```java
public class SomeTest {
    @Test
    void matchesValue() {
        MockedClass mockedClass = Mockito.spy(MockedClass.class);
        mockedClass.testMethod(Value.asValue("88ebay999"));
        verify(mockedClass).testMethod(ValueMatchers.matches(Pattern.compile("88[a-z]{4}999")));
    }
}
```
