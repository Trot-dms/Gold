package helpers;

import model.Nbp;
import retrofit2.Response;
import settings.Settings;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by trot on 14.01.17.
 */
public class AverageValuesHelper {

    private static final double DIFFRENT_VAL = Settings.CONGIG.getRecommendedProcent() / 100;

    public AverageValuesHelper() {
    }

    public Double getAverageBasedOnDays(Integer averageDaysToAdvice, Response<List<Nbp>> response) {
        OptionalDouble value = response.body().stream()
                .mapToDouble(a -> a.getCena())
                .limit(averageDaysToAdvice)
                .average();
        return value.isPresent() ? value.getAsDouble() : 0;
    }

    public Double getAverageValue(Response<List<Nbp>> response) {
        OptionalDouble value = response.body().stream()
                .mapToDouble(a -> a.getCena()).average();
        return value.isPresent() ? value.getAsDouble() : 0;
    }

    public String checkRecomendation(Double actualPrice, Double averagePrice) {

        if (actualPrice > averagePrice) {
            double diff = actualPrice - averagePrice;
            return diff > DIFFRENT_VAL * actualPrice ? "SPRZEDAJ" : "TRZYMAJ";
        } else {
            return "KUPUJ";
        }
    }
}
