
package model;

public class Application {

    private int applicationId;
    private int candidateId;
    private int jobId;
    private String applicationDate;
    private String eligibilityStatus;
    private String applicationStatus;

    // default constructor
    public Application() {
    }

    // parameterized constructor
    public Application(int applicationId, int candidateId,
                       int jobId, String applicationDate,
                       String eligibilityStatus,
                       String applicationStatus) {

        this.applicationId = applicationId;
        this.candidateId = candidateId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;
        this.eligibilityStatus = eligibilityStatus;
        this.applicationStatus = applicationStatus;
    }

    // getters and setters
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getEligibilityStatus() {
        return eligibilityStatus;
    }

    public void setEligibilityStatus(String eligibilityStatus) {
        this.eligibilityStatus = eligibilityStatus;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
