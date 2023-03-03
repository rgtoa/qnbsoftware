
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
    public ArrayList<Object[]> getTransactions(int status) { // 0 = pending; 1 = completed
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE FullyPaid=?");
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
                //get the name from customers table
                PreparedStatement ps2 = con.prepareStatement(
                        "SELECT concat(FirstName, ' ', LastName) AS Name, concat(Street,' ',Barangay,' ',City) AS Address FROM customers WHERE CustomerID=?");
                ps2.setString(1, rs.getString("CustomerID"));
                ResultSet rs2 = ps2.executeQuery();
                rs2.next();
                if (status == 0) {
                    list.add(new Object[] { // OrderID-Item/QTY-CustomerID-Name-Address-Price
                        rs.getLong("OrderID"),
                        products,
                        rs.getString("CustomerID"),
                        rs2.getString("Name"),
                        rs2.getString("Address"),
                        rs.getFloat("TotalPrice"),
                    });
                } else {
                    list.add(new Object[] { // OrderID-Item/QTY-CustomerID-Name-Address-AmountPaid-Time/Date
                        rs.getLong("OrderID"),
                        products,
                        rs.getString("CustomerID"),
                        rs2.getString("Name"),
                        rs2.getString("Address"),
                        rs.getFloat("AmountPaid"),
                        rs.getDate("DatePaid")
                    });
                }
                rs2.close();
                ps2.close();
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Object[]> getDeliveries(int status) {
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM orders WHERE FullyPaid=1");
            while(rs.next()) {
                //format the product & quantity into a string
                String products = "";
                String[] names = rs.getString("ProductNames").split(",");
                String[] qty = rs.getString("ProductQuantities").split(",");
                for (int i = 0; i < names.length; i++) {
                    products = names[i] + " - " + qty[i] + "\n";
                }
                //get the name from customers table
                PreparedStatement ps = con.prepareStatement(
                        "SELECT concat(FirstName, ' ', LastName) AS Name, concat(Street,' ',Barangay,' ',City) AS Address FROM customers WHERE CustomerID=?");
                ps.setString(1, rs.getString("CustomerID"));
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                list.add(new Object[] { // OrderID-Item/QTY-CustomerID-Name-Address-Price-Status
                    rs.getLong("OrderID"),
                    products,
                    rs.getString("CustomerID"),
                    rs2.getString("Name"),
                    rs2.getString("Address"),
                    rs.getFloat("AmountPaid"),
                    rs.getDate("DatePaid")
                });
                rs2.close();
                ps.close();
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        Database db;
        db = new Database();
        ArrayList<Object[]> list = db.getTransactions(1);
        for (Object[] n : list) {
            System.out.println(Arrays.toString(n));
        }
        db.closeConnection();
    }
}
