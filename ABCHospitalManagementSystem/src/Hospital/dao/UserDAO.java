package hospital.dao;

import hospital.database.DatabaseConnection;
import hospital.models.Doctor;
import hospital.models.LaboratoryTechnician;
import hospital.models.Nurse;
import hospital.models.Pharmacist;
import hospital.models.Staff;
import hospital.models.StaffRole;
import hospital.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // =========================================================
    // CREATE USER
    // =========================================================

    public boolean addUser(User user) {

        String sql = """
                INSERT INTO Users
                (
                    Username,
                    PasswordHash,
                    Role,
                    StaffId,
                    IsActive
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getPasswordHash()
            );

            statement.setString(
                    3,
                    user.getRole().name()
            );

            if (user.getStaff() != null) {
                statement.setInt(
                        4,
                        user.getStaff().getStaffId()
                );
            } else {
                statement.setNull(
                        4,
                        Types.INTEGER
                );
            }

            statement.setBoolean(
                    5,
                    user.isActive()
            );

            statement.executeUpdate();

            try (
                    ResultSet keys =
                            statement.getGeneratedKeys()
            ) {

                if (keys.next()) {

                    user.setId(
                            keys.getInt(1)
                    );

                    System.out.println(
                            "User inserted successfully."
                    );

                    System.out.println(
                            "User ID: " + user.getId()
                    );

                    return true;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error inserting user: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // READ ALL USERS
    // =========================================================

    public List<User> findAllUsers() {

        List<User> users =
                new ArrayList<>();

        String sql = """
                SELECT
                    UserId,
                    Username,
                    PasswordHash,
                    Role,
                    StaffId,
                    IsActive
                FROM Users
                ORDER BY UserId
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                User user =
                        mapUser(resultSet);

                users.add(user);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error retrieving users: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return users;
    }


    // =========================================================
    // FIND USER BY ID
    // =========================================================

    public User findUserById(int userId) {

        String sql = """
                SELECT
                    UserId,
                    Username,
                    PasswordHash,
                    Role,
                    StaffId,
                    IsActive
                FROM Users
                WHERE UserId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return mapUser(
                            resultSet
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding user: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // FIND USER BY USERNAME
    // =========================================================

    public User findUserByUsername(
            String username
    ) {

        String sql = """
                SELECT
                    UserId,
                    Username,
                    PasswordHash,
                    Role,
                    StaffId,
                    IsActive
                FROM Users
                WHERE Username = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    username
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    return mapUser(
                            resultSet
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding user by username: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return null;
    }


    // =========================================================
    // UPDATE USER
    // =========================================================

    public boolean update(User user) {

        String sql = """
                UPDATE Users
                SET
                    Username = ?,
                    PasswordHash = ?,
                    Role = ?,
                    StaffId = ?,
                    IsActive = ?
                WHERE UserId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(
                    1,
                    user.getUsername()
            );

            statement.setString(
                    2,
                    user.getPasswordHash()
            );

            statement.setString(
                    3,
                    user.getRole().name()
            );

            if (user.getStaff() != null) {

                statement.setInt(
                        4,
                        user.getStaff().getStaffId()
                );

            } else {

                statement.setNull(
                        4,
                        Types.INTEGER
                );
            }

            statement.setBoolean(
                    5,
                    user.isActive()
            );

            statement.setInt(
                    6,
                    user.getId()
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "User updated successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error updating user: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // DELETE USER
    // =========================================================

    public boolean delete(int userId) {

        String sql = """
                DELETE FROM Users
                WHERE UserId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "User deleted successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error deleting user: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // UPDATE USER STATUS
    // =========================================================

    public boolean updateStatus(
            int userId,
            boolean active
    ) {

        String sql = """
                UPDATE Users
                SET IsActive = ?
                WHERE UserId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setBoolean(
                    1,
                    active
            );

            statement.setInt(
                    2,
                    userId
            );

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        active
                                ? "User activated successfully."
                                : "User deactivated successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error updating user status: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return false;
    }


    // =========================================================
    // MAP RESULT SET TO USER
    // =========================================================

    private User mapUser(
            ResultSet resultSet
    ) throws SQLException {

        User user =
                new User();

        user.setId(
                resultSet.getInt(
                        "UserId"
                )
        );

        user.setUsername(
                resultSet.getString(
                        "Username"
                )
        );

        user.setPasswordHash(
                resultSet.getString(
                        "PasswordHash"
                )
        );


        // =====================================================
        // ROLE
        // =====================================================

        String role =
                resultSet.getString(
                        "Role"
                );

        if (role != null) {

            user.setRole(
                    StaffRole.valueOf(
                            role
                    )
            );
        }


        // =====================================================
        // ACTIVE STATUS
        // =====================================================

        user.setActive(
                resultSet.getBoolean(
                        "IsActive"
                )
        );


        // =====================================================
        // STAFF
        // =====================================================

        int staffId =
                resultSet.getInt(
                        "StaffId"
                );

        if (!resultSet.wasNull()) {

            Staff staff =
                    findStaffByRole(
                            staffId,
                            user.getRole()
                    );

            user.setStaff(staff);
        }

        return user;
    }


    // =========================================================
    // FIND STAFF ACCORDING TO USER ROLE
    // =========================================================

    private Staff findStaffByRole(
            int staffId,
            StaffRole role
    ) {

        if (role == null) {
            return null;
        }

        switch (role) {

            case DOCTOR:

                return findDoctor(
                        staffId
                );

            case NURSE:

                return findNurse(
                        staffId
                );

            case PHARMACIST:

                return findPharmacist(
                        staffId
                );

            case LABORATORY_TECHNICIAN:

                return findLaboratoryTechnician(
                        staffId
                );

            case STAFF:

                return findBasicStaff(
                        staffId
                );

            default:

                return null;
        }
    }


    // =========================================================
    // FIND DOCTOR
    // =========================================================

    private Doctor findDoctor(
            int staffId
    ) {

        String sql = """
                SELECT StaffId
                FROM Doctor
                WHERE StaffId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    staffId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    Doctor doctor =
                            new Doctor();

                    doctor.setStaffId(
                            resultSet.getInt(
                                    "StaffId"
                            )
                    );

                    return doctor;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding doctor for user: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // FIND NURSE
    // =========================================================

    private Nurse findNurse(
            int staffId
    ) {

        String sql = """
                SELECT StaffId
                FROM Nurse
                WHERE StaffId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    staffId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    Nurse nurse =
                            new Nurse();

                    nurse.setStaffId(
                            resultSet.getInt(
                                    "StaffId"
                            )
                    );

                    return nurse;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding nurse for user: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // FIND PHARMACIST
    // =========================================================

    private Pharmacist findPharmacist(
            int staffId
    ) {

        String sql = """
                SELECT StaffId
                FROM Pharmacist
                WHERE StaffId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    staffId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    Pharmacist pharmacist =
                            new Pharmacist();

                    pharmacist.setStaffId(
                            resultSet.getInt(
                                    "StaffId"
                            )
                    );

                    return pharmacist;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding pharmacist for user: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // FIND LABORATORY TECHNICIAN
    // =========================================================

    private LaboratoryTechnician findLaboratoryTechnician(
            int staffId
    ) {

        String sql = """
                SELECT StaffId
                FROM LaboratoryTechnician
                WHERE StaffId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    staffId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    LaboratoryTechnician technician =
                            new LaboratoryTechnician();

                    technician.setStaffId(
                            resultSet.getInt(
                                    "StaffId"
                            )
                    );

                    return technician;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding laboratory technician for user: "
                            + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // FIND BASIC STAFF
    // =========================================================

    private Staff findBasicStaff(
            int staffId
    ) {

        String sql = """
                SELECT StaffId
                FROM Staff
                WHERE StaffId = ?
                """;

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    staffId
            );

            try (
                    ResultSet resultSet =
                            statement.executeQuery()
            ) {

                if (resultSet.next()) {

                    /*
                     * Staff is abstract, so we cannot create
                     * a Staff object directly.
                     *
                     * The Staff role currently has no concrete
                     * Staff subclass in the provided models.
                     */
                    return null;
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "Error finding basic staff for user: "
                            + e.getMessage()
            );
        }

        return null;
    }
}