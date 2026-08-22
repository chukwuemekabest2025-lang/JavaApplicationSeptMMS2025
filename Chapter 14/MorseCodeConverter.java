import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class MorseCodeConverter {
    private static final Map<Character, String> charToMorse = new HashMap<>();
    private static final Map<String, Character> morseToChar = new HashMap<>();

    static {
        char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..",".----","..---","...--","....-",".....","-....","--...","---..","----.","-----"};

        for (int i = 0; i < chars.length; i++) {
            charToMorse.put(chars[i], codes[i]);
            morseToChar.put(codes[i], chars[i]);
        }
    }

    public static String encodeToMorse(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (c == ' ') {
                result.append("  "); // Extra space for word boundary
            } else if (charToMorse.containsKey(c)) {
                result.append(charToMorse.get(c)).append(" ");
            }
        }
        return result.toString().trim();
    }

    public static String decodeFromMorse(String morse) {
        StringBuilder result = new StringBuilder();
        String[] words = morse.split("   "); // 3 spaces between words
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                if (morseToChar.containsKey(letter)) {
                    result.append(morseToChar.get(letter));
                }
            }
            result.append(" ");
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter English text: ");
        String english = scanner.nextLine();

        String encoded = encodeToMorse(english);
        System.out.println("Morse Code: " + encoded);
        System.out.println("Decoded back: " + decodeFromMorse(encoded));

        scanner.close();
    }
}