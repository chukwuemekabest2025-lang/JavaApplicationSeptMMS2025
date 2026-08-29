
package Hospital.models;

import java.time.LocalDate;


public class Doctor extends Staff{
    private String specialization;
    private String licenceNumber;

    
    public Doctor(){
        
    }

    public Doctor(String firstName, 
            String lastName, char gender, 
            LocalDate dateOfBirth, String phone, 
            String email, String street, 
            String city, String country,  String staffId, 
            LocalDate employmentDate, 
            double salary, Department department, 
            String specialization, 
            String licenceNumber 
            ) {
        
        super(
                firstName, lastName, gender, 
                dateOfBirth, phone, email, 
                street, city, country, staffId, 
                employmentDate, salary, department);
        
        this.specialization = specialization;
        this.licenceNumber = licenceNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
      
    }
    
    
}
