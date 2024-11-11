package api;

import bot.Bot;

public class AiHandler {

    private final String apiKey;

    public AiHandler(Bot bot) {
        this.apiKey = bot.getConfig().getOpenAiToken();
    }
}
