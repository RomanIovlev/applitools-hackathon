package applitools.hackathon.custom;

import com.google.gson.Gson;

import static com.epam.jdi.light.driver.WebDriverFactory.jsExecute;

public class Chart {
    static Gson gson = new Gson();
    public ChartData get() {
        Object rowChartData = jsExecute("return { " +
            "labels:  window.barChartData.labels, " +
            "dataset: window.barChartData.datasets.map(ds => ({ " +
                "bgColor: ds.backgroundColor, " +
                "borderColor: ds.borderColor, " +
                "label: ds.label, " +
                "data: ds.data })) " +
            "}");
        return gson.fromJson(gson.toJson(rowChartData), ChartData.class);
    }
}
