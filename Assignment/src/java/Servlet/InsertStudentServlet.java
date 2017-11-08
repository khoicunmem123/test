/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ErrorObj.insertErr;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tbl_Account.AccountDAO;
import tbl_Account.AccountDTO;
import tbl_Role.RoleDAO;
import tbl_Status.StatusDAO;
import tbl_Student.StudentDAO;
import tbl_Student.StudentDTO;

/**
 *
 * @author khoi_
 */
@WebServlet(name = "InsertStudentServlet", urlPatterns = {"/InsertStudentServlet"})
public class InsertStudentServlet extends HttpServlet {

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
        String url = manageStudentPage;
        insertErr err = new insertErr();
        try {
            AccountDAO accountdao = new AccountDAO();
            String id = request.getParameter("txtId");
            if ("".equals(id)) {
                err.setIdLengthError(true);
            }
            if (!accountdao.checkId(id)) {
                err.setIdDuplicaError(true);
            }
            String lastname = request.getParameter("txtLastname");
            if ("".equals(lastname)) {
                err.setLastnameError(true);
            }
            String middlename = request.getParameter("txtMiddlename");
            String firstname = request.getParameter("txtFirstname");
            if ("".equals(firstname)) {
                err.setFirstnameError(true);
            }
            String sex = request.getParameter("chkSex");
            String address1 = request.getParameter("txtAddress1");
            if ("".equals(address1)) {
                err.setAddress1Error(true);
            }
            String address2 = request.getParameter("txtAddress2");
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
            String classname = request.getParameter("txtClass");
            if ("".equals(classname)) {
                err.setClassError(true);
            }
            if (!err.isError()) {
                StatusDAO statusdao = new StatusDAO();
                int status = statusdao.getStatus("break");
                RoleDAO roledao = new RoleDAO();
                int role = roledao.getRole("student");
                AccountDTO accountdto = new AccountDTO(id, null, role);
                accountdao.insertNewAccount(accountdto);
                StudentDTO studentdto = new StudentDTO(id, firstname, middlename, lastname, classname, status, address1, address2, phone, sex != null);
                StudentDAO studentdao = new StudentDAO();
                studentdao.insertStudent(studentdto);
            } else {
                request.setAttribute("ERROR", err);
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
