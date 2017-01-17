import client.ExecuteQuery;
import helpers.Presenter;
import model.Nbp;
import retrofit2.Response;
import settings.Lang;
import settings.Settings;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by trot on 14.01.17.
 */
public class Gold {
    public static void main(String[] args) {

        Integer daysCount = Settings.CONGIG.getDaysCount();
        LocalDate dateTo = LocalDate.now();
        LocalDate dateFrom = dateTo.minusDays(daysCount);

        System.out.println(Lang.TODAY_IS + dateTo);

        ExecuteQuery executeQuery = new ExecuteQuery();
        executeQuery.setDates(dateFrom, dateTo);
        executeQuery.execute();
        Response<List<Nbp>> response = executeQuery.getQueryResponse();
        Presenter presenter = new Presenter(response);

        presenter.showResultsToScreen();
        System.err.println(Lang.WARNING_IT_IS_NOT_ADVICE);

        presenter.saveResultsToFile("output.xls");
    }
}
