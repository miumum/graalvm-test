package org.mockito.graalvm;

import java.util.regex.Pattern;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ValueMatchersTest {

    public static class MockedClass {
        @SuppressWarnings("unused")
        void testMethod(Value ignored) {
        }
    }

    private static MockedClass mockedClass;

    @BeforeEach
    void beforeEach() {
        mockedClass = Mockito.spy(MockedClass.class);
    }

    @Test
    void eqStringValue() {
        mockedClass.testMethod(Value.asValue("something"));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqString("something"));
    }

    @Test
    void eqStringNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqString(null));
    }

    @Test
    void eqIntValue() {
        mockedClass.testMethod(Value.asValue(123));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqInt(123));
    }

    @Test
    void eqIntNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqInt(null));
    }

    @Test
    void eqBooleanValue() {
        mockedClass.testMethod(Value.asValue(true));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqBoolean(true));
    }

    @Test
    void eqBooleanNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqBoolean(null));
    }

    @Test
    void eqDoubleValue() {
        mockedClass.testMethod(Value.asValue(123d));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqDouble(123d));
    }

    @Test
    void eqDoubleNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.eqDouble(null));
    }

    @Test
    void matchesValue() {
        mockedClass.testMethod(Value.asValue("88ebay999"));
        verify(mockedClass).testMethod(ValueMatchers.matches(Pattern.compile("88[a-z]{4}999")));
    }

    @Test
    void matchesNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, never()).testMethod(ValueMatchers.matches(Pattern.compile(".*")));
    }

    @Test
    void eqNull() {
        mockedClass.testMethod(Value.asValue(null));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.isNull());
    }

    @Test
    void eqNotNull() {
        mockedClass.testMethod(Value.asValue("something"));
        verify(mockedClass, times(1)).testMethod(ValueMatchers.isNotNull());
    }
}