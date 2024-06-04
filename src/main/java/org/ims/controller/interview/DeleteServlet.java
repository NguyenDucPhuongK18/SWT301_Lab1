package org.ims.controller.interview;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.dao.InterviewDAO;

import java.io.IOException;

@WebServlet(name = "InterviewDeleteServlet", urlPatterns = {"/interview/delete"})
public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String interviewId = request.getParameter("interviewId");
        InterviewDAO iD = new InterviewDAO();
        iD.deleteAInterview(interviewId);
        response.sendRedirect(request.getContextPath() + "/interview");
    }
}
