package org.ims.controller.interview;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EInterviewResult;
import org.ims.constant.EInterviewStatus;
import org.ims.dao.CandidateDAO;
import org.ims.dao.InterviewDAO;
import org.ims.dao.JobDAO;
import org.ims.entity.Candidate;
import org.ims.entity.Interview;
import org.ims.entity.Job;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "InterviewUpdateServlet", urlPatterns = {"/interview/update"})
public class UpdateServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int interviewId = Integer.parseInt(request.getParameter("interviewId"));
        String interviewTitle = request.getParameter("interviewTitle");
        LocalDate interviewStartTime = LocalDate.parse(request.getParameter("interviewStartTime"));
        LocalDate interviewEndTime = LocalDate.parse(request.getParameter("interviewEndTime"));
        String interviewLocation = request.getParameter("interviewLocation");
        EInterviewStatus interviewStatus = EInterviewStatus.convertFromString("Finished");
        EInterviewResult interviewResult = EInterviewResult.convertFromString("Failed");
        String interviewNote = request.getParameter("interviewNote");

        CandidateDAO cD = new CandidateDAO();
        Candidate candidate;
        try {
            candidate = cD.getOneCandidate(request.getParameter("candidateId"));
        } catch (SQLException e) {
            candidate = new Candidate();
            e.printStackTrace(System.out);
        }

        JobDAO jD = new JobDAO();
        Job job = jD.getOneJob(request.getParameter("jobId"));

        String[] interviewers = request.getParameterValues("interviewers");
        Interview interview = new Interview(interviewId, interviewTitle, interviewStartTime, interviewEndTime, interviewLocation,
                interviewStatus, interviewResult, interviewNote, candidate, job);
        InterviewDAO iD = new InterviewDAO();
        iD.updateAInterview(String.valueOf(interviewId), interview);
        iD.updateInterviewers(String.valueOf(interviewId), interviewers);

        response.sendRedirect(request.getContextPath() + "/interview");
    }
}
