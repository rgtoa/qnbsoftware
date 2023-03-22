
package software1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database {
    
    private static Connection con;
    
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_qnb",
                    "root",
                    "root");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String[] getUsernames() throws SQLException {
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT username FROM users");
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            list.add(rs.getString("username"));
        }
        s.close();
        rs.close();
        return list.toArray(new String[list.size()]);
    }
    public boolean verifyUser(String user, String pass) {
        boolean output = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT password FROM users WHERE username=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = Crypto.decrypt(rs.getString("password")).equals(pass);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public String getRole(String user) {
        String output = "";
        try {
            PreparedStatement ps = con.prepareStatement("SELECT role FROM users WHERE username=?");
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getString("role");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public String getProductName(int id) {
        String output = "";
        try {
            PreparedStatement ps = con.prepareStatement("SELECT ProductName FROM products WHERE ProductID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getString("ProductName");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public float getProductPrice(int id) {
        float output = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT Price FROM products WHERE ProductID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                output = rs.getFloat("Price");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public boolean authenticate(String pass) {
        boolean output = false;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT Password FROM users WHERE username='owner'");
            if (rs.next()) {
                output = Crypto.decrypt(rs.getString("password")).equals(pass);
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public ArrayList<ArrayList<Object>> getTransactions(int status, int orderType) { // [0=pending;1=completed][0=all;1=walkin;2=delivery]
        ArrayList<ArrayList<Object>> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT orders.*, " +
                "concat(customers.FirstName,' ',customers.LastName) AS Name, " +
                "concat(customers.Street,' ',customers.Barangay,' ',customers.City) AS Address " +
                "FROM orders " +
                "INNER JOIN customers ON orders.CustomerID = customers.CustomerID " +
                "WHERE FullyPaid=? " +
                (orderType == 1 ? "AND orders.OrderID NOT IN (SELECT deliveries.OrderID FROM deliveries)" :
                 orderType == 2 ? "AND orders.OrderID IN (SELECT deliveries.OrderID FROM deliveries)" : "")
            );
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                //format the product & quantity into a string
                String products = "";
                String[] names = rs.getString("ProductNames").split(",");
                String[] qty = rs.getString("ProductQuantities").split(",");
                for (int i = 0; i < names.length; i++) {
                    products += names[i] + " - " + qty[i] + "\n";
                }
                ArrayList<Object> row = new ArrayList<>();
                row.add(rs.getLong("OrderID"));
                row.add(products);
                row.add(rs.getString("CustomerID"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Address"));
                if (status == 0) { // OrderID-Item/QTY-CustomerID-Name-Address-Price
                    row.add(rs.getFloat("TotalPrice"));
                } else { // OrderID-Item/QTY-CustomerID-Name-Address-AmountPaid-Time/Date
                    row.add(rs.getFloat("AmountPaid"));
                    row.add(rs.getDate("DatePaid"));
                }
                list.add(row);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<ArrayList<Object>> getDeliveries(int status) { // 0 = pending; 1 = ongoing; 2 = completed
        ArrayList<ArrayList<Object>> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT " +
                "deliveries.DeliveryStatus, deliveries.DeliveryMan, deliveries.DeliveryDate, " +
                "orders.*, " +
                "concat(customers.FirstName,' ',customers.LastName) AS Name, " +
                "concat(customers.Street,' ',customers.Barangay,' ',customers.City) AS Address " +
                "FROM deliveries " +
                "INNER JOIN orders ON deliveries.OrderID = orders.OrderID " +
                "INNER JOIN customers ON orders.CustomerID = customers.CustomerID " +
                "WHERE DeliveryStatus=?"
            );
            ps.setInt(1, status);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                //format the product & quantity into a string
                String products = "";
                String[] names = rs.getString("ProductNames").split(",");
                String[] qty = rs.getString("ProductQuantities").split(",");
                for (int i = 0; i < names.length; i++) {
                    products += names[i] + " - " + qty[i] + "\n";
                }
                ArrayList<Object> row = new ArrayList<>();
                row.add(rs.getLong("OrderID"));
                row.add(products);
                row.add(rs.getString("CustomerID"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Address"));
                row.add(rs.getFloat("TotalPrice"));
                if (status == 2) { // OrderID-Item/QTY-CustomerID-Name-Address-Price-Time/Date
                    row.add(rs.getDate("DeliveryDate"));
                } else { // OrderID-Item/QTY-CustomerID-Name-Address-Price-Status
                    row.add(rs.getDate("DatePaid"));
                    row.add(status == 0 ? "Pending" : "Ongoing");
                }
                list.add(row);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public boolean checkUser(String username) { 
        boolean output = true;
        try { 
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            output = rs.next(); // returns true if user exists
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public boolean addUser(String username, String password, String role) {
        boolean output = true;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(?,?,?)");
            ps.setString(1, username);
            ps.setString(2, Crypto.encrypt(password));
            ps.setString(3, role);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }
    public static void main(String[] args) {
        Database db = new Database();
        db.getTransactions(0, 0);
    }
}
