package command;

import api.AiHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HintCommand implements CommandManager {

    private final AiHandler aiHandler;

    public HintCommand(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }

    @Override
    public String getEventName() {
        return "힌트";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping option = event.getOption("주소");
        if (option == null) {
            event.getHook().sendMessage("주소를 입력해주세요").queue();
            return;
        }
        String address = option.getAsString();

        try {
            String hint = aiHandler.getHint(address);
            event.getHook().sendMessage(hint).queue();
        } catch (ExecutionException | InterruptedException e) {
            event.getHook().sendMessage("처리중 오류가 발생했습니다").queue();
        }

    }
}
