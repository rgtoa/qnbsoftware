
package software1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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
            ps = con.prepareStatement("SELECT password FROM users WHERE username=?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                output = Crypto.decrypt(rs.getString("password")).equals(pass);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public String getRole(String user) {
        String output = "";
        try {
            ps = con.prepareStatement("SELECT role FROM users WHERE username=?");
            ps.setString(1, user);
            rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getString("role");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public boolean authenticate(String pass) {
        boolean output = false;
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT Password FROM users WHERE username='owner'");
            if (rs.next()) {
                output = Crypto.decrypt(rs.getString("password")).equals(pass);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public ArrayList<Object[]> getPendingTransactions() {
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        try {
            s = con.createStatement();
            rs = s.executeQuery("SELECT * FROM orders WHERE FullyPaid=0");
            while(rs.next()) {
                //format the product & quantity into a string
                String products = "";
                String[] names = rs.getString("ProductNames").split(",");
                String[] qty = rs.getString("ProductQuantities").split(",");
                for (int i = 0; i < names.length; i++) {
                    products = names[i] + " - " + qty[i] + "\n";
                }
                //get the name from customers table
                ps = con.prepareStatement(
                        "SELECT concat(FirstName, ' ', LastName) AS Name, concat(Street,' ',Barangay,' ',City) AS Address FROM customers WHERE CustomerID=?");
                ps.setString(1, rs.getString("CustomerID"));
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                list.add(new Object[] { // OrderID-Item/QTY-CustomerID-Name-Address-Price
                    rs.getLong("OrderID"),
                    products,
                    rs.getString("CustomerID"),
                    rs2.getString("Name"),
                    rs2.getString("Address"),
                    rs.getFloat("TotalPrice"),
                });
                rs2.close();
                ps.close();
            }
            rs.close();
            s.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        Database db;
        db = new Database(
                "com.mysql.cj.jdbc.Driver",
                "root",
                "root",
                "jdbc:mysql://localhost:3306/db_qnb"
        );
        ArrayList<Object[]> list = db.getPendingTransactions();
        for (Object[] n : list) {
            System.out.println(Arrays.toString(n));
        }
    }
}
