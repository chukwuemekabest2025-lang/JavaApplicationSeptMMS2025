
package classwork2;


public class MainApp {
    public static void main(String[] args){
        Teacher teacher = new Teacher("John",31);
        
        Student student = new Student("Lucy Tom",19);
        
        System.out.println("======Teacher's Details======");
        teacher.displayDetails();
        teacher.performDuty();
        
        System.out.println("\n");
        
        System.out.println("======Student's Details======");
        student.displayDetails();
        student.performDuty();
    }
}
