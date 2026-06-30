import java.util.Arrays;

public class SievePrimes {
    public static void main(String[] args) {
        boolean[] primes = new boolean[1000];
        Arrays.fill(primes, true); 

        for (int i = 2; i < Math.sqrt(primes.length); i++) {
            if (primes[i]) {
                for (int j = i * 2; j < primes.length; j += i) {
                    primes[j] = false;
                }
            }
        }

        System.out.println("Prime numbers between 2 and 999:");
        int count = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i]) {
                System.out.printf("%4d", i);
                if (++count % 10 == 0) System.out.println();
            }
        }
    }
}