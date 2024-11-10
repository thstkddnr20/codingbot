package config;
//봇에 대한 모든 config를 저장해놓을 클래스이다
//사용자는 config.txt에 config 정보를 작성하면 이 클래스로 config 정보를 불러오게 된다

public class BotConfig {

    private String token, openAiToken;

    public BotConfig(String token, String openAiToken) {
        this.token = token;
        this.openAiToken = openAiToken;
    }

    public String getToken() {
        return token;
    }

    public String getOpenAiToken() {
        return openAiToken;
    }

    @Override
    public String toString() {
        return "BotConfig{" +
                "token='" + token + '\'' +
                ", openAiToken='" + openAiToken + '\'' +
                '}';
    }
}
