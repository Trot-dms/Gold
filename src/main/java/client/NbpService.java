package client;

import model.Nbp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by trot on 14.01.17.
 */

public interface NbpService {

    //http://api.nbp.pl/api/cenyzlota/last/2/?format=json
    @GET("api/cenyzlota/{fromDate}/{toDate}/?format=json")
    Call<List<Nbp>> getGoldPrice(@Path(value = "fromDate")LocalDate fromDate,
                                 @Path(value = "toDate")LocalDate toDate);

}
