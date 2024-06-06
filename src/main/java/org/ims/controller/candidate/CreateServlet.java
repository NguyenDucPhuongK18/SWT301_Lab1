package org.ims.controller.candidate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.ims.constant.ECandidateStatus;
import org.ims.dao.CandidateDAO;
import org.ims.entity.Candidate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CandidateAddServlet", urlPatterns = {"/candidate/add"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreateServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String candidateFullName = request.getParameter("candidateFullName");
        String candidateAddress = request.getParameter("candidateAddress");
        String candidatePhoneNumber = request.getParameter("candidatePhoneNumber");
        String candidateEmail = request.getParameter("candidateEmail");
        String candidateImage = request.getParameter("candidateImage");
        String candidateCvAttachment = request.getParameter("candidateCvAttachment");
        LocalDate candidateDob = LocalDate.parse(request.getParameter("candidateDob"));
        ECandidateStatus ecs = ECandidateStatus.convertFromString(request.getParameter("candidateStatus"));
        String candidateNote = request.getParameter("candidateNote");
        Candidate newCandidate = new Candidate(0, candidateFullName, candidateAddress, candidatePhoneNumber, candidateEmail, candidateImage, candidateDob, candidateCvAttachment, ecs, candidateNote);
        CandidateDAO cD = new CandidateDAO();
        try {
            cD.insertANewCandidate(newCandidate);

            Part filePart = request.getPart("inputCImage");
            String fileName = filePart.getSubmittedFileName();
            String directory = "D:\\DemoAgain\\src\\main\\webapp";
            String filePath = directory + File.separator + fileName;

            try (InputStream inputStream = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                Logger.getLogger(CreateServlet.class.getName()).log(Level.SEVERE, "An error has happened", e);
            } finally {
                response.sendRedirect(request.getContextPath() + "/candidate");
            }
        } catch (ServletException | SQLException e) {
            Logger.getLogger(CreateServlet.class.getName()).log(Level.SEVERE, "An error has happened", e);
        }
    }
}