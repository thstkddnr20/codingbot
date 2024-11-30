package command;

import api.AiHandler;
import java.util.List;

public class CommandFactory {

    private final AiHandler aiHandler;

    public CommandFactory(AiHandler aiHandler) {
        this.aiHandler = aiHandler;
    }

    public List<CommandManager> createCommands() {
        return List.of(
                new HintCommand(aiHandler),
                new TimeComplexityCommand(aiHandler),
                new AlgorithmCommand(aiHandler),
                new ImprovementCommand(aiHandler)


//                new CounterExampleCommand(aiHandler)
        );
    }

}
