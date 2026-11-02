
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Job;
import util.DBConnection;

public class JobDAO {

    // add a new job
    public boolean addJob(Job job) {

        String sql = "INSERT INTO jobs "
                + "(job_code, company_id, job_title, employment_type, "
                + "location, openings, min_package_lpa, max_package_lpa, "
                + "min_percentage, min_passing_year, max_passing_year, "
                + "max_backlogs, experience_min_years, qualification_rule, "
                + "posted_date, application_deadline, status, created_by) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String jobCode = "JOB" +
                UUID.randomUUID().toString()
                .replace("-", "").substring(0, 27);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, jobCode);
            ps.setInt(2, job.getCompanyId());
            ps.setString(3, job.getJobTitle());

            ps.setString(4, job.getEmploymentType());
            ps.setString(5, job.getLocation());
            ps.setInt(6, job.getOpenings());

            ps.setDouble(7, job.getMinimumPackage());
            ps.setDouble(8, job.getMaximumPackage());
            ps.setDouble(9, job.getMinimumPercentage());

            ps.setInt(10, job.getMinPassingYear());
            ps.setInt(11, job.getMaxPassingYear());
            ps.setInt(12, job.getMaxBacklogs());

            ps.setDouble(13, job.getExperienceRequired());
            ps.setString(14, job.getQualificationRule());

            ps.setDate(15, job.getPostedDate());
            ps.setDate(16, job.getApplicationDeadline());

            ps.setString(17, "OPEN");
            ps.setInt(18, job.getCreatedBy());

            int result = ps.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            System.out.println("Error adding job: " + e.getMessage());
            return false;
        }
    }

    // display all jobs
    public List<Job> getAllJobs() {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Job job = new Job();

                job.setJobId(rs.getInt("job_id"));
                job.setCompanyId(rs.getInt("company_id"));
                job.setJobCode(rs.getString("job_code"));
                job.setJobTitle(rs.getString("job_title"));

                job.setEmploymentType(rs.getString("employment_type"));
                job.setLocation(rs.getString("location"));
                job.setOpenings(rs.getInt("openings"));

                job.setMinimumPackage(rs.getDouble("min_package_lpa"));
                job.setMaximumPackage(rs.getDouble("max_package_lpa"));
                job.setMinimumPercentage(rs.getDouble("min_percentage"));

                job.setMinPassingYear(rs.getInt("min_passing_year"));
                job.setMaxPassingYear(rs.getInt("max_passing_year"));
                job.setMaxBacklogs(rs.getInt("max_backlogs"));

                job.setExperienceRequired(
                        rs.getDouble("experience_min_years"));

                job.setQualificationRule(
                        rs.getString("qualification_rule"));

                job.setPostedDate(rs.getDate("posted_date"));
                job.setApplicationDeadline(
                        rs.getDate("application_deadline"));

                job.setStatus(rs.getString("status"));

                jobs.add(job);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching jobs: " + e.getMessage());
        }

        return jobs;
    }
}
