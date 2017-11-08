/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author khoi_
 */
public class DBUtils implements Serializable {

    static public Connection makeConnection() throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatCtx = (Context) context.lookup("java:comp/env");
        DataSource data = (DataSource) tomcatCtx.lookup("Project");
        Connection con = data.getConnection();
        return con;
    }
}
