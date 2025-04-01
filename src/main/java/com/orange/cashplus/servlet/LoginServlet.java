package com.orange.cashplus.servlet;

import com.orange.cashplus.dao.UserDAO;
import com.orange.cashplus.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(" Login attempt for email: " + email); 

        if (UserDAO.validateUser(email, password)) {
            System.out.println(" User validated successfully");
            
            User user = UserDAO.getUserByEmail(email);
            System.out.println(" Retrieved user: " + user); 

            HttpSession session = request.getSession(true); 
            System.out.println(" Session ID: " + session.getId()); 
            
            session.setAttribute("user", user);
            System.out.println(" User stored in session: " + session.getAttribute("user"));

            response.sendRedirect("profile");
        } else {
            System.out.println(" Invalid credentials for email: " + email);
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
