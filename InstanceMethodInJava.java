public class InstanceMethodInJava{
	//Method here
	public static void main (String[] args){
		InstanceMethodInJava instanceMethod = new InstanceMethodInJava();
		int sum = instanceMethod.add(70,50,80);
		
		System.out.printf("The sum of all the numbers is %d%n",sum);
		
		sum = instanceMethod.add(90,40,30);
		System.out.printf("The sum of all the numbers is %d%n",sum);
		
		instanceMethod.details(21,"Kate Henry");
		
		instanceMethod.details(35,"John Doe");
		
		instanceMethod.details(22,"Frank Franklin");
	}
	public int add(int num1, int num2, int num3){
		int sum = num1 + num2 + num3;
		return sum;	
	}
	
	public void details(int age, String name){
		System.out.printf("Your name is %s%n",name);
		System.out.printf("You are %d years old%n",age);
		
	}
}