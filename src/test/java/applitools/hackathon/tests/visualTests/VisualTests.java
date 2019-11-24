package applitools.hackathon.tests.visualTests;

/**
 * Created by Roman Iovlev on 10.11.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import applitools.hackathon.VisualTestsInit;
import applitools.hackathon.entities.User;
import applitools.hackathon.test.data.TestDataProvider;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.fluent.Target;
import com.epam.jdi.light.elements.composite.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static applitools.hackathon.pages.AppPage.*;
import static applitools.hackathon.pages.ApplitoolsSite.appPage;
import static applitools.hackathon.pages.ApplitoolsSite.loginForm;
import static applitools.hackathon.pages.ChartPage.expensesChart;
import static applitools.hackathon.pages.ChartPage.showNextYear;
import static com.applitools.eyes.selenium.fluent.Target.*;
import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;

public class VisualTests extends VisualTestsInit {
    @BeforeMethod
    public void before() {
        WebPage.openUrl(startUrl);
    }

    @Test(suiteName = "Login Page UI Elements Test")
    public void loginPageValidation() {
        eyes.checkWindow("Login Page view");
    }

    @Test(suiteName = "Data-Driven Test",
            dataProvider = "correctUsers", dataProviderClass = TestDataProvider.class)
    public void loginSuccessValidation(User user) {
        loginForm().loginAs(user);
        appPage.checkOpened();
        eyes.checkWindow("App Page view");
    }

    @Test(suiteName = "Data-Driven Test",
            dataProvider = "emptyUsers", dataProviderClass = TestDataProvider.class)
    public void loginFailedValidation(User user, String message) {
        loginForm().loginAs(user);
        eyes.checkWindow("Failed login: " + message);
    }

    @Test(suiteName = "Table Sort Test")
    public void sortTableValidation() {
        loginForm().loginAs(new User());
        transactionsTable.headerUI().select("AMOUNT");
        WebElement transactions = transactionsTable.core().getWebElement();
        eyes.checkElement(transactions, "Transactions Ascending");

        transactionsTable.headerUI().select("AMOUNT");
        eyes.checkElement(transactions, "Transactions Descending");
    }

    @Test(suiteName = "Canvas Chart Test")
    public void canvasChartTest() {
        loginForm().loginAs(new User());
        compareExpenses.click();
        WebElement chart = transactionsTable.core().getWebElement();
        eyes.checkElement(chart, "Expenses Chart 2017-2018");
        showNextYear.click();
        eyes.checkElement(chart, "Expenses Chart 2017-2019");
    }

    @Test(suiteName = "Dynamic Content Test")
    public void dynamicAdTest() {
        WebPage.openUrl(startUrl+"?showAd=true");
        loginForm().loginAs(new User());
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.checkElement(advertisements.core().getWebElement(), "Dynamic Advertisement");
    }
}
