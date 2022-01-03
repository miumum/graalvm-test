package org.assertj.graalvm.api.js;

import java.time.LocalDate;

import org.assertj.graalvm.api.ValueAssertions;
import org.graalvm.polyglot.Value;

/**
 * Entry point for assertion methods for different types. Each method in this class is a static factory for a
 * type-specific assertion object.
 * <pre><code class='java'>
 *   {@link Value Value} actual = fixture.valueJsDateField();
 *   {@link JsValueAssertions#assertThat(Value) assertThat}(actual).{@link JsValueAssert#isJsDateThat() isJsDateThat}()
 *      .{@link JsDateAssert#isEqualTo(LocalDate) isEqualTo}({@link LocalDate#now() LocalDate.now()});
 *
 * </code></pre>
 */
public class JsValueAssertions extends ValueAssertions {

    protected JsValueAssertions() {
    }

    public static JsValueAssert assertThat(Value actual) {
        return new JsValueAssert(actual);
    }
}
