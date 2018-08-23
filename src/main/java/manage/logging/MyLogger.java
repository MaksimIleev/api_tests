package manage.logging;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import utils.PropertyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyLogger {

    private static Logger logger;

    static {init();}

    public static void init() {
        try {
            FileUtils.deleteDirectory(new File(PropertyUtils.get("logs_output")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new File(PropertyUtils.get("logs_output")).mkdir();

        System.setProperty("log4j.configurationFile", System.getProperty("user.dir") + "/" + PropertyUtils.get("log4j.configurationFile"));

        File logsDir = new File(System.getProperty("user.dir") + PropertyUtils.get("logs_output"));
        if(logsDir.exists()) {
            try {
                FileUtils.deleteDirectory(logsDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ConfigurationFactory factory = XmlConfigurationFactory.getInstance();
        ConfigurationSource source = null;
        try {
            source = new ConfigurationSource(new FileInputStream(new File(System.getProperty("log4j.configurationFile"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration config = factory.getConfiguration(LoggerContext.getContext(), source);
        ConsoleAppender appender = ConsoleAppender.createDefaultAppenderForLayout(PatternLayout.createDefaultLayout());
        config.addAppender(appender);

        LoggerConfig loggerConfig = new LoggerConfig("apis", Level.INFO, false);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger("apis", loggerConfig);

        LoggerContext context = new LoggerContext("TestContext");
        context.start(config);

        logger = context.getRootLogger();
    }

    public static Logger log() {
        return logger;
    }
}
