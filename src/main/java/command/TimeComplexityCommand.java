package command;

import api.AiHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
        System.out.println("hi timeComplexity");
    }
}
