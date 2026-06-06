public class FacebookGrowth {
    public static void main(String[] args) {
        double currentUsers = 1.0; // represented in billions
        double growthRate = 0.04; // 4% per month
        int months = 0;
        
        int target15 = -1;
        int target20 = -1;

        while (target15 == -1 || target20 == -1) {
            currentUsers += (currentUsers * growthRate);
            months++;

            if (currentUsers >= 1.5 && target15 == -1) {
                target15 = months;
            }
            if (currentUsers >= 2.0 && target20 == -1) {
                target20 = months;
            }
        }

        System.out.println("Months needed to hit 1.5 billion users: " + target15 + " months");
        System.out.println("Months needed to hit 2.0 billion users: " + target20 + " months");
    }
}