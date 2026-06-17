public class OperatorPart1{
	public static void main(String[] args){	
		//Assignment Operator(=)
		int num = 200;
		System.out.printf("The value of num is %d%n",num);
		
		
		
		//Arithmetic Operator(+,-,/,*,%)
		int num1 = 80;
		int num2 = 100;
		
		int addition = num1 + num2;
		int subtraction = num1 - num2;
		double division = (double) num1/num2;
		int multiplication = num1 * num2;
		int remainder = num1 % num2;

		
		System.out.printf("%d + %d = %d%n",num1,num2,addition);
		System.out.printf("%d - %d = %d%n",num1,num2,subtraction);
		System.out.printf("%d / %d = %.1f%n",num1,num2,division);
		System.out.printf("%d x %d = %d%n",num1,num2,multiplication);
		System.out.printf("%d %% %d = %d%n",num1,num2,remainder);
		System.out.println("");
		System.out.println("=====================================");
		
		//Compound assignment operator(+=,-=,*=,/=,%=)
		
		num1 += num2;
		System.out.printf("The value of num1 has been updated to %d%n",num1);
		
		num1 -= num2;
		System.out.printf("The value of num1 has been updated to %d%n",num1);
		
		num1 *= num2;
		System.out.printf("The value of num1 has been updated to %d%n",num1);
		
		num1 /= num2;
		System.out.printf("The value of num1 has been updated to %d%n",num1);
		
		num1 %= num2;
		System.out.printf("The value of num1 has been updated to %d%n",num1);
		
		System.out.println("");
		System.out.println("=====================================");

		//Relational Operators (==,!=,>,<,>=,<=)
		int number1 = 15;
		int number2 = 30;		
		
		boolean IsGreater = number1 > number2;
		boolean IsLessThan = number1 < number2;
		boolean IsGreaterOrEqualTo = number1 >= number2;
		boolean IsLessThanOrEqualTo = number1 <= number2;
		boolean IsEqualTo = number1 == number2;
		boolean IsNotEqualTo = number1 != number2;
		
		System.out.printf("Is %d > %d: %b%n",number1,number2,IsGreater);
		System.out.printf("Is %d < %d: %b%n",number1,number2,IsLessThan);
		System.out.printf("Is %d >= %d: %b%n",number1,number2,IsGreaterOrEqualTo);
		System.out.printf("Is %d <= %d: %b%n",number1,number2,IsLessThanOrEqualTo);
		System.out.printf("Is %d == %d: %b%n",number1,number2,IsEqualTo);
		System.out.printf("Is %d != %d: %b%n",number1,number2,IsNotEqualTo);
		
		
		
		
	}
}