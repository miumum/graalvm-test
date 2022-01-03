package org.assertj.graalvm.api;

import org.assertj.core.api.Assertions;
import org.graalvm.polyglot.Value;

/**
 * Entry point for assertion methods for different types. Each method in this class is a static factory for a
 * type-specific assertion object.
 * <p>
 * For example:
 * <pre><code class='java'>
 *   {@link Value Value} actual = fixture.valueDoubleField();
 *   {@link ValueAssertions#assertThat(Value) assertThat}(actual).{@link ValueAssert#isDoubleThat() isDoubleThat}()
 *   .{@link org.assertj.core.api.DoubleAssert#isEqualTo(Double) isEqualTo}(10);
 *
 *   </code></pre>
 * </p>
 */
public class ValueAssertions extends Assertions {

    protected ValueAssertions() {
    }

    public static ValueAssert assertThat(Value actual) {
        return new ValueAssert(actual);
    }
}
