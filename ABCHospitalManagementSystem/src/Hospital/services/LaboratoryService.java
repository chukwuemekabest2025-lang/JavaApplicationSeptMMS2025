package hospital.services;

import hospital.dao.LaboratoryDAO;
import hospital.models.LaboratoryTest;

import java.time.LocalDateTime;
import java.util.List;

public class LaboratoryService {

    private final LaboratoryDAO laboratoryDAO;

    public LaboratoryService() {
        this.laboratoryDAO = new LaboratoryDAO();
    }

    // =========================================================
    // ORDER LABORATORY TEST
    // =========================================================
    public boolean orderTest(LaboratoryTest test) {

        if (test == null) {
            System.err.println(
                    "Validation Error: Test object cannot be null."
            );
            return false;
        }

        // Validate patient
        if (test.getPatient() == null
                || test.getPatient().getPatientId() <= 0) {

            System.err.println(
                    "Validation Error: A valid patient must be assigned to the test."
            );
            return false;
        }

        // Validate test name
        if (test.getTestName() == null
                || test.getTestName().trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Test name is required."
            );
            return false;
        }

        // Set current date/time if none was provided
        if (test.getTestDate() == null) {
            test.setTestDate(LocalDateTime.now());
        }

        // Default status
        if (test.getStatus() == null
                || test.getStatus().trim().isEmpty()) {

            test.setStatus("Pending");
        }

        return laboratoryDAO.addLaboratoryTest(test);
    }


    // =========================================================
    // GET ALL LABORATORY TESTS
    // =========================================================
    public List<LaboratoryTest> getAllTests() {

        return laboratoryDAO.findAllLaboratoryTests();
    }


    // =========================================================
    // GET LABORATORY TEST BY ID
    // =========================================================
    public LaboratoryTest getTestById(int testId) {

        if (testId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Test ID."
            );

            return null;
        }

        return laboratoryDAO.findLaboratoryTestById(testId);
    }


    // =========================================================
    // RECORD TEST RESULT
    // =========================================================
    public boolean recordTestResult(
            int testId,
            int technicianId,
            String result,
            String referenceRange,
            String status) {

        if (testId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Test ID."
            );

            return false;
        }

        if (technicianId <= 0) {

            System.err.println(
                    "Validation Error: A valid laboratory technician ID is required."
            );

            return false;
        }

        if (result == null
                || result.trim().isEmpty()) {

            System.err.println(
                    "Validation Error: Test result cannot be empty when submitting results."
            );

            return false;
        }

        String finalStatus;

        if (status != null
                && !status.trim().isEmpty()) {

            finalStatus = status;

        } else {
            finalStatus = "Completed";
        }

        return laboratoryDAO.updateTestResult(
                testId,
                technicianId,
                result,
                referenceRange,
                finalStatus
        );
    }


    // =========================================================
    // DELETE LABORATORY TEST
    // =========================================================
    public boolean removeTest(int testId) {

        if (testId <= 0) {

            System.err.println(
                    "Validation Error: Invalid Test ID."
            );

            return false;
        }

        return laboratoryDAO.deleteLaboratoryTest(testId);
    }
}
