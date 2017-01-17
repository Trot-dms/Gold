import helpers.AverageValuesHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by trot on 14.01.17.
 */
public class BasicTests {

    @Test
    public void testCheckRecomendation() {
        AverageValuesHelper presenter = new AverageValuesHelper();

        assertEquals("KUPUJ", presenter.checkRecomendation(10d, 10d));
    }
}
