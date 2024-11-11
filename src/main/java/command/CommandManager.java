package command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface CommandManager {

    String getEventName();

    default boolean supports(String eventName) {
        return eventName.equals(getEventName());
    }

    void execute(SlashCommandInteractionEvent event);
}
