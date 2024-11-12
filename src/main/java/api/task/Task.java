package api.task;

import api.AiHandler;

import java.util.concurrent.Callable;

public abstract class Task implements Callable<String> {

    public Task(String option, String prompt) {
        this.option = option;
        this.prompt = prompt;
    }

    private final String option;
    private final String prompt;

    @Override
    public String call() throws Exception {
        String content = prompt + option;
        return AiHandler.requestApi(content);
    }
}
