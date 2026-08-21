
package classwork2;


public class Student extends Person {
    
    public Student(String name, int age){
        super(name,age);
    }

    @Override
    void performDuty() {
       System.out.println("The Student's duty is to study");
    }
    
    @Override
    void displayDetails(){
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
    }
    
}
