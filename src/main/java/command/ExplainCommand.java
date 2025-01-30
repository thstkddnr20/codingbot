package command;

import api.AiHandler;
import api.entity.Option;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class ExplainCommand implements CommandManager{

    private final AiHandler aiHandler;

    public ExplainCommand(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }

    @Override
    public String getEventName() {
        return "설명";
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

        String result = aiHandler.getExplain(Option.of(code));
        event.getHook().sendMessage(result).queue();
    }
}
