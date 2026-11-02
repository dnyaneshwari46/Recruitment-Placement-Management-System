
package app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import model.Application;
import model.Candidate;
import model.Company;
import model.Interview;
import model.InterviewFeedback;
import model.Job;
import model.Offer;
import model.Placement;
import service.ApplicationService;
import service.CandidateService;
import service.CompanyService;
import service.EligibilityService;
import service.InterviewFeedbackService;
import service.InterviewService;
import service.JobService;
import service.OfferService;
import service.PlacementService;
import service.ReportService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CandidateService candidateService = new CandidateService();
        CompanyService companyService = new CompanyService();
        JobService jobService = new JobService();
        ApplicationService applicationService = new ApplicationService();
        InterviewService interviewService = new InterviewService();
        OfferService offerService = new OfferService();
        PlacementService placementService = new PlacementService();
        InterviewFeedbackService feedbackService = new InterviewFeedbackService();
        EligibilityService eligibilityService = new EligibilityService();
        ReportService reportService = new ReportService();

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println(" RECRUITMENT AND PLACEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Candidate Management");
            System.out.println("2. Company Management");
            System.out.println("3. Job Management");
            System.out.println("4. Application Management");
            System.out.println("5. Interview Management");
            System.out.println("6. Offer Management");
            System.out.println("7. Placement Management");
            System.out.println("8. Interview Feedback Management");
            System.out.println("9. Eligibility Checking");
            System.out.println("10. Reports Management");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {

                // candidate management
                case 1: {

                    int candidateChoice;

                    do {
                        System.out.println("\n--- Candidate Management ---");
                        System.out.println("1. View Candidates");
                        System.out.println("2. Add Candidate");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        candidateChoice = Integer.parseInt(sc.nextLine().trim());

                        if (candidateChoice == 1) {

                            List<Candidate> candidates = candidateService.getAllCandidates();

                            System.out.println("\n--- Candidate List ---");

                            if (candidates.isEmpty()) {
                                System.out.println("No candidates found.");
                            } else {
                                for (Candidate c : candidates) {
                                    System.out.println("ID: " + c.getCandidateId());
                                    System.out.println("Name: " + c.getName());
                                    System.out.println("Email: " + c.getEmail());
                                    System.out.println("Mobile: " + c.getMobile());
                                    System.out.println("Qualification: " + c.getQualification());
                                    System.out.println("Passing Year: " + c.getPassingYear());
                                    System.out.println("Percentage: " + c.getPercentage());
                                    System.out.println("Backlogs: " + c.getBacklogCount());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (candidateChoice == 2) {

                            Candidate candidate = new Candidate();

                            System.out.print("Enter full name: ");
                            candidate.setName(sc.nextLine().trim());

                            System.out.print("Enter email: ");
                            candidate.setEmail(sc.nextLine().trim());

                            System.out.print("Enter mobile number: ");
                            candidate.setMobile(sc.nextLine().trim());

                            System.out.print("Enter qualification: ");
                            candidate.setQualification(sc.nextLine().trim());

                            System.out.print("Enter passing year: ");
                            candidate.setPassingYear(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter percentage: ");
                            candidate.setPercentage(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter backlog count: ");
                            candidate.setBacklogCount(Integer.parseInt(sc.nextLine().trim()));

                            candidateService.addCandidate(candidate);
                        }

                    } while (candidateChoice != 0);

                    break;
                }

                // company management
                case 2: {

                    int companyChoice;

                    do {
                        System.out.println("\n--- Company Management ---");
                        System.out.println("1. View Companies");
                        System.out.println("2. Add Company");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        companyChoice = Integer.parseInt(sc.nextLine().trim());

                        if (companyChoice == 1) {

                            List<Company> companies = companyService.getAllCompanies();

                            System.out.println("\n--- Company List ---");

                            if (companies.isEmpty()) {
                                System.out.println("No companies found.");
                            } else {
                                for (Company c : companies) {
                                    System.out.println("ID: " + c.getCompanyId());
                                    System.out.println("Company Name: " + c.getCompanyName());
                                    System.out.println("Industry: " + c.getIndustry());
                                    System.out.println("Website: " + c.getWebsite());
                                    System.out.println("Location: " + c.getLocation());
                                    System.out.println("Company Type: " + c.getCompanyType());
                                    System.out.println("HR Name: " + c.getHrName());
                                    System.out.println("HR Email: " + c.getHrEmail());
                                    System.out.println("HR Mobile: " + c.getHrMobile());
                                    System.out.println("Status: " + c.getStatus());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (companyChoice == 2) {

                            Company company = new Company();

                            System.out.print("Enter company name: ");
                            company.setCompanyName(sc.nextLine().trim());

                            System.out.print("Enter industry: ");
                            company.setIndustry(sc.nextLine().trim());

                            System.out.print("Enter website: ");
                            company.setWebsite(sc.nextLine().trim());

                            System.out.print("Enter location: ");
                            company.setLocation(sc.nextLine().trim());

                            System.out.print("Enter company type: ");
                            company.setCompanyType(sc.nextLine().trim());

                            System.out.print("Enter HR name: ");
                            company.setHrName(sc.nextLine().trim());

                            System.out.print("Enter HR email: ");
                            company.setHrEmail(sc.nextLine().trim());

                            System.out.print("Enter HR mobile: ");
                            company.setHrMobile(sc.nextLine().trim());

                            companyService.addCompany(company);
                        }

                    } while (companyChoice != 0);

                    break;
                }

                // job management
                case 3: {

                    int jobChoice;

                    do {
                        System.out.println("\n--- Job Management ---");
                        System.out.println("1. View Jobs");
                        System.out.println("2. Add Job");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        jobChoice = Integer.parseInt(sc.nextLine().trim());

                        if (jobChoice == 1) {

                            List<Job> jobs = jobService.getAllJobs();

                            System.out.println("\n--- Job List ---");

                            if (jobs.isEmpty()) {
                                System.out.println("No jobs found.");
                            } else {
                                for (Job j : jobs) {
                                    System.out.println("Job ID: " + j.getJobId());
                                    System.out.println("Company ID: " + j.getCompanyId());
                                    System.out.println("Job Title: " + j.getJobTitle());
                                    System.out.println("Employment Type: " + j.getEmploymentType());
                                    System.out.println("Location: " + j.getLocation());
                                    System.out.println("Openings: " + j.getOpenings());
                                    System.out.println("Minimum Package: " + j.getMinimumPackage());
                                    System.out.println("Maximum Package: " + j.getMaximumPackage());
                                    System.out.println("Minimum Percentage: " + j.getMinimumPercentage());
                                    System.out.println("Status: " + j.getStatus());
                                    System.out.println("Deadline: " + j.getApplicationDeadline());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (jobChoice == 2) {

                            Job job = new Job();

                            System.out.print("Enter company ID: ");
                            job.setCompanyId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter job title: ");
                            job.setJobTitle(sc.nextLine().trim());

                            System.out.print("Enter employment type (FULL_TIME / PART_TIME / INTERN): ");
                            job.setEmploymentType(sc.nextLine().trim().toUpperCase());

                            System.out.print("Enter location: ");
                            job.setLocation(sc.nextLine().trim());

                            System.out.print("Enter number of openings: ");
                            job.setOpenings(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter minimum package (LPA): ");
                            job.setMinimumPackage(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter maximum package (LPA): ");
                            job.setMaximumPackage(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter minimum percentage: ");
                            job.setMinimumPercentage(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter minimum passing year: ");
                            job.setMinPassingYear(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter maximum passing year: ");
                            job.setMaxPassingYear(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter maximum backlogs: ");
                            job.setMaxBacklogs(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter minimum experience (years): ");
                            job.setExperienceRequired(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter qualification requirement: ");
                            job.setQualificationRule(sc.nextLine().trim());

                            System.out.print("Enter posted date (YYYY-MM-DD): ");
                            job.setPostedDate(Date.valueOf(sc.nextLine().trim()));

                            System.out.print("Enter application deadline (YYYY-MM-DD): ");
                            job.setApplicationDeadline(Date.valueOf(sc.nextLine().trim()));

                            job.setCreatedBy(2);

                            jobService.addJob(job);
                        }

                    } while (jobChoice != 0);

                    break;
                }

                // application management
                case 4: {

                    int applicationChoice;

                    do {
                        System.out.println("\n--- Application Management ---");
                        System.out.println("1. View Applications");
                        System.out.println("2. Add Application");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        applicationChoice = Integer.parseInt(sc.nextLine().trim());

                        if (applicationChoice == 1) {

                            List<Application> applications = applicationService.getAllApplications();

                            System.out.println("\n--- Application List ---");

                            if (applications.isEmpty()) {
                                System.out.println("No applications found.");
                            } else {
                                for (Application a : applications) {
                                    System.out.println("Application ID: " + a.getApplicationId());
                                    System.out.println("Candidate ID: " + a.getCandidateId());
                                    System.out.println("Job ID: " + a.getJobId());
                                    System.out.println("Application Date: " + a.getApplicationDate());
                                    System.out.println("Eligibility Status: " + a.getEligibilityStatus());
                                    System.out.println("Application Status: " + a.getApplicationStatus());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (applicationChoice == 2) {

                            Application application = new Application();

                            System.out.print("Enter candidate ID: ");
                            application.setCandidateId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter job ID: ");
                            application.setJobId(Integer.parseInt(sc.nextLine().trim()));

                            applicationService.addApplication(application);
                        }

                    } while (applicationChoice != 0);

                    break;
                }

                // interview management
                case 5: {

                    int interviewChoice;

                    do {
                        System.out.println("\n--- Interview Management ---");
                        System.out.println("1. View Interviews");
                        System.out.println("2. Add Interview");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        interviewChoice = Integer.parseInt(sc.nextLine().trim());

                        if (interviewChoice == 1) {

                            List<Interview> interviews = interviewService.getAllInterviews();

                            System.out.println("\n--- Interview List ---");

                            if (interviews.isEmpty()) {
                                System.out.println("No interviews found.");
                            } else {
                                for (Interview i : interviews) {
                                    System.out.println("Interview ID: " + i.getInterviewId());
                                    System.out.println("Application ID: " + i.getApplicationId());
                                    System.out.println("Round Number: " + i.getRoundNumber());
                                    System.out.println("Round Type: " + i.getRoundType());
                                    System.out.println("Interview Date: " + i.getInterviewDate());
                                    System.out.println("Interview Time: " + i.getInterviewTime());
                                    System.out.println("Mode: " + i.getMode());
                                    System.out.println("Interviewer: " + i.getInterviewer());
                                    System.out.println("Meeting Location: " + i.getMeetingLocation());
                                    System.out.println("Status: " + i.getStatus());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (interviewChoice == 2) {

                            Interview interview = new Interview();

                            System.out.print("Enter application ID: ");
                            interview.setApplicationId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter round number: ");
                            interview.setRoundNumber(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter round type (TECHNICAL / HR / APTITUDE): ");
                            interview.setRoundType(sc.nextLine().trim());

                            System.out.print("Enter interview date (YYYY-MM-DD): ");
                            interview.setInterviewDate(sc.nextLine().trim());

                            System.out.print("Enter interview time (HH:MM:SS): ");
                            interview.setInterviewTime(sc.nextLine().trim());

                            System.out.print("Enter mode (ONLINE / OFFLINE): ");
                            interview.setMode(sc.nextLine().trim());

                            System.out.print("Enter interviewer name: ");
                            interview.setInterviewer(sc.nextLine().trim());

                            System.out.print("Enter meeting link or location: ");
                            interview.setMeetingLocation(sc.nextLine().trim());

                            System.out.print("Enter status (SCHEDULED / COMPLETED / CANCELLED): ");
                            interview.setStatus(sc.nextLine().trim());

                            interviewService.addInterview(interview);
                        }

                    } while (interviewChoice != 0);

                    break;
                }

                // offer management
                case 6: {

                    int offerChoice;

                    do {
                        System.out.println("\n--- Offer Management ---");
                        System.out.println("1. View Offers");
                        System.out.println("2. Add Offer");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        offerChoice = Integer.parseInt(sc.nextLine().trim());

                        if (offerChoice == 1) {

                            List<Offer> offers = offerService.getAllOffers();

                            System.out.println("\n--- Offer List ---");

                            if (offers.isEmpty()) {
                                System.out.println("No offers found.");
                            } else {
                                for (Offer o : offers) {
                                    System.out.println("Offer ID: " + o.getOfferId());
                                    System.out.println("Application ID: " + o.getApplicationId());
                                    System.out.println("Designation: " + o.getDesignation());
                                    System.out.println("Package (LPA): " + o.getPackageAmount());
                                    System.out.println("Offer Date: " + o.getOfferDate());
                                    System.out.println("Joining Date: " + o.getJoiningDate());
                                    System.out.println("Offer Status: " + o.getOfferStatus());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (offerChoice == 2) {

                            Offer offer = new Offer();

                            System.out.print("Enter application ID: ");
                            offer.setApplicationId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter designation: ");
                            offer.setDesignation(sc.nextLine().trim());

                            System.out.print("Enter offered package (LPA): ");
                            offer.setPackageAmount(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter offer date (YYYY-MM-DD): ");
                            offer.setOfferDate(sc.nextLine().trim());

                            System.out.print("Enter expected joining date (YYYY-MM-DD): ");
                            offer.setJoiningDate(sc.nextLine().trim());

                            System.out.print("Enter offer status (OFFERED / ACCEPTED / REJECTED / EXPIRED / WITHDRAWN): ");
                            offer.setOfferStatus(sc.nextLine().trim().toUpperCase());

                            offerService.addOffer(offer);
                        }

                    } while (offerChoice != 0);

                    break;
                }

                // placement management
                case 7: {

                    int placementChoice;

                    do {
                        System.out.println("\n--- Placement Management ---");
                        System.out.println("1. View Placements");
                        System.out.println("2. Add Placement");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        placementChoice = Integer.parseInt(sc.nextLine().trim());

                        if (placementChoice == 1) {

                            List<Placement> placements = placementService.getAllPlacements();

                            System.out.println("\n--- Placement List ---");

                            if (placements.isEmpty()) {
                                System.out.println("No placements found.");
                            } else {
                                for (Placement p : placements) {
                                    System.out.println("Placement ID: " + p.getPlacementId());
                                    System.out.println("Candidate ID: " + p.getCandidateId());
                                    System.out.println("Company ID: " + p.getCompanyId());
                                    System.out.println("Job ID: " + p.getJobId());
                                    System.out.println("Offer ID: " + p.getOfferId());
                                    System.out.println("Designation: " + p.getDesignation());
                                    System.out.println("Final Package (LPA): " + p.getFinalPackage());
                                    System.out.println("Expected Joining Date: " + p.getExpectedJoiningDate());
                                    System.out.println("Actual Joining Date: " + p.getActualJoiningDate());
                                    System.out.println("Joining Status: " + p.getJoiningStatus());
                                    System.out.println("Placement Status: " + p.getPlacementStatus());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (placementChoice == 2) {

                            Placement placement = new Placement();

                            System.out.print("Enter candidate ID: ");
                            placement.setCandidateId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter company ID: ");
                            placement.setCompanyId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter job ID: ");
                            placement.setJobId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter offer ID: ");
                            placement.setOfferId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter designation: ");
                            placement.setDesignation(sc.nextLine().trim());

                            System.out.print("Enter final package (LPA): ");
                            placement.setFinalPackage(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter expected joining date (YYYY-MM-DD): ");
                            placement.setExpectedJoiningDate(sc.nextLine().trim());

                            System.out.print("Enter actual joining date (YYYY-MM-DD) or press Enter if not joined: ");
                            placement.setActualJoiningDate(sc.nextLine().trim());

                            System.out.print("Enter joining status: ");
                            placement.setJoiningStatus(sc.nextLine().trim().toUpperCase());

                            System.out.print("Enter placement status: ");
                            placement.setPlacementStatus(sc.nextLine().trim().toUpperCase());

                            placementService.addPlacement(placement);
                        }

                    } while (placementChoice != 0);

                    break;
                }

                // interview feedback management
                case 8: {

                    int feedbackChoice;

                    do {
                        System.out.println("\n--- Interview Feedback Management ---");
                        System.out.println("1. View Interview Feedback");
                        System.out.println("2. Add Interview Feedback");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        feedbackChoice = Integer.parseInt(sc.nextLine().trim());

                        if (feedbackChoice == 1) {

                            List<InterviewFeedback> feedbackList = feedbackService.getAllFeedback();

                            System.out.println("\n--- Interview Feedback List ---");

                            if (feedbackList.isEmpty()) {
                                System.out.println("No interview feedback found.");
                            } else {
                                for (InterviewFeedback f : feedbackList) {
                                    System.out.println("Feedback ID: " + f.getFeedbackId());
                                    System.out.println("Interview ID: " + f.getInterviewId());
                                    System.out.println("Technical Rating: " + f.getTechnicalRating());
                                    System.out.println("Communication Rating: " + f.getCommunicationRating());
                                    System.out.println("Problem Solving Rating: " + f.getProblemSolvingRating());
                                    System.out.println("Overall Rating: " + f.getOverallRating());
                                    System.out.println("Recommendation: " + f.getRecommendation());
                                    System.out.println("Remarks: " + f.getRemarks());
                                    System.out.println("Submitted By: " + f.getSubmittedBy());
                                    System.out.println("----------------------------");
                                }
                            }

                        } else if (feedbackChoice == 2) {

                            InterviewFeedback feedback = new InterviewFeedback();

                            System.out.print("Enter interview ID: ");
                            feedback.setInterviewId(Integer.parseInt(sc.nextLine().trim()));

                            System.out.print("Enter technical rating (0-5): ");
                            feedback.setTechnicalRating(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter communication rating (0-5): ");
                            feedback.setCommunicationRating(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter problem solving rating (0-10): ");
                            feedback.setProblemSolvingRating(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter overall rating (0-5): ");
                            feedback.setOverallRating(Double.parseDouble(sc.nextLine().trim()));

                            System.out.print("Enter recommendation: ");
                            feedback.setRecommendation(sc.nextLine().trim());

                            System.out.print("Enter remarks: ");
                            feedback.setRemarks(sc.nextLine().trim());

                            System.out.print("Enter submitted by (user ID): ");
                            feedback.setSubmittedBy(Integer.parseInt(sc.nextLine().trim()));

                            feedbackService.addFeedback(feedback);
                        }

                    } while (feedbackChoice != 0);

                    break;
                }

                // eligibility checking
                case 9: {

                    System.out.println("\n--- Eligibility Checking ---");

                    System.out.print("Enter application ID: ");
                    int applicationId = Integer.parseInt(sc.nextLine().trim());

                    eligibilityService.checkEligibility(applicationId);

                    break;
                }

                // reports management
                case 10: {

                    int reportChoice;

                    do {
                        System.out.println("\n--- Reports Management ---");
                        System.out.println("1. Candidate Report");
                        System.out.println("2. Company Report");
                        System.out.println("3. Job Report");
                        System.out.println("4. Application Report");
                        System.out.println("5. Interview Report");
                        System.out.println("6. Offer Report");
                        System.out.println("7. Placement Report");
                        System.out.println("0. Back to Main Menu");

                        System.out.print("Enter your choice: ");
                        reportChoice = Integer.parseInt(sc.nextLine().trim());

                        switch (reportChoice) {

                            case 1:
                                reportService.candidateReport();
                                break;

                            case 2:
                                reportService.companyReport();
                                break;

                            case 3:
                                reportService.jobReport();
                                break;

                            case 4:
                                reportService.applicationReport();
                                break;

                            case 5:
                                reportService.interviewReport();
                                break;

                            case 6:
                                reportService.offerReport();
                                break;

                            case 7:
                                reportService.placementReport();
                                break;

                            case 0:
                                System.out.println("Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("Invalid report choice!");
                        }

                    } while (reportChoice != 0);

                    break;
                }

                case 0:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
