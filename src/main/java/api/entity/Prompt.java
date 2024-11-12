package api.entity;

public class Prompt {
    private final String prompt;

    private Prompt(String prompt) {
        this.prompt = prompt;
    }

    public static Prompt of(String prompt) {
        return new Prompt(prompt);
    }

    @Override
    public String toString() {
        return prompt;
    }
}
