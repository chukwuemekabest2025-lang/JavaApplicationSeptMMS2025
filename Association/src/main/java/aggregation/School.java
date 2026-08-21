package aggregation;

import java.util.List;

public class School {
    private String schoolName;
    private List<Student> students;
    
    
    
    public School(String schoolName,List<Student> students){
        
        this.schoolName = schoolName;
        this.students = students;
    }
    
    public void displayStudentDetails(){
        
        System.out.println();
        System.out.println("              SCHOOL INFORMATION");
        System.out.println("===============================================");
        
        System.out.println("School Name: " + schoolName );
        System.out.println("Number of Students: " + students.size());
        
        System.out.println("===============================================");
        
        for (Student student : students){
            
            student.displayStudentDetails();
        }
    }  
}