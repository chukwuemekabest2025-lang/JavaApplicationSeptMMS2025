import java.util.Random;

public class UsingRandomClass{
	public static void main(String[] args){
		Random random = new Random();
		
		int generatedInteger = random.nextInt();
		System.out.printf("The generated number is %d%n", Math.abs(generatedInteger));
		
		int rangeNumber = random.nextInt(100) + 1;
		System.out.printf("The number generated is %d%n", rangeNumber);
		
		boolean isJava = random.nextBoolean();
		System.out.printf("do you love java? %b%n", isJava);
		
		double decimalNumber = random.nextDouble();
		System.out.printf("The float-point value is %f%n", decimalNumber);
	}
}