
package main;

import java.time.LocalDate;
import school.Student;

public class SchoolManagementSystemApp {
    public static void main(String[] args){
        Student s1 = new Student(1,"Henry"
                ,"James",'M',
        LocalDate.parse("2000-09-15")
                ,"08034829472");
        
        System.out.println(s1.getFirstName() + "" + s1.getLastName());
    }
               
}