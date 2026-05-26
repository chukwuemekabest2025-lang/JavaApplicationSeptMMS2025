public class OperatorPresedence{
	public static void main(String[] args){
		/*Opertor Precedence - These are rules applied when dealing with hybrid equations 
		that involve more than one type of operator. In such cases, these rules determine which
		part of the equation to consider first, as there can be many different valuations for
		the same equation.*/
		
		//2x2*2y-(2x2+3y)*2x2
		
		int x = 2;
		int y = 3;
		int equation = 2*2*2*2*3 -(2*2*2+3*3)*2*2*2;
		System.out.printf("The result of the equation is %d%n",equation);
	}
}	