package command;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    public CommandListener(List<CommandManager> commands) {
        listener.addAll(commands);
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
        commandData.addAll(
                List.of(Commands.slash("힌트", "문제에 대한 힌트를 제공합니다")
                                .addOption(OptionType.STRING, "주소", "힌트를 받을 문제의 사이트 주소를 입력합니다", true),
                        Commands.slash("시간복잡도", "작성한 코드의 시간복잡도를 계산합니다")
                                .addOption(OptionType.STRING, "코드", "시간복잡도를 계산할 코드를 입력합니다", true),
                        Commands.slash("알고리즘", "알고리즘에 대한 설명을 받습니다")
                                .addOption(OptionType.STRING, "알고리즘", "설명을 받을 알고리즘의 이름을 작성합니다", true),
                        Commands.slash("반례", "작성한 코드를 문제와 비교하여 반례를 찾습니다")
                                .addOption(OptionType.STRING, "주소", "문제의 주소를 입력합니다", true)
                                .addOption(OptionType.STRING, "코드", "작성한 코드를 입력합니다", true),
                        Commands.slash("개선점", "작성한 코드의 개선할 점을 파악하여 알려줍니다")
                                .addOption(OptionType.STRING, "코드", "작성한 코드를 입력합니다", true)
                )
        );

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}
