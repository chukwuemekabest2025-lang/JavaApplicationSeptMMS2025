public class Demo{
	public static void main (String[] args){
		DemoInstanceMethod instanceMethod = new DemoInstanceMethod();
		
		int sum = instanceMethod.add(20,60,30);
		System.out.printf("The sum of the numbers is %s%n",sum);
		
		instanceMethod.details(40,"Emmanuel Peter");
	}
}