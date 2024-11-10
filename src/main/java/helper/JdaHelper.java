package helper;

import exception.ConfirmFailedException;
import net.dv8tion.jda.api.JDA;

//모든 method가 true를 반환하면 confirm이 완료된다
public class JdaHelper {

    public static void confirmJda(JDA jda) {
        checkPublic(jda);
    }

    private static void checkPublic(JDA jda) {
        if (jda.retrieveApplicationInfo().complete().isBotPublic()) {
            throw new ConfirmFailedException("봇이 Public으로 설정되어 있습니다");
        }
    }
}
