
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import util.DBConnection;
import util.FileHandler;

public class ReportDAO {

    // Common method to display and export report
    private void displayReport(String title, String sql) {

        System.out.println("\n========== " + title + " ==========");

        StringBuilder reportContent = new StringBuilder();

        reportContent.append("========== ")
                     .append(title)
                     .append(" ==========\n");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();

            boolean found = false;

            while (rs.next()) {

                found = true;

                for (int i = 1; i <= columns; i++) {

                    String line = meta.getColumnLabel(i) + ": "
                                  + rs.getString(i);

                    System.out.println(line);

                    reportContent.append(line).append("\n");
                }

                System.out.println("----------------------------");

                reportContent.append("----------------------------\n");
            }

            if (!found) {

                System.out.println("No records found!");

                reportContent.append("No records found!\n");
            }

            // Export report to text file
            String fileName = title.replace(" ", "_") + ".txt";

            FileHandler.writeToFile(
                fileName,
                reportContent.toString()
            );

        } catch (SQLException e) {

            System.out.println("Error generating report!");

            e.printStackTrace();
        }
    }

    // 1. Candidate Report
    public void candidateReport() {

        String sql =
            "SELECT candidate_id, candidate_code, full_name, email, " +
            "qualification, passing_year, percentage, " +
            "primary_technology, profile_status, placement_status " +
            "FROM candidates";

        displayReport("CANDIDATE REPORT", sql);
    }

    // 2. Company Report
    public void companyReport() {

        String sql =
            "SELECT company_id, company_code, company_name, " +
            "industry, location, company_type, status " +
            "FROM companies";

        displayReport("COMPANY REPORT", sql);
    }

    // 3. Job Report
    public void jobReport() {

        String sql =
            "SELECT j.job_id, j.job_code, c.company_name, " +
            "j.job_title, j.employment_type, j.location, " +
            "j.openings, j.min_package_lpa, j.max_package_lpa, " +
            "j.min_percentage, j.application_deadline, j.status " +
            "FROM jobs j " +
            "JOIN companies c ON j.company_id = c.company_id";

        displayReport("JOB REPORT", sql);
    }

    // 4. Application Report
    public void applicationReport() {

        String sql =
            "SELECT a.application_id, a.application_code, " +
            "c.full_name, j.job_title, a.applied_at, " +
            "a.eligibility_status, a.application_status, " +
            "a.eligibility_reason " +
            "FROM applications a " +
            "JOIN candidates c ON a.candidate_id = c.candidate_id " +
            "JOIN jobs j ON a.job_id = j.job_id";

        displayReport("APPLICATION REPORT", sql);
    }

    // 5. Interview Report
    public void interviewReport() {

        String sql =
            "SELECT i.interview_id, i.application_id, " +
            "c.full_name, i.round_number, i.round_type, " +
            "i.scheduled_date, i.scheduled_time, i.mode, " +
            "i.interviewer_name, i.status " +
            "FROM interview_rounds i " +
            "JOIN applications a ON i.application_id = a.application_id " +
            "JOIN candidates c ON a.candidate_id = c.candidate_id";

        displayReport("INTERVIEW REPORT", sql);
    }

    // 6. Offer Report
    public void offerReport() {

        String sql =
            "SELECT o.offer_id, o.offer_code, c.full_name, " +
            "o.designation, o.offered_package_lpa, " +
            "o.offer_date, o.expected_joining_date, o.offer_status " +
            "FROM offers o " +
            "JOIN applications a ON o.application_id = a.application_id " +
            "JOIN candidates c ON a.candidate_id = c.candidate_id";

        displayReport("OFFER REPORT", sql);
    }

    // 7. Placement Report
    public void placementReport() {

        String sql =
            "SELECT p.placement_id, c.full_name, co.company_name, " +
            "j.job_title, p.designation, p.final_package_lpa, " +
            "p.expected_joining_date, p.actual_joining_date, " +
            "p.joining_status, p.placement_status " +
            "FROM placements p " +
            "JOIN candidates c ON p.candidate_id = c.candidate_id " +
            "JOIN companies co ON p.company_id = co.company_id " +
            "JOIN jobs j ON p.job_id = j.job_id";

        displayReport("PLACEMENT REPORT", sql);
    }
}
