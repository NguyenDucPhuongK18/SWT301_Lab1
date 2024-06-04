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

@WebServlet(name = "MemberUpdateServlet", urlPatterns = {"/member/update"})
public class UpdateServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String memberId = request.getParameter("memberId");
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
        EMemberRole enum_memberRole = null;
        EMemberStatus enum_memberStatus = null;
        try {
            enum_memberRole = EMemberRole.convertFromString(memberRole);
            enum_memberStatus = EMemberStatus.convertFromString(memberStatus);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }

        Member member = new Member(Integer.parseInt(memberId), memberFullName, memberAccount, memberPassword, memberDob, memberCreatedTime, memberAddress, memberEmail,
                memberPhoneNumber, memberImage, memberNote, enum_memberStatus, enum_memberRole);
        MemberDAO mD = new MemberDAO();
        mD.updateAMember(memberId, member);
        response.sendRedirect(request.getContextPath() + "/member");
    }
}
