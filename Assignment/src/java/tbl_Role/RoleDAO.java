/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbl_Role;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author khoi_
 */
public class RoleDAO implements Serializable {

    public String getRoleDescription(int role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String result = null;
        try {
            con = Utils.DBUtils.makeConnection();
            if (con != null) {
                String sql = "select description from tbl_Role where role=?";
                pre = con.prepareStatement(sql);
                pre.setInt(1, role);
                rs = pre.executeQuery();
                if (rs.next()) {
                    result = rs.getString("description");
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
        return result;
    }

    public int getRole(String description) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            con = Utils.DBUtils.makeConnection();
            if (con != null) {
                String sql = "select role from tbl_Role where description=?";
                pre = con.prepareStatement(sql);
                pre.setString(1, description);
                rs = pre.executeQuery();
                if (rs.next()) {
                    return rs.getInt("role");
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
        return -1;
    }
}
