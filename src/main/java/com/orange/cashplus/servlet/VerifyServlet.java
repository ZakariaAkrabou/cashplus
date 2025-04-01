package com.orange.cashplus.servlet;

import com.orange.cashplus.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");

        if (UserDAO.verifyUser(token)) {
            response.sendRedirect(request.getContextPath() + "/login?success=Account verified. Please login.");
        } else {
            response.sendRedirect(request.getContextPath() + "/register?error=Invalid verification link.");
        }
    }
}
