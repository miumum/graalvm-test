package org.assertj.graalvm.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Predicate;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.AbstractByteAssert;
import org.assertj.core.api.AbstractDoubleAssert;
import org.assertj.core.api.AbstractFloatAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractLocalDateAssert;
import org.assertj.core.api.AbstractLocalTimeAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ObjectArrayAssert;
import org.graalvm.polyglot.Value;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assertion methods for a {@link Value} assuming the {@link Value} represents polyglot (any) guest language.
 * <p>
 * To create an instance of this class, invoke
 * <code>
 * {@link ValueAssertions ValueAssertions}{@link ValueAssertions#assertThat(Value) .assertThat(value)}
 * </code>
 * </p>
 *
 * @see AbstractAssert
 */
public class ValueAssert extends AbstractAssert<ValueAssert, Value> {

    public ValueAssert(Value value) {
        super(value, ValueAssert.class);
    }


    public AbstractStringAssert<?> isStringThat() {
        validateValueType(String.class, Value::isString);
        return assertThat(actual.asString());
    }

    public AbstractBooleanAssert<?> isBooleanThat() {
        validateValueType(Boolean.class, Value::isBoolean);
        return assertThat(actual.asBoolean());
    }

    @SuppressWarnings({"UnusedReturnValue"})
    public AbstractThrowableAssert<?, ? extends Throwable> isThrowableThat() {
        validateValueType(Throwable.class, Value::isException);
        return assertThat(actual.as(Throwable.class));
    }

    public AbstractDoubleAssert<?> isDoubleThat() {
        validateValueType(Double.TYPE, Value::fitsInDouble);
        return assertThat(actual.asDouble());
    }

    public AbstractIntegerAssert<?> isIntegerThat() {
        validateValueType(Integer.TYPE, Value::fitsInInt);
        return assertThat(actual.asInt());
    }

    public AbstractByteAssert<?> isByteThat() {
        validateValueType(Byte.TYPE, Value::fitsInByte);
        return assertThat(actual.asByte());
    }

    public AbstractFloatAssert<?> isFloatThat() {
        validateValueType(Float.TYPE, Value::fitsInFloat);
        return assertThat(actual.asFloat());
    }

    public AbstractLocalDateAssert<?> isLocalDateThat() {
        validateValueType(LocalDate.class, Value::isDate);
        return assertThat(actual.asDate());
    }

    public AbstractLocalTimeAssert<?> isLocalTimeThat() {
        validateValueType(LocalTime.class, Value::isTime);
        return assertThat(actual.asTime());
    }

    public ObjectArrayAssert<Value> isArrayThat() {
        validateValueType(Value[].class, Value::hasArrayElements);
        Value[] values = new Value[Long.valueOf(actual.getArraySize()).intValue()];
        for (int i = 0; i < actual.getArraySize(); i++) {
            values[i] = actual.getArrayElement(i);

        }
        return new ObjectArrayAssert<>(values);
    }

    private void validateValueType(Class<?> type, Predicate<Value> isType) {
        if (!isType.test(actual)) {
            failWithMessage("The polyglot value <%s> supposed to be <%s> but it is not", actual.toString(), type);
        }
    }
}
