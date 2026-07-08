/*Encapsulation is one of the fundamental concepts in object-oriented programming (OOOP) and it's essential for maintaining the integrity
of data and ensuring that the internal workings of an object are hidden from the outside world.*/

public class MainApp{
	public static void main(String[] args){
		Student student1 = new Student(1,"John","Willy",'M');
		Student student2 = new Student(2,"Clinton","Williams",'M');
		Student student3 = new Student(3,"Lucy","Ben",'F');
		Student student4 = new Student(4,"Joy","Martins",'F');
		Student student5 = new Student(5,"Johnny","Will",'M');
		
		student1.displayStudentInfo();
		student1.sing();
		System.out.println("=========================\n");
		
		student2.displayStudentInfo();
		System.out.println("=========================\n");
		
		student3.displayStudentInfo();
		System.out.println("=========================\n");
		
		student4.displayStudentInfo();
		System.out.println("=========================\n");
		
		student5.displayStudentInfo();
		System.out.println("=========================\n");
		
		
	}
}