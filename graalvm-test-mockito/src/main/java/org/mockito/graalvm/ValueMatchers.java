package org.mockito.graalvm;

import java.util.regex.Pattern;

import org.graalvm.polyglot.Value;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static java.lang.String.format;

/**
 * The utility class for Argument matchers for {@link Value}.
 *
 * @see Mockito#argThat(ArgumentMatcher)
 */
public class ValueMatchers {

    private ValueMatchers() {
    }

    /**
     * Verifies if value fulfils provided matcher
     *
     * @param matcher matcher to be fulfilled
     * @return <code>null</code>
     */
    public static Value argThat(ArgumentMatcher<Value> matcher) {
        return ArgumentMatchers.argThat(matcher);
    }

    /**
     * Verifies if string {@link Value} is equal to provided {@link String}
     *
     * @param expected expected value
     * @return <code>null</code>
     */
    public static Value eqString(String expected) {
        ArgumentMatcher<Value> valueMatcher;
        if (expected == null) {
            valueMatcher = new ValueMatcher("null", "eqString", Value::isNull);
        } else {
            valueMatcher = new ValueMatcher("\"" + expected + "\"", "eqString", arg -> expected.equals(arg.asString()));
        }
        return argThat(valueMatcher);
    }

    /**
     * Verifies if integer {@link Value} is equal to provided {@link Integer}
     *
     * @param expected expected value
     * @return <code>null</code>
     */
    public static Value eqInt(Integer expected) {
        ArgumentMatcher<Value> valueMatcher;
        if (expected == null) {
            valueMatcher = new ValueMatcher("null", "eqInt", Value::isNull);
        } else {
            valueMatcher = new ValueMatcher(expected.toString(), "eqInt", arg -> expected.equals(arg.asInt()));
        }
        return argThat(valueMatcher);
    }

    /**
     * Verifies if boolean {@link Value} is equal to provided {@link Boolean}
     *
     * @param expected expected value
     * @return <code>null</code>
     */
    public static Value eqBoolean(Boolean expected) {
        ArgumentMatcher<Value> valueMatcher;
        if (expected == null) {
            valueMatcher = new ValueMatcher("null", "eqBoolean", Value::isNull);
        } else {
            valueMatcher = new ValueMatcher(expected.toString(), "eqBoolean", arg -> expected.equals(arg.asBoolean()));
        }
        return argThat(valueMatcher);
    }

    /**
     * Verifies if double {@link Value} is equal to provided {@link Double}
     *
     * @param expected expected value
     * @return <code>null</code>
     */
    public static Value eqDouble(Double expected) {
        ArgumentMatcher<Value> valueMatcher;
        if (expected == null) {
            valueMatcher = new ValueMatcher("null", "eqDouble", Value::isNull);
        } else {
            valueMatcher = new ValueMatcher(expected.toString(), "eqDouble", arg -> expected.equals(arg.asDouble()));
        }
        return argThat(valueMatcher);
    }

    /**
     * Verifies if string {@link Value} <b>matches</b> to provided {@link Pattern}
     *
     * @return <code>null</code>
     */
    public static Value matches(Pattern pattern) {
        ValueMatcher valueMatcher = new ValueMatcher("matches",
                arg -> !arg.isNull() && pattern.matcher(arg.asString()).matches());
        return argThat(valueMatcher);
    }

    /**
     * Verifies if provided {@link Value} is <code>null</code>
     *
     * @return <code>null</code>
     */
    public static Value isNull() {
        ValueMatcher valueMatcher = new ValueMatcher("isNull", Value::isNull);
        return argThat(valueMatcher);
    }

    /**
     * Verifies if provided {@link Value} is <b>not</b> <code>null</code>
     *
     * @return <code>null</code>
     */
    public static Value isNotNull() {
        ValueMatcher valueMatcher = new ValueMatcher("isNotNull", arg -> !arg.isNull());
        return argThat(valueMatcher);
    }

    /**
     * The helper class to create {@link ArgumentMatcher} with toString method
     */
    private static class ValueMatcher implements ArgumentMatcher<Value> {

        private final String wanted;
        private final String name;
        private final ArgumentMatcher<Value> matcher;

        private ValueMatcher(String name, ArgumentMatcher<Value> matcher) {
            this("", name, matcher);
        }

        private ValueMatcher(String wanted, String name, ArgumentMatcher<Value> matcher) {
            this.wanted = wanted;
            this.name = name;
            this.matcher = matcher;
        }

        @Override
        public String toString() {
            return format("%s(%s)", name, wanted);
        }

        @Override
        public boolean matches(Value argument) {
            return matcher.matches(argument);
        }
    }
}
