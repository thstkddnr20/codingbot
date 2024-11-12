package api;

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

public class AiHandler {

    private final Bot bot;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final Integer MAX_TOKENS = 150;

    public AiHandler(Bot bot) {
        this.bot = bot;
    }

    public String getHint(String address) {
        try {
            String prompt = "There are coding problem at the given address and you are a helper who helps you solve coding problem, you should only provide hints. " +
                    "do not answers to the problem and do not make the example for the problem. Answer in KOREAN. address: " + address;

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

            return parseResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private String parseResponse(@NotNull String raw) {
        JSONObject jsonObject = new JSONObject(raw);

        return jsonObject.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }

}
