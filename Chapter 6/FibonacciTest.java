public class FibonacciTest {
    public static void main(String[] args) {
        System.out.println("Max index for 'int': " + findMaxIntIndex());
        System.out.println("Max index for 'double': " + findMaxDoubleIndex());
    }

    public static int findMaxIntIndex() {
        int prev = 0, curr = 1, index = 1;
        while (curr > 0) {
            int next = prev + curr;
            prev = curr;
            curr = next;
            index++;
        }
        return index - 1; 
    }

    public static int findMaxDoubleIndex() {
        double prev = 0, curr = 1;
        int index = 1;
        while (!Double.isInfinite(curr)) {
            double next = prev + curr;
            prev = curr;
            curr = next;
            index++;
        }
        return index - 1;
    }
}