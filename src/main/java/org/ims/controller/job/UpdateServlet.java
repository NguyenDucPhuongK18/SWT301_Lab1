package org.ims.controller.job;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EJobStatus;
import org.ims.dao.JobDAO;
import org.ims.entity.Job;

import java.io.IOException;

@WebServlet(name = "JobUpdateServlet", urlPatterns = {"/job/update"})
public class UpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        EJobStatus jobStatus = EJobStatus.convertFromString(request.getParameter("jobStatus"));
        float jobSalaryFrom = Float.parseFloat(request.getParameter("jobSalaryFrom"));
        float jobSalaryTo = Float.parseFloat(request.getParameter("jobSalaryTo"));
        Job newJob = new Job(jobId, jobTitle, jobDescription, jobStatus, jobSalaryFrom, jobSalaryTo);
        JobDAO jD = new JobDAO();
        jD.updateAJob(String.valueOf(jobId), newJob);
        response.sendRedirect(request.getContextPath() + "/job");
    }
}
