/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software1;

import java.sql.*;
public class Database {
    static Connection con;
    static Statement s;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public Database(String driver, String user, String pass, String url) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
