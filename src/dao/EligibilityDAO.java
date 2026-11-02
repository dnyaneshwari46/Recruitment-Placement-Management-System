package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnection;

public class EligibilityDAO {

    public String checkEligibility(int applicationId) {

        String selectSql =
                "SELECT c.percentage, c.passing_year, c.backlog_count, " +
                "j.min_percentage, j.min_passing_year, " +
                "j.max_passing_year, j.max_backlogs " +
                "FROM applications a " +
                "JOIN candidates c ON a.candidate_id = c.candidate_id " +
                "JOIN jobs j ON a.job_id = j.job_id " +
                "WHERE a.application_id = ?";

        String updateSql =
                "UPDATE applications SET " +
                "eligibility_status = ?, " +
                "application_status = CASE " +
                "WHEN application_status IN ('APPLIED', 'ELIGIBILITY_VERIFIED') " +
                "THEN ? ELSE application_status END, " +
                "eligibility_reason = ?, " +
                "updated_at = CURRENT_TIMESTAMP " +
                "WHERE application_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            double percentage;
            int passingYear;
            int backlogs;
            double minPercentage;
            int minYear;
            int maxYear;
            int maxBacklogs;

            try (PreparedStatement ps = con.prepareStatement(selectSql)) {

                ps.setInt(1, applicationId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next()) {
                        return "NOT_FOUND|Application not found!";
                    }

                    percentage = rs.getDouble("percentage");
                    passingYear = rs.getInt("passing_year");
                    backlogs = rs.getInt("backlog_count");

                    minPercentage = rs.getDouble("min_percentage");
                    minYear = rs.getInt("min_passing_year");
                    maxYear = rs.getInt("max_passing_year");
                    maxBacklogs = rs.getInt("max_backlogs");
                }
            }

            StringBuilder reason = new StringBuilder();

            if (percentage < minPercentage) {
                reason.append("Percentage below minimum requirement. ");
            }

            if (minYear > 0 && passingYear < minYear) {
                reason.append("Passing year is below requirement. ");
            }

            if (maxYear > 0 && passingYear > maxYear) {
                reason.append("Passing year is above requirement. ");
            }

            if (backlogs > maxBacklogs) {
                reason.append("Backlogs exceed the allowed limit. ");
            }

            String eligibilityStatus;
            String applicationStatus;

            if (reason.length() == 0) {

                eligibilityStatus = "ELIGIBLE";
                applicationStatus = "ELIGIBILITY_VERIFIED";
                reason.append("Candidate meets all eligibility requirements.");

            } else {

                eligibilityStatus = "NOT_ELIGIBLE";
                applicationStatus = "REJECTED";
            }

            try (PreparedStatement update = con.prepareStatement(updateSql)) {

                update.setString(1, eligibilityStatus);
                update.setString(2, applicationStatus);
                update.setString(3, reason.toString().trim());
                update.setInt(4, applicationId);

                int result = update.executeUpdate();

                if (result > 0) {
                    System.out.println("Eligibility Status: " + eligibilityStatus);
                    System.out.println("Application Status: " + applicationStatus);
                    System.out.println("Reason: " + reason);

                    return eligibilityStatus + "|" + reason.toString().trim();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking eligibility: " + e.getMessage());
            return "ERROR|Database error: " + e.getMessage();
        }

        return "ERROR|Unable to update eligibility.";
    }
}