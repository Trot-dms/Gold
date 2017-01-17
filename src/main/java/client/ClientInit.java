package client;

import model.Nbp;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by trot on 14.01.17.
 */
public class ClientInit {

    private final String URL = "http://api.nbp.pl";

    public NbpService setSyncClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NbpService nbpService = retrofit.create(NbpService.class);
        return nbpService;
    }

}
