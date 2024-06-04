package org.ims.controller.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ims.constant.EMemberRole;
import org.ims.constant.EMemberStatus;
import org.ims.dao.MemberDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MemberListServlet", urlPatterns = {"/member"})
public class ReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO mD = new MemberDAO();
        try {
            request.setAttribute("memberStatus", EMemberStatus.values());
            request.setAttribute("memberRole", EMemberRole.values());
            request.setAttribute("memberList", mD.getMembers("%%"));
        } catch (SQLException ex) {
            request.setAttribute("memberList", new ArrayList<>());
            ex.printStackTrace(System.out);
        } finally {
            request.getRequestDispatcher("member-list.jsp").forward(request, response);
        }
    }
}
