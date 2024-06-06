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
import java.util.List;

@WebServlet(name = "InterviewAddServlet", urlPatterns = {"/interview/add"})
public class CreateServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String interviewTitle = request.getParameter("interviewTitle");
        LocalDate interviewStartTime = LocalDate.parse(request.getParameter("interviewStartTime"));
        LocalDate interviewEndTime = LocalDate.parse(request.getParameter("interviewEndTime"));
        String interviewLocation = request.getParameter("interviewLocation");
        EInterviewStatus interviewStatus = EInterviewStatus.convertFromString(request.getParameter("interviewStatus"));
        EInterviewResult interviewResult = EInterviewResult.convertFromString(request.getParameter("interviewResult"));
        String interviewNote = request.getParameter("interviewNote");

        CandidateDAO cD = new CandidateDAO();
        Candidate candidate;
        try {
            candidate = cD.getOneCandidate(request.getParameter("candidate"));
        } catch (SQLException e) {
            candidate = new Candidate();
            e.printStackTrace(System.out);
        }

        JobDAO jD = new JobDAO();
        Job job = jD.getOneJob(request.getParameter("job"));

        String[] interviewers = request.getParameterValues("interviewers");

        Interview interview = new Interview(0, interviewTitle, interviewStartTime, interviewEndTime, interviewLocation,
                interviewStatus, interviewResult, interviewNote, candidate, job);
        InterviewDAO iD = new InterviewDAO();
        int insertedId = iD.insertANewInterview(interview);
        iD.insertInterviewersOfAnInterview(String.valueOf(insertedId), interviewers);
        response.sendRedirect(request.getContextPath() + "/interview");
    }
}
