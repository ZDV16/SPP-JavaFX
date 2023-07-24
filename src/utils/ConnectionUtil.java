/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author LABKOM-RPL29
 */

public class ConnectionUtil {
    
    Connection conn = null;
    public static String url ="jdbc:mysql://localhost/spp";
    public static String user ="root";
    
    public static java.sql.Connection connection()
    {
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(url,user,"");
            return conn;
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }   
}
