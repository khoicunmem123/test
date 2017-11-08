/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tbl_Account.AccountDAO;
import tbl_Student.StudentDAO;

/**
 *
 * @author khoi_
 */
@WebServlet(name = "NullServlet", urlPatterns = {"/NullServlet"})
public class NullServlet extends HttpServlet {

    private final String loginPage = "login.html";
    final private String manageStudentPage = "manageStudent.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = loginPage;
        try {
            Cookie[] cookie = request.getCookies();
            if (cookie != null) {
                for (int i = 0; i < cookie.length; i++) {
                    String username = cookie[i].getName();
                    String password = cookie[i].getValue();
                    AccountDAO dao = new AccountDAO();
                    if (dao.checkLogin(username, password)) {
                        url = manageStudentPage;
                        HttpSession session = request.getSession();
                        StudentDAO studentdao = new StudentDAO();
                        String fullname = studentdao.getFullname(username);
                        session.setAttribute("FULLNAME", fullname);
                        session.setAttribute("USERNAME", username);
                        cookie[i].setMaxAge(60 * 3);
                        response.addCookie(cookie[i]);
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            log("NullServlet_Naming: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NullServlet_SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
