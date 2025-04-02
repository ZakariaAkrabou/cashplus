package com.orange.cashplus.servlet;

import com.orange.cashplus.dao.UserDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String token = request.getParameter("token");
        
        if (token == null || !UserDAO.isValidResetToken(token)) {
            request.setAttribute("error", "Invalid or expired reset link");
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }
        
        request.setAttribute("token", token);
        request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
            return;
        }
        
        if (UserDAO.updatePasswordWithToken(token, password)) {
           
            HttpSession session = request.getSession();
            session.setAttribute("message", "Password updated successfully. Please login.");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("error", "Failed to update password. Link may be expired.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
        }
    }
}
