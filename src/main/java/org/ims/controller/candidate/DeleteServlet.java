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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CandidateDeleteServlet", urlPatterns = {"/candidate/delete"})
public class DeleteServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(DeleteServlet.class.getName());
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String candidateId = request.getParameter("candidateId");
        CandidateDAO cD = new CandidateDAO();

        try {
            Candidate c = cD.getOneCandidate(candidateId);
            deleteFile(c.getCandidateImage());
            deleteFile(c.getCandidateCvAttachment());
            cD.deleteACandidate(candidateId);
            response.sendRedirect(request.getContextPath() + "/candidate");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception while deleting candidate", e);
            // Handle the SQL exception, possibly redirect to an error page or inform the user/admin
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception while redirecting", e);
            // Handle the IO exception, possibly redirect to an error page or inform the user/admin
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected exception", e);
            // Handle any other unexpected exceptions
        }
        
    }

    private void deleteFile(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            String directory = "D:\\InterviewManagement_PRJ301\\src\\main\\webapp";
            String filePath = directory + File.separator + fileName;
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if(fileToDelete.delete()) {
                    logger.log(Level.INFO, "File deleted successfully");
                } else {
                    logger.log(Level.WARNING, "Failed to delete file");
                }
            }
        }
    }

}
