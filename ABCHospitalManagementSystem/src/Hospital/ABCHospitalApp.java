package hospitals;

import Hospital.models.Patient;
import Hospital.services.PatientService;
import Hospital.userview.PatientView;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ABCHospitalApp {

    private static final Scanner scanner =
            new Scanner(System.in);

    private static final PatientService patientService =
            new PatientService();

    private static final PatientView patientView =
            new PatientView();


    public static void main(String[] args) {

        while (true) {

            displayMainMenu();

            int choice = readInt(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    patientMenu();
                    break;

                case 0:
                    System.out.println(
                            "Thank you for using ABC Hospital System."
                    );

                    scanner.close();

                    return;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }


    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void displayMainMenu() {

        System.out.println();
        System.out.println(
                "========================================"
        );

        System.out.println(
                "       ABC HOSPITAL MANAGEMENT SYSTEM"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                "1. Patient Management"
        );

        System.out.println(
                "2. Doctor Management"
        );

        System.out.println(
                "3. Nurse Management"
        );

        System.out.println(
                "4. Appointment Management"
        );

        System.out.println(
                "5. Admission Management"
        );

        System.out.println(
                "6. Laboratory Management"
        );

        System.out.println(
                "7. Pharmacy Management"
        );

        System.out.println(
                "8. Billing Management"
        );

        System.out.println(
                "0. Exit"
        );

        System.out.println(
                "========================================"
        );
    }


    // =========================================================
    // PATIENT MENU
    // =========================================================

    private static void patientMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "           PATIENT MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Register Patient"
            );

            System.out.println(
                    "2. View All Patients"
            );

            System.out.println(
                    "3. Find Patient"
            );

            System.out.println(
                    "4. Update Patient"
            );

            System.out.println(
                    "5. Delete Patient"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice = readInt(
                    "Enter your choice: "
            );

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    viewAllPatients();
                    break;

                case 3:
                    findPatient();
                    break;

                case 4:
                    updatePatient();
                    break;

                case 5:
                    deletePatient();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }


    // =========================================================
    // REGISTER PATIENT
    // =========================================================

    private static void registerPatient() {

        System.out.println();
        System.out.println(
                "========== REGISTER PATIENT =========="
        );

        System.out.print(
                "First Name: "
        );

        String firstName =
                scanner.nextLine();

        System.out.print(
                "Last Name: "
        );

        String lastName =
                scanner.nextLine();

        System.out.print(
                "Gender (M/F): "
        );

        char gender =
                scanner.nextLine()
                        .charAt(0);

        System.out.print(
                "Date of Birth (yyyy-MM-dd): "
        );

        LocalDate dateOfBirth =
                LocalDate.parse(
                        scanner.nextLine()
                );

        System.out.print(
                "Phone: "
        );

        String phone =
                scanner.nextLine();

        System.out.print(
                "Email: "
        );

        String email =
                scanner.nextLine();

        System.out.print(
                "Street: "
        );

        String street =
                scanner.nextLine();

        System.out.print(
                "City: "
        );

        String city =
                scanner.nextLine();

        System.out.print(
                "Country: "
        );

        String country =
                scanner.nextLine();

        System.out.print(
                "Blood Group: "
        );

        String bloodGroup =
                scanner.nextLine();

        System.out.print(
                "Genotype: "
        );

        String genotype =
                scanner.nextLine();

        System.out.print(
                "Allergies: "
        );

        String allergies =
                scanner.nextLine();

        System.out.print(
                "Emergency Contact: "
        );

        String emergencyContact =
                scanner.nextLine();

        System.out.print(
                "Emergency Phone: "
        );

        String emergencyPhone =
                scanner.nextLine();


        Patient patient =
                new Patient(
                        0,
                        bloodGroup,
                        genotype,
                        allergies,
                        emergencyContact,
                        emergencyPhone,
                        firstName,
                        lastName,
                        gender,
                        dateOfBirth,
                        phone,
                        email,
                        street,
                        city,
                        country
                );


        boolean success =
                patientService.registerPatient(
                        patient
                );

        if (success) {

            System.out.println();
            System.out.println(
                    "Patient registered successfully."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Failed to register patient."
            );
        }
    }


    // =========================================================
    // VIEW ALL PATIENTS
    // =========================================================

    private static void viewAllPatients() {

        List<Patient> patients =
                patientService.getAllPatients();

        patientView.displayPatients(patients);
    }


    // =========================================================
    // FIND PATIENT
    // =========================================================

    private static void findPatient() {

        int id = readInt(
                "Enter Patient ID: "
        );

        Patient patient =
                patientService.getPatientById(id);

        patientView.displayPatient(patient);
    }


    // =========================================================
    // UPDATE PATIENT
    // =========================================================

    private static void updatePatient() {

        int id = readInt(
                "Enter Patient ID to update: "
        );

        Patient patient =
                patientService.getPatientById(id);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println(
                "Enter new patient information."
        );

        System.out.print(
                "First Name: "
        );

        patient.setFirstName(
                scanner.nextLine()
        );

        System.out.print(
                "Last Name: "
        );

        patient.setLastName(
                scanner.nextLine()
        );

        System.out.print(
                "Gender (M/F): "
        );

        patient.setGender(
                scanner.nextLine()
                        .charAt(0)
        );

        System.out.print(
                "Date of Birth (yyyy-MM-dd): "
        );

        patient.setDateOfBirth(
                LocalDate.parse(
                        scanner.nextLine()
                )
        );

        System.out.print(
                "Phone: "
        );

        patient.setPhone(
                scanner.nextLine()
        );

        System.out.print(
                "Email: "
        );

        patient.setEmail(
                scanner.nextLine()
        );

        System.out.print(
                "Street: "
        );

        patient.setStreet(
                scanner.nextLine()
        );

        System.out.print(
                "City: "
        );

        patient.setCity(
                scanner.nextLine()
        );

        System.out.print(
                "Country: "
        );

        patient.setCountry(
                scanner.nextLine()
        );

        System.out.print(
                "Blood Group: "
        );

        patient.setBloodGroup(
                scanner.nextLine()
        );

        System.out.print(
                "Genotype: "
        );

        patient.setGenotype(
                scanner.nextLine()
        );

        System.out.print(
                "Allergies: "
        );

        patient.setAllergies(
                scanner.nextLine()
        );

        System.out.print(
                "Emergency Contact: "
        );

        patient.setEmergencyContact(
                scanner.nextLine()
        );

        System.out.print(
                "Emergency Phone: "
        );

        patient.setEmergencyPhone(
                scanner.nextLine()
        );


        boolean success =
                patientService.updatePatient(
                        patient
                );

        if (success) {

            System.out.println(
                    "Patient updated successfully."
            );

        } else {

            System.out.println(
                    "Patient update failed."
            );
        }
    }


    // =========================================================
    // DELETE PATIENT
    // =========================================================

    private static void deletePatient() {

        int id = readInt(
                "Enter Patient ID to delete: "
        );

        Patient patient =
                patientService.getPatientById(id);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        // Display patient details before deleting
        patientView.displayPatient(patient);

        System.out.print(
                "Are you sure you want to delete this patient? (Y/N): "
        );

        String answer =
                scanner.nextLine();

        if (!answer.equalsIgnoreCase("Y")) {

            System.out.println(
                    "Delete cancelled."
            );

            return;
        }

        boolean success =
                patientService.deletePatient(id);

        if (success) {

            System.out.println(
                    "Patient deleted successfully."
            );

        } else {

            System.out.println(
                    "Patient deletion failed."
            );
        }
    }


    // =========================================================
    // READ INTEGER
    // =========================================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}