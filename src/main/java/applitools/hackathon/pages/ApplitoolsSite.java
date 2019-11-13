package applitools.hackathon.pages;

/**
 * Created by Roman Iovlev on 10.11.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import applitools.hackathon.entities.User;
import applitools.hackathon.sections.LoginPage;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.JSite;
import com.epam.jdi.light.elements.pageobjects.annotations.Title;
import com.epam.jdi.light.elements.pageobjects.annotations.Url;

import static com.epam.jdi.light.common.CheckTypes.MATCH;

@JSite("https://demo.applitools.com")
public class ApplitoolsSite {
    @Url(value = "/hackathon.html", template = ".*\\/hackathon(V2)?\\.html.*", validate = MATCH)
    @Title("ACME demo app")
    public static LoginPage loginPage;
    @Url(value = "/hackathonApp.html%s", template = ".*\\/hackathonApp(V2)?\\.html.*", validate = MATCH) @Title("ACME demo app")
    public static AppPage appPage;
    @Url(value = "/hackathonChart.html", template = ".*\\/hackathonChart(V2)?\\.html.*", validate = MATCH) @Title("ACME demo app")
    public static ChartPage chartPage;
    public static Form<User> loginForm() { return loginPage.asForm(); }
}
