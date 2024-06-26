package org.ims.controller.member;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EMemberRole;
import org.ims.constant.EMemberStatus;
import org.ims.dao.MemberDAO;
import org.ims.entity.Member;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "MemberAddServlet", urlPatterns = {"/member/add"})
public class CreateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String memberFullName = request.getParameter("memberFullName");
        String memberAccount = request.getParameter("memberAccount");
        String memberPassword = request.getParameter("memberPassword");
        LocalDate memberDob = LocalDate.parse(request.getParameter("memberDob"));
        LocalDate memberCreatedTime = LocalDate.now();
        String memberAddress = request.getParameter("memberAddress");
        String memberEmail = request.getParameter("memberEmail");
        String memberPhoneNumber = request.getParameter("memberPhoneNumber");
        String memberImage = request.getParameter("memberImage");
        String memberRole = request.getParameter("memberRole");
        String memberStatus = request.getParameter("memberStatus");
        String memberNote = request.getParameter("memberNote");
        EMemberRole memberRoleEnum = null;
        EMemberStatus memberStatusEnum = null;
        try {
            memberRoleEnum = EMemberRole.convertFromString(memberRole);
            memberStatusEnum = EMemberStatus.convertFromString(memberStatus);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }

        Member member = new Member(0, memberFullName, memberAccount, memberPassword, memberDob, memberCreatedTime, memberAddress, memberEmail,
                memberPhoneNumber, memberImage, memberNote, memberStatusEnum, memberRoleEnum);
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.insertANewMember(member);
        response.sendRedirect(request.getContextPath() + "/member");
    }
}
