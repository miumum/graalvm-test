package org.assertj.graalvm.api.js;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.assertj.graalvm.api.ValueAssert;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import com.oracle.truffle.js.lang.JavaScriptLanguage;

import static com.oracle.truffle.js.lang.JavaScriptLanguage.ID;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * Assertion methods for a {@link Value} assuming the {@link Value} represents {@link JavaScriptLanguage jsvascript}
 * guest language value.
 * <p>
 * To create an instance of this class, invoke
 * <code>
 * {@link JsValueAssertions JsValueAssertions}{@link JsValueAssertions#assertThat(Value) .assertThat(value)}
 * </code>
 * </p>
 *
 * @see AbstractAssert
 */
public class JsValueAssert extends ValueAssert {

    public JsValueAssert(Value value) {
        super(value);
    }

    public JsDateAssert isJsDateThat() {
        assertThatCode(() -> {
            Map<String, Object> bindings = new HashMap<>();
            bindings.put("dateVar", actual);
            String script = "if(dateVar instanceof Date === false) throw new Error(`the value '${dateVar}' not a javascript date`)";
            evalJavascript(script, bindings);
        })
                .doesNotThrowAnyException();
        long time = actual.getMember("getTime").execute().asLong();
        return new JsDateAssert(ZonedDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC));
    }

    protected Value evalJavascript(String sourceCode, Map<String, Object> bindingObjects) {
        Context context = actual.getContext();
        Value bindings = context.getBindings(ID);
        bindingObjects.forEach(bindings::putMember);

        return context.eval(ID, sourceCode);
    }
}
