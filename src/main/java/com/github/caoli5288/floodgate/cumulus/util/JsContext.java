package com.github.caoli5288.floodgate.cumulus.util;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JsContext {

    private static volatile ScriptEngine context;

    public static ScriptEngine context() {
        if (context == null) {
            synchronized (JsContext.class) {
                if (context == null) {
                    context = new ScriptEngineManager(JsContext.class.getClassLoader()).getEngineByName("nashorn");
                    try {
                        context.eval("yes=true;no=false;");
                    } catch (Exception e) {
                        //
                    }
                }
            }
        }
        return context;
    }

    public static void evalContext(Object obj, String exp) {
        ScriptEngine engine = context();
        Bindings ctx = engine.createBindings();
        try {
            Object global = engine.eval("this", ctx);
            Object jsObject = engine.eval("Object", ctx);
            ((Invocable) engine).invokeMethod(jsObject, "bindProperties", global, obj);
            engine.eval(exp, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object eval(String exp) {
        ScriptEngine engine = context();
        try {
            return engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
