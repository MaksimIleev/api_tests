package utils;

import manage.logging.MyLogger;
import org.apache.logging.log4j.ThreadContext;

import java.util.LinkedList;

public class LogUtils {

    static {
        MyLogger.init();
    }

    public static void removeLogThreadContext() {
        LogUtils.info("Removing 'logFileName' from ThreadContext...");
        ThreadContext.remove("logFileName");
        LogUtils.info("Done.");
    }

    public static void logThreadContext(String id) {
        ThreadContext.put("logFileName" , id);
    }

    public static void info(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
                System.out.println(s);
        MyLogger.log().info(s);
    }
    public static void green(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
            System.out.println(s);
        MyLogger.log().info("<span style=\"color:#131212; background:yellowgreen;\">" + s + "</span>");
    }

    public static void bold(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
            System.out.println(s);
        MyLogger.log().info("<b>" + s + "</b>");
    }

    public static void italic(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
            System.out.println(s);
        MyLogger.log().info("<i>" + s + "</i>");
    }


    public static void warn(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
            System.out.println(s);
        MyLogger.log().warn("<span style=\"color:red;\">" + s + "</span>");
    }

    public static void error(String s) {
        if(PropertyUtils.get("console.print").equals("true"))
            System.out.println(s);
        MyLogger.log().error(s);
    }

    public static void logStyledComparisson(String actual, String expected) {
        final ThreadLocal<DiffMatchPatch> dmp = ThreadLocal.withInitial(() -> new DiffMatchPatch());
        final ThreadLocal<LinkedList<DiffMatchPatch.Diff>> diff = new ThreadLocal<>();
        StringBuilder styledDiff = new StringBuilder();
        StringBuilder compare = new StringBuilder();

        diff.set(dmp.get().diff_main(actual, expected));

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

        compare.append("\n\t");
        compare.append(
                "<div class=\"row\"><h3> SIDE to SIDE Comparisson</h3></div>" +
                        "<div class=\"row\" style=\"border: 1px solid black;\">" +
                        "<div class=\"col-md-6\"><h3>Actual 1</h3>" + styledDiff.toString() + "</div> " +
                        "<div class=\"col-md-6\"><h3>Expected 2</h3>" + expected + "</div> " +
                        "</div>"
        );

        LogUtils.info(compare.toString());
    }
}
