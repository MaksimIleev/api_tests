package utils;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

import java.util.LinkedList;
import java.util.Map;

public class MySoftAsserter extends SoftAssert {

    private final ThreadLocal<DiffMatchPatch> dmp;
    private final ThreadLocal<LinkedList<DiffMatchPatch.Diff>> diff;
    private final ThreadLocal<Map<AssertionError, IAssert<?>>> m_errors;

    private MySoftAsserter(Builder builder) {
        this.dmp = builder.dmp;
        this.diff = builder.diff;
        this.m_errors = builder.m_errors;
    }

    protected void doAssert(IAssert<?> var1) {
        this.onBeforeAssert(var1);

        try {
            var1.doAssert();
            this.onAssertSuccess(var1);
            LogUtils.info("\n");
            LogUtils.bold("Validating " + var1.getMessage() + " - Status: PASSED");
            LogUtils.bold("Expected: " + var1.getExpected());
            LogUtils.bold("Actual: " + var1.getActual());
        } catch (AssertionError var6) {
            this.onAssertFailure(var1, var6);
            this.m_errors.get().put(var6, var1);
            LogUtils.info("\n");
            LogUtils.warn("Validating " + var1.getMessage() + " - Status: FAILED");
            LogUtils.warn("Expected: " + var1.getExpected());
            LogUtils.warn("Actual: " + var1.getActual());
        } finally {
            this.onAfterAssert(var1);
        }
    }

    public void assertAll() {
        if (!this.m_errors.get().isEmpty()) {

                this.m_errors.get().values().stream().forEach(error -> {

                    StringBuilder styledDiff = new StringBuilder();
                    StringBuilder errMsg = new StringBuilder("Soft Assert Error:");
                    StringBuilder var1 = new StringBuilder("The following asserts failed:");

                    if (error.getMessage() != null) {
                        errMsg.append(error.getMessage());
                    }

                    if (error.getActual() instanceof String && error.getExpected() instanceof String) {

                        diff.set(dmp.get().diff_main(error.getActual().toString(), error.getExpected().toString()));
                        dmp.get().diff_cleanupSemanticLossless(diff.get());
                        System.out.println(diff.get());

                        for (DiffMatchPatch.Diff diffContent : diff.get()) {
                            switch (diffContent.operation) {
                                case DELETE:
                                    styledDiff.append("<span style=\"background: #ffe6e6;\"><del>" + diffContent.text + "</del></span>");
                                    break;
                                case EQUAL:
                                    styledDiff.append("<span style=\"background: #e6ffe6;\">" + diffContent.text + "</span>");
                                    break;
                                case INSERT:
                                    styledDiff.append("<span style=\"background: #f3eb05;\">" + diffContent.text + "</span>");
                                    break;
                            }
                        }

                        var1.append("\n\t");
                        var1.append(
                                "<div class=\"row\"><h3>" + errMsg.toString() + "</h3></div>" +
                                        "<div class=\"row\" style=\"border: 1px solid black;\">" +
                                        "<div class=\"col-md-6\"><h3>ACTUAL</h3>" + styledDiff.toString() + "</div> " +
                                        "<div class=\"col-md-6\"><h3>EXPECTED</h3>" + error.getExpected() + "</div> " +
                                        "</div>"
                        );

                        LogUtils.info(var1.toString());

                    } else {

                        var1.append(
                                "<div class=\"row\"><h3>" + errMsg.toString() + "</h3></div>" +
                                        "<div class=\"row\" style=\"border: 1px solid black;\">" +
                                        "<div class=\"col-md-6\"><h3>ACTUAL</h3>" + error.getActual() + "</div> " +
                                        "<div class=\"col-md-6\"><h3>EXPECTED</h3>" + error.getExpected() + "</div> " +
                                        "</div>"
                        );

                        LogUtils.info(var1.toString());
                    }
                });


            throw new AssertionError("Soft Assertion failed, see logs please ->");

        }

    }

    public static class Builder {

        private final ThreadLocal<DiffMatchPatch> dmp = ThreadLocal.withInitial(() -> new DiffMatchPatch());
        private final ThreadLocal<LinkedList<DiffMatchPatch.Diff>> diff = new ThreadLocal<>();
        private final ThreadLocal<Map<AssertionError, IAssert<?>>> m_errors = ThreadLocal.withInitial(() -> Maps.newLinkedHashMap());

        public Builder() {}

        public MySoftAsserter build() {
            return new MySoftAsserter(this);
        }

    }
}
