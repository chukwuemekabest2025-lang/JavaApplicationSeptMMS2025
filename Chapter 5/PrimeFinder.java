public class PrimeFinder {
    public static void main(String[] args) {
        System.out.println("Finding all prime numbers less than 10,000 using the square root limit optimization...");
        
        int count = 0;
        for (int i = 2; i < 10000; i++) {
            if (isPrimeSqrt(i)) {
                count++;
            }
        }
        System.out.printf("Total primes found: %d%n", count);
        System.out.println("To ensure you've found all primes up to n, you must test up to √n.");
    }

    public static boolean isPrimeHalf(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static boolean isPrimeSqrt(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}