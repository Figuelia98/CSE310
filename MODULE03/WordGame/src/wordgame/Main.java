package wordgame;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String WORDS_PATH = "words.txt";
    private static final String SAVE_PATH = "save.txt";

    public static void main(String[] args) {
        try {
            List<String> words = Game.loadWords(WORDS_PATH);
            Game game = new Game(words);
            Scanner in = new Scanner(System.in);
            while (true) {
                printMenu();
                String choice = in.nextLine().trim();
                switch (choice) {
                    case "1":
                        // play one round
                        game.playRound(in);
                        break;
                    case "2":
                        // show score
                        System.out.println("Score: " + game.getScore());
                        break;
                    case "3":
                        // show history
                        System.out.println("History:");
                        game.getHistory().forEach(System.out::println);
                        break;
                    case "4":
                        // save
                        try { SaveManager.save(SAVE_PATH, game.getScore(), game.getHistory());
                            System.out.println("Saved to " + SAVE_PATH);
                        } catch (IOException e) { System.out.println("Save failed: " + e.getMessage()); }
                        break;
                    case "5":
                        // load
                        try {
                            SaveManager.SaveData data = SaveManager.load(SAVE_PATH);
                            System.out.println("Loaded. Score: " + data.score);
                            System.out.println("Loaded history:");
                            data.history.forEach(System.out::println);
                        } catch (IOException e) { System.out.println("Load failed: " + e.getMessage()); }
                        break;
                    case "6":
                        // help
                        printHelp();
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load words: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("=== Word Game Menu ===");
        System.out.println("1. Play round");
        System.out.println("2. Show score");
        System.out.println("3. Show history");
        System.out.println("4. Save game");
        System.out.println("5. Load game");
        System.out.println("6. Help");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void printHelp() {
        System.out.println("Help: Guess the original word from the scrambled letters. ");
        System.out.println("Scoring: +10 for correct, -2 for incorrect. Save/Load uses save.txt in the project folder.");
    }
}
