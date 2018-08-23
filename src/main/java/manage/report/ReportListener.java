package manage.report;

import manage.logging.MyLogger;
import manage.test.ITestData;
import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.testng.xml.XmlSuite;
import utils.DateUtils;
import utils.IOUtils;
import utils.PropertyUtils;
import utils.Utils;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ReportListener implements IReporter {

    private static final String id = Utils.generateId();

    private static final String ROW =
            " <tr scope=\"row\" class=\"%s\">" +
            "      <td>%s</td>" +
            "      <td>%s</td>" +
            "      <td>%s</td>" +
            "      <td>%s</td>" +
            "      <td><a href=\"%s\" target=\"_blank\">Test Logs</a></td>" +
            "      <td>%s</td>" +
            " </tr>";

    private static final String HEADER =

            "<div class=\"container\">"+
            "    <div class=\"row\">"+
            "        <div class=\"col-md-offset-1 col-md-2\">"+
            "            <img src=\"html/logo.png\" style=\"float: right;\">"+
            "        </div>"+
            "        <div class=\"col-md-6\">"+
            "            <h2 style=\"float:center;\">Automated Tests Results on " + System.getProperty("api.env") + "</h2>"+
            "        </div>"+
            "        <div class=\"col-md-2\" style=\"padding-top: 10px;\">"+
            "             <h5 style=\"padding-top: 10px;\"> Generated Date: " + DateUtils.getCurrentDate() + "</h5>"+
            "        </div>"+
            "    </div>"+

            "    <table class=\"table\">"+
            "        <thead>"+
            "            <tr>"+
            "                <th scope=\"col\"><h2>Total Tests# %s</h2></th>"+
            "                <th scope=\"col\"><h2>Passed# %s</h2></th>"+
            "                <th scope=\"col\"><h2>Failed# %s</h2></th>"+
            "                <th scope=\"col\"><h2>Skipped# %s</h2></th>"+
            "            </tr>"+
            "        </thead>"+
            "    </table>"+
            "</div>" + "TEST_COVERAGE_TABLE";

    public static final String TEST_COVERAGE_TABLE = "" +
            "<div class=\"row\">" +
            "        <div class=\"col-md-offset-2 col-md-8 col-md-offset-2\">" +
            "            <table class=\"table table-bordered\">" +
            "              <thead>" +
            "                <tr>" +
            "                  <th class=\"active\" scope=\"col\">Test</th>" +
            "                  <th class=\"success\" scope=\"col\">Passed</th>" +
            "                  <th class=\"danger\" scope=\"col\">Failed</th>" +
            "                  <th class=\"warning\" scope=\"col\">Skipped</th>" +
            "                </tr>" +
            "              </thead>" +
            "              <tbody>TEST_COVERAGE_TABLE_ROW</tbody>" +
            "            </table>" +
            "        </div>" +
            " </div>";

    private static final String TEST_COVERAGE_TABLE_ROW =
            " <tr>" +
            "    <th scope=\"row\">%s</th>" +
            "    <td>%s</td>" +
            "    <td>%s</td>" +
            "    <td>%s</td>" +
            " </tr>";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        String reportTemplate = initReportTemplate();
        int totalTestsCount = 0; int resultsPassed = 0; int resultsFailed = 0; int resultsSkipped = 0;
        String table_rows = "";

        for(ISuite suite: suites) {
            for(Map.Entry<String, ISuiteResult> results: suite.getResults().entrySet()) {

                // get current test info
                String currentTestName = results.getValue().getTestContext().getName();
                int currentTestPassed = results.getValue().getTestContext().getPassedTests().getAllResults().size();
                int currentTestFailed = results.getValue().getTestContext().getFailedTests().getAllResults().size();
                int currentTestSkipped = results.getValue().getTestContext().getSkippedTests().getAllResults().size();

                // generate table row of this test
                table_rows += String.format(TEST_COVERAGE_TABLE_ROW, currentTestName, currentTestPassed, currentTestFailed, currentTestSkipped);

                totalTestsCount += currentTestPassed + currentTestFailed + currentTestSkipped;
                resultsPassed += (results.getValue().getTestContext().getPassedTests().getAllResults().size());
                resultsFailed += (results.getValue().getTestContext().getFailedTests().getAllResults().size());
                resultsSkipped += (results.getValue().getTestContext().getSkippedTests().getAllResults().size());
            }
        }
        /*
        *  Finalize HTML
        * */
        final String coverage_table = TEST_COVERAGE_TABLE.replace("TEST_COVERAGE_TABLE_ROW", table_rows);
        final String head = HEADER.replace("TEST_COVERAGE_TABLE", coverage_table);
        final String container = String.format(head, totalTestsCount, resultsPassed, resultsFailed, resultsSkipped);

        reportTemplate = reportTemplate.replace("CONTAINER", container);

        System.out.println(reportTemplate);

        final String body = suites
                .stream()
                .flatMap(suiteToResults())
                .collect(Collectors.joining());


        reportTemplate = reportTemplate.replace("TBODY", body.replaceAll("\\$", "\\u0024"));

        saveReportTemplate(System.getProperty("user.dir") + PropertyUtils.get("report_output"), reportTemplate);

        File htmlFile = null;
        try {
            String reportPath = System.getProperty("user.dir") + "/" + PropertyUtils.get("report_output") + "/" + PropertyUtils.get("report_name");
            System.out.println("Saving report to - " + reportPath);
            htmlFile = new File(reportPath);
        } catch(Exception e) {
            System.err.println("Problem saving the report file...");
            e.printStackTrace();
        }
        if(PropertyUtils.get("openReport").equals("true")) {
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Function<ISuite, Stream<? extends String>> suiteToResults() {
        return suite -> suite.getResults().entrySet()
                .stream()
                .flatMap(resultsToRows(suite));
    }

    private Function<Map.Entry<String, ISuiteResult>, Stream<? extends String>> resultsToRows(ISuite suite) {
        return e -> {
            ITestContext testContext = e.getValue().getTestContext();

            Set<ITestResult> failedTests = testContext
                    .getFailedTests()
                    .getAllResults();
            Set<ITestResult> passedTests = testContext
                    .getPassedTests()
                    .getAllResults();
            Set<ITestResult> skippedTests = testContext
                    .getSkippedTests()
                    .getAllResults();

            String suiteName = suite.getName();

            return Stream
                    .of(failedTests, passedTests, skippedTests)
                    .flatMap(results -> generateReportRows(e.getKey(), suiteName, results).stream());
        };
    }

    private List<String> generateReportRows(String testName, String suiteName, Set<ITestResult> allTestResults) {
        return allTestResults.stream()
                .map(testResultToResultRow(testName, suiteName))
                .collect(toList());
    }

    private Function<ITestResult, String> testResultToResultRow(String testName, String suiteName) {
        System.gc();
        return testResult -> {
            /*
            *  Read test logs and Create HTML file of it
            * */
            StringBuilder logs = new StringBuilder("");
            try {
                System.out.println("Reading log file for Test: " + testResult.getMethod().getMethodName());
                logs = new StringBuilder(IOUtils.readFile(PropertyUtils.get("logs_output") + "/" + testResult.getAttribute("log") + ".log"));
            } catch(Exception e) {
                System.out.println("Problem reading the log!");
            }
            String logsTemplate = initLogsTemplate().replace("BODY_REPLACE", logs.toString());
            String logsPath = saveTemplate(PropertyUtils.get("logs_output")+ "/html/", logsTemplate, testResult.getAttribute("log") + "" );
           /*
           *  Set test description
           * */
            String testDescription = "";

            if(testDescription.equals("") && testResult.getParameters() != null) {
                try {
                    if(testDescription != null && testDescription.equals(""))
                    testDescription = ((ITestData)testResult.getParameters()[0]).getTestCaseDescription();
                } catch (Exception ex) {
                    try {
                        if(testDescription != null && testDescription.equals(""))
                        testDescription = Arrays.asList(testResult.getParameters()).toString();
                    } catch(Exception ex2) {
                        MyLogger.log().warn("Couldn't get test description!");
                        testDescription = "";
                    }
                }
            }
            if(testDescription.equals("") || testDescription.equals("[]")) {
                try {
                    testDescription = testResult.getMethod().getMethodName();
                } catch (Exception e) {
                    //skip
                }
            }
            String exception = "";
            try {
                exception = testResult.getThrowable().getMessage();
                String screenShot = (String)testResult.getTestContext().getAttribute("screenshotHtml");
                exception += screenShot != null ? screenShot: "";
            } catch(Exception e) {} // skip

            String timeTaken = String.valueOf((testResult.getEndMillis() - testResult.getStartMillis()) / 1000);

            switch (testResult.getStatus()) {
                case ITestResult.FAILURE: {
                    return String.format(ROW.replaceAll("\\$ID", Utils.generateId()), "danger", testName, testDescription, "FAILED", exception , logsPath, timeTaken);
                }
                case ITestResult.SUCCESS: {
                    return String.format(ROW.replaceAll("\\$ID", Utils.generateId()), "success", testName, testDescription, "PASSED", "N/A", logsPath, timeTaken);
                }
                case ITestResult.SKIP: {
                    return String.format(ROW.replaceAll("\\$ID", Utils.generateId()), "warning", testName,  testDescription, "SKIPPED", exception, logsPath, timeTaken);
                }
                default: return "";
            }
        };
    }

    /*
    *  Initializing report and logs templates
    * */
    private String initReportTemplate() {
        String template = null;
        byte[] reportTemplate;

        try {
            System.out.println("Generating Test Report...");
            reportTemplate = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/" + PropertyUtils.get("resources.html.reportTemplate")));
            template = new String(reportTemplate, "UTF-8");

        } catch (IOException e) {
            System.out.println("Problem initializing template");
        }
        copyResources();

        return template;
    }

    /*
     *  Initializing report and logs templates
     * */
    private String initLogsTemplate() {
        String logs = null;
        byte[] logsTemplate;

        try {
            System.out.println("Generating Logs Template...");
            logsTemplate = Files.readAllBytes(Paths.get(PropertyUtils.get("resources.logs.html")));
            logs = new String(logsTemplate, "UTF-8");

        } catch (IOException e) {
            System.out.println("Problem initializing template");
        }

        return logs;
    }

    private void copyResources() {
        /*
         *  Copy bootstrap js and css to target surefire dir
         * */
        System.out.println("Copying the 'resources.html' to 'report_output'.....");
        try {
            FileUtils.copyDirectoryToDirectory(new File(PropertyUtils.get("resources")), new File(PropertyUtils.get("report_output")));
            System.out.println("Copying success");

        } catch (IOException e) {
            System.out.println("Copying failure");
        }
    }

    private void saveReportTemplate(String outputDirectory, String reportTemplate) {
        if(!new File(outputDirectory).exists()) {
            new File(outputDirectory).mkdir();
        }
        try {
            PrintWriter reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(PropertyUtils.get("report_output"), PropertyUtils.get("report_name")))));
            reportWriter.println(reportTemplate);
            reportWriter.flush();
            reportWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Problem saving template");
        }
    }

    private String saveTemplate(String outputDirectory, String template, String htmlFileName) {
        if(!new File(outputDirectory).exists()) {
            new File(outputDirectory).mkdir();
        }
        PrintWriter reportWriter = null;
        try {
            reportWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(PropertyUtils.get("report_output"), htmlFileName + ".html"))));
            reportWriter.println(template);
            reportWriter.flush();
            reportWriter.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("Problem saving template");

        } finally {
            reportWriter.close();
        }

        return htmlFileName + ".html";
    }
}
