package api;

/**
 * 프롬프트 작성법
 * 옵션이 필요한 경우: 프롬프트 맨 뒤에 옵션이 추가 될 것이므로 옵션 넣을 자리를 생각하여 작성
 * 옵션이 필요없는 경우: 원하는 대로 작성
 */
public class PromptList {

    public static final String HINT_PROMPT = "There are coding problem at the given address and you are a helper who helps you solve coding problem, you should only provide hints. " +
            "do not answers to the problem and do not make the example for the problem. Answer in KOREAN. address: ";
}
