
package model;

public class Candidate {

    private int candidateId;
    private String candidateCode;
    private String name;
    private String email;
    private String mobile;
    private String qualification;
    private int passingYear;
    private double percentage;
    private int backlogCount;
    private boolean active;

    // default constructor
    public Candidate() {
    }

    // parameterized constructor
    public Candidate(int candidateId, String name, String email,
                     String mobile, double percentage,
                     int backlogCount, boolean active) {

        this.candidateId = candidateId;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.percentage = percentage;
        this.backlogCount = backlogCount;
        this.active = active;
    }

    // getters and setters

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getBacklogCount() {
        return backlogCount;
    }

    public void setBacklogCount(int backlogCount) {
        this.backlogCount = backlogCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
