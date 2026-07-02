import java.util.Set;
import java.util.LinkedHashSet;


public class UsingSet{
	public static void main(String[] args){
		Set<Integer> studentID = new LinkedHashSet<>();
			
			
			
		studentID.add(101);
		studentID.add(102);
		studentID.add(103);
		studentID.add(104);
		studentID.add(105);
		studentID.add(103);
		
		System.out.println(studentID);
		
		studentID.remove(104);
		
		System.out.println(studentID);
		
		System.out.println("Does StudentID contains 103? " + studentID.contains(102));
		
		System.out.println("Does StudentID contains 104? " + studentID.contains(104));
		
	}
}