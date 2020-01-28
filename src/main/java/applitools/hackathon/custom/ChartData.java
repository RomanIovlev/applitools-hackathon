package applitools.hackathon.custom;

import java.util.List;

public class ChartData {
    public List<String> labels;
    public List<DataSet> dataset;
    public DataSet getByLabel(String dataSetLabel) {
        for (DataSet ds : dataset)
            if (ds.label.equals(dataSetLabel))
                return ds;
        return null;
    }
}
