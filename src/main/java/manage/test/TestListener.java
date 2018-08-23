package manage.test;

import org.testng.*;
import utils.LogUtils;
import utils.ThreadUtils;
import utils.Utils;
import java.util.Arrays;

/**
 * Listens the test classes and perform the action on implemented methods
 */
public class TestListener extends TestListenerAdapter implements IInvokedMethodListener {
	static {
	    System.setProperty("outputDir", "target/surefire-reports");
	}

    public static final ThreadLocal<String> _testId = ThreadLocal.withInitial(() -> {
        return Utils.generateId();
    });

    @Override
    public void onFinish(ITestContext testContext) {
        LogUtils.info("onFinish()");
        LogUtils.removeLogThreadContext();
        ThreadUtils.cleanThreadLocals();
        System.gc();
    }

	/*
	*  TestNG executed this method for every test start
	* */
	@Override
	public void onTestStart(ITestResult tr) {
	    /*
        *  Log test thread
        * */
	    String id = tr.getMethod().getMethodName() + "_" + ThreadUtils.getThreadInfo() + "_" + Utils.generateId();
        LogUtils.logThreadContext(id);
        tr.setAttribute("log", id);

        /*
        *  set description from excel file, or provide default ""
        * */
        String description = "";
        for(Object obj: tr.getParameters()) {
            /*
            *  Get test description from Excel
            * */
            if(obj instanceof ITestData) {
                if((String)((ITestData) obj).getTestCaseDescription() != null) {
                    description = ((ITestData) obj).getTestCaseDescription().replaceAll("\\t", "<br/>");
                }
            } else {
                /*
                *  Get test parameters as description
                * */
                try {
                    description += "\t" + obj.toString().replaceAll("\\t", "<br/>");
                } catch(Exception e) {
                    System.err.println("Couldn't get parameter");
                }
            }
        }

        if(description == null || description.equals("")) {
            description = tr.getMethod().getDescription();
        }
        LogUtils.info("##################### START #####################");
        LogUtils.info("Description: " + description);
        LogUtils.info("Thread: " + Thread.currentThread().getName());

        // set description of the test
        tr.getMethod().setDescription(description);
    }

	@Override
	public void onTestSuccess(ITestResult tr) {
	    LogUtils.info("");
        LogUtils.info("Excecution time: " + (tr.getEndMillis() - tr.getStartMillis()) /1000 + " seconds");
        LogUtils.info(("Priority of this method is " + tr.getMethod().getPriority()));
        LogUtils.info(("###################### END ###################### "));
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    StackTraceElement stack[] = result.getThrowable().getStackTrace();
        Arrays.asList(stack).stream().forEach(s -> {
            LogUtils.warn(s.toString());
        });

        long end = result.getEndMillis();
        LogUtils.info("Excecution time: " + ((end - (long)result.getStartMillis()) / 1000) + " seconds");
        if(result.getMethod().getDescription() != null) {
            LogUtils.info("Test '" + result.getMethod().getMethodName() + "(" + result.getMethod().getDescription() + ")" + " - FAILED");
        }

        LogUtils.info("");
        LogUtils.info("Excecution time: " + (result.getEndMillis() - result.getStartMillis()) /1000 + " seconds");
        LogUtils.info(("Priority of this method is " + result.getMethod().getPriority()));
        LogUtils.info(("###################### END ###################### "));

    }

	@Override
	public void onTestSkipped(ITestResult tr) {
        LogUtils.info("Test " + tr.getMethod().getMethodName() +  "(" + tr.getMethod().getDescription() + ")" + " - SKIPPED");
	}

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	    //testResult.getMethod().setDescription(method.getTestMethod().get);
    }
}