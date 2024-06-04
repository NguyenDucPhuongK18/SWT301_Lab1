package org.ims.controller.job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.dao.JobDAO;

import java.io.IOException;

@WebServlet(name = "JobDeleteServlet", urlPatterns = {"/job/delete"})
public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobId = request.getParameter("jobId");
        JobDAO jD = new JobDAO();
        jD.deleteAJob(jobId);
        response.sendRedirect(request.getContextPath() + "/job");
    }
}
