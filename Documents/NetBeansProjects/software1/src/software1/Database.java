/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String[] getUsernames() throws SQLException {
        s = con.createStatement();
        rs = s.executeQuery("SELECT username FROM users");
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            list.add(rs.getString("username"));
        }
        s.close();
        rs.close();
        con.close();
        return list.toArray(new String[list.size()]);
    }
    public boolean verifyUser(String user, String pass) {
        boolean output = false;
        try {
            ps = con.prepareStatement("SELECT * FROM users WHERE username=?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getString("password").equals(pass);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
}
