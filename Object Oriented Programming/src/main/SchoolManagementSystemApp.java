
package main;

import java.time.LocalDate;
//import school.Student;
import school.GraduateStudent;
import school.UnderGraduateStudents;
import java.util.Scanner;
import school.Student;

public class SchoolManagementSystemApp {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter 1: For Graduate Student");
        System.out.println("Enter 2: For Under Graduate Student");
        System.out.println("Enter 3: To Pay School Fee");
        
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
        
        switch(choice){
            case 1:{
                System.out.println("Enter your student ID:");
                int studentID = scan.nextInt();
                
                System.out.print("Enter First Name: ");
                String firstName = scan.nextLine();
                
                System.out.print("Enter Last Name: ");
                String lastName = scan.nextLine();
                
                System.out.print("Enter Gender: ");
                char gender = scan.next().charAt(0);
                
                System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                String dateOfBirth = scan.nextLine();
                
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scan.nextLine();
                
                System.out.print("Enter Final Year Project: ");
                String finalYearProject = scan.nextLine();
                
                
                
            break;
            }
            case 2:{
                System.out.println("Enter your student ID:");
                int studentID = scan.nextInt();
                
                System.out.print("Enter First Name: ");
                String firstName = scan.nextLine();
                
                System.out.print("Enter Last Name: ");
                String lastName = scan.nextLine();
                
                System.out.print("Enter Gender: ");
                char gender = scan.next().charAt(0);
                
                System.out.print("Enter Date Of Birth (YYYY-MM-DD): ");
                String dateOfBirth = scan.nextLine();
                
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scan.nextLine();
                
                System.out.print("Enter Level: ");
                String finalYearProject = scan.nextLine();
                int level = 0;
                
                UnderGraduateStudents ugstudent =
                        new UnderGraduateStudents(studentID,firstName,
                        lastName,gender,LocalDate.parse(dateOfBirth),
                                phoneNumber,level);
                ugstudent.displayStudentInfo();
            
                
            break;
            }
            case 3:{
                System.out.println("Choose the following option");
                System.out.println("Enter 1: Amount only");
                System.out.println("Enter 2: Amount, Full Name only");
                System.out.println("Enter 3: Amount, Full Name,Paymentmethod only");
                
                System.out.print("Enter your Option");
                int myOption = scan.nextInt();
                
                switch (myOption){
                    case 1:{
                        System.out.print("Enter Student ID");
                        int studentID = scan.nextInt();
                        scan.nextLine();
                        
                        System.out.print("Enter First Name");
                        int firstName = scan.nextInt();                        
                        
                        System.out.print("Enter Last Name");
                        int lastName = scan.nextInt();
                        
                        System.out.print("Enter gender");
                        int gender = scan.nextInt(); 
                        scan.nextLine();
                        
                        System.out.print("Enter Birth Date (YYYY-MM-DD) ");
                        int dateOfBirth = scan.nextInt();                        
                        
                        System.out.print("Enter Phone Number");
                        int phoneNumber = scan.nextInt();                        
                        
                        Student student = new Student(studentID, firstName,
                                            lastName, gender, 
                                LocalDate.parse(birthDate), phoneNumber);
                        
                        System.out.println("Ener details for payment \n");
                        System.out.print("Enter Amount");
                        
                        double amount = scan.nextDouble();
                        scan.nextLine();
                        student.payFees(amount);
                    }
                    case 2:{
                        System.out.print("Enter Student ID");
                        int studentID = scan.nextInt();
                        scan.nextLine();
                        
                        System.out.print("Enter First Name");
                        int firstName = scan.nextInt();                        
                        
                        System.out.print("Enter Last Name");
                        int lastName = scan.nextInt();
                        
                        System.out.print("Enter gender");
                        int gender = scan.nextInt();   
                        scan.nextLine();
                        
                        System.out.print("Enter Birth Date (YYYY-MM-DD) ");
                        int dateOfBirth = scan.nextInt();                        
                        
                        System.out.print("Enter Phone Number");
                        int phoneNumber = scan.nextInt();                        
                        
                        Student student = new Student(studentID, firstName,
                                            lastName, gender, 
                                LocalDate.parse(birthDate), phoneNumber);
                        
                        System.out.print("Enter Amount");
                        
                        double amount = scan.nextDouble();
                        scan.nextLine();
                        
                        System.out.print("Enter full Name");
                        String fullName = scan.nextLine();
                        student.payFees(amount,fullName);
                    }
                    case 3:{
                        System.out.print("Enter Student ID");
                        int studentID = scan.nextInt();
                        scan.nextLine();
                        
                        System.out.print("Enter First Name");
                        int firstName = scan.nextInt();                        
                        
                        System.out.print("Enter Last Name");
                        int lastName = scan.nextInt();
                        
                        System.out.print("Enter gender");
                        int gender = scan.nextInt();   
                        scan.nextLine();
                        
                        System.out.print("Enter Birth Date (YYYY-MM-DD) ");
                        int dateOfBirth = scan.nextInt();                        
                        
                        System.out.print("Enter Phone Number");
                        int phoneNumber = scan.nextInt();                        
                        
                        Student student = new Student(studentID, firstName,
                                            lastName, gender, 
                                LocalDate.parse(birthDate), phoneNumber);
                        
                        System.out.print("Enter Amount");
                        
                        double amount = scan.nextDouble();
                        scan.nextLine();
                        
                         System.out.print("Enter full Name");
                        String fullName = scan.nextLine();
                        
                        System.out.print("Enter Payment Method");
                        String paymentMethod = scan.nextLine();
                        student.payFees (amount,fullName,paymentMethod);  
                    }

                }
            break;    
            }
            
            default:
                System.out.print("Invalid Input");
            
            
            }
            }
        }
        
        
        
    
               
