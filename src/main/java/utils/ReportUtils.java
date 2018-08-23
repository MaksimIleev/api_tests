package utils;

import org.testng.Reporter;

public class ReportUtils {

    public static void setDescription(String description) {
        Reporter.getCurrentTestResult().setParameters(new Object[]{description});
    }
}
