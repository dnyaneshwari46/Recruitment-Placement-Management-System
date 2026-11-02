
package model;

public class Placement {

    private int placementId;
    private int candidateId;
    private int companyId;
    private int jobId;
    private int offerId;
    private String designation;
    private double finalPackage;
    private String expectedJoiningDate;
    private String actualJoiningDate;
    private String joiningStatus;
    private String placementStatus;

    public Placement() {
    }

    public int getPlacementId() {
        return placementId;
    }

    public void setPlacementId(int placementId) {
        this.placementId = placementId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getFinalPackage() {
        return finalPackage;
    }

    public void setFinalPackage(double finalPackage) {
        this.finalPackage = finalPackage;
    }

    public String getExpectedJoiningDate() {
        return expectedJoiningDate;
    }

    public void setExpectedJoiningDate(String expectedJoiningDate) {
        this.expectedJoiningDate = expectedJoiningDate;
    }

    public String getActualJoiningDate() {
        return actualJoiningDate;
    }

    public void setActualJoiningDate(String actualJoiningDate) {
        this.actualJoiningDate = actualJoiningDate;
    }

    public String getJoiningStatus() {
        return joiningStatus;
    }

    public void setJoiningStatus(String joiningStatus) {
        this.joiningStatus = joiningStatus;
    }

    public String getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(String placementStatus) {
        this.placementStatus = placementStatus;
    }
}
