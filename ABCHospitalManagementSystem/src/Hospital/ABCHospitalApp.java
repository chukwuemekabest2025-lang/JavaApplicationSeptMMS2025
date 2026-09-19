package hospital;

import hospital.models.Patient;
import hospital.models.Doctor;
import hospital.models.Nurse;
import hospital.models.Department;
import hospital.models.Appointment;
import hospital.models.Admission;
import hospital.models.Bed;
import hospital.models.LaboratoryTest;
import hospital.models.LaboratoryTechnician;
import hospital.models.Pharmacist;
import hospital.models.Medication;
import hospital.models.Prescription;
import hospital.models.PrescriptionItem;
import hospital.models.MedicationDispensing;
import hospital.models.Invoice;
import hospital.models.InvoiceItem;
import hospital.models.Payment;
import hospital.models.User;
import hospital.models.StaffRole;
import hospital.models.Staff;

import hospital.services.PatientService;
import hospital.services.DoctorService;
import hospital.services.NurseService;
import hospital.services.AppointmentService;
import hospital.services.AdmissionService;
import hospital.services.LaboratoryService;
import hospital.services.PharmacistService;
import hospital.services.MedicationService;
import hospital.services.PrescriptionService;
import hospital.services.PrescriptionItemService;
import hospital.services.MedicationDispensingService;
import hospital.services.InvoiceService;
import hospital.services.InvoiceItemService;
import hospital.services.PaymentService;
import hospital.services.UserService;

import hospital.userview.PatientView;
import hospital.userview.DoctorView;
import hospital.userview.NurseView;
import hospital.userview.AppointmentView;
import hospital.userview.AdmissionView;
import hospital.userview.LaboratoryView;
import hospital.userview.PharmacistView;
import hospital.userview.MedicationView;
import hospital.userview.PrescriptionView;
import hospital.userview.PrescriptionItemView;
import hospital.userview.MedicationDispensingView;
import hospital.userview.InvoiceView;
import hospital.userview.InvoiceItemView;
import hospital.userview.PaymentView;
import hospital.userview.UserView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ABCHospitalApp {

    private static final Scanner scanner =
            new Scanner(System.in);

    // =========================================================
    // PATIENT
    // =========================================================

    private static final PatientService patientService =
            new PatientService();

    private static final PatientView patientView =
            new PatientView();


    // =========================================================
    // DOCTOR
    // =========================================================

    private static final DoctorService doctorService =
            new DoctorService();

    private static final DoctorView doctorView =
            new DoctorView();


    // =========================================================
    // NURSE
    // =========================================================

    private static final NurseService nurseService =
            new NurseService();

    private static final NurseView nurseView =
            new NurseView();


    // =========================================================
    // APPOINTMENT
    // =========================================================

    private static final AppointmentService appointmentService =
            new AppointmentService();

    private static final AppointmentView appointmentView =
            new AppointmentView();


    // =========================================================
    // ADMISSION
    // =========================================================

    private static final AdmissionService admissionService =
            new AdmissionService();

    private static final AdmissionView admissionView =
            new AdmissionView();


    // =========================================================
    // LABORATORY
    // =========================================================

    private static final LaboratoryService laboratoryService =
            new LaboratoryService();

    private static final LaboratoryView laboratoryView =
            new LaboratoryView();


    // =========================================================
    // PHARMACY
    // =========================================================

    private static final PharmacistService pharmacistService =
            new PharmacistService();

    private static final PharmacistView pharmacistView =
            new PharmacistView();


    private static final MedicationService medicationService =
            new MedicationService();

    private static final MedicationView medicationView =
            new MedicationView();


    private static final PrescriptionService prescriptionService =
            new PrescriptionService();

    private static final PrescriptionView prescriptionView =
            new PrescriptionView();


    private static final PrescriptionItemService prescriptionItemService =
            new PrescriptionItemService();

    private static final PrescriptionItemView prescriptionItemView =
            new PrescriptionItemView();


    private static final MedicationDispensingService medicationDispensingService =
            new MedicationDispensingService();

    private static final MedicationDispensingView medicationDispensingView =
            new MedicationDispensingView();


    // =========================================================
    // BILLING
    // =========================================================

    private static final InvoiceService invoiceService =
            new InvoiceService();

    private static final InvoiceView invoiceView =
            new InvoiceView();


    private static final InvoiceItemService invoiceItemService =
            new InvoiceItemService();

    private static final InvoiceItemView invoiceItemView =
            new InvoiceItemView();


    private static final PaymentService paymentService =
            new PaymentService();

    private static final PaymentView paymentView =
            new PaymentView();


    // =========================================================
    // USER MANAGEMENT
    // =========================================================

    private static final UserService userService =
            new UserService();

    private static final UserView userView =
            new UserView();


    // =========================================================
    // DATE FORMATTER
    // =========================================================

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        if (!setupFirstUser()) {
            scanner.close();
            return;
        }

        while (true) {

            User loggedInUser = login();

            if (loggedInUser == null) {
                System.out.println();
                System.out.println("Thank you for using ABC Hospital System.");
                scanner.close();
                return;
            }

            while (true) {

                displayMainMenu(loggedInUser);

                int choice =
                        readInt(
                                "Enter your choice: "
                        );

                switch (choice) {

                    case 1:
                        patientMenu();
                        break;

                    case 2:
                        doctorMenu();
                        break;

                    case 3:
                        nurseMenu();
                        break;

                    case 4:
                        appointmentMenu();
                        break;

                    case 5:
                        admissionMenu();
                        break;

                    case 6:
                        laboratoryMenu();
                        break;

                    case 7:
                        pharmacyMenu();
                        break;

                    case 8:
                        billingMenu();
                        break;

                    case 9:
                        userMenu();
                        break;

                    case 0:
                        System.out.println();
                        System.out.println("========================================");
                        System.out.println("              LOGGED OUT");
                        System.out.println("========================================");
                        System.out.println("Goodbye, " + loggedInUser.getUsername() + ".");
                        System.out.println();
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

                if (choice == 0) {
                    break;
                }
            }
        }
    }


    // =========================================================
    // FIRST-TIME USER SETUP
    // =========================================================

    private static boolean setupFirstUser() {

        try {
            List<User> users = userService.getAllUsers();

            if (users != null && !users.isEmpty()) {
                return true;
            }

            while (true) {
                System.out.println();
                System.out.println("==========================================");
                System.out.println("       ABC HOSPITAL FIRST-TIME SETUP");
                System.out.println("==========================================");
                System.out.println("No user accounts have been found.");
                System.out.println("Create the first user account to continue.");
                System.out.println();

                String username;
                while (true) {
                    System.out.print("Create Username: ");
                    username = scanner.nextLine().trim();

                    if (username.isEmpty()) {
                        System.out.println("Username is required.");
                        continue;
                    }
                    break;
                }

                String password;
                while (true) {
                    System.out.print("Create Password: ");
                    password = scanner.nextLine();

                    if (password.trim().isEmpty()) {
                        System.out.println("Password is required.");
                        continue;
                    }

                    System.out.print("Confirm Password: ");
                    String confirmPassword = scanner.nextLine();

                    if (!password.equals(confirmPassword)) {
                        System.out.println("Passwords do not match. Please try again.");
                        continue;
                    }
                    break;
                }

                User firstUser = new User();
                firstUser.setUsername(username);
                firstUser.setPasswordHash(password);
                firstUser.setRole(StaffRole.STAFF);
                firstUser.setStaff(null);
                firstUser.setActive(true);

                userService.addUser(firstUser);

                System.out.println();
                System.out.println("==========================================");
                System.out.println("       FIRST USER CREATED SUCCESSFULLY");
                System.out.println("==========================================");
                System.out.println("Username: " + username);
                System.out.println("Role: STAFF");
                System.out.println("Account Status: Active");
                System.out.println("==========================================");
                System.out.println("Please use these credentials to log in.");
                System.out.println();

                return true;
            }

        } catch (Exception e) {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("          SYSTEM STARTUP ERROR");
            System.out.println("==========================================");
            System.out.println("Unable to check user accounts.");
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            return false;
        }
    }


    // =========================================================
    // LOGIN
    // =========================================================

    private static User login() {

        while (true) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("          ABC HOSPITAL LOGIN");
            System.out.println("==========================================");

            System.out.print("Username (or 0 to Exit): ");
            String username = scanner.nextLine().trim();

            if (username.equals("0")) {
                System.out.println();
                System.out.println("==========================================");
                System.out.println("       THANK YOU FOR USING ABC HOSPITAL");
                System.out.println("==========================================");
                return null;
            }

            if (username.isEmpty()) {
                System.out.println("Username is required.");
                continue;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();

            try {

                User user = userService.getUserByUsername(username);

                if (user == null ||
                        user.getPasswordHash() == null ||
                        !user.getPasswordHash().equals(password)) {

                    System.out.println();
                    System.out.println("==========================================");
                    System.out.println("             LOGIN FAILED");
                    System.out.println("==========================================");
                    System.out.println("Invalid username or password.");
                    System.out.println();
                    continue;
                }

                if (!user.isActive()) {
                    System.out.println();
                    System.out.println("==========================================");
                    System.out.println("          ACCOUNT INACTIVE");
                    System.out.println("==========================================");
                    System.out.println("This account is currently inactive.");
                    System.out.println();
                    continue;
                }

                System.out.println();
                System.out.println("==========================================");
                System.out.println("             LOGIN SUCCESSFUL");
                System.out.println("==========================================");
                System.out.println("Welcome, " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("==========================================");

                return user;

            } catch (Exception e) {
                System.out.println();
                System.out.println("Login error: " + e.getMessage());
                System.out.println();
            }
        }
    }


    // =========================================================
    // MAIN MENU
    // =========================================================

    private static void displayMainMenu(User loggedInUser) {

        System.out.println();
        System.out.println("============================================================");
        System.out.println("                 ABC HOSPITAL DASHBOARD");
        System.out.println("============================================================");
        System.out.println("Logged in User : " + loggedInUser.getUsername());
        System.out.println("Role           : " + loggedInUser.getRole());
        System.out.println("Account Status : " + (loggedInUser.isActive() ? "Active" : "Inactive"));
        System.out.println("System Status  : ONLINE");
        System.out.println("============================================================");
        System.out.println();
        System.out.println("                    HOSPITAL SERVICES");
        System.out.println("============================================================");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Nurse Management");
        System.out.println("4. Appointment Management");
        System.out.println("5. Admission Management");
        System.out.println("6. Laboratory Management");
        System.out.println("7. Pharmacy Management");
        System.out.println("8. Billing Management");
        System.out.println("9. User");
        System.out.println("0. Logout");
        System.out.println("============================================================");
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

            int choice =
                    readInt(
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

        patientView.displayPatients(
                patients
        );
    }


    // =========================================================
    // FIND PATIENT
    // =========================================================

    private static void findPatient() {

        int id =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        id
                );

        patientView.displayPatient(
                patient
        );
    }


    // =========================================================
    // UPDATE PATIENT
    // =========================================================

    private static void updatePatient() {

        int id =
                readInt(
                        "Enter Patient ID to update: "
                );

        Patient patient =
                patientService.getPatientById(
                        id
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

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

        int id =
                readInt(
                        "Enter Patient ID to delete: "
                );

        Patient patient =
                patientService.getPatientById(
                        id
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        patientView.displayPatient(
                patient
        );

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
                patientService.deletePatient(
                        id
                );

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
    // DOCTOR MENU
    // =========================================================

    private static void doctorMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "           DOCTOR MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Register Doctor"
            );

            System.out.println(
                    "2. View All Doctors"
            );

            System.out.println(
                    "3. Find Doctor"
            );

            System.out.println(
                    "4. Update Doctor"
            );

            System.out.println(
                    "5. Delete Doctor"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    registerDoctor();
                    break;

                case 2:
                    viewAllDoctors();
                    break;

                case 3:
                    findDoctor();
                    break;

                case 4:
                    updateDoctor();
                    break;

                case 5:
                    deleteDoctor();
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
    // REGISTER DOCTOR
    // =========================================================

    private static void registerDoctor() {

        System.out.println();

        System.out.println(
                "========== REGISTER DOCTOR =========="
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
                "Employment Date (yyyy-MM-dd): "
        );

        LocalDate employmentDate =
                LocalDate.parse(
                        scanner.nextLine()
                );

        System.out.print(
                "Salary: "
        );

        double salary =
                Double.parseDouble(
                        scanner.nextLine()
                );

        int departmentId =
                readInt(
                        "Department ID: "
                );

        System.out.print(
                "Department Name: "
        );

        String departmentName =
                scanner.nextLine();

        Department department =
                new Department();

        department.setId(
                departmentId
        );

        department.setName(
                departmentName
        );

        System.out.print(
                "Specialization: "
        );

        String specialization =
                scanner.nextLine();

        System.out.print(
                "License Number: "
        );

        String licenseNumber =
                scanner.nextLine();

        Doctor doctor =
                new Doctor(
                        firstName,
                        lastName,
                        gender,
                        dateOfBirth,
                        phone,
                        email,
                        street,
                        city,
                        country,
                        0,
                        employmentDate,
                        salary,
                        department,
                        specialization,
                        licenseNumber
                );

        boolean success =
                doctorService.registerDoctor(
                        doctor
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Doctor registered successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to register doctor."
            );
        }
    }


    // =========================================================
    // VIEW ALL DOCTORS
    // =========================================================

    private static void viewAllDoctors() {

        List<Doctor> doctors =
                doctorService.getAllDoctors();

        doctorView.displayDoctors(
                doctors
        );
    }


    // =========================================================
    // FIND DOCTOR
    // =========================================================

    private static void findDoctor() {

        int id =
                readInt(
                        "Enter Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        id
                );

        doctorView.displayDoctor(
                doctor
        );
    }


    // =========================================================
    // UPDATE DOCTOR
    // =========================================================

    private static void updateDoctor() {

        int id =
                readInt(
                        "Enter Doctor/Staff ID to update: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        id
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        System.out.println();

        doctorView.displayDoctor(
                doctor
        );

        System.out.println();

        System.out.println(
                "Enter new doctor information."
        );

        System.out.print(
                "First Name: "
        );

        doctor.setFirstName(
                scanner.nextLine()
        );

        System.out.print(
                "Last Name: "
        );

        doctor.setLastName(
                scanner.nextLine()
        );

        System.out.print(
                "Gender (M/F): "
        );

        doctor.setGender(
                scanner.nextLine()
                        .charAt(0)
        );

        System.out.print(
                "Date of Birth (yyyy-MM-dd): "
        );

        doctor.setDateOfBirth(
                LocalDate.parse(
                        scanner.nextLine()
                )
        );

        System.out.print(
                "Phone: "
        );

        doctor.setPhone(
                scanner.nextLine()
        );

        System.out.print(
                "Email: "
        );

        doctor.setEmail(
                scanner.nextLine()
        );

        System.out.print(
                "Street: "
        );

        doctor.setStreet(
                scanner.nextLine()
        );

        System.out.print(
                "City: "
        );

        doctor.setCity(
                scanner.nextLine()
        );

        System.out.print(
                "Country: "
        );

        doctor.setCountry(
                scanner.nextLine()
        );

        System.out.print(
                "Employment Date (yyyy-MM-dd): "
        );

        doctor.setEmploymentDate(
                LocalDate.parse(
                        scanner.nextLine()
                )
        );

        System.out.print(
                "Salary: "
        );

        doctor.setSalary(
                Double.parseDouble(
                        scanner.nextLine()
                )
        );

        int departmentId =
                readInt(
                        "Department ID: "
                );

        System.out.print(
                "Department Name: "
        );

        String departmentName =
                scanner.nextLine();

        Department department =
                new Department();

        department.setId(
                departmentId
        );

        department.setName(
                departmentName
        );

        doctor.setDepartment(
                department
        );

        System.out.print(
                "Specialization: "
        );

        doctor.setSpecialization(
                scanner.nextLine()
        );

        System.out.print(
                "License Number: "
        );

        doctor.setLicenseNumber(
                scanner.nextLine()
        );

        boolean success =
                doctorService.updateDoctor(
                        doctor
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Doctor updated successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Doctor update failed."
            );
        }
    }


    // =========================================================
    // DELETE DOCTOR
    // =========================================================

    private static void deleteDoctor() {

        int id =
                readInt(
                        "Enter Doctor/Staff ID to delete: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        id
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        doctorView.displayDoctor(
                doctor
        );

        System.out.print(
                "Are you sure you want to delete this doctor? (Y/N): "
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
                doctorService.deleteDoctor(
                        id
                );

        if (success) {

            System.out.println(
                    "Doctor deleted successfully."
            );

        } else {

            System.out.println(
                    "Doctor deletion failed."
            );
        }
    }


    // =========================================================
    // NURSE MENU
    // =========================================================

    private static void nurseMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "            NURSE MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Register Nurse"
            );

            System.out.println(
                    "2. View All Nurses"
            );

            System.out.println(
                    "3. Find Nurse"
            );

            System.out.println(
                    "4. Update Nurse"
            );

            System.out.println(
                    "5. Delete Nurse"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    registerNurse();
                    break;

                case 2:
                    viewAllNurses();
                    break;

                case 3:
                    findNurse();
                    break;

                case 4:
                    updateNurse();
                    break;

                case 5:
                    deleteNurse();
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
    // REGISTER NURSE
    // =========================================================

    private static void registerNurse() {

        System.out.println();

        System.out.println(
                "========== REGISTER NURSE =========="
        );

        System.out.println();

        System.out.println(
                "----- PERSONAL INFORMATION -----"
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

        System.out.println();

        System.out.println(
                "----- STAFF INFORMATION -----"
        );

        System.out.print(
                "Employment Date (yyyy-MM-dd): "
        );

        LocalDate employmentDate =
                LocalDate.parse(
                        scanner.nextLine()
                );

        System.out.print(
                "Salary: "
        );

        double salary =
                Double.parseDouble(
                        scanner.nextLine()
                );

        System.out.println();

        System.out.println(
                "----- DEPARTMENT INFORMATION -----"
        );

        int departmentId =
                readInt(
                        "Department ID: "
                );

        System.out.print(
                "Department Name: "
        );

        String departmentName =
                scanner.nextLine();

        Department department =
                new Department();

        department.setId(
                departmentId
        );

        department.setName(
                departmentName
        );

        System.out.println();

        System.out.println(
                "----- NURSE INFORMATION -----"
        );

        System.out.print(
                "Nursing License: "
        );

        String nursingLicense =
                scanner.nextLine();

        System.out.print(
                "Qualification: "
        );

        String qualification =
                scanner.nextLine();

        Nurse nurse =
                new Nurse(
                        firstName,
                        lastName,
                        gender,
                        dateOfBirth,
                        phone,
                        email,
                        street,
                        city,
                        country,
                        0,
                        employmentDate,
                        salary,
                        department,
                        nursingLicense,
                        qualification
                );

        boolean success =
                nurseService.registerNurse(
                        nurse
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Nurse registered successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to register nurse."
            );
        }
    }


    // =========================================================
    // VIEW ALL NURSES
    // =========================================================

    private static void viewAllNurses() {

        List<Nurse> nurses =
                nurseService.getAllNurses();

        nurseView.displayNurses(
                nurses
        );
    }


    // =========================================================
    // FIND NURSE
    // =========================================================

    private static void findNurse() {

        int staffId =
                readInt(
                        "Enter Nurse/Staff ID: "
                );

        Nurse nurse =
                nurseService.getNurseById(
                        staffId
                );

        nurseView.displayNurse(
                nurse
        );
    }


    // =========================================================
    // UPDATE NURSE
    // =========================================================

    private static void updateNurse() {

        int staffId =
                readInt(
                        "Enter Nurse/Staff ID to update: "
                );

        Nurse nurse =
                nurseService.getNurseById(
                        staffId
                );

        if (nurse == null) {

            System.out.println(
                    "Nurse not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Current nurse information:"
        );

        nurseView.displayNurse(
                nurse
        );

        System.out.println();

        System.out.println(
                "Enter new nurse information."
        );

        System.out.println();

        System.out.println(
                "----- PERSONAL INFORMATION -----"
        );

        System.out.print(
                "First Name: "
        );

        nurse.setFirstName(
                scanner.nextLine()
        );

        System.out.print(
                "Last Name: "
        );

        nurse.setLastName(
                scanner.nextLine()
        );

        System.out.print(
                "Gender (M/F): "
        );

        nurse.setGender(
                scanner.nextLine()
                        .charAt(0)
        );

        System.out.print(
                "Date of Birth (yyyy-MM-dd): "
        );

        nurse.setDateOfBirth(
                LocalDate.parse(
                        scanner.nextLine()
                )
        );

        System.out.print(
                "Phone: "
        );

        nurse.setPhone(
                scanner.nextLine()
        );

        System.out.print(
                "Email: "
        );

        nurse.setEmail(
                scanner.nextLine()
        );

        System.out.print(
                "Street: "
        );

        nurse.setStreet(
                scanner.nextLine()
        );

        System.out.print(
                "City: "
        );

        nurse.setCity(
                scanner.nextLine()
        );

        System.out.print(
                "Country: "
        );

        nurse.setCountry(
                scanner.nextLine()
        );

        System.out.println();

        System.out.println(
                "----- STAFF INFORMATION -----"
        );

        System.out.print(
                "Employment Date (yyyy-MM-dd): "
        );

        nurse.setEmploymentDate(
                LocalDate.parse(
                        scanner.nextLine()
                )
        );

        System.out.print(
                "Salary: "
        );

        nurse.setSalary(
                Double.parseDouble(
                        scanner.nextLine()
                )
        );

        System.out.println();

        System.out.println(
                "----- DEPARTMENT INFORMATION -----"
        );

        int departmentId =
                readInt(
                        "Department ID: "
                );

        System.out.print(
                "Department Name: "
        );

        String departmentName =
                scanner.nextLine();

        Department department =
                new Department();

        department.setId(
                departmentId
        );

        department.setName(
                departmentName
        );

        nurse.setDepartment(
                department
        );

        System.out.println();

        System.out.println(
                "----- NURSE INFORMATION -----"
        );

        System.out.print(
                "Nursing License: "
        );

        nurse.setNursingLicense(
                scanner.nextLine()
        );

        System.out.print(
                "Qualification: "
        );

        nurse.setQualification(
                scanner.nextLine()
        );

        boolean success =
                nurseService.updateNurse(
                        nurse
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Nurse updated successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Nurse update failed."
            );
        }
    }


    // =========================================================
    // DELETE NURSE
    // =========================================================

    private static void deleteNurse() {

        int staffId =
                readInt(
                        "Enter Nurse/Staff ID to delete: "
                );

        Nurse nurse =
                nurseService.getNurseById(
                        staffId
                );

        if (nurse == null) {

            System.out.println(
                    "Nurse not found."
            );

            return;
        }

        nurseView.displayNurse(
                nurse
        );

        System.out.print(
                "Are you sure you want to delete this nurse? (Y/N): "
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
                nurseService.deleteNurse(
                        staffId
                );

        if (success) {

            System.out.println(
                    "Nurse deleted successfully."
            );

        } else {

            System.out.println(
                    "Nurse deletion failed."
            );
        }
    }


    // =========================================================
    // APPOINTMENT MENU
    // =========================================================

    private static void appointmentMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "        APPOINTMENT MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Create Appointment"
            );

            System.out.println(
                    "2. View All Appointments"
            );

            System.out.println(
                    "3. Find Appointment"
            );

            System.out.println(
                    "4. Update Appointment"
            );

            System.out.println(
                    "5. Delete Appointment"
            );

            System.out.println(
                    "6. View Patient Appointments"
            );

            System.out.println(
                    "7. View Doctor Appointments"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    createAppointment();
                    break;

                case 2:
                    viewAllAppointments();
                    break;

                case 3:
                    findAppointment();
                    break;

                case 4:
                    updateAppointment();
                    break;

                case 5:
                    deleteAppointment();
                    break;

                case 6:
                    viewPatientAppointments();
                    break;

                case 7:
                    viewDoctorAppointments();
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
    // CREATE APPOINTMENT
    // =========================================================

    private static void createAppointment() {

        System.out.println();

        System.out.println(
                "========== CREATE APPOINTMENT =========="
        );

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Patient: "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        int doctorId =
                readInt(
                        "Enter Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Doctor: Dr. "
                        + doctor.getFirstName()
                        + " "
                        + doctor.getLastName()
        );

        System.out.println(
                "Specialization: "
                        + doctor.getSpecialization()
        );

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd HH:mm"
        );

        System.out.print(
                "Appointment Date: "
        );

        String dateInput =
                scanner.nextLine();

        LocalDateTime appointmentDate;

        try {

            appointmentDate =
                    LocalDateTime.parse(
                            dateInput,
                            DATE_TIME_FORMATTER
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );

            return;
        }

        System.out.print(
                "Reason for Appointment: "
        );

        String reason =
                scanner.nextLine();

        String status =
                selectAppointmentStatus();

        if (status == null) {
            return;
        }

        System.out.print(
                "Notes: "
        );

        String notes =
                scanner.nextLine();

        Appointment appointment =
                new Appointment();

        appointment.setPatient(
                patient
        );

        appointment.setDoctor(
                doctor
        );

        appointment.setAppointmentDate(
                appointmentDate
        );

        appointment.setReason(
                reason
        );

        appointment.setStatus(
                status
        );

        appointment.setNotes(
                notes
        );

        boolean success =
                appointmentService.addAppointment(
                        appointment
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Appointment created successfully."
            );

            if (appointment.getId() > 0) {

                System.out.println(
                        "Appointment ID: "
                                + appointment.getId()
                );
            }

        } else {

            System.out.println();

            System.out.println(
                    "Failed to create appointment."
            );
        }
    }


    // =========================================================
    // SELECT APPOINTMENT STATUS
    // =========================================================

    private static String selectAppointmentStatus() {

        System.out.println();

        System.out.println(
                "Appointment Status"
        );

        System.out.println(
                "1. Scheduled"
        );

        System.out.println(
                "2. Completed"
        );

        System.out.println(
                "3. Cancelled"
        );

        System.out.println(
                "4. Pending"
        );

        int choice =
                readInt(
                        "Select status: "
                );

        switch (choice) {

            case 1:
                return "Scheduled";

            case 2:
                return "Completed";

            case 3:
                return "Cancelled";

            case 4:
                return "Pending";

            default:

                System.out.println(
                        "Invalid status."
                );

                return null;
        }
    }


    // =========================================================
    // VIEW ALL APPOINTMENTS
    // =========================================================

    private static void viewAllAppointments() {

        List<Appointment> appointments =
                appointmentService.getAllAppointments();

        appointmentView.displayAppointments(
                appointments
        );
    }


    // =========================================================
    // FIND APPOINTMENT
    // =========================================================

    private static void findAppointment() {

        int appointmentId =
                readInt(
                        "Enter Appointment ID: "
                );

        Appointment appointment =
                appointmentService.getAppointmentById(
                        appointmentId
                );

        appointmentView.displayAppointment(
                appointment
        );
    }


    // =========================================================
    // UPDATE APPOINTMENT
    // =========================================================

    private static void updateAppointment() {

        int appointmentId =
                readInt(
                        "Enter Appointment ID to update: "
                );

        Appointment appointment =
                appointmentService.getAppointmentById(
                        appointmentId
                );

        if (appointment == null) {

            System.out.println(
                    "Appointment not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Current appointment information:"
        );

        appointmentView.displayAppointment(
                appointment
        );

        int patientId =
                readInt(
                        "Enter new Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        appointment.setPatient(
                patient
        );

        int doctorId =
                readInt(
                        "Enter new Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        appointment.setDoctor(
                doctor
        );

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd HH:mm"
        );

        System.out.print(
                "Appointment Date: "
        );

        String dateInput =
                scanner.nextLine();

        try {

            appointment.setAppointmentDate(
                    LocalDateTime.parse(
                            dateInput,
                            DATE_TIME_FORMATTER
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );

            return;
        }

        System.out.print(
                "Reason: "
        );

        appointment.setReason(
                scanner.nextLine()
        );

        String status =
                selectAppointmentStatus();

        if (status == null) {
            return;
        }

        appointment.setStatus(
                status
        );

        System.out.print(
                "Notes: "
        );

        appointment.setNotes(
                scanner.nextLine()
        );

        boolean success =
                appointmentService.updateAppointment(
                        appointment
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Appointment updated successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Appointment update failed."
            );
        }
    }


    // =========================================================
    // DELETE APPOINTMENT
    // =========================================================

    private static void deleteAppointment() {

        int appointmentId =
                readInt(
                        "Enter Appointment ID to delete: "
                );

        Appointment appointment =
                appointmentService.getAppointmentById(
                        appointmentId
                );

        if (appointment == null) {

            System.out.println(
                    "Appointment not found."
            );

            return;
        }

        appointmentView.displayAppointment(
                appointment
        );

        System.out.print(
                "Are you sure you want to delete this appointment? (Y/N): "
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
                appointmentService.deleteAppointment(
                        appointmentId
                );

        if (success) {

            System.out.println(
                    "Appointment deleted successfully."
            );

        } else {

            System.out.println(
                    "Appointment deletion failed."
            );
        }
    }


    // =========================================================
    // VIEW PATIENT APPOINTMENTS
    // =========================================================

    private static void viewPatientAppointments() {

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Appointments for "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        List<Appointment> appointments =
                appointmentService.getAppointmentsByPatient(
                        patientId
                );

        appointmentView.displayAppointments(
                appointments
        );
    }


    // =========================================================
    // VIEW DOCTOR APPOINTMENTS
    // =========================================================

    private static void viewDoctorAppointments() {

        int doctorId =
                readInt(
                        "Enter Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Appointments for Dr. "
                        + doctor.getFirstName()
                        + " "
                        + doctor.getLastName()
        );

        List<Appointment> appointments =
                appointmentService.getAppointmentsByDoctor(
                        doctorId
                );

        appointmentView.displayAppointments(
                appointments
        );
    }


    // =========================================================
    // ADMISSION MENU
    // =========================================================

    private static void admissionMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "          ADMISSION MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Admit Patient"
            );

            System.out.println(
                    "2. Discharge Patient"
            );

            System.out.println(
                    "3. View All Admissions"
            );

            System.out.println(
                    "4. Find Admission"
            );

            System.out.println(
                    "5. Update Admission"
            );

            System.out.println(
                    "6. Delete Admission"
            );

            System.out.println(
                    "7. View Patient Admissions"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    admitPatient();
                    break;

                case 2:
                    dischargePatient();
                    break;

                case 3:
                    viewAllAdmissions();
                    break;

                case 4:
                    findAdmission();
                    break;

                case 5:
                    updateAdmission();
                    break;

                case 6:
                    deleteAdmission();
                    break;

                case 7:
                    viewPatientAdmissions();
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
    // ADMIT PATIENT
    // =========================================================

    private static void admitPatient() {

        System.out.println();

        System.out.println(
                "========== ADMIT PATIENT =========="
        );

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Patient: "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        // ---------------------------------------------------------
        // SELECT WARD
        // ---------------------------------------------------------

        List<hospital.models.Ward> wards =
                admissionService.getAvailableWards();

        if (wards == null || wards.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No wards currently have available beds."
            );

            return;
        }

        admissionView.displayAvailableWards(
                wards
        );

        int wardChoice =
                readInt(
                        "Select Ward: "
                );

        if (wardChoice < 1 || wardChoice > wards.size()) {

            System.out.println(
                    "Invalid ward selection."
            );

            return;
        }

        hospital.models.Ward selectedWard =
                wards.get(wardChoice - 1);

        // ---------------------------------------------------------
        // SELECT ROOM
        // ---------------------------------------------------------

        List<hospital.models.Room> rooms =
                admissionService.getAvailableRooms(
                        selectedWard.getId()
                );

        if (rooms == null || rooms.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No available rooms found in the selected ward."
            );

            return;
        }

        admissionView.displayAvailableRooms(
                rooms
        );

        int roomChoice =
                readInt(
                        "Select Room: "
                );

        if (roomChoice < 1 || roomChoice > rooms.size()) {

            System.out.println(
                    "Invalid room selection."
            );

            return;
        }

        hospital.models.Room selectedRoom =
                rooms.get(roomChoice - 1);

        // ---------------------------------------------------------
        // SELECT BED
        // ---------------------------------------------------------

        List<Bed> beds =
                admissionService.getAvailableBeds(
                        selectedRoom.getId()
                );

        if (beds == null || beds.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No available beds found in the selected room."
            );

            return;
        }

        admissionView.displayAvailableBeds(
                beds
        );

        int bedChoice =
                readInt(
                        "Select Bed: "
                );

        if (bedChoice < 1 || bedChoice > beds.size()) {

            System.out.println(
                    "Invalid bed selection."
            );

            return;
        }

        Bed selectedBed =
                beds.get(bedChoice - 1);

        System.out.println();

        System.out.println(
                "Selected Location:"
        );

        System.out.println(
                "Ward: "
                        + selectedWard.getName()
        );

        System.out.println(
                "Room: "
                        + selectedRoom.getRoomNumber()
        );

        System.out.println(
                "Bed: "
                        + selectedBed.getBedNumber()
        );

        // ---------------------------------------------------------
        // ADMISSION DATE
        // ---------------------------------------------------------

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd HH:mm"
        );

        System.out.print(
                "Admission Date: "
        );

        String dateInput =
                scanner.nextLine();

        LocalDateTime admissionDate;

        try {

            admissionDate =
                    LocalDateTime.parse(
                            dateInput,
                            DATE_TIME_FORMATTER
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );

            return;
        }

        // ---------------------------------------------------------
        // REASON
        // ---------------------------------------------------------

        System.out.print(
                "Reason for Admission: "
        );

        String reason =
                scanner.nextLine();

        if (reason.trim().isEmpty()) {

            System.out.println(
                    "Admission reason is required."
            );

            return;
        }

        Admission admission =
                new Admission();

        admission.setPatient(
                patient
        );

        admission.setBed(
                selectedBed
        );

        admission.setAdmissionDate(
                admissionDate
        );

        admission.setReason(
                reason
        );

        admission.setStatus(
                "Admitted"
        );

        boolean success =
                admissionService.addAdmission(
                        admission
                );

        if (success) {

            admissionView.displayAdmissionCreated();

            if (admission.getId() > 0) {

                System.out.println(
                        "Admission ID: "
                                + admission.getId()
                );
            }

            System.out.println(
                    "Ward: "
                            + selectedWard.getName()
            );

            System.out.println(
                    "Room: "
                            + selectedRoom.getRoomNumber()
            );

            System.out.println(
                    "Bed: "
                            + selectedBed.getBedNumber()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to admit patient."
            );
        }
    }


    // =========================================================
    // DISCHARGE PATIENT
    // =========================================================

    private static void dischargePatient() {

        System.out.println();

        System.out.println(
                "========== DISCHARGE PATIENT =========="
        );

        int admissionId =
                readInt(
                        "Enter Admission ID: "
                );

        Admission admission =
                admissionService.getAdmissionById(
                        admissionId
                );

        if (admission == null) {

            System.out.println(
                    "Admission record not found."
            );

            return;
        }

        if ("Discharged".equalsIgnoreCase(
                admission.getStatus())) {

            System.out.println(
                    "This patient has already been discharged."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Patient admission information:"
        );

        admissionView.displayAdmission(
                admission
        );

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd"
        );

        System.out.print(
                "Discharge Date: "
        );

        String dischargeDateInput =
                scanner.nextLine();

        LocalDate dischargeDate;

        try {

            dischargeDate =
                    LocalDate.parse(
                            dischargeDateInput
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd"
            );

            return;
        }

        if (admission.getAdmissionDate() != null
                && dischargeDate.isBefore(
                        admission.getAdmissionDate().toLocalDate()
                )) {

            System.out.println(
                    "Discharge date cannot be before the admission date."
            );

            return;
        }

        boolean success =
                admissionService.dischargePatient(
                        admissionId,
                        dischargeDate
                );

        if (success) {

            admissionView.displayPatientDischarged();

            System.out.println(
                    "Discharge Date: "
                            + dischargeDate
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to discharge patient."
            );
        }
    }


    // =========================================================
    // VIEW ALL ADMISSIONS
    // =========================================================

    private static void viewAllAdmissions() {

        List<Admission> admissions =
                admissionService.getAllAdmissions();

        admissionView.displayAdmissions(
                admissions
        );
    }


    // =========================================================
    // FIND ADMISSION
    // =========================================================

    private static void findAdmission() {

        int admissionId =
                readInt(
                        "Enter Admission ID: "
                );

        Admission admission =
                admissionService.getAdmissionById(
                        admissionId
                );

        admissionView.displayAdmission(
                admission
        );
    }


    // =========================================================
    // UPDATE ADMISSION
    // =========================================================

    private static void updateAdmission() {

        int admissionId =
                readInt(
                        "Enter Admission ID to update: "
                );

        Admission admission =
                admissionService.getAdmissionById(
                        admissionId
                );

        if (admission == null) {

            System.out.println(
                    "Admission record not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Current admission information:"
        );

        admissionView.displayAdmission(
                admission
        );

        // ---------------------------------------------------------
        // PATIENT
        // ---------------------------------------------------------

        int patientId =
                readInt(
                        "Enter new Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        admission.setPatient(
                patient
        );

        // ---------------------------------------------------------
        // WARD
        // ---------------------------------------------------------

        List<hospital.models.Ward> wards =
                admissionService.getAvailableWards();

        if (wards == null || wards.isEmpty()) {

            System.out.println();
            System.out.println(
                    "No wards currently have available beds."
            );

            return;
        }

        admissionView.displayAvailableWards(
                wards
        );

        int wardChoice =
                readInt(
                        "Select new Ward: "
                );

        if (wardChoice < 1 || wardChoice > wards.size()) {

            System.out.println(
                    "Invalid ward selection."
            );

            return;
        }

        hospital.models.Ward selectedWard =
                wards.get(wardChoice - 1);

        // ---------------------------------------------------------
        // ROOM
        // ---------------------------------------------------------

        List<hospital.models.Room> rooms =
                admissionService.getAvailableRooms(
                        selectedWard.getId()
                );

        if (rooms == null || rooms.isEmpty()) {

            System.out.println(
                    "No available rooms found in the selected ward."
            );

            return;
        }

        admissionView.displayAvailableRooms(
                rooms
        );

        int roomChoice =
                readInt(
                        "Select new Room: "
                );

        if (roomChoice < 1 || roomChoice > rooms.size()) {

            System.out.println(
                    "Invalid room selection."
            );

            return;
        }

        hospital.models.Room selectedRoom =
                rooms.get(roomChoice - 1);

        // ---------------------------------------------------------
        // BED
        // ---------------------------------------------------------

        List<Bed> beds =
                admissionService.getAvailableBeds(
                        selectedRoom.getId()
                );

        if (beds == null || beds.isEmpty()) {

            System.out.println(
                    "No available beds found in the selected room."
            );

            return;
        }

        admissionView.displayAvailableBeds(
                beds
        );

        int bedChoice =
                readInt(
                        "Select new Bed: "
                );

        if (bedChoice < 1 || bedChoice > beds.size()) {

            System.out.println(
                    "Invalid bed selection."
            );

            return;
        }

        Bed selectedBed =
                beds.get(bedChoice - 1);

        admission.setBed(
                selectedBed
        );

        // ---------------------------------------------------------
        // ADMISSION DATE
        // ---------------------------------------------------------

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd HH:mm"
        );

        System.out.print(
                "Admission Date: "
        );

        String dateInput =
                scanner.nextLine();

        try {

            admission.setAdmissionDate(
                    LocalDateTime.parse(
                            dateInput,
                            DATE_TIME_FORMATTER
                    )
            );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );

            return;
        }

        // ---------------------------------------------------------
        // REASON
        // ---------------------------------------------------------

        System.out.print(
                "Reason for Admission: "
        );

        String reason =
                scanner.nextLine();

        if (reason.trim().isEmpty()) {

            System.out.println(
                    "Admission reason is required."
            );

            return;
        }

        admission.setReason(
                reason
        );

        // ---------------------------------------------------------
        // STATUS
        // ---------------------------------------------------------

        String status =
                selectAdmissionStatus();

        if (status == null) {
            return;
        }

        admission.setStatus(
                status
        );

        if ("Discharged".equalsIgnoreCase(status)) {

            System.out.println();

            System.out.println(
                    "Date format: yyyy-MM-dd"
            );

            System.out.print(
                    "Discharge Date: "
            );

            String dischargeDateInput =
                    scanner.nextLine();

            try {

                LocalDate dischargeDate =
                        LocalDate.parse(
                                dischargeDateInput
                        );

                if (admission.getAdmissionDate() != null
                        && dischargeDate.isBefore(
                                admission.getAdmissionDate().toLocalDate()
                        )) {

                    System.out.println(
                            "Discharge date cannot be before the admission date."
                    );

                    return;
                }

                admission.setDischargeDate(
                        dischargeDate
                );

            } catch (Exception e) {

                System.out.println(
                        "Invalid discharge date format."
                );

                System.out.println(
                        "Please use: yyyy-MM-dd"
                );

                return;
            }

        } else {

            admission.setDischargeDate(
                    null
            );
        }

        boolean success =
                admissionService.updateAdmission(
                        admission
                );

        if (success) {

            admissionView.displayAdmissionUpdated();

        } else {

            System.out.println();

            System.out.println(
                    "Admission update failed."
            );
        }
    }


    // =========================================================
    // SELECT ADMISSION STATUS
    // =========================================================

    private static String selectAdmissionStatus() {

        System.out.println();

        System.out.println(
                "Admission Status"
        );

        System.out.println(
                "1. Admitted"
        );

        System.out.println(
                "2. Discharged"
        );

        System.out.println(
                "3. Cancelled"
        );

        int choice =
                readInt(
                        "Select status: "
                );

        switch (choice) {

            case 1:
                return "Admitted";

            case 2:
                return "Discharged";

            case 3:
                return "Cancelled";

            default:

                System.out.println(
                        "Invalid status."
                );

                return null;
        }
    }


    // =========================================================
    // DELETE ADMISSION
    // =========================================================

    private static void deleteAdmission() {

        int admissionId =
                readInt(
                        "Enter Admission ID to delete: "
                );

        Admission admission =
                admissionService.getAdmissionById(
                        admissionId
                );

        if (admission == null) {

            System.out.println(
                    "Admission record not found."
            );

            return;
        }

        admissionView.displayAdmission(
                admission
        );

        System.out.print(
                "Are you sure you want to delete this admission? (Y/N): "
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
                admissionService.deleteAdmission(
                        admissionId
                );

        if (success) {

            admissionView.displayAdmissionDeleted();

        } else {

            System.out.println();

            System.out.println(
                    "Admission deletion failed."
            );
        }
    }


    // =========================================================
    // VIEW PATIENT ADMISSIONS
    // =========================================================

    private static void viewPatientAdmissions() {

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Admissions for "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        List<Admission> admissions =
                admissionService.getAdmissionsByPatient(
                        patientId
                );

        admissionView.displayAdmissions(
                admissions
        );
    }


    // =========================================================
    // LABORATORY MENU
    // =========================================================

    private static void laboratoryMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "          LABORATORY MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Order Laboratory Test"
            );

            System.out.println(
                    "2. View All Laboratory Tests"
            );

            System.out.println(
                    "3. Find Laboratory Test"
            );

            System.out.println(
                    "4. Record Test Result"
            );

            System.out.println(
                    "5. Delete Laboratory Test"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    orderLaboratoryTest();
                    break;

                case 2:
                    viewAllLaboratoryTests();
                    break;

                case 3:
                    findLaboratoryTest();
                    break;

                case 4:
                    recordLaboratoryResult();
                    break;

                case 5:
                    deleteLaboratoryTest();
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
    // ORDER LABORATORY TEST
    // =========================================================

    private static void orderLaboratoryTest() {

        System.out.println();

        System.out.println(
                "========== ORDER LABORATORY TEST =========="
        );

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Patient: "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        int technicianId =
                readInt(
                        "Enter Laboratory Technician/Staff ID: "
                );

        if (technicianId <= 0) {

            System.out.println(
                    "Invalid technician ID."
            );

            return;
        }

        System.out.print(
                "Test Name: "
        );

        String testName =
                scanner.nextLine();

        if (testName.trim().isEmpty()) {

            System.out.println(
                    "Test name is required."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Date format: yyyy-MM-dd HH:mm"
        );

        System.out.print(
                "Test Date: "
        );

        String dateInput =
                scanner.nextLine();

        LocalDateTime testDate;

        try {

            testDate =
                    LocalDateTime.parse(
                            dateInput,
                            DATE_TIME_FORMATTER
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date/time format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd HH:mm"
            );

            return;
        }

        System.out.print(
                "Reference Range (optional): "
        );

        String referenceRange =
                scanner.nextLine();

        LaboratoryTest test =
                new LaboratoryTest();

        test.setPatient(
                patient
        );

        LaboratoryTechnician technician =
                new LaboratoryTechnician();

        technician.setStaffId(
                technicianId
        );

        test.setTechnician(
                technician
        );

        test.setTestName(
                testName
        );

        test.setTestDate(
                testDate
        );

        test.setResult(
                null
        );

        test.setReferenceRange(
                referenceRange.trim().isEmpty()
                        ? null
                        : referenceRange
        );

        test.setStatus(
                "Pending"
        );

        boolean success =
                laboratoryService.orderTest(
                        test
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Laboratory test ordered successfully."
            );

            if (test.getId() > 0) {

                System.out.println(
                        "Laboratory Test ID: "
                                + test.getId()
                );
            }

        } else {

            System.out.println();

            System.out.println(
                    "Failed to order laboratory test."
            );
        }
    }


    // =========================================================
    // VIEW ALL LABORATORY TESTS
    // =========================================================

    private static void viewAllLaboratoryTests() {

        List<LaboratoryTest> tests =
                laboratoryService.getAllTests();

        laboratoryView.displayLaboratoryTests(
                tests
        );
    }


    // =========================================================
    // FIND LABORATORY TEST
    // =========================================================

    private static void findLaboratoryTest() {

        int testId =
                readInt(
                        "Enter Laboratory Test ID: "
                );

        LaboratoryTest test =
                laboratoryService.getTestById(
                        testId
                );

        laboratoryView.displayLaboratoryTest(
                test
        );
    }


    // =========================================================
    // RECORD LABORATORY RESULT
    // =========================================================

    private static void recordLaboratoryResult() {

        System.out.println();

        System.out.println(
                "========== RECORD LABORATORY RESULT =========="
        );

        int testId =
                readInt(
                        "Enter Laboratory Test ID: "
                );

        LaboratoryTest test =
                laboratoryService.getTestById(
                        testId
                );

        if (test == null) {

            System.out.println(
                    "Laboratory test not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Current test information:"
        );

        laboratoryView.displayLaboratoryTest(
                test
        );

        int technicianId =
                readInt(
                        "Enter Laboratory Technician/Staff ID: "
                );

        System.out.print(
                "Result: "
        );

        String result =
                scanner.nextLine();

        if (result.trim().isEmpty()) {

            System.out.println(
                    "Test result cannot be empty."
            );

            return;
        }

        System.out.print(
                "Reference Range (optional): "
        );

        String referenceRange =
                scanner.nextLine();

        String status =
                selectLaboratoryStatus();

        if (status == null) {
            return;
        }

        boolean success =
                laboratoryService.recordTestResult(
                        testId,
                        technicianId,
                        result,
                        referenceRange.trim().isEmpty()
                                ? null
                                : referenceRange,
                        status
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Laboratory test result recorded successfully."
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to record laboratory test result."
            );
        }
    }


    // =========================================================
    // SELECT LABORATORY STATUS
    // =========================================================

    private static String selectLaboratoryStatus() {

        System.out.println();

        System.out.println(
                "Laboratory Test Status"
        );

        System.out.println(
                "1. Pending"
        );

        System.out.println(
                "2. Completed"
        );

        System.out.println(
                "3. Cancelled"
        );

        int choice =
                readInt(
                        "Select status: "
                );

        switch (choice) {

            case 1:
                return "Pending";

            case 2:
                return "Completed";

            case 3:
                return "Cancelled";

            default:

                System.out.println(
                        "Invalid status."
                );

                return null;
        }
    }


    // =========================================================
    // DELETE LABORATORY TEST
    // =========================================================

    private static void deleteLaboratoryTest() {

        int testId =
                readInt(
                        "Enter Laboratory Test ID to delete: "
                );

        LaboratoryTest test =
                laboratoryService.getTestById(
                        testId
                );

        if (test == null) {

            System.out.println(
                    "Laboratory test not found."
            );

            return;
        }

        laboratoryView.displayLaboratoryTest(
                test
        );

        System.out.print(
                "Are you sure you want to delete this laboratory test? (Y/N): "
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
                laboratoryService.removeTest(
                        testId
                );

        if (success) {

            System.out.println(
                    "Laboratory test deleted successfully."
            );

        } else {

            System.out.println(
                    "Laboratory test deletion failed."
            );
        }
    }


    // =========================================================
    // PHARMACY MENU
    // =========================================================

    private static void pharmacyMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "           PHARMACY MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Pharmacist Management"
            );

            System.out.println(
                    "2. Medication Management"
            );

            System.out.println(
                    "3. Prescription Management"
            );

            System.out.println(
                    "4. Prescription Item Management"
            );

            System.out.println(
                    "5. Medication Dispensing"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    pharmacistMenu();
                    break;

                case 2:
                    medicationMenu();
                    break;

                case 3:
                    prescriptionMenu();
                    break;

                case 4:
                    prescriptionItemMenu();
                    break;

                case 5:
                    medicationDispensingMenu();
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
    // PHARMACIST MENU
    // =========================================================

    private static void pharmacistMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "          PHARMACIST MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Register Pharmacist"
            );

            System.out.println(
                    "2. View All Pharmacists"
            );

            System.out.println(
                    "3. Find Pharmacist"
            );

            System.out.println(
                    "4. Find Pharmacist by Staff ID"
            );

            System.out.println(
                    "5. Update Pharmacist"
            );

            System.out.println(
                    "6. Delete Pharmacist"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    registerPharmacist();
                    break;

                case 2:
                    viewAllPharmacists();
                    break;

                case 3:
                    findPharmacist();
                    break;

                case 4:
                    findPharmacistByStaffId();
                    break;

                case 5:
                    updatePharmacist();
                    break;

                case 6:
                    deletePharmacist();
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
    // REGISTER PHARMACIST
    // =========================================================

    private static void registerPharmacist() {

        System.out.println();

        System.out.println(
                "========== REGISTER PHARMACIST =========="
        );

        int staffId =
                readInt(
                        "Enter Staff ID: "
                );

        if (staffId <= 0) {

            System.out.println(
                    "Invalid Staff ID."
            );

            return;
        }

        Pharmacist existing =
                pharmacistService.getPharmacistByStaffId(
                        staffId
                );

        if (existing != null) {

            System.out.println(
                    "A pharmacist already exists for this Staff ID."
            );

            return;
        }

        System.out.print(
                "Qualification: "
        );

        String qualification =
                scanner.nextLine();

        System.out.print(
                "License Number: "
        );

        String licenseNumber =
                scanner.nextLine();

        Pharmacist pharmacist =
                new Pharmacist();

        pharmacist.setStaffId(
                staffId
        );

        pharmacist.setQualification(
                qualification
        );

        pharmacist.setLicenseNumber(
                licenseNumber
        );

        boolean success =
                pharmacistService.addPharmacist(
                        pharmacist
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Pharmacist registered successfully."
            );

            System.out.println(
                    "Pharmacist ID: "
                            + pharmacist.getPharmacistId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to register pharmacist."
            );
        }
    }


    // =========================================================
    // VIEW ALL PHARMACISTS
    // =========================================================

    private static void viewAllPharmacists() {

        List<Pharmacist> pharmacists =
                pharmacistService.getAllPharmacists();

        pharmacistView.displayPharmacists(
                pharmacists
        );
    }


    // =========================================================
    // FIND PHARMACIST
    // =========================================================

    private static void findPharmacist() {

        int pharmacistId =
                readInt(
                        "Enter Pharmacist ID: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistById(
                        pharmacistId
                );

        pharmacistView.displayPharmacist(
                pharmacist
        );
    }


    // =========================================================
    // FIND PHARMACIST BY STAFF ID
    // =========================================================

    private static void findPharmacistByStaffId() {

        int staffId =
                readInt(
                        "Enter Staff ID: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistByStaffId(
                        staffId
                );

        pharmacistView.displayPharmacist(
                pharmacist
        );
    }


    // =========================================================
    // UPDATE PHARMACIST
    // =========================================================

    private static void updatePharmacist() {

        int pharmacistId =
                readInt(
                        "Enter Pharmacist ID to update: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistById(
                        pharmacistId
                );

        if (pharmacist == null) {

            System.out.println(
                    "Pharmacist not found."
            );

            return;
        }

        pharmacistView.displayPharmacist(
                pharmacist
        );

        System.out.println();

        System.out.println(
                "Enter new pharmacist information."
        );

        int staffId =
                readInt(
                        "Staff ID: "
                );

        System.out.print(
                "Qualification: "
        );

        String qualification =
                scanner.nextLine();

        System.out.print(
                "License Number: "
        );

        String licenseNumber =
                scanner.nextLine();

        pharmacist.setStaffId(
                staffId
        );

        pharmacist.setQualification(
                qualification
        );

        pharmacist.setLicenseNumber(
                licenseNumber
        );

        boolean success =
                pharmacistService.updatePharmacist(
                        pharmacist
                );

        if (success) {

            System.out.println(
                    "Pharmacist updated successfully."
            );

        } else {

            System.out.println(
                    "Pharmacist update failed."
            );
        }
    }


    // =========================================================
    // DELETE PHARMACIST
    // =========================================================

    private static void deletePharmacist() {

        int pharmacistId =
                readInt(
                        "Enter Pharmacist ID to delete: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistById(
                        pharmacistId
                );

        if (pharmacist == null) {

            System.out.println(
                    "Pharmacist not found."
            );

            return;
        }

        pharmacistView.displayPharmacist(
                pharmacist
        );

        System.out.print(
                "Are you sure you want to delete this pharmacist? (Y/N): "
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
                pharmacistService.removePharmacist(
                        pharmacistId
                );

        if (success) {

            System.out.println(
                    "Pharmacist deleted successfully."
            );

        } else {

            System.out.println(
                    "Pharmacist deletion failed."
            );
        }
    }


    // =========================================================
    // MEDICATION MENU
    // =========================================================

    private static void medicationMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "           MEDICATION MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Add Medication"
            );

            System.out.println(
                    "2. View All Medications"
            );

            System.out.println(
                    "3. Find Medication"
            );

            System.out.println(
                    "4. Update Medication"
            );

            System.out.println(
                    "5. Update Medication Stock"
            );

            System.out.println(
                    "6. Delete Medication"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    addMedication();
                    break;

                case 2:
                    viewAllMedications();
                    break;

                case 3:
                    findMedication();
                    break;

                case 4:
                    updateMedication();
                    break;

                case 5:
                    updateMedicationStock();
                    break;

                case 6:
                    deleteMedication();
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
    // ADD MEDICATION
    // =========================================================

    private static void addMedication() {

        System.out.println();

        System.out.println(
                "========== ADD MEDICATION =========="
        );

        System.out.print(
                "Name: "
        );

        String name =
                scanner.nextLine();

        System.out.print(
                "Description: "
        );

        String description =
                scanner.nextLine();

        System.out.print(
                "Dosage Form: "
        );

        String dosageForm =
                scanner.nextLine();

        System.out.print(
                "Price: "
        );

        double price =
                Double.parseDouble(
                        scanner.nextLine()
                );

        int quantity =
                readInt(
                        "Quantity In Stock: "
                );

        Medication medication =
                new Medication();

        medication.setName(
                name
        );

        medication.setDescription(
                description
        );

        medication.setDosageForm(
                dosageForm
        );

        medication.setPrice(
                price
        );

        medication.setQuantityInStock(
                quantity
        );

        boolean success =
                medicationService.addMedication(
                        medication
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Medication added successfully."
            );

            System.out.println(
                    "Medication ID: "
                            + medication.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to add medication."
            );
        }
    }


    // =========================================================
    // VIEW ALL MEDICATIONS
    // =========================================================

    private static void viewAllMedications() {

        List<Medication> medications =
                medicationService.getAllMedications();

        medicationView.displayMedications(
                medications
        );
    }


    // =========================================================
    // FIND MEDICATION
    // =========================================================

    private static void findMedication() {

        int medicationId =
                readInt(
                        "Enter Medication ID: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        medicationView.displayMedication(
                medication
        );
    }


    // =========================================================
    // UPDATE MEDICATION
    // =========================================================

    private static void updateMedication() {

        int medicationId =
                readInt(
                        "Enter Medication ID to update: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        medicationView.displayMedication(
                medication
        );

        System.out.println();

        System.out.println(
                "Enter new medication information."
        );

        System.out.print(
                "Name: "
        );

        medication.setName(
                scanner.nextLine()
        );

        System.out.print(
                "Description: "
        );

        medication.setDescription(
                scanner.nextLine()
        );

        System.out.print(
                "Dosage Form: "
        );

        medication.setDosageForm(
                scanner.nextLine()
        );

        System.out.print(
                "Price: "
        );

        medication.setPrice(
                Double.parseDouble(
                        scanner.nextLine()
                )
        );

        int quantity =
                readInt(
                        "Quantity In Stock: "
                );

        medication.setQuantityInStock(
                quantity
        );

        boolean success =
                medicationService.updateMedication(
                        medication
                );

        if (success) {

            System.out.println(
                    "Medication updated successfully."
            );

        } else {

            System.out.println(
                    "Medication update failed."
            );
        }
    }


    // =========================================================
    // UPDATE MEDICATION STOCK
    // =========================================================

    private static void updateMedicationStock() {

        int medicationId =
                readInt(
                        "Enter Medication ID: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        medicationView.displayMedication(
                medication
        );

        int quantity =
                readInt(
                        "Enter new quantity in stock: "
                );

        boolean success =
                medicationService.updateStock(
                        medicationId,
                        quantity
                );

        if (success) {

            System.out.println(
                    "Medication stock updated successfully."
            );

        } else {

            System.out.println(
                    "Medication stock update failed."
            );
        }
    }


    // =========================================================
    // DELETE MEDICATION
    // =========================================================

    private static void deleteMedication() {

        int medicationId =
                readInt(
                        "Enter Medication ID to delete: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        medicationView.displayMedication(
                medication
        );

        System.out.print(
                "Are you sure you want to delete this medication? (Y/N): "
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
                medicationService.removeMedication(
                        medicationId
                );

        if (success) {

            System.out.println(
                    "Medication deleted successfully."
            );

        } else {

            System.out.println(
                    "Medication deletion failed."
            );
        }
    }


    // =========================================================
    // PRESCRIPTION MENU
    // =========================================================

    private static void prescriptionMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "         PRESCRIPTION MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Create Prescription"
            );

            System.out.println(
                    "2. View All Prescriptions"
            );

            System.out.println(
                    "3. Find Prescription"
            );

            System.out.println(
                    "4. View Patient Prescriptions"
            );

            System.out.println(
                    "5. View Doctor Prescriptions"
            );

            System.out.println(
                    "6. Update Prescription"
            );

            System.out.println(
                    "7. Delete Prescription"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    createPrescription();
                    break;

                case 2:
                    viewAllPrescriptions();
                    break;

                case 3:
                    findPrescription();
                    break;

                case 4:
                    viewPatientPrescriptions();
                    break;

                case 5:
                    viewDoctorPrescriptions();
                    break;

                case 6:
                    updatePrescription();
                    break;

                case 7:
                    deletePrescription();
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
    // CREATE PRESCRIPTION
    // =========================================================

    private static void createPrescription() {

        System.out.println();

        System.out.println(
                "========== CREATE PRESCRIPTION =========="
        );

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        int doctorId =
                readInt(
                        "Enter Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        System.out.print(
                "Prescription Date (yyyy-MM-dd): "
        );

        LocalDate prescriptionDate;

        try {

            prescriptionDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            return;
        }

        Prescription prescription =
                new Prescription();

        prescription.setPatient(
                patient
        );

        prescription.setDoctor(
                doctor
        );

        prescription.setPrescriptionDate(
                prescriptionDate
        );

        boolean success =
                prescriptionService.addPrescription(
                        prescription
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Prescription created successfully."
            );

            System.out.println(
                    "Prescription ID: "
                            + prescription.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to create prescription."
            );
        }
    }


    // =========================================================
    // VIEW ALL PRESCRIPTIONS
    // =========================================================

    private static void viewAllPrescriptions() {

        List<Prescription> prescriptions =
                prescriptionService.getAllPrescriptions();

        prescriptionView.displayPrescriptions(
                prescriptions
        );
    }


    // =========================================================
    // FIND PRESCRIPTION
    // =========================================================

    private static void findPrescription() {

        int prescriptionId =
                readInt(
                        "Enter Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        prescriptionView.displayPrescription(
                prescription
        );
    }


    // =========================================================
    // VIEW PATIENT PRESCRIPTIONS
    // =========================================================

    private static void viewPatientPrescriptions() {

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        List<Prescription> prescriptions =
                prescriptionService.getPrescriptionsByPatientId(
                        patientId
                );

        prescriptionView.displayPrescriptions(
                prescriptions
        );
    }


    // =========================================================
    // VIEW DOCTOR PRESCRIPTIONS
    // =========================================================

    private static void viewDoctorPrescriptions() {

        int doctorId =
                readInt(
                        "Enter Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        List<Prescription> prescriptions =
                prescriptionService.getPrescriptionsByDoctorId(
                        doctorId
                );

        prescriptionView.displayPrescriptions(
                prescriptions
        );
    }


    // =========================================================
    // UPDATE PRESCRIPTION
    // =========================================================

    private static void updatePrescription() {

        int prescriptionId =
                readInt(
                        "Enter Prescription ID to update: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        prescriptionView.displayPrescription(
                prescription
        );

        int patientId =
                readInt(
                        "Enter new Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        int doctorId =
                readInt(
                        "Enter new Doctor/Staff ID: "
                );

        Doctor doctor =
                doctorService.getDoctorById(
                        doctorId
                );

        if (doctor == null) {

            System.out.println(
                    "Doctor not found."
            );

            return;
        }

        System.out.print(
                "Prescription Date (yyyy-MM-dd): "
        );

        LocalDate prescriptionDate;

        try {

            prescriptionDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            return;
        }

        prescription.setPatient(
                patient
        );

        prescription.setDoctor(
                doctor
        );

        prescription.setPrescriptionDate(
                prescriptionDate
        );

        boolean success =
                prescriptionService.updatePrescription(
                        prescription
                );

        if (success) {

            System.out.println(
                    "Prescription updated successfully."
            );

        } else {

            System.out.println(
                    "Prescription update failed."
            );
        }
    }


    // =========================================================
    // DELETE PRESCRIPTION
    // =========================================================

    private static void deletePrescription() {

        int prescriptionId =
                readInt(
                        "Enter Prescription ID to delete: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        prescriptionView.displayPrescription(
                prescription
        );

        System.out.print(
                "Are you sure you want to delete this prescription? (Y/N): "
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
                prescriptionService.removePrescription(
                        prescriptionId
                );

        if (success) {

            System.out.println(
                    "Prescription deleted successfully."
            );

        } else {

            System.out.println(
                    "Prescription deletion failed."
            );
        }
    }


    // =========================================================
    // PRESCRIPTION ITEM MENU
    // =========================================================

    private static void prescriptionItemMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "      PRESCRIPTION ITEM MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Add Prescription Item"
            );

            System.out.println(
                    "2. View All Prescription Items"
            );

            System.out.println(
                    "3. Find Prescription Item"
            );

            System.out.println(
                    "4. View Items by Prescription"
            );

            System.out.println(
                    "5. View Items by Medication"
            );

            System.out.println(
                    "6. Update Prescription Item"
            );

            System.out.println(
                    "7. Delete Prescription Item"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    addPrescriptionItem();
                    break;

                case 2:
                    viewAllPrescriptionItems();
                    break;

                case 3:
                    findPrescriptionItem();
                    break;

                case 4:
                    viewPrescriptionItemsByPrescription();
                    break;

                case 5:
                    viewPrescriptionItemsByMedication();
                    break;

                case 6:
                    updatePrescriptionItem();
                    break;

                case 7:
                    deletePrescriptionItem();
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
    // ADD PRESCRIPTION ITEM
    // =========================================================

    private static void addPrescriptionItem() {

        System.out.println();

        System.out.println(
                "========== ADD PRESCRIPTION ITEM =========="
        );

        int prescriptionId =
                readInt(
                        "Enter Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        int medicationId =
                readInt(
                        "Enter Medication ID: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        System.out.print(
                "Dosage: "
        );

        String dosage =
                scanner.nextLine();

        System.out.print(
                "Frequency: "
        );

        String frequency =
                scanner.nextLine();

        int duration =
                readInt(
                        "Duration: "
                );

        System.out.print(
                "Duration Unit: "
        );

        String durationUnit =
                scanner.nextLine();

        System.out.print(
                "Instructions: "
        );

        String instructions =
                scanner.nextLine();

        PrescriptionItem item =
                new PrescriptionItem();

        item.setPrescription(
                prescription
        );

        item.setMedication(
                medication
        );

        item.setDosage(
                dosage
        );

        item.setFrequency(
                frequency
        );

        item.setDuration(
                duration
        );

        item.setDurationUnit(
                durationUnit
        );

        item.setInstructions(
                instructions
        );

        boolean success =
                prescriptionItemService.addPrescriptionItem(
                        item
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Prescription item added successfully."
            );

            System.out.println(
                    "Prescription Item ID: "
                            + item.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to add prescription item."
            );
        }
    }


    // =========================================================
    // VIEW ALL PRESCRIPTION ITEMS
    // =========================================================

    private static void viewAllPrescriptionItems() {

        List<PrescriptionItem> items =
                prescriptionItemService.getAllPrescriptionItems();

        prescriptionItemView.displayPrescriptionItems(
                items
        );
    }


    // =========================================================
    // FIND PRESCRIPTION ITEM
    // =========================================================

    private static void findPrescriptionItem() {

        int itemId =
                readInt(
                        "Enter Prescription Item ID: "
                );

        PrescriptionItem item =
                prescriptionItemService.getPrescriptionItemById(
                        itemId
                );

        prescriptionItemView.displayPrescriptionItem(
                item
        );
    }


    // =========================================================
    // VIEW ITEMS BY PRESCRIPTION
    // =========================================================

    private static void viewPrescriptionItemsByPrescription() {

        int prescriptionId =
                readInt(
                        "Enter Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        List<PrescriptionItem> items =
                prescriptionItemService
                        .getPrescriptionItemsByPrescriptionId(
                                prescriptionId
                        );

        prescriptionItemView.displayPrescriptionItems(
                items
        );
    }


    // =========================================================
    // VIEW ITEMS BY MEDICATION
    // =========================================================

    private static void viewPrescriptionItemsByMedication() {

        int medicationId =
                readInt(
                        "Enter Medication ID: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        List<PrescriptionItem> items =
                prescriptionItemService
                        .getPrescriptionItemsByMedicationId(
                                medicationId
                        );

        prescriptionItemView.displayPrescriptionItems(
                items
        );
    }


    // =========================================================
    // UPDATE PRESCRIPTION ITEM
    // =========================================================

    private static void updatePrescriptionItem() {

        int itemId =
                readInt(
                        "Enter Prescription Item ID to update: "
                );

        PrescriptionItem item =
                prescriptionItemService.getPrescriptionItemById(
                        itemId
                );

        if (item == null) {

            System.out.println(
                    "Prescription item not found."
            );

            return;
        }

        prescriptionItemView.displayPrescriptionItem(
                item
        );

        int prescriptionId =
                readInt(
                        "Enter new Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        int medicationId =
                readInt(
                        "Enter new Medication ID: "
                );

        Medication medication =
                medicationService.getMedicationById(
                        medicationId
                );

        if (medication == null) {

            System.out.println(
                    "Medication not found."
            );

            return;
        }

        System.out.print(
                "Dosage: "
        );

        String dosage =
                scanner.nextLine();

        System.out.print(
                "Frequency: "
        );

        String frequency =
                scanner.nextLine();

        int duration =
                readInt(
                        "Duration: "
                );

        System.out.print(
                "Duration Unit: "
        );

        String durationUnit =
                scanner.nextLine();

        System.out.print(
                "Instructions: "
        );

        String instructions =
                scanner.nextLine();

        item.setPrescription(
                prescription
        );

        item.setMedication(
                medication
        );

        item.setDosage(
                dosage
        );

        item.setFrequency(
                frequency
        );

        item.setDuration(
                duration
        );

        item.setDurationUnit(
                durationUnit
        );

        item.setInstructions(
                instructions
        );

        boolean success =
                prescriptionItemService.updatePrescriptionItem(
                        item
                );

        if (success) {

            System.out.println(
                    "Prescription item updated successfully."
            );

        } else {

            System.out.println(
                    "Prescription item update failed."
            );
        }
    }


    // =========================================================
    // DELETE PRESCRIPTION ITEM
    // =========================================================

    private static void deletePrescriptionItem() {

        int itemId =
                readInt(
                        "Enter Prescription Item ID to delete: "
                );

        PrescriptionItem item =
                prescriptionItemService.getPrescriptionItemById(
                        itemId
                );

        if (item == null) {

            System.out.println(
                    "Prescription item not found."
            );

            return;
        }

        prescriptionItemView.displayPrescriptionItem(
                item
        );

        System.out.print(
                "Are you sure you want to delete this prescription item? (Y/N): "
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
                prescriptionItemService.removePrescriptionItem(
                        itemId
                );

        if (success) {

            System.out.println(
                    "Prescription item deleted successfully."
            );

        } else {

            System.out.println(
                    "Prescription item deletion failed."
            );
        }
    }


    // =========================================================
    // MEDICATION DISPENSING MENU
    // =========================================================

    private static void medicationDispensingMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "       MEDICATION DISPENSING"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Dispense Medication"
            );

            System.out.println(
                    "2. View All Dispensing Records"
            );

            System.out.println(
                    "3. Find Dispensing Record"
            );

            System.out.println(
                    "4. View Patient Dispensing Records"
            );

            System.out.println(
                    "5. Update Dispensing Record"
            );

            System.out.println(
                    "6. Delete Dispensing Record"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    dispenseMedication();
                    break;

                case 2:
                    viewAllMedicationDispensings();
                    break;

                case 3:
                    findMedicationDispensing();
                    break;

                case 4:
                    viewPatientMedicationDispensings();
                    break;

                case 5:
                    updateMedicationDispensing();
                    break;

                case 6:
                    deleteMedicationDispensing();
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
    // DISPENSE MEDICATION
    // =========================================================

    private static void dispenseMedication() {

        System.out.println();

        System.out.println(
                "========== DISPENSE MEDICATION =========="
        );

        int prescriptionId =
                readInt(
                        "Enter Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        int prescriptionItemId =
                readInt(
                        "Enter Prescription Item ID: "
                );

        PrescriptionItem prescriptionItem =
                prescriptionItemService
                        .getPrescriptionItemById(
                                prescriptionItemId
                        );

        if (prescriptionItem == null) {

            System.out.println(
                    "Prescription item not found."
            );

            return;
        }

        int pharmacistId =
                readInt(
                        "Enter Pharmacist ID: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistById(
                        pharmacistId
                );

        if (pharmacist == null) {

            System.out.println(
                    "Pharmacist not found."
            );

            return;
        }

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        int quantity =
                readInt(
                        "Quantity: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Status: "
        );

        String status =
                scanner.nextLine();

        if (status.trim().isEmpty()) {

            status = "Dispensed";
        }

        System.out.print(
                "Notes: "
        );

        String notes =
                scanner.nextLine();

        MedicationDispensing dispensing =
                new MedicationDispensing();

        dispensing.setPrescription(
                prescription
        );

        dispensing.setPrescriptionItem(
                prescriptionItem
        );

        dispensing.setPharmacist(
                pharmacist
        );

        dispensing.setPatient(
                patient
        );

        dispensing.setQuantity(
                quantity
        );

        dispensing.setStatus(
                status
        );

        dispensing.setNotes(
                notes
        );

        boolean success =
                medicationDispensingService
                        .dispenseMedication(
                                dispensing
                        );

        if (success) {

            System.out.println();

            System.out.println(
                    "Medication dispensed successfully."
            );

            System.out.println(
                    "Dispensing ID: "
                            + dispensing.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to dispense medication."
            );
        }
    }


    // =========================================================
    // VIEW ALL MEDICATION DISPENSINGS
    // =========================================================

    private static void viewAllMedicationDispensings() {

        List<MedicationDispensing> dispensings =
                medicationDispensingService
                        .getAllMedicationDispensings();

        medicationDispensingView
                .displayMedicationDispensings(
                        dispensings
                );
    }


    // =========================================================
    // FIND MEDICATION DISPENSING
    // =========================================================

    private static void findMedicationDispensing() {

        int dispensingId =
                readInt(
                        "Enter Dispensing ID: "
                );

        MedicationDispensing dispensing =
                medicationDispensingService
                        .getMedicationDispensingById(
                                dispensingId
                        );

        medicationDispensingView
                .displayMedicationDispensing(
                        dispensing
                );
    }


    // =========================================================
    // VIEW PATIENT MEDICATION DISPENSINGS
    // =========================================================

    private static void viewPatientMedicationDispensings() {

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        List<MedicationDispensing> dispensings =
                medicationDispensingService
                        .getMedicationDispensingsByPatientId(
                                patientId
                        );

        medicationDispensingView
                .displayMedicationDispensings(
                        dispensings
                );
    }


    // =========================================================
    // UPDATE MEDICATION DISPENSING
    // =========================================================

    private static void updateMedicationDispensing() {

        int dispensingId =
                readInt(
                        "Enter Dispensing ID to update: "
                );

        MedicationDispensing dispensing =
                medicationDispensingService
                        .getMedicationDispensingById(
                                dispensingId
                        );

        if (dispensing == null) {

            System.out.println(
                    "Dispensing record not found."
            );

            return;
        }

        medicationDispensingView
                .displayMedicationDispensing(
                        dispensing
                );

        int prescriptionId =
                readInt(
                        "Enter Prescription ID: "
                );

        Prescription prescription =
                prescriptionService.getPrescriptionById(
                        prescriptionId
                );

        if (prescription == null) {

            System.out.println(
                    "Prescription not found."
            );

            return;
        }

        int prescriptionItemId =
                readInt(
                        "Enter Prescription Item ID: "
                );

        PrescriptionItem prescriptionItem =
                prescriptionItemService
                        .getPrescriptionItemById(
                                prescriptionItemId
                        );

        if (prescriptionItem == null) {

            System.out.println(
                    "Prescription item not found."
            );

            return;
        }

        int pharmacistId =
                readInt(
                        "Enter Pharmacist ID: "
                );

        Pharmacist pharmacist =
                pharmacistService.getPharmacistById(
                        pharmacistId
                );

        if (pharmacist == null) {

            System.out.println(
                    "Pharmacist not found."
            );

            return;
        }

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        int quantity =
                readInt(
                        "Quantity: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Status: "
        );

        String status =
                scanner.nextLine();

        System.out.print(
                "Notes: "
        );

        String notes =
                scanner.nextLine();

        dispensing.setPrescription(
                prescription
        );

        dispensing.setPrescriptionItem(
                prescriptionItem
        );

        dispensing.setPharmacist(
                pharmacist
        );

        dispensing.setPatient(
                patient
        );

        dispensing.setQuantity(
                quantity
        );

        dispensing.setStatus(
                status
        );

        dispensing.setNotes(
                notes
        );

        boolean success =
                medicationDispensingService
                        .updateMedicationDispensing(
                                dispensing
                        );

        if (success) {

            System.out.println(
                    "Dispensing record updated successfully."
            );

        } else {

            System.out.println(
                    "Dispensing record update failed."
            );
        }
    }


    // =========================================================
    // DELETE MEDICATION DISPENSING
    // =========================================================

    private static void deleteMedicationDispensing() {

        int dispensingId =
                readInt(
                        "Enter Dispensing ID to delete: "
                );

        MedicationDispensing dispensing =
                medicationDispensingService
                        .getMedicationDispensingById(
                                dispensingId
                        );

        if (dispensing == null) {

            System.out.println(
                    "Dispensing record not found."
            );

            return;
        }

        medicationDispensingView
                .displayMedicationDispensing(
                        dispensing
                );

        System.out.print(
                "Are you sure you want to delete this dispensing record? (Y/N): "
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
                medicationDispensingService
                        .removeMedicationDispensing(
                                dispensingId
                        );

        if (success) {

            System.out.println(
                    "Dispensing record deleted successfully."
            );

        } else {

            System.out.println(
                    "Dispensing record deletion failed."
            );
        }
    }


    // =========================================================
    // BILLING MENU
    // =========================================================

    private static void billingMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "             BILLING MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Invoice Management"
            );

            System.out.println(
                    "2. Invoice Item Management"
            );

            System.out.println(
                    "3. Payment Management"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    invoiceMenu();
                    break;

                case 2:
                    invoiceItemMenu();
                    break;

                case 3:
                    paymentMenu();
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
    // INVOICE MENU
    // =========================================================

    private static void invoiceMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "            INVOICE MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Create Invoice"
            );

            System.out.println(
                    "2. View All Invoices"
            );

            System.out.println(
                    "3. Find Invoice"
            );

            System.out.println(
                    "4. View Patient Invoices"
            );

            System.out.println(
                    "5. Update Invoice"
            );

            System.out.println(
                    "6. Delete Invoice"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    createInvoice();
                    break;

                case 2:
                    viewAllInvoices();
                    break;

                case 3:
                    findInvoice();
                    break;

                case 4:
                    viewPatientInvoices();
                    break;

                case 5:
                    updateInvoice();
                    break;

                case 6:
                    deleteInvoice();
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
    // CREATE INVOICE
    // =========================================================

    private static void createInvoice() {

        System.out.println();

        System.out.println(
                "========== CREATE INVOICE =========="
        );

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Patient: "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        System.out.print(
                "Invoice Date (yyyy-MM-dd): "
        );

        LocalDate invoiceDate;

        try {

            invoiceDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd"
            );

            return;
        }

        System.out.print(
                "Total Amount: "
        );

        double totalAmount;

        try {

            totalAmount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid amount."
            );

            return;
        }

        if (totalAmount < 0) {

            System.out.println(
                    "Total amount cannot be negative."
            );

            return;
        }

        System.out.print(
                "Status: "
        );

        String status =
                scanner.nextLine();

        Invoice invoice =
                new Invoice();

        invoice.setPatient(
                patient
        );

        invoice.setInvoiceDate(
                invoiceDate
        );

        invoice.setTotalAmount(
                totalAmount
        );

        invoice.setStatus(
                status
        );

        boolean success =
                invoiceService.addInvoice(
                        invoice
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Invoice created successfully."
            );

            System.out.println(
                    "Invoice ID: "
                            + invoice.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to create invoice."
            );
        }
    }


    // =========================================================
    // VIEW ALL INVOICES
    // =========================================================

    private static void viewAllInvoices() {

        List<Invoice> invoices =
                invoiceService.getAllInvoices();

        invoiceView.displayInvoices(
                invoices
        );
    }


    // =========================================================
    // FIND INVOICE
    // =========================================================

    private static void findInvoice() {

        int invoiceId =
                readInt(
                        "Enter Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        invoiceView.displayInvoice(
                invoice
        );
    }


    // =========================================================
    // VIEW PATIENT INVOICES
    // =========================================================

    private static void viewPatientInvoices() {

        int patientId =
                readInt(
                        "Enter Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.println();

        System.out.println(
                "Invoices for "
                        + patient.getFirstName()
                        + " "
                        + patient.getLastName()
        );

        List<Invoice> invoices =
                invoiceService.getInvoicesByPatientId(
                        patientId
                );

        invoiceView.displayInvoices(
                invoices
        );
    }


    // =========================================================
    // UPDATE INVOICE
    // =========================================================

    private static void updateInvoice() {

        int invoiceId =
                readInt(
                        "Enter Invoice ID to update: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        invoiceView.displayInvoice(
                invoice
        );

        int patientId =
                readInt(
                        "Enter new Patient ID: "
                );

        Patient patient =
                patientService.getPatientById(
                        patientId
                );

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.print(
                "Invoice Date (yyyy-MM-dd): "
        );

        LocalDate invoiceDate;

        try {

            invoiceDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd"
            );

            return;
        }

        System.out.print(
                "Total Amount: "
        );

        double totalAmount;

        try {

            totalAmount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid amount."
            );

            return;
        }

        if (totalAmount < 0) {

            System.out.println(
                    "Total amount cannot be negative."
            );

            return;
        }

        System.out.print(
                "Status: "
        );

        String status =
                scanner.nextLine();

        invoice.setPatient(
                patient
        );

        invoice.setInvoiceDate(
                invoiceDate
        );

        invoice.setTotalAmount(
                totalAmount
        );

        invoice.setStatus(
                status
        );

        boolean success =
                invoiceService.updateInvoice(
                        invoice
                );

        if (success) {

            System.out.println(
                    "Invoice updated successfully."
            );

        } else {

            System.out.println(
                    "Invoice update failed."
            );
        }
    }


    // =========================================================
    // DELETE INVOICE
    // =========================================================

    private static void deleteInvoice() {

        int invoiceId =
                readInt(
                        "Enter Invoice ID to delete: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        invoiceView.displayInvoice(
                invoice
        );

        System.out.print(
                "Are you sure you want to delete this invoice? (Y/N): "
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
                invoiceService.removeInvoice(
                        invoiceId
                );

        if (success) {

            System.out.println(
                    "Invoice deleted successfully."
            );

        } else {

            System.out.println(
                    "Invoice deletion failed."
            );
        }
    }


    // =========================================================
    // INVOICE ITEM MENU
    // =========================================================

    private static void invoiceItemMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "         INVOICE ITEM MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Add Invoice Item"
            );

            System.out.println(
                    "2. View All Invoice Items"
            );

            System.out.println(
                    "3. Find Invoice Item"
            );

            System.out.println(
                    "4. View Items by Invoice"
            );

            System.out.println(
                    "5. Update Invoice Item"
            );

            System.out.println(
                    "6. Delete Invoice Item"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    addInvoiceItem();
                    break;

                case 2:
                    viewAllInvoiceItems();
                    break;

                case 3:
                    findInvoiceItem();
                    break;

                case 4:
                    viewInvoiceItemsByInvoice();
                    break;

                case 5:
                    updateInvoiceItem();
                    break;

                case 6:
                    deleteInvoiceItem();
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
    // ADD INVOICE ITEM
    // =========================================================

    private static void addInvoiceItem() {

        System.out.println();

        System.out.println(
                "========== ADD INVOICE ITEM =========="
        );

        int invoiceId =
                readInt(
                        "Enter Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        System.out.print(
                "Description: "
        );

        String description =
                scanner.nextLine();

        if (description.trim().isEmpty()) {

            System.out.println(
                    "Description is required."
            );

            return;
        }

        int quantity =
                readInt(
                        "Quantity: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Unit Price: "
        );

        double unitPrice;

        try {

            unitPrice =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid unit price."
            );

            return;
        }

        if (unitPrice < 0) {

            System.out.println(
                    "Unit price cannot be negative."
            );

            return;
        }

        InvoiceItem item =
                new InvoiceItem();

        item.setInvoice(
                invoice
        );

        item.setDescription(
                description
        );

        item.setQuantity(
                quantity
        );

        item.setUnitPrice(
                unitPrice
        );

        boolean success =
                invoiceItemService.addInvoiceItem(
                        item
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Invoice item added successfully."
            );

            System.out.println(
                    "Invoice Item ID: "
                            + item.getId()
            );

            System.out.printf(
                    "Amount: %.2f%n",
                    item.getAmount()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to add invoice item."
            );
        }
    }


    // =========================================================
    // VIEW ALL INVOICE ITEMS
    // =========================================================

    private static void viewAllInvoiceItems() {

        List<InvoiceItem> items =
                invoiceItemService.getAllInvoiceItems();

        invoiceItemView.displayInvoiceItems(
                items
        );
    }


    // =========================================================
    // FIND INVOICE ITEM
    // =========================================================

    private static void findInvoiceItem() {

        int itemId =
                readInt(
                        "Enter Invoice Item ID: "
                );

        InvoiceItem item =
                invoiceItemService.getInvoiceItemById(
                        itemId
                );

        invoiceItemView.displayInvoiceItem(
                item
        );
    }


    // =========================================================
    // VIEW ITEMS BY INVOICE
    // =========================================================

    private static void viewInvoiceItemsByInvoice() {

        int invoiceId =
                readInt(
                        "Enter Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        List<InvoiceItem> items =
                invoiceItemService.getInvoiceItemsByInvoiceId(
                        invoiceId
                );

        invoiceItemView.displayInvoiceItems(
                items
        );
    }


    // =========================================================
    // UPDATE INVOICE ITEM
    // =========================================================

    private static void updateInvoiceItem() {

        int itemId =
                readInt(
                        "Enter Invoice Item ID to update: "
                );

        InvoiceItem item =
                invoiceItemService.getInvoiceItemById(
                        itemId
                );

        if (item == null) {

            System.out.println(
                    "Invoice item not found."
            );

            return;
        }

        invoiceItemView.displayInvoiceItem(
                item
        );

        int invoiceId =
                readInt(
                        "Enter new Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        System.out.print(
                "Description: "
        );

        String description =
                scanner.nextLine();

        if (description.trim().isEmpty()) {

            System.out.println(
                    "Description is required."
            );

            return;
        }

        int quantity =
                readInt(
                        "Quantity: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Unit Price: "
        );

        double unitPrice;

        try {

            unitPrice =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid unit price."
            );

            return;
        }

        if (unitPrice < 0) {

            System.out.println(
                    "Unit price cannot be negative."
            );

            return;
        }

        item.setInvoice(
                invoice
        );

        item.setDescription(
                description
        );

        item.setQuantity(
                quantity
        );

        item.setUnitPrice(
                unitPrice
        );

        boolean success =
                invoiceItemService.updateInvoiceItem(
                        item
                );

        if (success) {

            System.out.println(
                    "Invoice item updated successfully."
            );

        } else {

            System.out.println(
                    "Invoice item update failed."
            );
        }
    }


    // =========================================================
    // DELETE INVOICE ITEM
    // =========================================================

    private static void deleteInvoiceItem() {

        int itemId =
                readInt(
                        "Enter Invoice Item ID to delete: "
                );

        InvoiceItem item =
                invoiceItemService.getInvoiceItemById(
                        itemId
                );

        if (item == null) {

            System.out.println(
                    "Invoice item not found."
            );

            return;
        }

        invoiceItemView.displayInvoiceItem(
                item
        );

        System.out.print(
                "Are you sure you want to delete this invoice item? (Y/N): "
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
                invoiceItemService.removeInvoiceItem(
                        itemId
                );

        if (success) {

            System.out.println(
                    "Invoice item deleted successfully."
            );

        } else {

            System.out.println(
                    "Invoice item deletion failed."
            );
        }
    }


    // =========================================================
    // PAYMENT MENU
    // =========================================================

    private static void paymentMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "            PAYMENT MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Record Payment"
            );

            System.out.println(
                    "2. View All Payments"
            );

            System.out.println(
                    "3. Find Payment"
            );

            System.out.println(
                    "4. View Payments by Invoice"
            );

            System.out.println(
                    "5. Update Payment"
            );

            System.out.println(
                    "6. Delete Payment"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    recordPayment();
                    break;

                case 2:
                    viewAllPayments();
                    break;

                case 3:
                    findPayment();
                    break;

                case 4:
                    viewPaymentsByInvoice();
                    break;

                case 5:
                    updatePayment();
                    break;

                case 6:
                    deletePayment();
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
    // RECORD PAYMENT
    // =========================================================

    private static void recordPayment() {

        System.out.println();

        System.out.println(
                "========== RECORD PAYMENT =========="
        );

        int invoiceId =
                readInt(
                        "Enter Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        System.out.print(
                "Payment Amount: "
        );

        double amount;

        try {

            amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid payment amount."
            );

            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Payment amount must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Payment Date (yyyy-MM-dd): "
        );

        LocalDate paymentDate;

        try {

            paymentDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd"
            );

            return;
        }

        System.out.print(
                "Payment Method: "
        );

        String paymentMethod =
                scanner.nextLine();

        Payment payment =
                new Payment();

        payment.setInvoice(
                invoice
        );

        payment.setAmount(
                amount
        );

        payment.setPaymentDate(
                paymentDate
        );

        payment.setPaymentMethod(
                paymentMethod
        );

        boolean success =
                paymentService.addPayment(
                        payment
                );

        if (success) {

            System.out.println();

            System.out.println(
                    "Payment recorded successfully."
            );

            System.out.println(
                    "Payment ID: "
                            + payment.getId()
            );

        } else {

            System.out.println();

            System.out.println(
                    "Failed to record payment."
            );
        }
    }


    // =========================================================
    // VIEW ALL PAYMENTS
    // =========================================================

    private static void viewAllPayments() {

        List<Payment> payments =
                paymentService.getAllPayments();

        paymentView.displayPayments(
                payments
        );
    }


    // =========================================================
    // FIND PAYMENT
    // =========================================================

    private static void findPayment() {

        int paymentId =
                readInt(
                        "Enter Payment ID: "
                );

        Payment payment =
                paymentService.getPaymentById(
                        paymentId
                );

        paymentView.displayPayment(
                payment
        );
    }


    // =========================================================
    // VIEW PAYMENTS BY INVOICE
    // =========================================================

    private static void viewPaymentsByInvoice() {

        int invoiceId =
                readInt(
                        "Enter Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        List<Payment> payments =
                paymentService.getPaymentsByInvoiceId(
                        invoiceId
                );

        paymentView.displayPayments(
                payments
        );
    }


    // =========================================================
    // UPDATE PAYMENT
    // =========================================================

    private static void updatePayment() {

        int paymentId =
                readInt(
                        "Enter Payment ID to update: "
                );

        Payment payment =
                paymentService.getPaymentById(
                        paymentId
                );

        if (payment == null) {

            System.out.println(
                    "Payment not found."
            );

            return;
        }

        paymentView.displayPayment(
                payment
        );

        int invoiceId =
                readInt(
                        "Enter new Invoice ID: "
                );

        Invoice invoice =
                invoiceService.getInvoiceById(
                        invoiceId
                );

        if (invoice == null) {

            System.out.println(
                    "Invoice not found."
            );

            return;
        }

        System.out.print(
                "Payment Amount: "
        );

        double amount;

        try {

            amount =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid payment amount."
            );

            return;
        }

        if (amount <= 0) {

            System.out.println(
                    "Payment amount must be greater than zero."
            );

            return;
        }

        System.out.print(
                "Payment Date (yyyy-MM-dd): "
        );

        LocalDate paymentDate;

        try {

            paymentDate =
                    LocalDate.parse(
                            scanner.nextLine()
                    );

        } catch (Exception e) {

            System.out.println(
                    "Invalid date format."
            );

            System.out.println(
                    "Please use: yyyy-MM-dd"
            );

            return;
        }

        System.out.print(
                "Payment Method: "
        );

        String paymentMethod =
                scanner.nextLine();

        payment.setInvoice(
                invoice
        );

        payment.setAmount(
                amount
        );

        payment.setPaymentDate(
                paymentDate
        );

        payment.setPaymentMethod(
                paymentMethod
        );

        boolean success =
                paymentService.updatePayment(
                        payment
                );

        if (success) {

            System.out.println(
                    "Payment updated successfully."
            );

        } else {

            System.out.println(
                    "Payment update failed."
            );
        }
    }


    // =========================================================
    // DELETE PAYMENT
    // =========================================================

    private static void deletePayment() {

        int paymentId =
                readInt(
                        "Enter Payment ID to delete: "
                );

        Payment payment =
                paymentService.getPaymentById(
                        paymentId
                );

        if (payment == null) {

            System.out.println(
                    "Payment not found."
            );

            return;
        }

        paymentView.displayPayment(
                payment
        );

        System.out.print(
                "Are you sure you want to delete this payment? (Y/N): "
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
                paymentService.removePayment(
                        paymentId
                );

        if (success) {

            System.out.println(
                    "Payment deleted successfully."
            );

        } else {

            System.out.println(
                    "Payment deletion failed."
            );
        }
    }


    // =========================================================
    // USER MANAGEMENT MENU
    // =========================================================

    private static void userMenu() {

        while (true) {

            System.out.println();

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "             USER MANAGEMENT"
            );

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "1. Add User"
            );

            System.out.println(
                    "2. View All Users"
            );

            System.out.println(
                    "3. Find User by ID"
            );

            System.out.println(
                    "4. Find User by Username"
            );

            System.out.println(
                    "5. Update User"
            );

            System.out.println(
                    "6. Delete User"
            );

            System.out.println(
                    "7. Activate/Deactivate User"
            );

            System.out.println(
                    "0. Back"
            );

            System.out.println(
                    "========================================"
            );

            int choice =
                    readInt(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    addUser();
                    break;

                case 2:
                    viewAllUsers();
                    break;

                case 3:
                    findUserById();
                    break;

                case 4:
                    findUserByUsername();
                    break;

                case 5:
                    updateUser();
                    break;

                case 6:
                    deleteUser();
                    break;

                case 7:
                    updateUserStatus();
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
    // ADD USER
    // =========================================================

    private static void addUser() {

        System.out.println();

        System.out.println(
                "========== ADD USER ==========" 
        );

        System.out.print(
                "Username: "
        );

        String username =
                scanner.nextLine().trim();

        if (username.isEmpty()) {

            System.out.println(
                    "Username is required."
            );

            return;
        }

        System.out.print(
                "Password Hash: "
        );

        String passwordHash =
                scanner.nextLine().trim();

        if (passwordHash.isEmpty()) {

            System.out.println(
                    "Password hash is required."
            );

            return;
        }

        StaffRole role =
                readStaffRole();

        if (role == null) {
            return;
        }

        Staff staff =
                readStaffForRole(role);

        if (role != StaffRole.STAFF && staff == null) {
            return;
        }

        User user =
                new User();

        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        user.setStaff(staff);
        user.setActive(true);

        try {

            userService.addUser(
                    user
            );

            System.out.println(
                    "User added successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to add user: " + e.getMessage()
            );
        }
    }


    // =========================================================
    // VIEW ALL USERS
    // =========================================================

    private static void viewAllUsers() {

        try {

            List<User> users =
                    userService.getAllUsers();

            userView.displayUsers(
                    users
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to retrieve users: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // FIND USER BY ID
    // =========================================================

    private static void findUserById() {

        int userId =
                readInt(
                        "Enter User ID: "
                );

        try {

            User user =
                    userService.getUserById(
                            userId
                    );

            if (user == null) {

                System.out.println(
                        "User not found."
                );

                return;
            }

            userView.displayUser(
                    user
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to find user: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // FIND USER BY USERNAME
    // =========================================================

    private static void findUserByUsername() {

        System.out.print(
                "Enter Username: "
        );

        String username =
                scanner.nextLine().trim();

        if (username.isEmpty()) {

            System.out.println(
                    "Username is required."
            );

            return;
        }

        try {

            User user =
                    userService.getUserByUsername(
                            username
                    );

            if (user == null) {

                System.out.println(
                        "User not found."
                );

                return;
            }

            userView.displayUser(
                    user
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to find user: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // UPDATE USER
    // =========================================================

    private static void updateUser() {

        int userId =
                readInt(
                        "Enter User ID to update: "
                );

        User user;

        try {

            user =
                    userService.getUserById(
                            userId
                    );

        } catch (Exception e) {

            System.out.println(
                    "Failed to find user: "
                            + e.getMessage()
            );

            return;
        }

        if (user == null) {

            System.out.println(
                    "User not found."
            );

            return;
        }

        userView.displayUser(
                user
        );

        System.out.print(
                "New Username: "
        );

        String username =
                scanner.nextLine().trim();

        if (username.isEmpty()) {

            System.out.println(
                    "Username is required."
            );

            return;
        }

        System.out.print(
                "New Password Hash: "
        );

        String passwordHash =
                scanner.nextLine().trim();

        if (passwordHash.isEmpty()) {

            System.out.println(
                    "Password hash is required."
            );

            return;
        }

        StaffRole role =
                readStaffRole();

        if (role == null) {
            return;
        }

        Staff staff =
                readStaffForRole(role);

        if (role != StaffRole.STAFF && staff == null) {
            return;
        }

        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        user.setStaff(staff);

        try {

            userService.updateUser(
                    user
            );

            System.out.println(
                    "User updated successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to update user: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // DELETE USER
    // =========================================================

    private static void deleteUser() {

        int userId =
                readInt(
                        "Enter User ID to delete: "
                );

        User user;

        try {

            user =
                    userService.getUserById(
                            userId
                    );

        } catch (Exception e) {

            System.out.println(
                    "Failed to find user: "
                            + e.getMessage()
            );

            return;
        }

        if (user == null) {

            System.out.println(
                    "User not found."
            );

            return;
        }

        userView.displayUser(
                user
        );

        System.out.print(
                "Are you sure you want to delete this user? (Y/N): "
        );

        String answer =
                scanner.nextLine();

        if (!answer.equalsIgnoreCase("Y")) {

            System.out.println(
                    "Delete cancelled."
            );

            return;
        }

        try {

            userService.deleteUser(
                    userId
            );

            System.out.println(
                    "User deleted successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "User deletion failed: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // ACTIVATE / DEACTIVATE USER
    // =========================================================

    private static void updateUserStatus() {

        int userId =
                readInt(
                        "Enter User ID: "
                );

        User user;

        try {

            user =
                    userService.getUserById(
                            userId
                    );

        } catch (Exception e) {

            System.out.println(
                    "Failed to find user: "
                            + e.getMessage()
            );

            return;
        }

        if (user == null) {

            System.out.println(
                    "User not found."
            );

            return;
        }

        userView.displayUser(
                user
        );

        boolean newStatus =
                !user.isActive();

        System.out.println(
                "New status: "
                        + (newStatus ? "Active" : "Inactive")
        );

        System.out.print(
                "Confirm? (Y/N): "
        );

        String answer =
                scanner.nextLine();

        if (!answer.equalsIgnoreCase("Y")) {

            System.out.println(
                    "Status update cancelled."
            );

            return;
        }

        try {

            userService.updateUserStatus(
                    userId,
                    newStatus
            );

            System.out.println(
                    "User status updated successfully."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "User status update failed: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // READ STAFF ROLE
    // =========================================================

    private static StaffRole readStaffRole() {

        System.out.println();

        System.out.println(
                "Select User Role:"
        );

        System.out.println(
                "1. STAFF"
        );

        System.out.println(
                "2. DOCTOR"
        );

        System.out.println(
                "3. NURSE"
        );

        System.out.println(
                "4. PHARMACIST"
        );

        System.out.println(
                "5. LABORATORY_TECHNICIAN"
        );

        int choice =
                readInt(
                        "Enter role: "
                );

        switch (choice) {

            case 1:
                return StaffRole.STAFF;

            case 2:
                return StaffRole.DOCTOR;

            case 3:
                return StaffRole.NURSE;

            case 4:
                return StaffRole.PHARMACIST;

            case 5:
                return StaffRole.LABORATORY_TECHNICIAN;

            default:
                System.out.println(
                        "Invalid role."
                );

                return null;
        }
    }


    // =========================================================
    // CREATE STAFF REFERENCE FOR USER
    // =========================================================

    private static Staff readStaffForRole(
            StaffRole role
    ) {

        if (role == StaffRole.STAFF) {

            int staffId =
                    readInt(
                            "Enter Staff ID (0 for none): "
                    );

            if (staffId == 0) {
                return null;
            }

            if (staffId < 0) {

                System.out.println(
                        "Invalid Staff ID."
                );

                return null;
            }

            Staff staff =
                    new Doctor();

            staff.setStaffId(
                    staffId
            );

            return staff;
        }

        int staffId =
                readInt(
                        "Enter Staff ID: "
                );

        if (staffId <= 0) {

            System.out.println(
                    "Invalid Staff ID."
            );

            return null;
        }

        Staff staff;

        switch (role) {

            case DOCTOR:
                staff = new Doctor();
                break;

            case NURSE:
                staff = new Nurse();
                break;

            case PHARMACIST:
                staff = new Pharmacist();
                break;

            case LABORATORY_TECHNICIAN:
                staff = new LaboratoryTechnician();
                break;

            default:
                return null;
        }

        staff.setStaffId(
                staffId
        );

        return staff;
    }


    // =========================================================
    // READ INTEGER
    // =========================================================

    private static int readInt(
            String message
    ) {

        while (true) {

            try {

                System.out.print(
                        message
                );

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