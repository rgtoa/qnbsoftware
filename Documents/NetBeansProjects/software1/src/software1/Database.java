
package software1;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                "SELECT * FROM orders WHERE FullyPaid=? " +
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
                String cID = "N/A";
                String name = "N/A";
                String addr = "N/A";
                if (rs.getString("CustomerID") != null) {
                    Object[] customer = getCustomer(rs.getString("CustomerID"));
                    cID = (String) customer[0];
                    name = (String) customer[2] + " " + customer[1];
                    addr = (String) customer[3] + " " + customer[4] + " " + customer[5];
                }
                ArrayList<Object> row = new ArrayList<>();
                row.add(rs.getLong("OrderID"));
                row.add(products);
                row.add(cID);
                row.add(name);
                row.add(addr);
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
            rs.close();
            ps.close();
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
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }
    public ArrayList<Object[]> getCustomers() {
        ArrayList<Object[]> customers = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                customers.add(new Object[] {
                rs.getString("CustomerID"),
                rs.getString("LastName"),
                rs.getString("FirstName"),
                rs.getString("Street"),
                rs.getString("Barangay"),
                rs.getString("City")
                });
            }
            rs.close();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    public Object[] getCustomer(String customerID) {
        Object[] customer = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE CustomerID=?");
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Object[]{
                    rs.getString("CustomerID"),
                    rs.getString("LastName"),
                    rs.getString("FirstName"),
                    rs.getString("Street"),
                    rs.getString("Barangay"),
                    rs.getString("City"),
                    rs.getInt("MobileNumber"),
                };
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    public boolean checkCustomer(String customerID) {
        boolean output = true;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT CustomerID FROM customers WHERE CustomerID=?");
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            output = rs.next();
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    public String addCustomer(String lastName, String firstName, String street, String brgy, String city, Integer mobileNumber) {
        String cID = null;
        try {
            cID = (lastName+firstName).replaceAll(" ", "").toLowerCase();
            int n = 0;
            while (checkCustomer(cID+(n==0?"":n))) n++;
            if (n!=0) cID = cID + n;
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO customers VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, cID);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setString(4, street);
            ps.setString(5, brgy);
            ps.setString(6, city);
            if (mobileNumber == null) ps.setNull(7, java.sql.Types.INTEGER);
            else ps.setInt(7, mobileNumber);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cID;
    }
    public long placeOrder(boolean isWalkIn, String customerID, String productNames, String productQTY, float price, float amountPaid) {
        Long orderID = null;
        try {
            orderID = Long.valueOf(LocalDateTime.now().toString().replaceAll("[^\\d]", "").substring(2,14));
            System.out.println("orderID: " + orderID);
            Date date = Date.valueOf(LocalDate.now());
            System.out.println("date: " + date.toString());
            System.out.println("Product Names: " + productNames);
            System.out.println("Product QTY: " + productQTY);
            System.out.println("Price: " + price);
            System.out.println("customerID: " + customerID);
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, orderID);
            ps.setString(2, customerID);
            ps.setString(3, productNames);
            ps.setString(4, productQTY);
            ps.setFloat(5, price);
            ps.setFloat(6, amountPaid);
            ps.setBoolean(7, price == amountPaid);
            ps.setDate(8, date);
            ps.setDate(9, price == amountPaid ? date : null);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderID;
    }
    public Object[] getOrder(Long orderID) {
        Object[] order = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE orderID=?");
            ps.setLong(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Object[] {
                    rs.getLong("OrderID"),
                    rs.getString("CustomerID"),
                    rs.getString("ProductNames"),
                    rs.getString("ProductQuantities"),
                    rs.getFloat("TotalPrice"),
                    rs.getFloat("AmountPaid"),
                    rs.getBoolean("FullyPaid"),
                    rs.getDate("DateOrdered"),
                    rs.getDate("DatePaid"),
                };
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    public void placeForDelivery(Long orderID) { //initially pending
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO deliveries VALUES(?,?,?,?)");
            ps.setLong(1, orderID);
            ps.setInt(2, 0);
            ps.setString(3, null); // waiting for delivery man
            ps.setDate(4, null);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public float getTotalPrice(Long orderID) {
        float totalPrice = 0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT TotalPrice FROM orders WHERE orderID = ?");
            ps.setLong(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getFloat("TotalPrice");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPrice;
    }
    public void updateOrderToFullyPaid(Long orderID, Float amountPaid) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET AmountPaid=?,FullyPaid=?,DatePaid=? WHERE OrderID=?");
            ps.setFloat(1, amountPaid);
            ps.setBoolean(2, true);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setLong(4, orderID);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Database db = new Database();
        
        db.addCustomer("rib", "rob", "aa", "aa", "aa", null);
        db.addCustomer("rib", "rob", "bb", "bb", "bb", null);
        db.addCustomer("rib", "rob", "cc", "cc", "cc", null);
        db.addCustomer("rib", "rob", "dd", "dd", "dd", null);
        
        
        db.closeConnection();
    }
}
