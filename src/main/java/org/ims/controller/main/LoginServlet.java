package org.ims.controller.main;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ims.dao.MemberDAO;
import org.ims.entity.Member;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        String account = request.getParameter("account");
        String password = request.getParameter("password");

        MemberDAO uD = new MemberDAO();
        Member u = uD.getOneMemberByMembernameAndPassword(account, password);
        HttpSession session = request.getSession();

        if (account != null && password != null) {
            if (u != null) {
                session.setAttribute("user", u);
                session.removeAttribute("error");
            } else {
                session.setAttribute("error", "Invalid credentials!");
            }
        }

        response.sendRedirect(request.getContextPath());
    }
}
