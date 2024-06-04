package org.ims.controller.interview;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EInterviewResult;
import org.ims.constant.EInterviewStatus;
import org.ims.dao.CandidateDAO;
import org.ims.dao.InterviewDAO;
import org.ims.dao.JobDAO;
import org.ims.dao.MemberDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "InterviewListServlet", urlPatterns = {"/interview"})
public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InterviewDAO iD = new InterviewDAO();
        CandidateDAO cD = new CandidateDAO();
        JobDAO jD = new JobDAO();
        MemberDAO mD = new MemberDAO();
        try {
            request.setAttribute("interviewStatus", EInterviewStatus.values());
            request.setAttribute("interviewResult", EInterviewResult.values());
            request.setAttribute("interviewList", iD.getInterviews("%%"));
            request.setAttribute("candidateList", cD.getCandidates("%%"));
            request.setAttribute("jobList", jD.getJobs("%%"));
            request.setAttribute("interviewerList",iD.getInterviewersOfAnInterview());
            request.setAttribute("memberList", mD.getMembers("%%"));
        } catch (SQLException ex) {
            request.setAttribute("interviewList", new ArrayList<>());
            request.setAttribute("candidateList", new ArrayList<>());
            request.setAttribute("jobList", new ArrayList<>());
            request.setAttribute("interviewerList", new ArrayList<>());
            request.setAttribute("memberList", new ArrayList<>());
            ex.printStackTrace(System.out);
        } finally {
            request.getRequestDispatcher("interview-list.jsp").forward(request, response);
        }
    }
}
