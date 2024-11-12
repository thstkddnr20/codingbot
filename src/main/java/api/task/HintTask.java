package api.task;

import api.PromptList;

public class HintTask extends Task {

    public HintTask(String option) {
        super(option, PromptList.HINT_PROMPT);
    }

}
