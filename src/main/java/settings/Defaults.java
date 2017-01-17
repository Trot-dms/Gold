package settings;

/**
 * Created by trot on 15.01.17.
 */
public enum Defaults {
    DAYS_COUNT("default.daysCount"),
    DEFAULT_DAYS_COUNT("10"),
    DAYS_TO_ADVICE("default.daysToAdvice"),
    DEFAULT_DAYS_TO_ADVICE("3"),
    RECOMMENDED_PROCENT("default.recommendedProcent"),
    DEFAULT_RECOMMENDED_PROCENT("5"),
    XLS_OUTPUT_FILE("default.xlsOutputFileName"),
    XLS_SAVE_PATH("default.xlsSavePath"),
    DEFAULT_XLS_OUTPUT_FILE("output.xls"),
    DEFAULT_XLS_PATH("/home/");
    private String defaults;

    Defaults(String defaults) {
        this.defaults = defaults;
    }

    public String get() {
        return defaults;
    }

}
