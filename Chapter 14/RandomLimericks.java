import java.security.SecureRandom;

public class RandomLimericks {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        // Lists grouped by rhyming sound
        String[] rhymeA1 = {"there was a young fellow named Mark", "an old dog who loved to go bark", "a sailor who sailed in the dark"};
        String[] rhymeA2 = {"who went for a walk in the park", "and chased a stray cat in the dark", "whose dog had a very loud bark"};
        String[] rhymeB1 = {"he tripped on a stone", "he gnawed on a bone"};
        String[] rhymeB2 = {"and fell on his own", "and sat all alone"};
        String[] rhymeA3 = {"and lost his way home in the dark.", "and ended up back at the park.", "and made quite a scene in the park."};

        for (int i = 0; i < 5; i++) {
            System.out.println("--- Limerick " + (i + 1) + " ---");
            System.out.println(rhymeA1[random.nextInt(rhymeA1.length)]);
            System.out.println(rhymeA2[random.nextInt(rhymeA2.length)]);
            System.out.println(rhymeB1[random.nextInt(rhymeB1.length)]);
            System.out.println(rhymeB2[random.nextInt(rhymeB2.length)]);
            System.out.println(rhymeA3[random.nextInt(rhymeA3.length)]);
            System.out.println();
        }
    }
}