/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.UserAccess;

import DAO.UserDAO;
import Model.User.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hbtth
 */
public class authenticationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet authenticationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet authenticationController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/loginView/authenticationPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("s_u_is_check_authentication");
        String username = user.getUsername();
        UserDAO db = new UserDAO();
        db.authenticationAccount(username);
        Cookie c_u_tikilazapee = new Cookie("c_s_u_tikilazapee", username);
        Cookie c_p_tikilazapee = new Cookie("c_s_p_tikilazapee", user.getPassword());
        c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
        c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(c_u_tikilazapee);
        response.addCookie(c_p_tikilazapee);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
