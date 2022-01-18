package org.assertj.graalvm.api;

import java.time.LocalDate;
import java.time.LocalTime;

import org.assertj.core.api.Assertions;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;

import static org.assertj.graalvm.api.ValueAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The test for {@link ValueAssertions}
 */
public class ValueAssertionsTest extends AbstractGraalvmTest {

    @Test
    public void isStringThatTest() {
        assertThat(context.asValue("john doe")).isStringThat().isEqualTo("john doe");
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isStringThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <666> supposed to be <class java.lang.String> but it is not");
    }

    @Test
    public void isBooleanThatTest() {
        assertThat(context.asValue(true)).isBooleanThat().isTrue();
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isBooleanThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <666> supposed to be <class java.lang.Boolean> but it is not");
    }

    @Test
    public void isThrowableThatTest() {
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isThrowableThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <666> supposed to be <class java.lang.Throwable> but it is not");
    }

    @Test
    public void isDoubleThatTest() {
        assertThat(context.asValue(123.456)).isDoubleThat().isEqualTo(123.456);
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue("John Doe"))
                .isDoubleThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <John Doe> supposed to be <double> but it is not");
    }

    @Test
    public void isIntegerThatTest() {
        assertThat(context.asValue(123)).isIntegerThat().isEqualTo(123);
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue("John Doe"))
                .isIntegerThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <John Doe> supposed to be <int> but it is not");
    }

    @Test
    public void isByteThatTest() {
        Value value = evalJavascript("0x40");
        assertThat(value).isByteThat().isEqualTo(value.asByte());
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue("John Doe"))
                .isByteThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <John Doe> supposed to be <byte> but it is not");
    }

    @Test
    public void isFloatThatTest() {
        assertThat(context.asValue(123f)).isFloatThat().isEqualTo(123f);
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue("John Doe"))
                .isFloatThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <John Doe> supposed to be <float> but it is not");
    }

    @Test
    public void isLocalDateThatTest() {
        Value value = evalJavascript("new Date(2020, 0, 2, 3, 4, 5)");
        assertThat(value).isLocalDateThat().isEqualTo(LocalDate.of(2020, 1, 2));
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isLocalDateThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <666> supposed to be <class java.time.LocalDate> but it is not");
    }

    @Test
    public void isLocalTimeThatTest() {
        Value value = evalJavascript("new Date(2020, 0, 2, 3, 4, 5)");
        assertThat(value).isLocalTimeThat().isEqualTo(LocalTime.of(3, 4, 5));
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isLocalTimeThat());
        Assertions.assertThat(exception)
                .hasMessage("The polyglot value <666> supposed to be <class java.time.LocalTime> but it is not");
    }

    @Test
    public void isArrayThatTest() {
        Value value = evalJavascript("['aaa', 'bbb']");
        assertThat(value).isArrayThat().hasSize(2);
        Throwable exception = assertThrows(AssertionError.class, () -> assertThat(context.asValue(666))
                .isArrayThat());
        Assertions.assertThat(exception).hasMessage(
                "The polyglot value <666> supposed to be <class [Lorg.graalvm.polyglot.Value;> but it is not");
    }
}
