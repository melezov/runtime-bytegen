package org.revenj;

import org.jinq.rebased.org.objectweb.asm.Type;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PrimitivesTest {
    @Test
    public void testWrapping() {
        assertEquals(Boolean.class, Primitives.wrap(boolean.class));
        assertEquals(Byte.class, Primitives.wrap(byte.class));
        assertEquals(Character.class, Primitives.wrap(char.class));
        assertEquals(Double.class, Primitives.wrap(double.class));
        assertEquals(Float.class, Primitives.wrap(float.class));
        assertEquals(Integer.class, Primitives.wrap(int.class));
        assertEquals(Long.class, Primitives.wrap(long.class));
        assertEquals(Short.class, Primitives.wrap(short.class));
        assertEquals(Void.class, Primitives.wrap(void.class));

        assertEquals(String.class, Primitives.wrap(String.class));
        assertEquals(Long.class, Primitives.wrap(Long.class));
    }

    @Test
    public void testUnwrapping() {
        assertEquals(boolean.class, Primitives.unwrap(Boolean.class));
        assertEquals(byte.class, Primitives.unwrap(Byte.class));
        assertEquals(char.class, Primitives.unwrap(Character.class));
        assertEquals(double.class, Primitives.unwrap(Double.class));
        assertEquals(float.class, Primitives.unwrap(Float.class));
        assertEquals(int.class, Primitives.unwrap(Integer.class));
        assertEquals(long.class, Primitives.unwrap(Long.class));
        assertEquals(short.class, Primitives.unwrap(Short.class));
        assertEquals(void.class, Primitives.unwrap(Void.class));
    }

    @Test
    public void testAbbreviating() {
        assertEquals(Type.getDescriptor(boolean.class), Primitives.abbreviate(boolean.class));
        assertEquals(Type.getDescriptor(byte.class), Primitives.abbreviate(byte.class));
        assertEquals(Type.getDescriptor(char.class), Primitives.abbreviate(char.class));
        assertEquals(Type.getDescriptor(double.class), Primitives.abbreviate(double.class));
        assertEquals(Type.getDescriptor(float.class), Primitives.abbreviate(float.class));
        assertEquals(Type.getDescriptor(int.class), Primitives.abbreviate(int.class));
        assertEquals(Type.getDescriptor(long.class), Primitives.abbreviate(long.class));
        assertEquals(Type.getDescriptor(short.class), Primitives.abbreviate(short.class));
        assertEquals(Type.getDescriptor(void.class), Primitives.abbreviate(void.class));
    }

    @Test
    public void testDescriptions() {
        assertEquals(Type.getDescriptor(Boolean.class), Primitives.describe(Boolean.class));
        assertEquals(Type.getDescriptor(Byte.class), Primitives.describe(Byte.class));
        assertEquals(Type.getDescriptor(Character.class), Primitives.describe(Character.class));
        assertEquals(Type.getDescriptor(Double.class), Primitives.describe(Double.class));
        assertEquals(Type.getDescriptor(Float.class), Primitives.describe(Float.class));
        assertEquals(Type.getDescriptor(Integer.class), Primitives.describe(Integer.class));
        assertEquals(Type.getDescriptor(Long.class), Primitives.describe(Long.class));
        assertEquals(Type.getDescriptor(Short.class), Primitives.describe(Short.class));
        assertEquals(Type.getDescriptor(Void.class), Primitives.describe(Void.class));
    }
}
