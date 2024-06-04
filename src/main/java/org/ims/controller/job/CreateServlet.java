package org.ims.controller.job;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EJobStatus;
import org.ims.dao.JobDAO;
import org.ims.entity.Job;

import java.io.IOException;

@WebServlet(name = "JobAddServlet", urlPatterns = {"/job/add"})
public class CreateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        EJobStatus jobStatus = EJobStatus.convertFromString(request.getParameter("jobStatus"));
        float jobSalaryFrom = Float.parseFloat(request.getParameter("jobSalaryFrom"));
        float jobSalaryTo = Float.parseFloat(request.getParameter("jobSalaryTo"));
        Job newJob = new Job(0, jobTitle, jobDescription, jobStatus, jobSalaryFrom, jobSalaryTo);
        JobDAO jD = new JobDAO();
        jD.insertANewJob(newJob);
        response.sendRedirect(request.getContextPath() + "/job");
    }
}
