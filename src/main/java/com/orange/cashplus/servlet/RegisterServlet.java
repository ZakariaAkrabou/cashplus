package com.orange.cashplus.servlet;

import com.orange.cashplus.dao.UserDAO;
import com.orange.cashplus.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);

        boolean isRegistered = UserDAO.registerUser(user);
        
        if (isRegistered) {
        	request.setAttribute("message", "Registration successful! Please check your email for verification.");
        	request.getRequestDispatcher("/views/login.jsp").forward(request, response);

        } else {
            request.setAttribute("error", "Registration failed. Try again!");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}
