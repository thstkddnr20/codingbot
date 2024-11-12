package api;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    public Task(String option, String prompt) {
        this.option = option;
        this.prompt = prompt;
    }

    public Task(String prompt) {
        this.prompt = prompt;
        this.option = "";
    }

    private final String option;
    private final String prompt;

    @Override
    public String call() throws Exception {
        String content = prompt + option;
        return AiHandler.requestApi(content);
    }
}
