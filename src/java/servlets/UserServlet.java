package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          UserService us = new UserService();

        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            List<User> users = us.getAll(email);
            //i donno what notes it's talking about in demo so i jus put users
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        //we have to create an href for this part in jsp so i donno what the purpose of this is for
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            try {
                String email = request.getParameter("email");
                User user = us.get(email);
                request.setAttribute("selectedAcc", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        UserService us = new UserService();

       
        String action = request.getParameter("action");
        String userEmail = request.getParameter("email");
        String userFname = request.getParameter("firstName");
        String userLname = request.getParameter("lastName");
        String userRole= request.getParameter("role");
        int pass = Integer.parseInt(request.getParameter("password"));
        boolean activation = Boolean.parseBoolean(request.getParameter("status"));
        try {
            switch (action) {
                case "add":
                    us.insert(userEmail, userFname, userLname, userRole,pass,
                            Boolean.parseBoolean(activation));
                    break;
                case "edit":
                    us.update(userEmail, userFname, userLname, userRole,pass,
                            Boolean.parseBoolean(activation));
                    break;
                case "delete":
                    us.delete(email);
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<User> users = us.getAll(email);
            request.setAttribute("Users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
