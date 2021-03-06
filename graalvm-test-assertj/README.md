# graalvm-test-assertj

The <a href="https://assertj.github.io/doc/" target="_blank">assertj</a> utilities for fluent assertion of
graalvm <a href="https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html" target="_blank">`Value`</a>

## Set up dependency

```xml
<dependency>
    <groupId>com.github.miumum</groupId>
    <artifactId>graalvm-test-assertj</artifactId>
    <version>1.0</version>
</dependency>
```

## Usage

```java
public class SomeTest {
    
    @Test
    public void isArrayThatTest() {
        var context = Context.newBuilder(JavaScriptLanguage.ID)
                .timeZone(ZoneOffset.UTC)
                .build();
        
        var value = context.eval(JavaScriptLanguage.ID, "['aaa', 'bbb']");        
        ValueAssertions.assertThat(value).isArrayThat().hasSize(2);
        
        var exception = Assertions.assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isArrayThat());
        Assertions.assertThat(exception).hasMessage(
                "The polyglot value <666> supposed to be <class [Lorg.graalvm.polyglot.Value;> but it is not");

        context.close();
    }
}
```
