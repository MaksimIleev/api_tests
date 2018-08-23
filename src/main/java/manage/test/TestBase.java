package manage.test;


import manage.report.ReportListener;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, ReportListener.class})
public class TestBase {

    @BeforeSuite
    public void registerTestAnalyzer(ITestContext context) {
        ThreadContext.clearAll();
        for(ITestNGMethod method: context.getAllTestMethods()) {
            method.setRetryAnalyzer(new RetryAnalyzer());
        }
    }
}
