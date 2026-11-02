
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Application;
import util.DBConnection;

public class ApplicationDAO {

    // add a new application
    public boolean addApplication(Application application) {

        String sql = "INSERT INTO applications "
                + "(application_code, candidate_id, job_id, "
                + "eligibility_status, application_status, source, "
                + "assigned_recruiter_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String applicationCode = "APP" +
                UUID.randomUUID().toString()
                .replace("-", "").substring(0, 27);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, applicationCode);
            ps.setInt(2, application.getCandidateId());
            ps.setInt(3, application.getJobId());
            ps.setString(4, "PENDING");
            ps.setString(5, "APPLIED");
            ps.setString(6, "RECRUITER");
            ps.setInt(7, 2);

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("Error adding application: " + e.getMessage());
            return false;
        }
    }

    // display all applications
    public List<Application> getAllApplications() {

        List<Application> applications = new ArrayList<>();

        String sql = "SELECT application_id, candidate_id, job_id, "
                + "applied_at, eligibility_status, application_status "
                + "FROM applications";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Application application = new Application();

                application.setApplicationId(
                        rs.getInt("application_id"));

                application.setCandidateId(
                        rs.getInt("candidate_id"));

                application.setJobId(
                        rs.getInt("job_id"));

                application.setApplicationDate(
        rs.getTimestamp("applied_at").toString());

                application.setEligibilityStatus(
                        rs.getString("eligibility_status"));

                application.setApplicationStatus(
                        rs.getString("application_status"));

                applications.add(application);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching applications: " + e.getMessage());
        }

        return applications;
    }
}
