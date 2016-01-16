package org.revenj;

import static org.junit.Assert.*;
import org.junit.*;
import org.revenj.patterns.Query;
import test.Transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Random;

public class CompareCompilerTest {
    @SuppressWarnings("unchecked")
    public static <U, V extends Comparable<V>> Query.Compare<U, V> makeComparer(
            final Class<U> sourceClass,
            final String property) throws Exception {
        final byte[] bytecode = CompareCompiler.compile(sourceClass, property);
        return (Query.Compare<U, V>) new BytecodeLoader().define(bytecode).newInstance();
    }

    @Test
    public void randomTests() throws Exception {
        final Random rnd = new Random();
        for (int i = 0; i < 1000; i++) {
            final Transaction transaction = new Transaction()
                    .setIn(rnd.nextBoolean()
                            ? BigDecimal.valueOf(rnd.nextLong()).divide(BigDecimal.valueOf(100))
                            : null)
                    .setOut(rnd.nextBoolean()
                            ? BigDecimal.valueOf(rnd.nextLong()).divide(BigDecimal.valueOf(100))
                            : null)
                    .setAt(OffsetDateTime.now().plusMinutes(100000 - rnd.nextInt(2000000)))
                    .setDescription("This is a test: " + rnd.nextLong())
                    .setExchangeRate(rnd.nextBoolean()
                            ? rnd.nextDouble()
                            : null);

            final long startAt = System.nanoTime();
            assertEquals(transaction.getID(), makeComparer(Transaction.class, "ID").compare(transaction));
            assertSame(transaction.getIn(), makeComparer(Transaction.class, "in").compare(transaction));
            assertSame(transaction.getOut(), makeComparer(Transaction.class, "out").compare(transaction));
            assertSame(transaction.getAt(), makeComparer(Transaction.class, "at").compare(transaction));
            assertSame(transaction.getDescription(), makeComparer(Transaction.class, "description").compare(transaction));
            assertSame(transaction.getExchangeRate(), makeComparer(Transaction.class, "exchangeRate").compare(transaction));
            final long tookMs = (System.nanoTime() - startAt) / 1000000;

            System.out.println("Compilation & test took: " + tookMs + "ms");
        }
    }
}
