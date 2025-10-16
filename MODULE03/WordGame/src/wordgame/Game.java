package wordgame;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Game {
    private final List<String> words;
    private final Random random = new Random();
    private int score = 0;
    private final List<String> history = new ArrayList<>();

    public Game(List<String> words) {
        this.words = new ArrayList<>(words);
    }

    public static List<String> loadWords(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    public String scramble(String word) {
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);
        return sb.toString();
    }

    public String playRound(Scanner in) {
        String word = words.get(random.nextInt(words.size()));
        String scrambled = scramble(word);
        System.out.println("Scrambled word: " + scrambled);
        System.out.print("Your guess: ");
        String guess = in.nextLine().trim();
        String result;
        if (guess.equalsIgnoreCase(word)) {
            score += 10;
            result = "Correct";
        } else {
            score -= 2;
            result = "Incorrect (answer: " + word + ")";
        }
        String entry = String.format("%s => %s (%s)", scrambled, guess, result);
        history.add(entry);
        System.out.println(result + "! Current score: " + score);
        return entry;
    }

    public int getScore() { return score; }
    public List<String> getHistory() { return history; }
}
