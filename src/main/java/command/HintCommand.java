package command;


import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HintCommand implements CommandManager {

    @Override
    public String getEventName() {
        return "힌트";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("hi hint").queue();
    }
}
