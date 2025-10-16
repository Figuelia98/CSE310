package wordgame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveManager {
    public static void save(String path, int score, List<String> history) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("score=").append(score).append("\n");
        for (String h : history) sb.append(h.replace('\n',' ')).append("\n");
        Files.write(Paths.get(path), sb.toString().getBytes());
    }

    public static SaveData load(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        int score = 0;
        List<String> history = new java.util.ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("score=")) {
                try { score = Integer.parseInt(line.substring(6)); } catch (NumberFormatException e) { score = 0; }
            } else if (!line.trim().isEmpty()) {
                history.add(line);
            }
        }
        return new SaveData(score, history);
    }

    public static class SaveData {
        public final int score;
        public final List<String> history;
        public SaveData(int score, List<String> history) { this.score = score; this.history = history; }
    }
}
