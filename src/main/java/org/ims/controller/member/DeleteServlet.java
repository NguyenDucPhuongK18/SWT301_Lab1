package org.ims.controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ims.dao.MemberDAO;
import org.ims.entity.Member;

import java.io.IOException;

@WebServlet(name = "MemberDeleteServlet", urlPatterns = {"/member/delete"})
public class DeleteServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        MemberDAO mD = new MemberDAO();
        HttpSession session = request.getSession();
        Member u = (Member) session.getAttribute("user");
        mD.deleteAMember(memberId);
        if (u != null) {
            if (u.getMemberId() == Integer.parseInt(memberId)) {
                session.invalidate();
                request.logout();
            }
        }
        response.sendRedirect(request.getContextPath() + "/member");
    }
}
