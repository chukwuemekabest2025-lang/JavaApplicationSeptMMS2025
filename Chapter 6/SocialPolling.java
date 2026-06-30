import java.util.Scanner;

public class SocialPolling {
    public static void main(String[] args) {
        String[] topics = {"Climate Change", "Data Privacy", "Education Reform", "Economic Inequality", "Healthcare Access"};
        int[][] responses = new int[5][10];
        Scanner input = new Scanner(System.in);

        
        for (int user = 1; user <= 3; user++) {
            System.out.println("\n--- User Survey #" + user + " (Rate 1 to 10) ---");
            for (int i = 0; i < topics.length; i++) {
                System.out.print(topics[i] + ": ");
                int rating = input.nextInt();
                if (rating >= 1 && rating <= 10) {
                    responses[i][rating - 1]++;
                }
            }
        }
        System.out.printf("\n%-23s", "Topic");
        for (int r = 1; r <= 10; r++) System.out.printf("%3d", r);
        System.out.println("   Average");

        int highestTotal = -1, lowestTotal = Integer.MAX_VALUE;
        String bestTopic = "", worstTopic = "";

        for (int i = 0; i < topics.length; i++) {
            System.out.printf("%-23s", topics[i]);
            int totalPoints = 0;
            int totalVotes = 0;
            
            for (int r = 0; r < 10; r++) {
                System.out.printf("%3d", responses[i][r]);
                totalPoints += responses[i][r] * (r + 1);
                totalVotes += responses[i][r];
            }

            double avg = totalVotes == 0 ? 0 : (double) totalPoints / totalVotes;
            System.out.printf("   %.2f\n", avg);

            if (totalPoints > highestTotal) {
                highestTotal = totalPoints;
                bestTopic = topics[i];
            }
            if (totalPoints < lowestTotal) {
                lowestTotal = totalPoints;
                worstTopic = topics[i];
            }
        }

        System.out.println("\nHighest point total: " + bestTopic + " (" + highestTotal + " pts)");
        System.out.println("Lowest point total: " + worstTopic + " (" + lowestTotal + " pts)");
    }
}