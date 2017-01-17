package client;

import model.Nbp;
import retrofit2.Call;
import retrofit2.Response;
import settings.Lang;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by trot on 16.01.17.
 */
public class ExecuteQuery {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Response<List<Nbp>> queryResponse;

    public void execute() {
        NbpService nbpService = new ClientInit().setSyncClient();
        Call<List<Nbp>> nbpGold = nbpService.getGoldPrice(dateFrom, dateTo);

        try {
            Response<List<Nbp>> response = nbpGold.execute();
            if (response != null && response.isSuccessful() && response.errorBody() == null) {
                queryResponse = response;
            } else {
                System.err.println(Lang.ERROR_NO_RESPONSE + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setDates(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Response<List<Nbp>> getQueryResponse() {
        return queryResponse;
    }
}
