package org.ims.controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.dao.MemberDAO;
import org.ims.entity.Member;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CustomUpdateServlet", urlPatterns = {"/custom-update"})
public class CustomUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String memberFullName = request.getParameter("memberFullName");
        String memberAccount = request.getParameter("memberAccount");
        String memberPassword = request.getParameter("memberPassword");
        LocalDate memberDob = LocalDate.parse(request.getParameter("memberDob"));
        String memberAddress = request.getParameter("memberAddress");
        String memberEmail = request.getParameter("memberEmail");
        String memberPhoneNumber = request.getParameter("memberPhoneNumber");
        String memberNote = request.getParameter("memberNote");

        MemberDAO mD = new MemberDAO();
        Member found = mD.getOneMember(memberId);
        
        Member member = new Member(Integer.parseInt(memberId), memberFullName, memberAccount, memberPassword, memberDob, found.getMemberCreatedTime(), memberAddress, memberEmail,
                memberPhoneNumber, found.getMemberImage(), memberNote, found.getMemberStatus(), found.getMemberRole());

        mD.updateAMember(memberId, member);
        response.sendRedirect(request.getContextPath() + "/member");
    }

}
