import java.util.Set;
import java.util.HashSet;


public class UsingSetClassWork{
	public static void main(String[] args){
		Set<String> studentEmails = new HashSet<>();
			
			
			
		studentEmails.add("niit@gmail.com");
		studentEmails.add("mercy@gmail.com");
		studentEmails.add("Tony@gmail.com");
		studentEmails.add("nfk@gmail.com");
		studentEmails.add("reaper@gmail.com");
		studentEmails.add("ferg@gmail.com");
		studentEmails.add("krypto@gmail.com");
		studentEmails.add("marcel@gmail.com");
		studentEmails.add("jota@gmail.com");
		studentEmails.add("asake@gmail.com");
		
		for(String studentEmail : studentEmails){
			System.out.println(studentEmail);
		}
		
		
	}
}