package helpers;

import model.Nbp;
import model.Recommendation;
import retrofit2.Response;
import settings.Lang;
import settings.Settings;
import xls.XlsManipulator;

import java.io.IOException;
import java.util.List;

/**
 * Created by trot on 15.01.17.
 */

public class Presenter {

    private Response<List<Nbp>> response;
    private Recommendation r;

    private Double averageValue;

    public Presenter(Response<List<Nbp>> response) {
        this.response = response;
        r = new Recommendation();
        setAverageAndRecomendation(response);
    }

    public void showResultsToScreen() {
        System.out.println(Lang.HISTORY_NOTES);
        response.body().stream()
                .forEach(x -> System.out.println(Lang.DATE + x.getData()
                        + ", "+ Lang.PRICE + x.getCena()));
        System.out.println(Lang.AVERAGE_FROM_ALL_TIME + averageValue);
        System.out.println(String.format(Lang.AVERAGE_FROM_DAYS, + r.recommendationDays) + r.recommendationValue);
        System.out.println(Lang.RECOMMENDATION + r.recommendation);
    }

    public void saveResultsToFile(String filename) {

        XlsManipulator xlsManipulator = new XlsManipulator();
        xlsManipulator.setXlsOutputFileName(filename);
        try {
            xlsManipulator.setRecommendation(r);
            xlsManipulator.createXls(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAverageAndRecomendation(Response<List<Nbp>> response) {
        r.recommendationDays = Settings.CONGIG.getDaysToAdvice();
        AverageValuesHelper helper = new AverageValuesHelper();
        averageValue = helper.getAverageValue(response);
        r.recommendationValue = helper.getAverageBasedOnDays(Settings.CONGIG.getDaysToAdvice(), response);
        r.recommendation = helper.checkRecomendation(r.recommendationValue, averageValue);
    }

}
