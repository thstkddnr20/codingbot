package bot;//Bot에 대한 config와 설정을 모두 관리하는 클래스이다
//모든 정보는 이 bot.Bot 클래스를 통하여 이용된다고 보면된다

import config.BotConfig;

public class Bot {

    private BotConfig config;

    public Bot(BotConfig config) {
        this.config = config;
    }

    public BotConfig getConfig() {
        return config;
    }
}
