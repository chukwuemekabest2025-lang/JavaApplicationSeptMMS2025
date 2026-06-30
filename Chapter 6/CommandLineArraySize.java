public class CommandLineArraySize {
    public static void main(String[] args) {
        int arrayLength;

        if (args.length > 0) {
            try {
                arrayLength = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument supplied. Defaulting size to 10.");
                arrayLength = 10;
            }
        } else {
            arrayLength = 10; 
        }

        int[] array = new int[arrayLength];

        System.out.printf("%s%8s%n", "Index", "Value");

        for (int counter = 0; counter < array.length; counter++) {
            System.out.printf("%5d%8d%n", counter, array[counter]);
        }
    }
}