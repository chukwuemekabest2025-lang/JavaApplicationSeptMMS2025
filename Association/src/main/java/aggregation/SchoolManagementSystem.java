package aggregation;

//import composition.Payment;
import java.time.LocalDate;
//import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class SchoolManagementSystem {
    public static void main(String[] args){
       
        //Students objects Created
        Student s1 = new Student(
                201, 
                "Son", 
                "GOku", 
                'M'
        );
        
        Student s2 = new Student(
                202, 
                "Prince", 
                "Vegeta",
                'M'
        );
        
        Student s3 = new Student(
                203, 
                "Bulma", 
                "Brief",
                'F'
        );
        
        Student s4 = new Student(
                204, 
                "Son", 
                "Gohan",
                'M'
        );
        
        Student s5 = new Student(
                205, 
                "Diogo", 
                "Jota",
                'M'
        );
        
        s1.makePayment(
                300000.00,
                LocalDate.of(2026,8, 10), 
                "Transfer", 
                "Paid for Data Analytics"
        );
        
        
        s2.makePayment(
                400000.00,
                LocalDate.of(2026,8, 11), 
                "Cash", 
                "Paid for Python"
        );
                
        s3.makePayment(
                450000.00,
                LocalDate.of(2026,8, 12), 
                "POS", 
                "Paid for Cyber Security"
        );
                        
        s4.makePayment(
                500000.00,
                LocalDate.of(2026,8, 13), 
                "POS", 
                "Paid for Java"
        );
        
        s5.makePayment(
                350000.00,
                
                LocalDate.of(2026,8,14), 
                "Transfer", 
                "Paid for MMS"
                );

        
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        
        //School Object Created
        School school = new School("Jota's group of schools",
                students);
        
        school.displayStudentDetails();
    }
}

