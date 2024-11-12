package api;

import api.entity.Option;
import api.entity.Prompt;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {

    public Task(Option option, Prompt prompt) {
        this.option = option;
        this.prompt = prompt;
    }

    public Task(Prompt prompt) {
        this.prompt = prompt;
        this.option = Option.of("");
    }

    private final Option option;
    private final Prompt prompt;

    @Override
    public String call() throws Exception {
        String content = prompt.toString() + option.toString();
        return AiHandler.requestApi(content);
    }
}
