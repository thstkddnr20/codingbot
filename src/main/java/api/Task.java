package api;

import api.entity.Option;
import api.entity.Prompt;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    public Task(Option option, Prompt prompt) {
        this.option2 = Option.of("");
        this.option1 = option;
        this.prompt = prompt;
    }

    public Task(Prompt prompt) {
        this.prompt = prompt;
        this.option1 = Option.of("");
        this.option2 = Option.of("");
    }

    public Task(Option option1, Option option2, Prompt prompt) {
        this.option2 = option2;
        this.option1 = option1;
        this.prompt = prompt;
    }

    private final Option option1;
    private final Option option2;
    private final Prompt prompt;

    @Override
    public String call() throws Exception {
        String content = prompt.toString() + option1.toString() + " " + option2.toString();
        return AiHandler.requestApi(content);
    }
}
