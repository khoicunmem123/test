/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ErrorObj.updateErr;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tbl_Status.StatusDAO;
import tbl_Student.StudentDAO;

/**
 *
 * @author khoi_
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private final String updateErrorPage = "updateError.jsp";

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
        String url = updateErrorPage;
        updateErr err = new updateErr();
        try {
            String searchStatus = request.getParameter("txtSearchStatus");
            String searchName = request.getParameter("txtSearchName");
            if (searchName.trim().length() == 0 && searchStatus.trim().length() == 0) {
                url = "showAll";
            } else {
                url = "search?txtSearchStatus=" + searchStatus + "&txtSearchName=" + searchName;
            }
            String id = request.getParameter("txtId");
            String classname = request.getParameter("txtClass");
            if ("".equals(classname)) {
                err.setClassError(true);
            }
            String address1 = request.getParameter("txtAddress1");
            if ("".equals(address1)) {
                err.setAddress1Error(true);
            }
            String address2 = request.getParameter("txtAddress2");
            String status = request.getParameter("txtStatus");
            StatusDAO statusdao = new StatusDAO();
            if (!statusdao.checkStatus(status)) {
                err.setStatusError(true);
            }
            String phone = request.getParameter("txtPhone");
            if ("".equals(phone)) {
                err.setPhoneError(true);
            } else {
                for (int i = 0; i < phone.length(); i++) {
                    if (!Character.isDigit(phone.charAt(i))) {
                        err.setPhoneError(true);
                        break;
                    }
                }
            }
            if (err.isError()) {
                request.setAttribute("ERROR", err);
                url = updateErrorPage;
            } else {
                StudentDAO studentdao = new StudentDAO();
                studentdao.updateStudent(id, classname, status, address1, address2, phone);
            }
        } catch (NamingException ex) {
            log("InsertServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("InsertServlet_SQL: " + ex.getMessage());
        } finally {
            if (err.isError()) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
