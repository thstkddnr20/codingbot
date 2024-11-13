package command;

import api.AiHandler;
import api.entity.Option;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class AlgorithmCommand implements CommandManager {

    private final AiHandler aiHandler;

    public AlgorithmCommand(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }

    @Override
    public String getEventName() {
        return "알고리즘";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping option = event.getOption("알고리즘");
        if (option == null) {
            event.getHook().sendMessage("알고리즘을 입력해주세요").queue();
            return;
        }

        String algorithm = option.getAsString();

        String result = aiHandler.getAlgorithmExplanation(Option.of(algorithm));
        event.getHook().sendMessage(result).queue();
    }
}
