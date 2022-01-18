package org.assertj.graalvm.api.js;

import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.oracle.truffle.js.lang.JavaScriptLanguage;

public abstract class AbstractGraalvmTest {

    protected Context context;

    protected final Engine engine = Engine.newBuilder().build();

    @BeforeEach
    protected void setUp() {
        context = Context.newBuilder(JavaScriptLanguage.ID)
                .engine(engine)
                .timeZone(ZoneOffset.UTC)
                .build();
    }

    @AfterEach
    protected void tearDown() {
        context.close();
    }


    /**
     * Evaluates javascript source code
     * @param sourceCode the javascript
     * @return the result of evaluation
     */
    protected Value evalJavascript(String sourceCode) {
        return evalJavascript(sourceCode, new HashMap<>());
    }

    /**
     * Evaluates javascript source code
     *
     * @param sourceCode the javascript
     * @param bindingObjects the objects bind to the evaluation context as variables
     * @return the result of evaluation
     */
    protected Value evalJavascript(String sourceCode, Map<String, Object> bindingObjects) {
        Value bindings = context.getBindings(JavaScriptLanguage.ID);
        bindingObjects.forEach(bindings::putMember);
        try {
            return context.eval(JavaScriptLanguage.ID, sourceCode);
        } catch (Exception e) {
            context.close();
            throw e;
        }
    }

}
