package api.entity;

public class Option {
    private final String option;

    private Option(String option) {
        this.option = option;
    }

    public static Option of(String option) {
        return new Option(option);
    }

    @Override
    public String toString() {
        return option;
    }
}
