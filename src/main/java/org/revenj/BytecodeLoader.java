package org.revenj;

public class BytecodeLoader extends ClassLoader {
    public BytecodeLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> define(final byte[] bytecode) {
        return super.defineClass(null, bytecode, 0, bytecode.length);
    }
}
