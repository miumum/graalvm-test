package org.assertj.graalvm.api.js;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ZonedDateTimeAssert;
import org.graalvm.polyglot.Value;

import com.oracle.truffle.js.lang.JavaScriptLanguage;

/**
 * Assertion methods for a {@link Value} assuming the {@link Value} represents {@link JavaScriptLanguage jsvascript}
 * <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date">date</a>.
 * <p>
 * To create an instance of this class, invoke
 * <code>
 * {@link JsValueAssertions JsValueAssertions}
 * {@link JsValueAssertions#assertThat(Value) .assertThat(value)}{@link JsValueAssert#isJsDateThat() .isJsDateThat()}
 * </code>
 * </p>
 *
 * @see AbstractAssert
 */
public class JsDateAssert extends ZonedDateTimeAssert {

    /**
     * Creates a new <code>{@link JsDateAssert}</code>.
     *
     * @param actual the actual {@link Value} to verify
     */
    JsDateAssert(ZonedDateTime actual) {
        super(actual);
    }

    public JsDateAssert isEqualTo(ZonedDateTime other) {
        super.isEqualTo(other);
        return this;
    }

    public JsDateAssert isEqualTo(LocalDate other) {
        return this.isEqualTo(LocalDateTime.of(other, LocalTime.MIDNIGHT));
    }

    public JsDateAssert isEqualTo(LocalDateTime other) {
        return this.isEqualTo(other.atZone(ZoneOffset.UTC));
    }
}
