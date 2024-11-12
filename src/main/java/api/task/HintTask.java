package api.task;

import api.AiHandler;

import java.util.concurrent.Callable;

public class HintTask implements Callable<String> {

    public HintTask(String address) {
        this.address = address;
    }

    private final static String PROMPT = "There are coding problem at the given address and you are a helper who helps you solve coding problem, you should only provide hints. " +
            "do not answers to the problem and do not make the example for the problem. Answer in KOREAN. address: ";
    private final String address;

    @Override
    public String call() throws Exception {
        String content = PROMPT + address;
        return AiHandler.requestApi(content);
    }
}
