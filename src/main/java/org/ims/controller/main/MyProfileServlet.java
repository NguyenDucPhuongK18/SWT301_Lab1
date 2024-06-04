package org.ims.controller.main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ims.dao.MemberDAO;
import org.ims.entity.Member;

import java.io.IOException;

@WebServlet(name = "MyProfileServlet", urlPatterns = {"/profile"})
public class MyProfileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MemberDAO mD = new MemberDAO();
        Member m = (Member) session.getAttribute("user");
        String memberId = String.valueOf(m.getMemberId());
        m = mD.getOneMember(memberId);
        session.setAttribute("user", m);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
