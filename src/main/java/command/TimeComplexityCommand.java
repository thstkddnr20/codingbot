package command;

import api.AiHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.concurrent.ExecutionException;

public class TimeComplexityCommand implements CommandManager {

    private final AiHandler aiHandler;

    public TimeComplexityCommand(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }
    @Override
    public String getEventName() {
        return "시간복잡도";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping option = event.getOption("코드");
        if (option == null) {
            event.getHook().sendMessage("코드를 입력해주세요").queue();
            return;
        }

        String code = option.getAsString();

        try {
            String timeComplexity = aiHandler.getTimeComplexity(code);
            event.getHook().sendMessage(timeComplexity).queue();
        } catch (ExecutionException | InterruptedException e) {
            event.getHook().sendMessage("처리중 오류가 발생했습니다").queue();
        }


    }
}
