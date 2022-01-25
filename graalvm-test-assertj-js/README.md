# graalvm-test-assertj

The [assertj](https://assertj.github.io/doc/) utilities for fluent assertion of
graalvm [`Value`](https://www.graalvm.org/sdk/javadoc/org/graalvm/polyglot/Value.html) 
representing the evaluation result of javascrit as a hosted language.

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
        
        var value = context.eval(JavaScriptLanguage.ID, "new Date(2020, 0, 2, 0, 0, 0)"); 
        JsValueAssertions.assertThat(value)
                .isJsDateThat()
                .isEqualTo(LocalDateTime.of(2020, 1, 2, 0, 0, 0))
                .isEqualTo(LocalDate.of(2020, 1, 2));

        context.close();
    }
}
```
