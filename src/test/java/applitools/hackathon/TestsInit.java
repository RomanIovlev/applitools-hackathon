package applitools.hackathon;

import applitools.hackathon.pages.ApplitoolsSite;
import org.testng.annotations.*;

import static applitools.hackathon.pages.ApplitoolsSite.loginPage;
import static com.epam.jdi.light.driver.WebDriverUtils.killAllSeleniumDrivers;
import static com.epam.jdi.light.elements.init.PageFactory.initSite;
import static com.epam.jdi.tools.PropertyReader.readProperties;

public class TestsInit {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        initSite(ApplitoolsSite.class);
        if (readProperties().getProperty("environment").equals("V2"))
            loginPage.url = "https://demo.applitools.com/hackathonV2.html";
    }
    @AfterSuite(alwaysRun = true)
    public static void teardown() {
        killAllSeleniumDrivers();
    }
}
