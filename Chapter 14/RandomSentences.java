import java.security.SecureRandom;

public class RandomSentences {
    public static void main(String[] args) {
        String[] article = {"the", "a", "one", "some", "any"};
        String[] noun = {"boy", "girl", "dog", "town", "car"};
        String[] verb = {"drove", "jumped", "ran", "walked", "skipped"};
        String[] preposition = {"to", "from", "over", "under", "on"};

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 20; i++) {
            String word1 = article[random.nextInt(article.length)];
            String word2 = noun[random.nextInt(noun.length)];
            String word3 = verb[random.nextInt(verb.length)];
            String word4 = preposition[random.nextInt(preposition.length)];
            String word5 = article[random.nextInt(article.length)];
            String word6 = noun[random.nextInt(noun.length)];

            // Capitalize first letter of sentence
            word1 = Character.toUpperCase(word1.charAt(0)) + word1.substring(1);

            StringBuilder sentence = new StringBuilder();
            sentence.append(word1).append(" ")
                    .append(word2).append(" ")
                    .append(word3).append(" ")
                    .append(word4).append(" ")
                    .append(word5).append(" ")
                    .append(word6).append(".");

            System.out.println(sentence);
        }
    }
}