.version 52 0
.source TransactionHelpers.java
.class super test/helpers/TransactionHelpers$1
.super java/lang/Object
.implements org/revenj/patterns/Query$Compare
.inner abstract static interface public Compare org/revenj/patterns/Query$Compare org/revenj/patterns/Query
.inner  [0] test/helpers/TransactionHelpers$1 [0]
.enclosing method test/helpers/TransactionHelpers [0]
.signature [_22]

.field synthetic final this$0 Ltest/helpers/TransactionHelpers;

.method  <init> : (Ltest/helpers/TransactionHelpers;)V
	; method code size: 10 bytes
	.limit stack 2
	.limit locals 2
	aload_0
	aload_1
	putfield test/helpers/TransactionHelpers$1 this$0 Ltest/helpers/TransactionHelpers;
	aload_0
	invokespecial java/lang/Object <init> ()V
	return
.end method

.method public compare : (Ltest/Transaction;)Ljava/lang/Long;
	; method code size: 8 bytes
	.limit stack 2
	.limit locals 2
	aload_1
	invokevirtual test/Transaction getID ()J
	invokestatic java/lang/Long valueOf (J)Ljava/lang/Long;
	areturn
.end method

.method synthetic bridge public compare : (Ljava/lang/Object;)Ljava/lang/Comparable;
	; method code size: 9 bytes
	.limit stack 2
	.limit locals 2
	aload_0
	aload_1
	checkcast test/Transaction
	invokevirtual test/helpers/TransactionHelpers$1 compare (Ltest/Transaction;)Ljava/lang/Long;
	areturn
.end method

.const [_22] = Utf8 Ljava/lang/Object;Lorg/revenj/patterns/Query$Compare<Ltest/Transaction;Ljava/lang/Long;>;