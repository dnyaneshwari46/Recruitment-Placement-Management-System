
package model;

import java.sql.Date;

public class Job {

    private int jobId;
    private int companyId;
    private int createdBy;

    private String jobCode;
    private String jobTitle;
    private String employmentType;
    private String location;
    private String qualificationRule;
    private String status;

    private int openings;
    private int minPassingYear;
    private int maxPassingYear;
    private int maxBacklogs;

    private double minPackage;
    private double maxPackage;
    private double minPercentage;
    private double experienceRequired;

    private Date postedDate;
    private Date applicationDeadline;

    public Job() {
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQualificationRule() {
        return qualificationRule;
    }

    public void setQualificationRule(String qualificationRule) {
        this.qualificationRule = qualificationRule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOpenings() {
        return openings;
    }

    public void setOpenings(int openings) {
        this.openings = openings;
    }

    public int getMinPassingYear() {
        return minPassingYear;
    }

    public void setMinPassingYear(int minPassingYear) {
        this.minPassingYear = minPassingYear;
    }

    public int getMaxPassingYear() {
        return maxPassingYear;
    }

    public void setMaxPassingYear(int maxPassingYear) {
        this.maxPassingYear = maxPassingYear;
    }

    public int getMaxBacklogs() {
        return maxBacklogs;
    }

    public void setMaxBacklogs(int maxBacklogs) {
        this.maxBacklogs = maxBacklogs;
    }

    public double getMinimumPackage() {
        return minPackage;
    }

    public void setMinimumPackage(double minPackage) {
        this.minPackage = minPackage;
    }

    public double getMaximumPackage() {
        return maxPackage;
    }

    public void setMaximumPackage(double maxPackage) {
        this.maxPackage = maxPackage;
    }

    public double getMinimumPercentage() {
        return minPercentage;
    }

    public void setMinimumPercentage(double minPercentage) {
        this.minPercentage = minPercentage;
    }

    public double getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(double experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Date applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }
}
