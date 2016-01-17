package org.revenj;

public final class Primitives {
    private Primitives() {}

    private static final Class[] wrappers = {
            Integer.class,
            Double.class,
            Byte.class,
            Boolean.class,
            Character.class,
            Void.class,
            Short.class,
            Float.class,
            Long.class
    };

    @SuppressWarnings("unchecked")
    public static <T> Class<T> wrap(final Class<T> clazz) {
        if (!clazz.isPrimitive()) return clazz;
        final String name = clazz.getName();
        final int c0 = name.charAt(0);
        final int c2 = name.charAt(2);
        final int mapper = (c0 + c0 + c0 + 5) & (118 - c2);
        return (Class<T>) wrappers[mapper];
    }

    private static final Class[] primitives = {
            int.class,
            double.class,
            byte.class,
            boolean.class,
            char.class,
            void.class,
            short.class,
            float.class,
            long.class
    };

    @SuppressWarnings("unchecked")
    public static <T> Class<T> unwrap(final Class<T> wrapperClass) {
        final String name = wrapperClass.getName();
        final int c10 = name.charAt(10);
        final int c12 = name.charAt(12);
        final int mapper = (c10 + c10 + c10 + 5) & (118 - c12);
        return (Class<T>) primitives[mapper];
    }

    private static final String[] abbreviations = {
            "I",
            "D",
            "B",
            "Z",
            "C",
            "V",
            "S",
            "F",
            "J"
    };

    public static <T> String abbreviate(final Class<T> primitiveClass) {
        final String name = primitiveClass.getName();
        final int c0 = name.charAt(0);
        final int c2 = name.charAt(2);
        final int mapper = (c0 + c0 + c0 + 5) & (118 - c2);
        return abbreviations[mapper];
    }

    private static String[] descriptions = {
            "Ljava/lang/Integer;",
            "Ljava/lang/Double;",
            "Ljava/lang/Byte;",
            "Ljava/lang/Boolean;",
            "Ljava/lang/Character;",
            "Ljava/lang/Void;",
            "Ljava/lang/Short;",
            "Ljava/lang/Float;",
            "Ljava/lang/Long;"
    };

    public static <T> String describe(final Class<T> wrapperClass) {
        final String name = wrapperClass.getName();
        final int c10 = name.charAt(10);
        final int c12 = name.charAt(12);
        final int mapper = (c10 + c10 + c10 + 5) & (118 - c12);
        return descriptions[mapper];
    }
}
