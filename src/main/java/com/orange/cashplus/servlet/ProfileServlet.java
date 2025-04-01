package com.orange.cashplus.servlet;

import com.orange.cashplus.model.User;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            
            response.sendRedirect("login");
        } else {
           
            request.setAttribute("user", user);
            request.getRequestDispatcher("/views/profile.jsp").forward(request, response);
        }
    }
}
