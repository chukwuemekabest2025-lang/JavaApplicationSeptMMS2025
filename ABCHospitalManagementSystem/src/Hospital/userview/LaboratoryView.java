package hospital.userview;

import hospital.models.LaboratoryTest;

import java.util.List;

public class LaboratoryView {

    // Display all laboratory tests
    public void displayLaboratoryTests(List<LaboratoryTest> tests) {

        if (tests == null || tests.isEmpty()) {
            System.out.println("No laboratory tests found.");
            return;
        }

        System.out.println();

        System.out.println(
                "========================================================================================================================================================"
        );

        System.out.printf(
                "%-8s %-12s %-12s %-20s %-20s %-12s %-25s %-20s%n",
                "Test ID",
                "Patient ID",
                "Tech ID",
                "Test Name",
                "Test Date",
                "Status",
                "Result",
                "Reference Range"
        );

        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------"
        );

        for (LaboratoryTest test : tests) {

            int patientId = (test.getPatient() != null) ? test.getPatient().getPatientId() : 0;
            int techId = (test.getTechnician() != null) ? test.getTechnician().getStaffId() : 0;

            System.out.printf(
                    "%-8d %-12d %-12d %-20s %-20s %-12s %-25s %-20s%n",
                    test.getId(),
                    patientId,
                    techId,
                    test.getTestName() != null ? test.getTestName() : "N/A",
                    test.getTestDate() != null ? test.getTestDate().toString() : "N/A",
                    test.getStatus() != null ? test.getStatus() : "Pending",
                    test.getResult() != null ? test.getResult() : "N/A",
                    test.getReferenceRange() != null ? test.getReferenceRange() : "N/A"
            );
        }

        System.out.println(
                "========================================================================================================================================================"
        );
    }

    // Display one laboratory test by ID
    public void displayLaboratoryTest(LaboratoryTest test) {

        if (test == null) {
            System.out.println("Laboratory test not found.");
            return;
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("           LABORATORY TEST DETAILS");
        System.out.println("==============================================");

        System.out.printf("%-20s : %s%n", "Test ID", test.getId());
        
        System.out.printf(
                "%-20s : %s%n", 
                "Patient ID", 
                (test.getPatient() != null) ? test.getPatient().getPatientId() : "N/A"
        );
        
        System.out.printf(
                "%-20s : %s%n", 
                "Technician ID", 
                (test.getTechnician() != null) ? test.getTechnician().getStaffId() : "Unassigned"
        );

        System.out.printf("%-20s : %s%n", "Test Name", test.getTestName());
        System.out.printf("%-20s : %s%n", "Test Date", test.getTestDate());
        System.out.printf("%-20s : %s%n", "Status", test.getStatus());
        System.out.printf("%-20s : %s%n", "Result", test.getResult() != null ? test.getResult() : "Pending");
        System.out.printf("%-20s : %s%n", "Reference Range", test.getReferenceRange() != null ? test.getReferenceRange() : "N/A");

        System.out.println("==============================================");
    }
}