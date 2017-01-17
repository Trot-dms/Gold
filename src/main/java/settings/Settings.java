package settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by trot on 15.01.17.
 */
public enum Settings {
    CONGIG;

    private final Properties props;
    private InputStream config = null;

    Settings() {
        props = new Properties();
        config = Settings.class.getResourceAsStream("/default.conf");
        try {
            props.load(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getDaysCount() {
        Integer value = Optional.ofNullable(Integer.parseInt(props.getProperty(Defaults.DAYS_COUNT.get())))
                .orElse(Integer.parseInt(Defaults.DEFAULT_DAYS_COUNT.get()));
        return value;
    }

    public Integer getDaysToAdvice() {
        Integer value = Optional.ofNullable(Integer.parseInt(props.getProperty(Defaults.DAYS_TO_ADVICE.get())))
                .orElse(Integer.parseInt(Defaults.DEFAULT_DAYS_TO_ADVICE.get()));
        return value;
    }

    public Double getRecommendedProcent() {
        Double value = Optional.ofNullable(Double.parseDouble(props.getProperty(Defaults.RECOMMENDED_PROCENT.get())))
                .orElse(Double.parseDouble(Defaults.DEFAULT_RECOMMENDED_PROCENT.get()));
        return value;
    }

    public String getSaveFileName() {
        String value = Optional.ofNullable(props.getProperty(Defaults.XLS_OUTPUT_FILE.get()))
                .orElse(Defaults.DEFAULT_XLS_OUTPUT_FILE.get());
        return value;
    }

    public String getOutputPath() {
        String value = Optional.ofNullable(props.getProperty(Defaults.XLS_SAVE_PATH.get()))
                .orElse(Defaults.DEFAULT_XLS_PATH.get());
        return value;
    }
}
