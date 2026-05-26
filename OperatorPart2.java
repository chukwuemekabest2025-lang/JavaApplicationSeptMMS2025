public class OperatorPart2{
	public static void main(String[] args){
		/*Logical Operator[and(&&),or(||),not(!)]
		Logical Operator are used to join two or more conditions or to reverse a condition*/
		int num1 = 15;
		int num2 = 30;
		int num3 = 18;
		
		boolean andOperator = (num1 > num2) && (num1 > num3);
		System.out.printf("Is (%d > %d) && (%d > %d): %b%n",num1,num2,num1,num3,andOperator);
			
		boolean orOperator = (num1 > num2) || (num1 > num3);
		System.out.printf("Is (%d > %d) || (%d > %d): %b%n",num1,num2,num1,num3,orOperator);

		boolean notOperator = !(num1 > num2) || (num1 > num3);
		System.out.printf("Is (%d > %d) ! (%d > %d): %b%n",num1,num2,num1,num3,notOperator);
		
		System.out.println("");
		System.out.println("=====================================");
		
		/*Unary Operator - They are operators that work on a single operand (one variabel
		or value). They are called unary because they need only one operand to
		perform an operation*/
		
		//Unary Operator(increment(++),decrement(--),pre-increment(++x),post-increment(x++))
		int x = 5;
		int y = 10;
		
		System.out.printf("The value of x is %d%n",++x);
		System.out.printf("The value of y is %d%n",y++);
		System.out.printf("The value of y is %d%n",y);
		
		System.out.printf("The value of x is %d%n",--x);
		System.out.printf("The value of y is %d%n",y--);
		System.out.printf("The value of y is %d%n",y);	
	
	}
}