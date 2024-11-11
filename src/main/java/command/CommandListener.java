package command;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    public CommandListener(CommandManager... commandManagers) {
        listener.addAll(Arrays.asList(commandManagers));
    }

    private List<CommandManager> listener = new ArrayList<>();
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String eventName = event.getName();
        for (CommandManager commandManager : listener) {
            if (commandManager.supports(eventName)) {
                commandManager.execute(event);
            }
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(
                Commands.slash("힌트", "문제에 대한 힌트를 제공합니다")
                        .addOption(OptionType.STRING, "주소", "힌트를 받을 문제의 사이트 주소를 입력합니다")
        );

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}
