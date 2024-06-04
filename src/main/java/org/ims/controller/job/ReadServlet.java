package org.ims.controller.job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EJobStatus;
import org.ims.dao.JobDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JobListServlet", urlPatterns = {"/job"})
public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JobDAO jD = new JobDAO();
        try {
            request.setAttribute("jobStatus", EJobStatus.values());
            request.setAttribute("jobList", jD.getJobs("%%"));
        } catch (SQLException ex) {
            request.setAttribute("jobList", new ArrayList<>());
            ex.printStackTrace(System.out);
        } finally {
            request.getRequestDispatcher("job-list.jsp").forward(request, response);
        }
    }
}
