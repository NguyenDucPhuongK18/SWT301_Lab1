package org.ims.controller.candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.dao.CandidateDAO;
import org.ims.entity.Candidate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CandidateDeleteServlet", urlPatterns = {"/candidate/delete"})
public class DeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String candidateId = request.getParameter("candidateId");
        CandidateDAO cD = new CandidateDAO();

        try {
            Candidate c = cD.getOneCandidate(candidateId);
            deleteFile(c.getCandidateImage());
            deleteFile(c.getCandidateCvAttachment());
            cD.deleteACandidate(candidateId);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        response.sendRedirect(request.getContextPath() + "/candidate");
    }

    private void deleteFile(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            String directory = "D:\\InterviewManagement_PRJ301\\src\\main\\webapp";
            String filePath = directory + File.separator + fileName;
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                fileToDelete.delete(); // Delete the file from the server
            }
        }
    }

}
