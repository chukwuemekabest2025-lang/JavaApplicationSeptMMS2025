import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {
    public static void main(String[] args) {
        System.out.println("Generated 15-Digit Password: " + generatePassword(15));
    }

    public static String generatePassword(int length) {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[{]};:',<.>/?";
        String allCharacters = lower + upper + digits + special;

        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(lower.charAt(random.nextInt(lower.length())));
        passwordChars.add(upper.charAt(random.nextInt(upper.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(special.charAt(random.nextInt(special.length())));

        for (int i = 0; i < length - 4; i++) {
            passwordChars.add(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        Collections.shuffle(passwordChars, random);

        StringBuilder password = new StringBuilder();
        for (char ch : passwordChars) {
            password.append(ch);
        }

        return password.toString();
    }
}