/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Account;

import Utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.NamingException;
import tbl_Role.RoleDAO;

public class AccountDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = Utils.DBUtils.makeConnection();
            String sql = "select * from tbl_Account where id=? and password=?";
            pre = con.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                int role = rs.getInt("role");
                RoleDAO dao = new RoleDAO();
                if (dao.getRoleDescription(role).equals("staff")) {
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

    public boolean insertNewAccount(AccountDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "Insert Into tbl_Account (id, role) Values (?, ?)";
                pre = con.prepareStatement(sql);
                pre.setString(1, dto.getId());
                pre.setInt(2, dto.getRole());
                int row = pre.executeUpdate();
                if (row > 0) {
                    return true;
                }
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

    public boolean checkId(String id) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "select * from tbl_Account where id=?";
                pre = con.prepareStatement(sql);
                pre.setString(1, id);
                rs = pre.executeQuery();
                if (rs.next()) {
                    return false;
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
        return true;
    }
}
