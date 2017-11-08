/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Student;

import Utils.DBUtils;
import bean.searchItem;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import tbl_Status.StatusDAO;

/**
 *
 * @author khoi_
 */
public class StudentDAO implements Serializable {

    Map<String, searchItem> result = null;

    public Map<String, searchItem> getResult() {
        return result;
    }

    public String getFullname(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "select *\n"
                    + "from tbl_Student\n"
                    + "where id=?";
            pre = con.prepareStatement(sql);
            pre.setString(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                String firstname = rs.getString("firstname");
                String midname = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                String fullname;
                if (midname != null) {
                    fullname = firstname + " " + midname + " " + lastname;
                } else {
                    fullname = firstname + " " + lastname;
                }
                return fullname;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void searchStatus(String status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "select * from tbl_Student";
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int stt = rs.getInt("status");
                StatusDAO dao = new StatusDAO();
                if (dao.getStatusDescription(stt).equals(status)) {
                    String id = rs.getString("id");
                    String fullname = getFullname(id);
                    String classname = rs.getString("class");
                    String address1 = rs.getString("address1");
                    String address2 = rs.getString("address2");
                    String phone = rs.getString("phone");
                    searchItem item = new searchItem(id, fullname, classname, address1, address2, status, phone);
                    if (result == null) {
                        result = new HashMap<String, searchItem>();
                    }
                    result.put(id, item);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchName(String name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "select * \n"
                    + "from tbl_Student\n"
                    + "where firstname like ? or middlename like ? or lastname like ?";
            pre = con.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            pre.setString(2, "%" + name + "%");
            pre.setString(3, "%" + name + "%");
            rs = pre.executeQuery();
            while (rs.next()) {
                int stt = rs.getInt("status");
                StatusDAO dao = new StatusDAO();
                String id = rs.getString("id");
                String fullname = getFullname(id);
                String classname = rs.getString("class");
                String address1 = rs.getString("address1");
                String address2 = rs.getString("address2");
                String status = dao.getStatusDescription(stt);
                String phone = rs.getString("phone");
                searchItem item = new searchItem(id, fullname, classname, address1, address2, status, phone);
                if (result == null) {
                    result = new HashMap<String, searchItem>();
                }
                result.put(id, item);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void showAll() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "select * from tbl_Student";
            pre = con.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                int stt = rs.getInt("status");
                StatusDAO dao = new StatusDAO();
                String status = dao.getStatusDescription(stt);
                String id = rs.getString("id");
                String fullname = getFullname(id);
                String classname = rs.getString("class");
                String address1 = rs.getString("address1");
                String address2 = rs.getString("address2");
                String phone = rs.getString("phone");
                searchItem item = new searchItem(id, fullname, classname, address1, address2, status, phone);
                if (result == null) {
                    result = new HashMap<String, searchItem>();
                }
                result.put(id, item);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertStudent(StudentDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = DBUtils.makeConnection();
            String sql = "insert into tbl_Student values (?,?,?,?,?,?,?,?,?,?)";
            pre = con.prepareStatement(sql);
            pre.setString(1, dto.getId());
            pre.setString(2, dto.getFirstname());
            pre.setString(3, dto.getMiddlename());
            pre.setString(4, dto.getLastname());
            pre.setString(5, dto.getClassname());
            pre.setInt(6, dto.getStatus());
            pre.setString(7, dto.getAddress1());
            pre.setString(8, dto.getAddress2());
            pre.setString(9, dto.getPhone());
            pre.setBoolean(10, dto.isSex());
            int row = pre.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateStudent(String id, String classname, String status, String address1, String address2, String phone) throws NamingException, SQLException {
        StatusDAO statusDao = new StatusDAO();
        int stt = statusDao.getStatus(status);
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Update tbl_Student Set class = ?, status = ?, address1 = ?, address2 = ?, phone = ? Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, classname);
                stm.setInt(2, stt);
                stm.setString(3, address1);
                stm.setString(4, address2);
                stm.setString(5, phone);
                stm.setString(6, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "delete from tbl_Student where id=?";
                pre = con.prepareStatement(sql);
                pre.setString(1, id);
                int row = pre.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
