package config;

import javax.swing.*;
import java.io.*;

public class ConfigHandler {

    private static final String CONFIG_FILE_NAME = "config.txt";

    public BotConfig load() {

        File file = isConfigExist();
        if (file == null) {
            createNewConfig();
            file = loadConfig();
        }


        String token = null;
        String openAiToken = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("token=")) {
                    token = line.substring("token=".length());
                }
                if (line.startsWith("openAiToken=")) {
                    openAiToken = line.substring("openAiToken=".length());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return new BotConfig(token, openAiToken);
    }

    private File isConfigExist() {
        File file = loadConfig();
        if (file.exists()) {
            return file;
        }
        return null;
    }

    private File loadConfig() {
        return new File("./" + CONFIG_FILE_NAME);
    }

    private void createNewConfig() {

        int result = JOptionPane.showConfirmDialog(
                null,
                "config.txt 파일이 없습니다. 새로 생성하시겠습니까?",
                "Config 파일 생성",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        // "예"를 선택한 경우
        if (result == JOptionPane.YES_OPTION) {
            String discordToken = JOptionPane.showInputDialog(null, "디스코드 봇 토큰 값을 입력하세요:", "Config 설정", JOptionPane.PLAIN_MESSAGE);
            String openAiToken = JOptionPane.showInputDialog(null, "Open Api API Key를 입력하세요:", "Config 설정", JOptionPane.PLAIN_MESSAGE);
            // 입력값이 유효할 때 파일 작성
            if (discordToken != null && !discordToken.trim().isEmpty() && openAiToken != null && !openAiToken.trim().isEmpty()) {
                try (FileWriter writer = new FileWriter("./" + CONFIG_FILE_NAME)) {
                    writer.write("token=" + discordToken + "\n");
                    writer.write("openAiToken=" + openAiToken + "\n");
                    JOptionPane.showMessageDialog(null, "config.txt 파일이 생성되었습니다.");
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "파일 생성 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "유효한 토큰을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "취소되었습니다.");
        }

    }
}
