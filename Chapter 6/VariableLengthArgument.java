public class VariableLengthArgument {
    
    public static int product(int... numbers) {
        if (numbers.length == 0) {
            return 0; 
        }
        
        int result = 1;
        for (int num : numbers) {
            result *= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.printf("Product of (2, 3): %d%n", product(2, 3));
        System.out.printf("Product of (10, 20, 30): %d%n", product(10, 20, 30));
        System.out.printf("Product of (1, 2, 3, 4, 5): %d%n", product(1, 2, 3, 4, 5));
        System.out.printf("Product of no arguments: %d%n", product());
    }
}