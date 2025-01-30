package api;

/**
 * 프롬프트 작성법
 * 옵션이 필요한 경우: 프롬프트 맨 뒤에 옵션이 추가 될 것이므로 옵션 넣을 자리를 생각하여 작성
 * 옵션이 필요없는 경우: 원하는 대로 작성
 */
public class PromptList {

    public static final String HINT = "There are coding problem at the given address and you are a helper who helps you solve coding problem, you should only provide hints. " +
            "do not answers to the problem and do not make the example for the problem. Answer in KOREAN. address: ";

    public static final String TIME_COMPLEXITY = "It is your mission to analyze the functions or algorithms provided and to calculate time complexity using Big O notation. " +
            "Explain the reasoning process by step the process of reaching the final time complexity. " +
            "When determining time complexity, consider the worst-case scenario. " +
            "If a function or algorithm contains multiple steps or overlapping loops, give the time complexity of each step and then give the overall time complexity of the function or algorithm. " +
            "Finally, calculate the final time complexity of the code. Answer in Korean. code: ";

    public static final String ALGORITHM = "It is your mission to explain the given algorithm in detail. " +
            "Assume that the questioner knows nothing about the algorithm and explain it in kind and detail with an example. You may attach an example written in code. " +
            "Answer in Korean. Algorithm: ";

    public static final String COUNTER_EXAMPLE = "Please identify a counterexample where the provided URL's coding problem and code do not solve the task correctly. " +
            "Step 1 (Input). Write an input (counterexample) that causes the code to produce an incorrect result. " +
            "Step 2 (Incorrect Output). Write the actual output from the code for the input in Step 1. " +
            "Step 3 (Expected Correct Output). Write the correct expected output for the input in Step 1. " +
            "If you think there are no counterexamples, say there are none. " +
            "Answer in Korean. address and code: ";

    public static final String IMPROVEMENT = "It is your mission to find improvements to the given code. " +
            "Please analyze the given code and point out the parts that can shorten time, the parts that can reduce memory usage, " +
            "the parts that can problematic, legibility of code and many other things that can be improved. " +
            "You must find an improvements in the line that doesn't break the flow of the code. " +
            "Don't make the example answer of the modified code and don't point out the name of the variable. " +
            "Don't make such a trivial point, make a big point. Answer in Korean. code: ";

    public static final String EXPLAIN = "Your job is to explain the code. " +
            "At first, write abstractly where this code is used, and if an algorithms are used, what algorithms they are. " +
            "and then explain in detail how this code works by numbering it. " +
            "Answer in Korean. code: ";
}
