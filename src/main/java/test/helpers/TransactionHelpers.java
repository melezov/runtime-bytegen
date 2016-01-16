package test.helpers;

public enum TransactionHelpers {
    INSTANCE;

    public final org.revenj.patterns.Query.Compare<test.Transaction, Long> compareID_Long = new org.revenj.patterns.Query.Compare<test.Transaction, Long>() {
        @Override
        public Long compare(final test.Transaction transaction) {
            return transaction.getID();
        }
    };

    public final org.revenj.patterns.Query.Compare<test.Transaction, java.time.OffsetDateTime> compareAt_java_time_OffsetDateTime = new org.revenj.patterns.Query.Compare<test.Transaction, java.time.OffsetDateTime>() {
        @Override
        public java.time.OffsetDateTime compare(final test.Transaction transaction) {
            return transaction.getAt();
        }
    };

    public final org.revenj.patterns.Query.Compare<test.Transaction, java.math.BigDecimal> compareIn_nullable_java_math_BigDecimal = new org.revenj.patterns.Query.Compare<test.Transaction, java.math.BigDecimal>() {
        @Override
        public java.math.BigDecimal compare(final test.Transaction transaction) {
            return transaction.getIn();
        }
    };

    public final org.revenj.patterns.Query.Compare<test.Transaction, java.math.BigDecimal> compareOut_nullable_java_math_BigDecimal = new org.revenj.patterns.Query.Compare<test.Transaction, java.math.BigDecimal>() {
        @Override
        public java.math.BigDecimal compare(final test.Transaction transaction) {
            return transaction.getOut();
        }
    };

    public final org.revenj.patterns.Query.Compare<test.Transaction, String> compareDescription_String = new org.revenj.patterns.Query.Compare<test.Transaction, String>() {
        @Override
        public String compare(final test.Transaction transaction) {
            return transaction.getDescription();
        }
    };
}
