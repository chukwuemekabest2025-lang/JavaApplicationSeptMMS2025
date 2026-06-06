import java.util.Scanner;

public class GlobalWarmingQuiz {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;

        System.out.println("Welcome to the Global Warming Knowledge Quiz!\n");

        // Question 1
        System.out.println("1. Which greenhouse gas is most widely produced by human activities?");
        System.out.println("1) Methane\n2) Carbon Dioxide\n3) Nitrous Oxide\n4) Ozone");
        if (input.nextInt() == 2) score++;

        // Question 2
        System.out.println("\n2. What is a primary point raised by global warming skeptics?");
        System.out.println("1) CO2 levels aren't rising\n2) Climate has natural cycles of warming/cooling\n3) Glaciers are expanding\n4) Satellites don't track temperature");
        if (input.nextInt() == 2) score++;

        // Question 3
        System.out.println("\n3. What year did Al Gore and the IPCC share the Nobel Peace Prize?");
        System.out.println("1) 2000\n2) 2005\n3) 2007\n4) 2012");
        if (input.nextInt() == 3) score++;

        // Question 4
        System.out.println("\n4. What does the IPCC stand for?");
        System.out.println("1) International Panel on Climate Changes\n2) Intergovernmental Panel on Climate Change\n3) Internal Protection of Climate Council\n4) Integrated Policy for Climate Control");
        if (input.nextInt() == 2) score++;

        // Question 5
        System.out.println("\n5. What is the main goal of agreements like the Paris Accord?");
        System.out.println("1) Stop all factory operations\n2) Limit global temp rise to well below 2 degrees Celsius\n3) Ban all fossil fuel vehicles immediately\n4) Eliminate national environmental taxes");
        if (input.nextInt() == 2) score++;

        // Results
        System.out.println("\n--- Quiz Finished ---");
        System.out.println("Your Score: " + score + "/5");

        if (score == 5) {
            System.out.println("Excellent!");
        } else if (score == 4) {
            System.out.println("Very good!");
        } else {
            System.out.println("Time to brush up on your knowledge of global warming.");
            System.out.println("Check out these resources to learn more:");
            System.out.println("- NASA Global Climate Change: climate.nasa.gov");
            System.out.println("- Intergovernmental Panel on Climate Change: www.ipcc.ch");
            System.out.println("- Climate Debate Information: www.skepticalscience.com");
        }
    }
}