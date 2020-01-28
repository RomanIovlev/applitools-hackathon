package applitools.hackathon.pages;

import applitools.hackathon.custom.Chart;
import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.jdi.light.ui.html.elements.common.Image;
import com.epam.jdi.light.ui.html.elements.common.Link;

public class ChartPage extends WebPage {
    @UI("#addDataset") public static Link showNextYear;
    @UI("#canvas") public static Image expensesChart;
    public static Chart chartData = new Chart();
}
