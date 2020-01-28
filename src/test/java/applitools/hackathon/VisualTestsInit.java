package applitools.hackathon;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.selenium.Eyes;
import com.epam.jdi.tools.Timer;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import static com.applitools.eyes.selenium.StitchMode.SCROLL;
import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static java.lang.String.format;

public class VisualTestsInit {
    protected static Eyes eyes;
    protected static final String appName = "Applitools Hackathon";

    protected static String startUrl = "https://demo.applitools.com/hackathon.html";

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        TestsInit.setUp();
        setupEyes();
    }

    private static void setupEyes() {
        eyes = new Eyes();
        try {
            eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
        } catch (Exception ignore) {
            throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
        }
        eyes.setBatch(new BatchInfo("Hackathon Roman Iovlev:" + Timer.nowDate()));
        eyes.setForceFullPageScreenshot(true);
        eyes.setStitchMode(SCROLL);
    }

    @BeforeMethod
    public void beforeEyes(Method method) {
        String batchName = format("%s.%s", method.getDeclaringClass().getSimpleName(), method.getName());
        eyes.open(getDriver(), appName, batchName);
    }

    @AfterMethod
    public void afterEyes() {
        try {
            if (eyes.getIsOpen())
                eyes.close(false);
        } finally {
            if (eyes.getIsOpen())
                eyes.abortIfNotClosed();
        }
    }
    @AfterSuite(alwaysRun = true)
    public static void teardown() {
        killAllSeleniumDrivers();
    }
}
