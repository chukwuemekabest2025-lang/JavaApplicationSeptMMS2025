public class RecursiveMethod{
	public static void main(String[] args){
		displayName();
	}
	public static void displayName(){
		String name = "Oasis Reginald";
		System.out.printf("Your name is %s%n", name);
		
		displayName();
	}
}