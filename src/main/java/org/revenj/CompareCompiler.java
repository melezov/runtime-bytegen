package org.revenj;

import jdk.internal.org.objectweb.asm.Type;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Method;

public class CompareCompiler implements Opcodes {

    private static String getGetterName(final String property) {
        return "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
    }

    private static String getComparatorClassName(final String sourceClassName, final String property) {
        final String suffix = "Comparator_" + property;
        final int dotIndex = sourceClassName.lastIndexOf('.');
        if (dotIndex == -1) {
            return sourceClassName + suffix;
        } else {
            final String packageName = sourceClassName.substring(0, dotIndex + 1).replace('.', '/') + "comparators/";
            return packageName + sourceClassName.substring(dotIndex + 1) + suffix;
        }
    }

    private static String wrapPrimitives(final Class<?> type) {
        if (type.isPrimitive()) {
            switch (type.getName()) {
                case "long": return "java/lang/Long";
                default: throw new RuntimeException("Unsupported primitive type:" + type);
            }
        }
        return Type.getInternalName(type);
    }

    public static String L(final String descriptor) {
        return "L" + descriptor + ";";
    }

    public static byte[] compile(final Class<?> sourceClass, final String property) throws Exception {
        final ClassWriter classWriter = new ClassWriter(0);

        // source class
        final String sourceClassName = sourceClass.getName();
        final String sourceClassNameInternal = Type.getInternalName(sourceClass);
        final String LsourceClass = Type.getDescriptor(sourceClass);
        final String comparatorClassName = getComparatorClassName(sourceClassName, property);

        // getter
        final String getterName = getGetterName(property);
        final Method method = sourceClass.getMethod(getterName);

        // source type
        final Class<?> sourceType = method.getReturnType();
        final String LsourceType = Type.getDescriptor(sourceType);
        final String wrappedSourceType = wrapPrimitives(sourceType);
        final String LwrappedSourceType = L(wrappedSourceType);

        {
            final int javaLevel = 50; // 1.6
            final int classFlags = ACC_PUBLIC + ACC_FINAL + ACC_SUPER;
            final String signature = L("java/lang/Object") + L("org/revenj/patterns/Query$Compare") + "<" + LsourceClass + LwrappedSourceType + ">;";
            classWriter.visit(javaLevel, classFlags, comparatorClassName, signature, "java/lang/Object", new String[]{ "org/revenj/patterns/Query$Compare" });

            final int interfaceFlags = ACC_PUBLIC + ACC_STATIC + ACC_ABSTRACT + ACC_INTERFACE;
            classWriter.visitInnerClass("org/revenj/patterns/Query$Compare", "org/revenj/patterns/Query", "Compare", interfaceFlags);
        }
        {
            final MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            final MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_FINAL, "compare", "(" + LsourceClass + ")" + LwrappedSourceType, null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, sourceClassNameInternal, getterName, "()" + LsourceType, false);
            if (!LsourceType.equals(LwrappedSourceType)) {
                methodVisitor.visitMethodInsn(INVOKESTATIC, wrappedSourceType, "valueOf", "(" + LsourceType + ")" + LwrappedSourceType, false);
            }
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            final MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "compare", "(Ljava/lang/Object;)Ljava/lang/Comparable;", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitTypeInsn(CHECKCAST, sourceClassNameInternal);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, comparatorClassName, "compare", "(" + L(sourceClassNameInternal) + ")" + LwrappedSourceType, false);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }

        return classWriter.toByteArray();
    }
}
