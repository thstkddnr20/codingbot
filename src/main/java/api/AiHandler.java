package api;

import api.entity.Option;
import api.entity.Prompt;
import bot.Bot;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class AiHandler {

    private static Bot bot;
    public static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final Integer MAX_TOKENS = 1000;
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(2, 3, 600, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

    public AiHandler(Bot bot) {
        AiHandler.bot = bot;
    }

    public String getHint(Option address) {
        return doTask(address, Prompt.of(PromptList.HINT));
    }

    public String getTimeComplexity(Option code) {
        return doTask(code, Prompt.of(PromptList.TIME_COMPLEXITY));
    }

    public String getAlgorithmExplanation(Option algorithm) {
        return doTask(algorithm, Prompt.of(PromptList.ALGORITHM));
    }

    public String getCounterExample(Option address, Option code) {
        return doTask(address, code, Prompt.of(PromptList.COUNTER_EXAMPLE));
    }

    public String getImprovement(Option code) {
        return doTask(code, Prompt.of(PromptList.IMPROVEMENT));
    }

    public static String requestApi(String prompt) throws IOException {
        URL url = new URL(API_URL);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + bot.getConfig().getOpenAiToken());

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("max_tokens", MAX_TOKENS);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.put(message);
        requestBody.put("messages", messages);

        // 요청 본문 전송
        OutputStream os = conn.getOutputStream();
        byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);

        // 응답 데이터 읽기
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        return parseJson(response.toString());
    }

    private static String parseJson(@NotNull String raw) {
        JSONObject jsonObject = new JSONObject(raw);

        return jsonObject.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }

    /**
     * doTask
     */
    private String doTask(Option option, Prompt prompt) {
        Task task = new Task(option, prompt);
        Future<String> future = THREAD_POOL.submit(task);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "처리중 오류가 발생했습니다";
        }
    }

    private String doTask(Prompt prompt) {
        Task task = new Task(prompt);
        Future<String> future = THREAD_POOL.submit(task);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "처리중 오류가 발생했습니다";
        }
    }

    private String doTask(Option option1, Option option2, Prompt prompt) {
        Task task = new Task(option1, option2, prompt);
        Future<String> future = THREAD_POOL.submit(task);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "처리중 오류가 발생했습니다";
        }
    }

}
