package applitools.hackathon.tests.visualTests;

import applitools.hackathon.VisualTestsInit;
import applitools.hackathon.entities.User;
import applitools.hackathon.test.data.TestDataProvider;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.fluent.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static applitools.hackathon.pages.AppPage.*;
import static applitools.hackathon.pages.ApplitoolsSite.*;
import static applitools.hackathon.pages.ChartPage.*;

public class VisualTests extends VisualTestsInit {
    @BeforeMethod
    public void before() {
        loginPage.open();
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
        eyes.check("Alert: " + message, Target.region(By.cssSelector(".alert-warning")));
        //eyes.checkWindow("Failed login: " + message);
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
        WebElement chart = expensesChart.core().getWebElement();
        eyes.checkElement(chart, "Expenses Chart 2017-2018");
        showNextYear.click();
        eyes.checkElement(chart, "Expenses Chart 2017-2019");
    }

    @Test(suiteName = "Dynamic Content Test")
    public void dynamicAdTest() {
        loginPage.open("showAd=true");
        loginForm().loginAs(new User());
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.checkElement(advertisements.core().getWebElement(), "Dynamic Advertisement");
    }
}
