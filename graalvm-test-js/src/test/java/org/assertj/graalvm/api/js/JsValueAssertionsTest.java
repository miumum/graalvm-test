package org.assertj.graalvm.api.js;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The test for {@link JsValueAssertions}
 */
class JsValueAssertionsTest extends AbstractGraalvmTest {

    @Test
    public void isJsDateThatTest() {
        Value value = evalJavascript("new Date(2020, 0, 2, 0, 0, 0)");
        JsValueAssertions.assertThat(value)
                .isJsDateThat()
                .isEqualTo(LocalDateTime.of(2020, 1, 2, 0, 0, 0))
                .isEqualTo(LocalDate.of(2020, 1, 2));
    }

    @Test
    public void isJsDateThatNotJsDateTest() {
        Throwable exception = Assertions.assertThrows(AssertionError.class, () -> {
            Value value = evalJavascript("'some string'");
            JsValueAssertions.assertThat(value).isJsDateThat();
        });
        assertThat(exception).hasMessageContaining("the value 'some string' not a javascript date");
    }

}
