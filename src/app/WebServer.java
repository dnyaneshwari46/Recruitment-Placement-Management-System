
package app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

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

public class WebServer {

    private static final int PORT = 8080;

    private static final CandidateService candidateService =
            new CandidateService();

    private static final CompanyService companyService =
            new CompanyService();

    private static final JobService jobService =
            new JobService();

    private static final ApplicationService applicationService =
            new ApplicationService();

    private static final InterviewService interviewService =
            new InterviewService();

    private static final OfferService offerService =
            new OfferService();

    private static final PlacementService placementService =
            new PlacementService();

    private static final InterviewFeedbackService feedbackService =
            new InterviewFeedbackService();

    private static final EligibilityService eligibilityService =
            new EligibilityService();

    private static final ReportService reportService =
            new ReportService();

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(
                new InetSocketAddress(PORT), 0);

        server.createContext("/api/candidates",
                WebServer::handleCandidates);

        server.createContext("/api/companies",
                WebServer::handleCompanies);

        server.createContext("/api/jobs",
                WebServer::handleJobs);

        server.createContext("/api/applications",
                WebServer::handleApplications);

        server.createContext("/api/interviews",
                WebServer::handleInterviews);

        server.createContext("/api/offers",
                WebServer::handleOffers);

        server.createContext("/api/placements",
                WebServer::handlePlacements);

        server.createContext("/api/feedback",
                WebServer::handleFeedback);

        server.createContext("/api/eligibility",
                WebServer::handleEligibility);

        server.createContext("/api/reports",
                WebServer::handleReports);

        server.createContext("/", WebServer::handleFrontend);

        server.setExecutor(null);
        server.start();

        System.out.println("==================================");
        System.out.println("Recruitment Placement System Started!");
        System.out.println("Open: http://localhost:8080/index.html");
        System.out.println("==================================");
    }

    // Candidate API
    private static void handleCandidates(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Candidate> candidates =
                        candidateService.getAllCandidates();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Candidate c : candidates) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"candidateId\":").append(c.getCandidateId())
                        .append(",\"candidateCode\":\"").append(escapeJson(c.getCandidateCode()))
                        .append("\",\"name\":\"").append(escapeJson(c.getName()))
                        .append("\",\"email\":\"").append(escapeJson(c.getEmail()))
                        .append("\",\"mobile\":\"").append(escapeJson(c.getMobile()))
                        .append("\",\"qualification\":\"").append(escapeJson(c.getQualification()))
                        .append("\",\"passingYear\":").append(c.getPassingYear())
                        .append(",\"percentage\":").append(c.getPercentage())
                        .append(",\"backlogCount\":").append(c.getBacklogCount())
                        .append("}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Candidate candidate = new Candidate();

                candidate.setName(data.get("fullName"));
                candidate.setEmail(data.get("email"));
                candidate.setMobile(data.get("mobile"));
                candidate.setQualification(data.get("qualification"));
                candidate.setPassingYear(Integer.parseInt(data.get("passingYear")));
                candidate.setPercentage(Double.parseDouble(data.get("percentage")));
                candidate.setBacklogCount(Integer.parseInt(data.get("backlogCount")));

                boolean result = candidateService.addCandidate(candidate);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Candidate added successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to add candidate. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid numeric values.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Company API
    private static void handleCompanies(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Company> companies = companyService.getAllCompanies();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Company c : companies) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"companyId\":").append(c.getCompanyId())
                        .append(",\"companyName\":\"").append(escapeJson(c.getCompanyName()))
                        .append("\",\"industry\":\"").append(escapeJson(c.getIndustry()))
                        .append("\",\"website\":\"").append(escapeJson(c.getWebsite()))
                        .append("\",\"location\":\"").append(escapeJson(c.getLocation()))
                        .append("\",\"companyType\":\"").append(escapeJson(c.getCompanyType()))
                        .append("\",\"hrName\":\"").append(escapeJson(c.getHrName()))
                        .append("\",\"hrEmail\":\"").append(escapeJson(c.getHrEmail()))
                        .append("\",\"hrMobile\":\"").append(escapeJson(c.getHrMobile()))
                        .append("\",\"status\":\"").append(escapeJson(c.getStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Company company = new Company();

                company.setCompanyName(data.get("companyName"));
                company.setIndustry(data.get("industry"));
                company.setWebsite(data.get("website"));
                company.setLocation(data.get("location"));
                company.setCompanyType(data.get("companyType"));
                company.setHrName(data.get("hrName"));
                company.setHrEmail(data.get("hrEmail"));
                company.setHrMobile(data.get("hrMobile"));

                boolean result = companyService.addCompany(company);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Company added successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to add company. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"message\":\"Method not allowed\"}");
            }

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Job API
    private static void handleJobs(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Job> jobs = jobService.getAllJobs();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Job j : jobs) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"jobId\":").append(j.getJobId())
                        .append(",\"companyId\":").append(j.getCompanyId())
                        .append(",\"jobCode\":\"").append(escapeJson(j.getJobCode()))
                        .append("\",\"jobTitle\":\"").append(escapeJson(j.getJobTitle()))
                        .append("\",\"employmentType\":\"").append(escapeJson(j.getEmploymentType()))
                        .append("\",\"location\":\"").append(escapeJson(j.getLocation()))
                        .append("\",\"openings\":").append(j.getOpenings())
                        .append(",\"minimumPackage\":").append(j.getMinimumPackage())
                        .append(",\"maximumPackage\":").append(j.getMaximumPackage())
                        .append(",\"minimumPercentage\":").append(j.getMinimumPercentage())
                        .append(",\"minPassingYear\":").append(j.getMinPassingYear())
                        .append(",\"maxPassingYear\":").append(j.getMaxPassingYear())
                        .append(",\"maxBacklogs\":").append(j.getMaxBacklogs())
                        .append(",\"experienceRequired\":").append(j.getExperienceRequired())
                        .append(",\"qualificationRule\":\"").append(escapeJson(j.getQualificationRule()))
                        .append("\",\"postedDate\":\"").append(j.getPostedDate())
                        .append("\",\"applicationDeadline\":\"").append(j.getApplicationDeadline())
                        .append("\",\"status\":\"").append(escapeJson(j.getStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Job job = new Job();

                job.setCompanyId(Integer.parseInt(data.get("companyId")));
                job.setJobTitle(data.get("jobTitle"));
                job.setEmploymentType(data.get("employmentType"));
                job.setLocation(data.get("location"));
                job.setOpenings(Integer.parseInt(data.get("openings")));
                job.setMinimumPackage(Double.parseDouble(data.get("minimumPackage")));
                job.setMaximumPackage(Double.parseDouble(data.get("maximumPackage")));
                job.setMinimumPercentage(Double.parseDouble(data.get("minimumPercentage")));
                job.setMinPassingYear(Integer.parseInt(data.get("minPassingYear")));
                job.setMaxPassingYear(Integer.parseInt(data.get("maxPassingYear")));
                job.setMaxBacklogs(Integer.parseInt(data.get("maxBacklogs")));
                job.setExperienceRequired(Double.parseDouble(data.get("experienceRequired")));
                job.setQualificationRule(data.get("qualificationRule"));
                job.setPostedDate(Date.valueOf(data.get("postedDate")));
                job.setApplicationDeadline(Date.valueOf(data.get("applicationDeadline")));
                job.setCreatedBy(Integer.parseInt(data.get("createdBy")));

                boolean result = jobService.addJob(job);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Job added successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to add job. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid numeric values.\"}");

        } catch (IllegalArgumentException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter dates in YYYY-MM-DD format and valid form values.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Application API
    private static void handleApplications(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Application> applications =
                        applicationService.getAllApplications();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Application a : applications) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"applicationId\":").append(a.getApplicationId())
                        .append(",\"candidateId\":").append(a.getCandidateId())
                        .append(",\"jobId\":").append(a.getJobId())
                        .append(",\"applicationDate\":\"").append(escapeJson(a.getApplicationDate()))
                        .append("\",\"eligibilityStatus\":\"").append(escapeJson(a.getEligibilityStatus()))
                        .append("\",\"applicationStatus\":\"").append(escapeJson(a.getApplicationStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Application application = new Application();

                application.setCandidateId(Integer.parseInt(data.get("candidateId")));
                application.setJobId(Integer.parseInt(data.get("jobId")));

                boolean result = applicationService.addApplication(application);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Application added successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to add application. Check candidate ID and job ID.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid candidate and job IDs.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Interview API
    private static void handleInterviews(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Interview> interviews =
                        interviewService.getAllInterviews();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Interview i : interviews) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"interviewId\":").append(i.getInterviewId())
                        .append(",\"applicationId\":").append(i.getApplicationId())
                        .append(",\"roundNumber\":").append(i.getRoundNumber())
                        .append(",\"roundType\":\"").append(escapeJson(i.getRoundType()))
                        .append("\",\"interviewDate\":\"").append(escapeJson(i.getInterviewDate()))
                        .append("\",\"interviewTime\":\"").append(escapeJson(i.getInterviewTime()))
                        .append("\",\"mode\":\"").append(escapeJson(i.getMode()))
                        .append("\",\"interviewer\":\"").append(escapeJson(i.getInterviewer()))
                        .append("\",\"meetingLocation\":\"").append(escapeJson(i.getMeetingLocation()))
                        .append("\",\"status\":\"").append(escapeJson(i.getStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Interview interview = new Interview();

                interview.setApplicationId(Integer.parseInt(data.get("applicationId")));
                interview.setRoundNumber(Integer.parseInt(data.get("roundNumber")));
                interview.setRoundType(data.get("roundType"));
                interview.setInterviewDate(data.get("interviewDate"));

                String interviewTime = data.get("interviewTime");

                if (interviewTime != null && interviewTime.length() == 5) {
                    interviewTime = interviewTime + ":00";
                }

                interview.setInterviewTime(interviewTime);
                interview.setMode(data.get("mode"));
                interview.setInterviewer(data.get("interviewerName"));
                interview.setMeetingLocation(data.get("meetingLocation"));
                interview.setStatus("SCHEDULED");

                boolean result = interviewService.addInterview(interview);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Interview scheduled successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to schedule interview. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid application ID and round number.\"}");

        } catch (IllegalArgumentException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter a valid interview date and time.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Offer API
    private static void handleOffers(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Offer> offers = offerService.getAllOffers();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Offer o : offers) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"offerId\":").append(o.getOfferId())
                        .append(",\"applicationId\":").append(o.getApplicationId())
                        .append(",\"designation\":\"").append(escapeJson(o.getDesignation()))
                        .append("\",\"packageAmount\":").append(o.getPackageAmount())
                        .append(",\"offerDate\":\"").append(escapeJson(o.getOfferDate()))
                        .append("\",\"joiningDate\":\"").append(escapeJson(o.getJoiningDate()))
                        .append("\",\"offerStatus\":\"").append(escapeJson(o.getOfferStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Offer offer = new Offer();

                offer.setApplicationId(Integer.parseInt(data.get("applicationId")));
                offer.setDesignation(data.get("designation"));
                offer.setPackageAmount(Double.parseDouble(data.get("packageAmount")));
                offer.setOfferDate(data.get("offerDate"));
                offer.setJoiningDate(data.get("joiningDate"));
                offer.setOfferStatus(data.get("offerStatus"));

                boolean result = offerService.addOffer(offer);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Offer created successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to create offer. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"success\":false,\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid application ID and package amount.\"}");

        } catch (IllegalArgumentException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid dates in YYYY-MM-DD format.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Placement API
    private static void handlePlacements(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<Placement> placements =
                        placementService.getAllPlacements();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (Placement p : placements) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"placementId\":").append(p.getPlacementId())
                        .append(",\"candidateId\":").append(p.getCandidateId())
                        .append(",\"companyId\":").append(p.getCompanyId())
                        .append(",\"jobId\":").append(p.getJobId())
                        .append(",\"offerId\":").append(p.getOfferId())
                        .append(",\"designation\":\"").append(escapeJson(p.getDesignation()))
                        .append("\",\"finalPackage\":").append(p.getFinalPackage())
                        .append(",\"expectedJoiningDate\":\"").append(escapeJson(p.getExpectedJoiningDate()))
                        .append("\",\"actualJoiningDate\":\"").append(escapeJson(p.getActualJoiningDate()))
                        .append("\",\"joiningStatus\":\"").append(escapeJson(p.getJoiningStatus()))
                        .append("\",\"placementStatus\":\"").append(escapeJson(p.getPlacementStatus()))
                        .append("\"}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                Placement placement = new Placement();

                placement.setCandidateId(Integer.parseInt(data.get("candidateId")));
                placement.setCompanyId(Integer.parseInt(data.get("companyId")));
                placement.setJobId(Integer.parseInt(data.get("jobId")));
                placement.setOfferId(Integer.parseInt(data.get("offerId")));
                placement.setDesignation(data.get("designation"));

                placement.setFinalPackage(
                        Double.parseDouble(data.get("finalPackage")));

                placement.setExpectedJoiningDate(
                        data.get("expectedJoiningDate"));

                placement.setActualJoiningDate(
                        data.get("actualJoiningDate"));

                placement.setJoiningStatus(data.get("joiningStatus"));
                placement.setPlacementStatus(data.get("placementStatus"));

                boolean result = placementService.addPlacement(placement);

                if (result) {
                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Placement added successfully!\"}");
                } else {
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to add placement. Check the entered details.\"}");
                }

            } else {
                sendResponse(exchange, 405,
                        "{\"success\":false,\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid numeric IDs and package amount.\"}");

        } catch (IllegalArgumentException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid dates in YYYY-MM-DD format.\"}");

        } catch (Exception e) {

            e.printStackTrace();
            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Interview Feedback API
    private static void handleFeedback(HttpExchange exchange)
            throws IOException {

        try {

            String method = exchange.getRequestMethod();

            if (method.equalsIgnoreCase("GET")) {

                List<InterviewFeedback> feedbackList =
                        feedbackService.getAllFeedback();

                StringBuilder json = new StringBuilder("[");
                boolean first = true;

                for (InterviewFeedback f : feedbackList) {

                    if (!first) json.append(",");
                    first = false;

                    json.append("{")
                        .append("\"feedbackId\":").append(f.getFeedbackId())
                        .append(",\"interviewId\":").append(f.getInterviewId())
                        .append(",\"technicalRating\":").append(f.getTechnicalRating())
                        .append(",\"communicationRating\":").append(f.getCommunicationRating())
                        .append(",\"problemSolvingRating\":").append(f.getProblemSolvingRating())
                        .append(",\"overallRating\":").append(f.getOverallRating())
                        .append(",\"recommendation\":\"").append(escapeJson(f.getRecommendation()))
                        .append("\",\"remarks\":\"").append(escapeJson(f.getRemarks()))
                        .append("\",\"submittedBy\":").append(f.getSubmittedBy())
                        .append("}");
                }

                json.append("]");
                sendResponse(exchange, 200, json.toString());

            } else if (method.equalsIgnoreCase("POST")) {

                Map<String, String> data = parseFormData(
                        new String(exchange.getRequestBody().readAllBytes(),
                                StandardCharsets.UTF_8));

                InterviewFeedback feedback = new InterviewFeedback();

                feedback.setInterviewId(
                        Integer.parseInt(data.get("interviewId")));

                feedback.setSubmittedBy(
                        Integer.parseInt(data.get("submittedBy")));

                feedback.setTechnicalRating(
                        Double.parseDouble(data.get("technicalRating")));

                feedback.setCommunicationRating(
                        Double.parseDouble(data.get("communicationRating")));

                feedback.setProblemSolvingRating(
                        Double.parseDouble(data.get("problemSolvingRating")));

                feedback.setOverallRating(
                        Double.parseDouble(data.get("overallRating")));

                feedback.setRecommendation(data.get("recommendation"));
                feedback.setRemarks(data.get("remarks"));

                boolean result = feedbackService.addFeedback(feedback);

                if (result) {

                    sendResponse(exchange, 200,
                            "{\"success\":true,\"message\":\"Interview feedback submitted successfully!\"}");

                } else {

                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Unable to submit feedback. Check the entered details or whether feedback already exists for this interview.\"}");
                }

            } else {

                sendResponse(exchange, 405,
                        "{\"success\":false,\"message\":\"Method not allowed\"}");
            }

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Enter valid numeric values.\"}");

        } catch (Exception e) {

            e.printStackTrace();

            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Eligibility API
    private static void handleEligibility(HttpExchange exchange)
            throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {

            sendResponse(exchange, 405,
                    "{\"success\":false,\"message\":\"Method not allowed\"}");

            return;
        }

        try {

            String query = exchange.getRequestURI().getRawQuery();

            if (query == null || query.isEmpty()) {

                sendResponse(exchange, 400,
                        "{\"success\":false,\"message\":\"Application ID is required.\"}");

                return;
            }

            Map<String, String> data = parseFormData(query);

            String idValue = data.get("applicationId");

            if (idValue == null || idValue.trim().isEmpty()) {

                sendResponse(exchange, 400,
                        "{\"success\":false,\"message\":\"Application ID is required.\"}");

                return;
            }

            int applicationId = Integer.parseInt(idValue);

            if (applicationId <= 0) {

                sendResponse(exchange, 400,
                        "{\"success\":false,\"message\":\"Enter a valid Application ID.\"}");

                return;
            }

            String result =
                    eligibilityService.checkEligibilityResult(applicationId);

            if (result.startsWith("NOT_FOUND|")) {

                sendResponse(exchange, 404,
                        "{\"success\":false,\"message\":\"Application not found!\"}");

                return;
            }

            if (result.startsWith("ERROR|")) {

                sendResponse(exchange, 500,
                        "{\"success\":false,\"message\":\""
                        + escapeJson(result.substring(6)) + "\"}");

                return;
            }

            String[] parts = result.split("\\|", 2);

            if (parts.length != 2) {

                sendResponse(exchange, 500,
                        "{\"success\":false,\"message\":\"Unexpected eligibility response.\"}");

                return;
            }

            String status = parts[0];
            String reason = parts[1];

            String json =
                    "{\"success\":true,"
                    + "\"applicationId\":" + applicationId + ","
                    + "\"status\":\"" + escapeJson(status) + "\","
                    + "\"reason\":\"" + escapeJson(reason) + "\"}";

            sendResponse(exchange, 200, json);

        } catch (NumberFormatException e) {

            sendResponse(exchange, 400,
                    "{\"success\":false,\"message\":\"Application ID must be a number.\"}");

        } catch (Exception e) {

            e.printStackTrace();

            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Server error: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Reports API
    private static void handleReports(HttpExchange exchange)
            throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {

            sendResponse(exchange, 405,
                    "{\"success\":false,\"message\":\"Method not allowed\"}");

            return;
        }

        try {

            String query = exchange.getRequestURI().getRawQuery();

            if (query == null || query.isEmpty()) {

                sendResponse(exchange, 400,
                        "{\"success\":false,\"message\":\"Report type is required.\"}");

                return;
            }

            Map<String, String> data = parseFormData(query);

            String type = data.get("type");
            String fileName;

            switch (type == null ? "" : type.toLowerCase()) {

                case "candidate":
                    reportService.candidateReport();
                    fileName = "CANDIDATE_REPORT.txt";
                    break;

                case "company":
                    reportService.companyReport();
                    fileName = "COMPANY_REPORT.txt";
                    break;

                case "job":
                    reportService.jobReport();
                    fileName = "JOB_REPORT.txt";
                    break;

                case "application":
                    reportService.applicationReport();
                    fileName = "APPLICATION_REPORT.txt";
                    break;

                case "interview":
                    reportService.interviewReport();
                    fileName = "INTERVIEW_REPORT.txt";
                    break;

                case "offer":
                    reportService.offerReport();
                    fileName = "OFFER_REPORT.txt";
                    break;

                case "placement":
                    reportService.placementReport();
                    fileName = "PLACEMENT_REPORT.txt";
                    break;

                default:
                    sendResponse(exchange, 400,
                            "{\"success\":false,\"message\":\"Invalid report type.\"}");
                    return;
            }

            Path reportPath = Paths.get(fileName)
                    .toAbsolutePath()
                    .normalize();

            if (!Files.exists(reportPath)
                    || !Files.isRegularFile(reportPath)) {

                sendResponse(exchange, 404,
                        "{\"success\":false,\"message\":\"Report file was not generated.\"}");

                return;
            }

            byte[] fileBytes = Files.readAllBytes(reportPath);

            exchange.getResponseHeaders().set(
                    "Content-Type",
                    "text/plain; charset=UTF-8");

            exchange.getResponseHeaders().set(
                    "Content-Disposition",
                    "attachment; filename=\"" + fileName + "\"");

            exchange.sendResponseHeaders(200, fileBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(fileBytes);
            }

        } catch (Exception e) {

            e.printStackTrace();

            sendResponse(exchange, 500,
                    "{\"success\":false,\"message\":\"Unable to generate report: "
                    + escapeJson(e.getMessage()) + "\"}");
        }
    }

    // Convert form data into a Map
    private static Map<String, String> parseFormData(String body)
            throws IOException {

        Map<String, String> data = new HashMap<>();

        String[] pairs = body.split("&");

        for (String pair : pairs) {

            String[] parts = pair.split("=", 2);

            String key = URLDecoder.decode(
                    parts[0], StandardCharsets.UTF_8);

            String value = "";

            if (parts.length > 1) {
                value = URLDecoder.decode(
                        parts[1], StandardCharsets.UTF_8);
            }

            data.put(key, value);
        }

        return data;
    }

    // Escape JSON special characters
    private static String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }

    // Send response
    private static void sendResponse(
            HttpExchange exchange,
            int status,
            String response) throws IOException {

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json; charset=UTF-8");

        exchange.sendResponseHeaders(status, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    // Serve frontend files
    private static void handleFrontend(HttpExchange exchange)
            throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {

            sendResponse(exchange, 405,
                    "{\"message\":\"Method not allowed\"}");

            return;
        }

        String requestedPath =
                exchange.getRequestURI().getPath();

        if (requestedPath.equals("/")) {
            requestedPath = "/index.html";
        }

        Path frontendDirectory =
                Paths.get("frontend").toAbsolutePath().normalize();

        Path requestedFile = frontendDirectory.resolve(
                requestedPath.substring(1)).normalize();

        if (!requestedFile.startsWith(frontendDirectory)) {
            sendResponse(exchange, 403, "Forbidden");
            return;
        }

        if (!Files.exists(requestedFile)
                || !Files.isRegularFile(requestedFile)) {
            sendResponse(exchange, 404, "File not found");
            return;
        }

        byte[] fileBytes = Files.readAllBytes(requestedFile);

        String fileName = requestedFile.toString().toLowerCase();

        String contentType = "text/plain; charset=UTF-8";

        if (fileName.endsWith(".html")) {
            contentType = "text/html; charset=UTF-8";

        } else if (fileName.endsWith(".css")) {
            contentType = "text/css; charset=UTF-8";

        } else if (fileName.endsWith(".js")) {
            contentType = "application/javascript; charset=UTF-8";
        }

        exchange.getResponseHeaders().set(
                "Content-Type", contentType);

        exchange.sendResponseHeaders(200, fileBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(fileBytes);
        }
    }
}
