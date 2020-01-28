package applitools.hackathon.tests.traditionalTests;

import applitools.hackathon.TestsInit;
import applitools.hackathon.custom.ChartData;
import applitools.hackathon.entities.*;
import applitools.hackathon.test.data.TestDataProvider;
import applitools.hackathon.utils.Utils;
import com.epam.jdi.light.elements.complex.table.Line;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.tools.Timer;
import org.testng.annotations.*;
import java.util.List;
import static applitools.hackathon.pages.AppPage.*;
import static applitools.hackathon.pages.ApplitoolsSite.*;
import static applitools.hackathon.pages.ChartPage.*;
import static applitools.hackathon.utils.Utils.toArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class TraditionalTests extends TestsInit {
    @BeforeMethod
    public void before() {
        loginPage.open();
    }

    @Test(suiteName = "Login Page UI Elements Test")
    public void loginPageValidation() {
        loginPage.validatateForm();
        loginPage.alert.is().hidden();
    }

    @Test(suiteName = "Data-Driven Test", dataProvider = "correctUsers", dataProviderClass = TestDataProvider.class)
    public void loginSuccessValidation(User user) {
        loginForm().loginAs(user);
        appPage.checkOpened();
    }

    @Test(suiteName = "Data-Driven Test", dataProvider = "emptyUsers", dataProviderClass = TestDataProvider.class)
    public void loginFailedValidation(User user, String message) {
        loginForm().loginAs(user);
        loginPage.validatateAlert(message);
    }

    @Test(suiteName = "Table Sort Test")
    public void sortTableValidation() {
        loginForm().loginAs(new User());
        List<Transaction> unsortedTransactions = transactionsTable.allData();
        List<Line> images = transactionsTable.rowsImages();

        transactionsTable.headerUI().select("AMOUNT");
        transactionsTable.assertThat()
            .rows(hasItems(toArray(unsortedTransactions)))
            .sortedBy((prev,next) -> prev.amount.value() < next.amount.value())
            .rowsVisualValidation("Description", images);

        transactionsTable.headerUI().select("AMOUNT");
        transactionsTable.assertThat()
            .rows(hasItems(toArray(unsortedTransactions)))
            .sortedBy((prev,next) -> prev.amount.value() > next.amount.value())
            .rowsVisualValidation("Description", images);
    }

    @Test(suiteName = "Canvas Chart Test")
    public void canvasChartTest() {
        loginForm().loginAs(new User());
        compareExpenses.click();
        ChartData chartBefore = chartData.get();
        WebPage.zoom(0.8);
        Timer.sleep(1000);
        expensesChart.visualValidation("Expense Chart 2017-2018");
        showNextYear.click();
        Timer.sleep(1000);
        ChartData chartAfter = chartData.get();
        assertThat(chartBefore.labels, equalTo(chartAfter.labels));
        assertEquals(chartBefore.getByLabel("2017"),
                      chartAfter.getByLabel("2017"));
        assertEquals(chartBefore.getByLabel("2018"),
                      chartAfter.getByLabel("2018"));
        expensesChart.visualValidation("Expense Chart 2017-2019");
        WebPage.zoom(1);
    }

    @Test(suiteName = "Dynamic Content Test")
    public void dynamicAdTest() {
        loginPage.open("showAd=true");
        loginForm().loginAs(new User());
        advertisement.has().size(3)
            .all().elements(Utils::advertismentPresent);
    }
}
