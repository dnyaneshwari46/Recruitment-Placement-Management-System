
package service;

import java.util.List;

import dao.JobDAO;
import model.Job;

public class JobService {

    private JobDAO jobDAO = new JobDAO();

    // add job
    public boolean addJob(Job job) {

        if (job.getCompanyId() <= 0) {
            System.out.println("Enter a valid company ID!");
            return false;
        }

        if (job.getJobTitle() == null ||
                job.getJobTitle().trim().isEmpty()) {
            System.out.println("Job title is required!");
            return false;
        }

        if (job.getLocation() == null ||
                job.getLocation().trim().isEmpty()) {
            System.out.println("Job location is required!");
            return false;
        }

        if (job.getOpenings() <= 0) {
            System.out.println("Openings must be greater than zero!");
            return false;
        }

        if (job.getMinimumPackage() < 0 ||
                job.getMaximumPackage() < job.getMinimumPackage()) {
            System.out.println("Enter valid package details!");
            return false;
        }

        if (job.getMinimumPercentage() < 0 ||
                job.getMinimumPercentage() > 100) {
            System.out.println("Percentage must be between 0 and 100!");
            return false;
        }

        if (job.getMinPassingYear() > 0 &&
                job.getMaxPassingYear() > 0 &&
                job.getMinPassingYear() > job.getMaxPassingYear()) {
            System.out.println("Invalid passing year range!");
            return false;
        }

        if (job.getMaxBacklogs() < 0) {
            System.out.println("Backlogs cannot be negative!");
            return false;
        }

        if (job.getExperienceRequired() < 0) {
            System.out.println("Experience cannot be negative!");
            return false;
        }

        if (job.getPostedDate() == null ||
                job.getApplicationDeadline() == null) {
            System.out.println("Job dates are required!");
            return false;
        }

        if (job.getApplicationDeadline().before(job.getPostedDate())) {
            System.out.println("Deadline cannot be before the posted date!");
            return false;
        }

        boolean result = jobDAO.addJob(job);

        if (result) {
            System.out.println("Job added successfully!");
        }

        return result;
    }

    // view all jobs
    public List<Job> getAllJobs() {

        return jobDAO.getAllJobs();
    }
}
