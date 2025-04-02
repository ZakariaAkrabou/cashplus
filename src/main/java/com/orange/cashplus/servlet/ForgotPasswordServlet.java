package com.orange.cashplus.servlet;

import com.orange.cashplus.dao.UserDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	        
	       
	        request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        
        if (UserDAO.emailExists(email)) {
            boolean emailSent = UserDAO.createPasswordResetToken(email);
            if (emailSent) {
                request.setAttribute("message", "Password reset link sent to your email");
            } else {
                request.setAttribute("error", "Failed to send reset email. Please try again.");
            }
        } else {
            request.setAttribute("error", "No account found with this email");
        }
        
        request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
    }
}