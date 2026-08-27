package myUoPeopleTerm4JavaPrograms;

import java.util.*;

// Text Analysis Tool
public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // USER INPUT
            System.out.println("Enter a paragraph or text:");
            String text = scanner.nextLine();

            // Input validation
            if (text == null || text.trim().isEmpty()) {
                throw new IllegalArgumentException("Text input cannot be empty.");
            }

            // Normalize text for case-insensitive operations
            String lowerText = text.toLowerCase();

            // CHARACTER COUNT
            int charCount = text.length();

            // WORD COUNT
            String[] wordsArray = lowerText.trim().split("\\s+");
            int wordCount = wordsArray.length;

            // MOST COMMON CHARACTER
            Map<Character, Integer> charFreqMap = new HashMap<>();

            for (char ch : lowerText.toCharArray()) {
                if (ch != ' ') {
                    charFreqMap.put(ch, charFreqMap.getOrDefault(ch, 0) + 1);
                }
            }

            char mostCommonChar = ' ';
            int maxFreq = 0;

            for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
                if (entry.getValue() > maxFreq) {
                    mostCommonChar = entry.getKey();
                    maxFreq = entry.getValue();
                }
            }

            // CHARACTER FREQUENCY
            System.out.print("Enter a character to search: ");
            String charInput = scanner.nextLine();

            if (charInput.length() != 1) {
                throw new IllegalArgumentException("Please enter exactly one character.");
            }

            char searchChar = Character.toLowerCase(charInput.charAt(0));
            int searchCharCount = charFreqMap.getOrDefault(searchChar, 0);

            // WORD FREQUENCY
            System.out.print("Enter a word to search: ");
            String searchWord = scanner.nextLine().toLowerCase();

            if (searchWord.trim().isEmpty()) {
                throw new IllegalArgumentException("Word cannot be empty.");
            }

            int wordFrequency = 0;
            for (String word : wordsArray) {
                if (word.equals(searchWord)) {
                    wordFrequency++;
                }
            }

            // UNIQUE WORDS
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(wordsArray));
            int uniqueWordCount = uniqueWords.size();

            // OUTPUT
            System.out.println("\n--- TEXT ANALYSIS RESULT ---");
            System.out.println("Total Characters: " + charCount);
            System.out.println("Total Words: " + wordCount);
            System.out.println("Most Common Character: '" + mostCommonChar + "' occurred " + maxFreq + " times");
            System.out.println("Frequency of '" + searchChar + "': " + searchCharCount);
            System.out.println("Frequency of word \"" + searchWord + "\": " + wordFrequency);
            System.out.println("Unique Words Count: " + uniqueWordCount);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Program execution completed.");
        }
    }
}