//main 메서드가 들어가는 클래스
//실제 정보들은 bot.Bot 클래스에서 관리 될 예정
//main 메서드에서 BotConfig의 정보를 Bot에 저장하고 JDA를 생성하는 역할을 할 예정이다

import api.AiHandler;
import bot.Bot;
import command.CommandListener;
import command.HintCommand;
import command.TimeComplexityCommand;
import config.BotConfig;
import config.ConfigHandler;
import exception.ConfirmFailedException;
import helper.JdaHelper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class CodingBot {
    public static void main(String[] args) {

        BotConfig config = new ConfigHandler().load();
        Bot bot = new Bot(config);
        new AiHandler(bot);

        JDA jda = JDABuilder.createLight(config.getToken())
                .addEventListeners(new CommandListener(
                new HintCommand(),
                new TimeComplexityCommand()

                )
                        ).build();

        //JDA 확인받기
        try {
            JdaHelper.confirmJda(jda);
        } catch (ConfirmFailedException e) {
            System.out.println("JDA 검증 실패: " + e.getMessage());
            jda.shutdown();
            System.exit(1);
        }



    }
}
