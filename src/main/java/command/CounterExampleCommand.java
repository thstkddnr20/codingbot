package command;

import api.AiHandler;
import api.entity.Option;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class CounterExampleCommand implements CommandManager{

    private final AiHandler aiHandler;

    public CounterExampleCommand(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }

    @Override
    public String getEventName() {
        return "반례";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        OptionMapping addressOption = event.getOption("주소");
        OptionMapping codeOption = event.getOption("코드");

        if (addressOption == null || codeOption == null) {
            event.getHook().sendMessage("올바른 옵션을 입력해주세요").queue();
            return;
        }

        String address = addressOption.getAsString();
        String code = codeOption.getAsString();

        String result = aiHandler.getCounterExample(Option.of(address), Option.of(code));
        event.getHook().sendMessage(result).queue();
    }
}
