package org.ims.controller.candidate;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.ECandidateStatus;
import org.ims.dao.CandidateDAO;
import org.ims.entity.Candidate;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CandidateUpdateServlet", urlPatterns = {"/candidate/update"})
public class UpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String candidateId = request.getParameter("candidateId");
        String candidateName = request.getParameter("candidateFullName");
        String candidateAddress = request.getParameter("candidateAddress");
        String candidatePhoneNumber = request.getParameter("candidatePhoneNumber");
        String candidateEmail = request.getParameter("candidateEmail");
        String candidateImage = request.getParameter("candidateImage");
        String candidateCvAttachment = request.getParameter("candidateCvAttachment");
        LocalDate candidateDob = LocalDate.parse(request.getParameter("candidateDob"));
        ECandidateStatus ecs = ECandidateStatus.convertFromString(request.getParameter("candidateStatus"));
        String candidateNote = request.getParameter("candidateNote");
        Candidate newCandidate = new Candidate(Integer.parseInt(candidateId), candidateName, candidateAddress, candidatePhoneNumber,
                candidateEmail, candidateImage, candidateDob, candidateCvAttachment, ecs, candidateNote);
        CandidateDAO cD = new CandidateDAO();
        cD.updateACandidate(candidateId, newCandidate);
        response.sendRedirect(request.getContextPath() + "/candidate");
    }
}
