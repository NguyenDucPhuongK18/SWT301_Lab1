package org.ims.controller.candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.ECandidateStatus;
import org.ims.dao.CandidateDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CandidateListServlet", urlPatterns = {"/candidate"})
public class ReadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CandidateDAO cD = new CandidateDAO();
        try {
            request.setAttribute("candidateStatus", ECandidateStatus.values());
            request.setAttribute("candidateList", cD.getCandidates("%%"));
        } catch (SQLException ex) {
            request.setAttribute("candidateList", new ArrayList<>());
            ex.printStackTrace(System.out);
        } finally {
            request.getRequestDispatcher("candidate-list.jsp").forward(request, response);
        }
    }
}
