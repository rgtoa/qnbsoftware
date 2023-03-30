
package software1;

import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import glasspanepopup.GlassPanePopup;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.PlainDocument;

public class Main extends javax.swing.JFrame {
    
    private final String role;
    private final String username;
    private boolean isAuth = false;
    private int productNum = 0;
    private ArrayList<Object[]> cart = new ArrayList<>(); //ID-name-qty-price
    private ArrayList<Object[]> customers;
    
    public Main(String role, String username) {
        this.role = role;
        this.username = username;
        initComponents();
        GlassPanePopup.install(this);
        this.setLocationRelativeTo(null);
        System.out.println("owner?"+role.equals("owner"));
        
        if (this.role.equals("delivery")) {
            showCard(pendingdeliver);
            boldCard(deliverbtn);
            return;
        }
        refreshPendingTransact();
        refreshCompleteTransact();
        refreshPendingDelivery();
        refreshCompleteDelivery();
        
        editmodule1.setVisible(false);
        editmodule2.setVisible(false);
        editmodule3.setVisible(false);
        editmodule4.setVisible(false);
        
        populateCustomersBox();
        refreshFormVisibility();
        
        // make form textfield to only accept integers
        PlainDocument doc1 = (PlainDocument) amount1field.getDocument();
        doc1.setDocumentFilter(new MyFloatFilter());
        PlainDocument doc2 = (PlainDocument) amount2field.getDocument();
        doc2.setDocumentFilter(new MyFloatFilter());

        scaleIcons();
        scaleProducts();
        scaleReports();
        
//        databasebtn.setVisible(false);
    }
    private void populateCustomersBox() {
        customerdetails.removeAllItems();
        customerdetails.addItem("- New Customer -");
        
        Database db = new Database();
        customers = db.getCustomers();
        db.closeConnection();
        for (Object[] row : customers) {//ID-LN-FN-St-Brgy-City
            customerdetails.addItem(row[2] + " " + row[1] + " - " + row[3]);
        }
    }
    private void refreshFormVisibility() {
        boolean x = !paymentcheckbox.isSelected() || radiodeliver.isSelected();
        
        jLabel17.setVisible(!x);
        amount1field.setVisible(!x);
        
        jLabel2.setVisible(x);
        customerdetails.setVisible(x);
        jLabel11.setVisible(x);
        jLabel12.setVisible(x);
        jLabel13.setVisible(x);
        jLabel14.setVisible(x);
        jLabel15.setVisible(x);
        jLabel16.setVisible(x);
        fnamefield.setVisible(x);
        lnamefield.setVisible(x);
        housefield.setVisible(x);
        brgyfield.setVisible(x);
        cityfield.setVisible(x);
        mobilefield.setVisible(x);
        jLabel18.setVisible(x);
        amount2field.setVisible(x);
        
        if (x) amount2field.setText(amount1field.getText());
        else amount1field.setText(amount2field.getText());
    }
    private void resetForm() {
        paymentcheckbox.setSelected(true);
        radiowalkin.setSelected(true);
        refreshFormVisibility();
        emptyForm();
        populateCustomersBox();
    }
    private void emptyForm() {
        amount1field.setText("");
        fnamefield.setText("");
        lnamefield.setText("");
        housefield.setText("");
        brgyfield.setText("");
        cityfield.setText("");
        mobilefield.setText("");
        amount2field.setText("");
    }
    private void setFormEnabled(boolean b) {
        fnamefield.setEnabled(b);
        lnamefield.setEnabled(b);
        housefield.setEnabled(b);
        brgyfield.setEnabled(b);
        cityfield.setEnabled(b);
        mobilefield.setEnabled(b);
    }
    
    private void scaleIcons(){     
        ImageIcon icon3 = new ImageIcon("arrow.png");
        Image arrow = icon3.getImage();
        Image imgScale3 = arrow.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(imgScale3);
        jLabel4.setIcon(scaledIcon3);
        jLabel5.setIcon(scaledIcon3);
        jLabel6.setIcon(scaledIcon3);
        jLabel7.setIcon(scaledIcon3);
        
        ImageIcon icon4 = new ImageIcon("acc1.png");
        Image acc = icon4.getImage();
        Image imgScale4 = acc.getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon4 = new ImageIcon(imgScale4);
        jLabel8.setIcon(scaledIcon4);
        
        ImageIcon icon7 = new ImageIcon("leftarrow.png");
        Image la = icon7.getImage();
        Image imgScale7 = la.getScaledInstance(leftarrow.getWidth(), leftarrow.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon7 = new ImageIcon(imgScale7);
        leftarrow.setIcon(scaledIcon7);
        
        ImageIcon icon8 = new ImageIcon("rightarrow.png");
        Image ra = icon8.getImage();
        Image imgScale8 = ra.getScaledInstance(rightarrow.getWidth(), rightarrow.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon8 = new ImageIcon(imgScale8);
        rightarrow.setIcon(scaledIcon8);
    }
    private void scaleProducts() {
        ImageIcon icon;
        String text;
        if (Math.abs(this.productNum) == 0) {
            icon = new ImageIcon("product1.png");
            text = "Round Gallon";
        } else {
            icon = new ImageIcon("product2.png");
            text = "Slim Gallon";
        }
        Image prod = icon.getImage();
        Image imageScale = prod.getScaledInstance(productimg.getWidth(), productimg.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imageScale);
        productimg.setIcon(scaledIcon);
        productname1.setText(text);
    }
    private void scaleReports() {
        ImageIcon icon7 = new ImageIcon("transacicon.png");
        Image prod3 = icon7.getImage();
        Image imageScale7 = prod3.getScaledInstance(transacicon.getWidth(), transacicon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon7 = new ImageIcon(imageScale7);
        transacicon.setIcon(scaledIcon7);
        
        ImageIcon icon8 = new ImageIcon("delivericon.png");
        Image prod4 = icon8.getImage();
        Image imageScale8 = prod4.getScaledInstance(delivericon.getWidth(), delivericon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon8 = new ImageIcon(imageScale8);
        delivericon.setIcon(scaledIcon8);
        
        ImageIcon icon9 = new ImageIcon("financeicon.png");
        Image prod5 = icon9.getImage();
        Image imageScale9 = prod5.getScaledInstance(financeicon.getWidth(), financeicon.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon9 = new ImageIcon(imageScale9);
        financeicon.setIcon(scaledIcon9);
    }
    private void refreshPendingTransact() {
        Database db = new Database();
        populateTable(pendingtransactbl, db.getTransactions(0, jComboBox1.getSelectedIndex()));
        db.closeConnection();
    }
    private void refreshCompleteTransact() {
        Database db = new Database();
        populateTable(completetransactbl, db.getTransactions(1, jComboBox3.getSelectedIndex()));
        db.closeConnection();
    }
    private void refreshPendingDelivery() {
        Database db = new Database();
        populateTable(pendingdelivertbl, db.getDeliveries(jComboBox5.getSelectedIndex()));
        db.closeConnection();
    }
    private void refreshCompleteDelivery() {
        Database db = new Database();
        populateTable(completedelivertbl, db.getDeliveries(2));
        db.closeConnection();
    }
    private void populateTable(javax.swing.JTable table, ArrayList<ArrayList<Object>> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // reset the table
        for (ArrayList<Object> n : list) {
            model.addRow(n.toArray());
        }
    }
    private void showCard(java.awt.Component card) {
        invoices.setVisible(false);
        form.setVisible(false);
        pendingtransac.setVisible(false);
        completetransac.setVisible(false);
        pendingdeliver.setVisible(false);
        completedeliver.setVisible(false);
        authreports.setVisible(false);
        dborders.setVisible(false);
        dbdeliveries.setVisible(false);
        dbcustomers.setVisible(false);
        dborders.setVisible(false);
        dbusers.setVisible(false);
        dbproducts.setVisible(false);
        card.setVisible(true);
    }  
    private void boldCard(java.awt.Component btn) {
        transacbtn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 18));
        invoicebtn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 18));
        deliverbtn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 18));
        authenticbtn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 18));
        databasebtn.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 18));

        btn.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 20));
    }
    private void showMsg(String msg) {
        GlassPanePopup.showPopup(new Message(msg));
    }
    private void refreshDBOrders() {
        Database db = new Database();
        populateTable(dborderstbl, db.getTableList("orders"));
        db.closeConnection();
    }
    private void refreshDBProducts() {
        Database db = new Database();
        populateTable(dbproductstbl, db.getTableList("products"));
        db.closeConnection();
    }
    private void refreshDBDeliveries() {
        Database db = new Database();
        populateTable(dbdeliveriestbl, db.getTableList("deliveries"));
        db.closeConnection();
    }
    private void refreshDBCustomers() {
        Database db = new Database();
        populateTable(dbcustomerstbl, db.getTableList("customers"));
        db.closeConnection();
    }
    private void refreshDBUsers() {
        Database db = new Database();
        populateTable(dbuserstbl, db.getUserList());
        db.closeConnection();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        maintabs = new javax.swing.JPanel();
        qnb = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        invoicetab = new javax.swing.JPanel();
        invoicebtn = new javax.swing.JLabel();
        invoicebtn.setVisible(!this.role.equals("delivery"));
        transactab = new javax.swing.JPanel();
        transacbtn = new javax.swing.JLabel();
        transacbtn.setVisible(!this.role.equals("delivery"));
        delivertab = new javax.swing.JPanel();
        deliverbtn = new javax.swing.JLabel();
        authentictab = new javax.swing.JPanel();
        authenticbtn = new javax.swing.JLabel();
        authenticbtn.setVisible(role.equals("owner"));
        jLabel8 = new javax.swing.JLabel();
        databasebtn = new javax.swing.JLabel();
        authenticbtn.setVisible(role.equals("owner"));
        signout = new javax.swing.JPanel();
        signoutbtn = new javax.swing.JLabel();
        tabcontent = new javax.swing.JPanel();
        invoices = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        productimg = new javax.swing.JLabel();
        rightarrow = new javax.swing.JLabel();
        leftarrow = new javax.swing.JLabel();
        background1 = new software1.Background();
        jPanel12 = new javax.swing.JPanel();
        productdesc1 = new javax.swing.JLabel();
        productname1 = new javax.swing.JLabel();
        productqty = new javax.swing.JSpinner();
        productprice1 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        orderbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        selectOrder = new javax.swing.JButton();
        stockLabel = new javax.swing.JLabel();
        form = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fnamefield = new javax.swing.JTextField();
        lnamefield = new javax.swing.JTextField();
        housefield = new javax.swing.JTextField();
        brgyfield = new javax.swing.JTextField();
        cityfield = new javax.swing.JTextField();
        mobilefield = new javax.swing.JTextField();
        radiowalkin = new javax.swing.JRadioButton();
        radiodeliver = new javax.swing.JRadioButton();
        submitform = new javax.swing.JButton();
        customerdetails = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cancelorderbtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        amount1field = new javax.swing.JTextField();
        paymentcheckbox = new javax.swing.JCheckBox();
        amount2field = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        formsimg1 = new javax.swing.JLabel();
        background2 = new software1.Background();
        pendingtransac = new javax.swing.JPanel();
        completebtn1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pendingtransactbl = new javax.swing.JTable();
        pendingbtn1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        generatebtn = new javax.swing.JButton();
        editmodule1 = new javax.swing.JButton();
        updatebtn1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        background3 = new software1.Background();
        completetransac = new javax.swing.JPanel();
        completebtn2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        completetransactbl = new javax.swing.JTable();
        pendingbtn2 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        generatebtn1 = new javax.swing.JButton();
        editmodule2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        background4 = new software1.Background();
        pendingdeliver = new javax.swing.JPanel();
        pendingbtn3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        pendingdelivertbl = new javax.swing.JTable();
        completebtn3 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        generatebtn2 = new javax.swing.JButton();
        editmodule3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        background5 = new software1.Background();
        updatebtn2 = new javax.swing.JButton();
        completedeliver = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        completedelivertbl = new javax.swing.JTable();
        pendingbtn4 = new javax.swing.JLabel();
        completebtn4 = new javax.swing.JLabel();
        generatebtn3 = new javax.swing.JButton();
        editmodule4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        background6 = new software1.Background();
        authreports = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        genreport4 = new javax.swing.JButton();
        genreport5 = new javax.swing.JButton();
        genreport6 = new javax.swing.JButton();
        transacicon = new javax.swing.JLabel();
        delivericon = new javax.swing.JLabel();
        financeicon = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        background7 = new software1.Background();
        dborders = new javax.swing.JPanel();
        deliveriesbtndb = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        dborderstbl = new javax.swing.JTable();
        ordersbtndb = new javax.swing.JLabel();
        background8 = new software1.Background();
        customersbtndb = new javax.swing.JLabel();
        usersbtndb = new javax.swing.JLabel();
        productsbtndb = new javax.swing.JLabel();
        dbdeliveries = new javax.swing.JPanel();
        deliveriesbtndb1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        dbdeliveriestbl = new javax.swing.JTable();
        ordersbtndb1 = new javax.swing.JLabel();
        background9 = new software1.Background();
        customersbtndb1 = new javax.swing.JLabel();
        usersbtndb1 = new javax.swing.JLabel();
        productsbtndb1 = new javax.swing.JLabel();
        dbcustomers = new javax.swing.JPanel();
        deliveriesbtndb2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        dbcustomerstbl = new javax.swing.JTable();
        ordersbtndb2 = new javax.swing.JLabel();
        background10 = new software1.Background();
        customersbtndb2 = new javax.swing.JLabel();
        usersbtndb2 = new javax.swing.JLabel();
        productsbtndb2 = new javax.swing.JLabel();
        dbusers = new javax.swing.JPanel();
        deliveriesbtndb3 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        dbuserstbl = new javax.swing.JTable();
        ordersbtndb3 = new javax.swing.JLabel();
        background11 = new software1.Background();
        customersbtndb3 = new javax.swing.JLabel();
        usersbtndb3 = new javax.swing.JLabel();
        productsbtndb3 = new javax.swing.JLabel();
        dbproducts = new javax.swing.JPanel();
        deliveriesbtndb4 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        dbproductstbl = new javax.swing.JTable();
        ordersbtndb4 = new javax.swing.JLabel();
        background12 = new software1.Background();
        customersbtndb4 = new javax.swing.JLabel();
        usersbtndb4 = new javax.swing.JLabel();
        productsbtndb4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QNB System");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        maintabs.setBackground(new java.awt.Color(255, 255, 255));
        maintabs.setPreferredSize(new java.awt.Dimension(1250, 75));

        qnb.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(10, 64, 83));
        jLabel1.setText(this.username + " [" + this.role.toUpperCase() + "]");
        jLabel1.setPreferredSize(new java.awt.Dimension(175, 70));

        javax.swing.GroupLayout qnbLayout = new javax.swing.GroupLayout(qnb);
        qnb.setLayout(qnbLayout);
        qnbLayout.setHorizontalGroup(
            qnbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qnbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        qnbLayout.setVerticalGroup(
            qnbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qnbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        maintabs.add(qnb);

        invoicetab.setBackground(new java.awt.Color(255, 255, 255));

        invoicebtn.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 20)); // NOI18N
        invoicebtn.setForeground(new java.awt.Color(10, 64, 83));
        invoicebtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invoicebtn.setText("Invoices");
        invoicebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        invoicebtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        invoicebtn.setPreferredSize(new java.awt.Dimension(175, 70));
        invoicebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                invoicebtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout invoicetabLayout = new javax.swing.GroupLayout(invoicetab);
        invoicetab.setLayout(invoicetabLayout);
        invoicetabLayout.setHorizontalGroup(
            invoicetabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invoicetabLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(invoicebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        invoicetabLayout.setVerticalGroup(
            invoicetabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invoicetabLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(invoicebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        maintabs.add(invoicetab);

        transactab.setBackground(new java.awt.Color(255, 255, 255));

        transacbtn.setFont(new java.awt.Font("Source Sans Pro Light", 0, 18)); // NOI18N
        transacbtn.setForeground(new java.awt.Color(10, 64, 83));
        transacbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        transacbtn.setText("Transactions");
        transacbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transacbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        transacbtn.setPreferredSize(new java.awt.Dimension(175, 70));
        transacbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transacbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transactabLayout = new javax.swing.GroupLayout(transactab);
        transactab.setLayout(transactabLayout);
        transactabLayout.setHorizontalGroup(
            transactabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactabLayout.createSequentialGroup()
                .addComponent(transacbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        transactabLayout.setVerticalGroup(
            transactabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactabLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(transacbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        maintabs.add(transactab);

        delivertab.setBackground(new java.awt.Color(255, 255, 255));

        deliverbtn.setFont(new java.awt.Font("Source Sans Pro Light", 0, 18)); // NOI18N
        deliverbtn.setForeground(new java.awt.Color(10, 64, 83));
        deliverbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliverbtn.setText("Deliveries");
        deliverbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deliverbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deliverbtn.setPreferredSize(new java.awt.Dimension(175, 70));
        deliverbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliverbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout delivertabLayout = new javax.swing.GroupLayout(delivertab);
        delivertab.setLayout(delivertabLayout);
        delivertabLayout.setHorizontalGroup(
            delivertabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delivertabLayout.createSequentialGroup()
                .addComponent(deliverbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        delivertabLayout.setVerticalGroup(
            delivertabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delivertabLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(deliverbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        maintabs.add(delivertab);

        authentictab.setBackground(new java.awt.Color(255, 255, 255));

        authenticbtn.setFont(new java.awt.Font("Source Sans Pro Light", 0, 18)); // NOI18N
        authenticbtn.setForeground(new java.awt.Color(10, 64, 83));
        authenticbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        authenticbtn.setText("Authenticate");
        authenticbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        authenticbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        authenticbtn.setPreferredSize(new java.awt.Dimension(175, 70));
        authenticbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authenticbtnMouseClicked(evt);
            }
        });

        databasebtn.setFont(new java.awt.Font("Source Sans Pro Light", 0, 18)); // NOI18N
        databasebtn.setForeground(new java.awt.Color(10, 64, 83));
        databasebtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        databasebtn.setText("Database");
        databasebtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        databasebtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        databasebtn.setPreferredSize(new java.awt.Dimension(175, 70));
        databasebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                databasebtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout authentictabLayout = new javax.swing.GroupLayout(authentictab);
        authentictab.setLayout(authentictabLayout);
        authentictabLayout.setHorizontalGroup(
            authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authentictabLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(authenticbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(databasebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        authentictabLayout.setVerticalGroup(
            authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authentictabLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(authenticbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(databasebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        maintabs.add(authentictab);

        signout.setBackground(new java.awt.Color(255, 255, 255));

        signoutbtn.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        signoutbtn.setForeground(new java.awt.Color(10, 64, 83));
        signoutbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signoutbtn.setText("Sign out");
        signoutbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signoutbtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        signoutbtn.setPreferredSize(new java.awt.Dimension(175, 70));
        signoutbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signoutbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout signoutLayout = new javax.swing.GroupLayout(signout);
        signout.setLayout(signoutLayout);
        signoutLayout.setHorizontalGroup(
            signoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signoutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signoutbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        signoutLayout.setVerticalGroup(
            signoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(signoutbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        maintabs.add(signout);

        getContentPane().add(maintabs, java.awt.BorderLayout.PAGE_START);

        tabcontent.setBackground(new java.awt.Color(255, 255, 255));
        tabcontent.setPreferredSize(new java.awt.Dimension(1250, 675));
        tabcontent.setLayout(new java.awt.CardLayout());

        invoices.setBackground(new java.awt.Color(255, 255, 255));
        invoices.setPreferredSize(new java.awt.Dimension(1250, 625));
        invoices.setLayout(new javax.swing.BoxLayout(invoices, javax.swing.BoxLayout.LINE_AXIS));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jPanel14ComponentAdded(evt);
            }
        });

        productimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        rightarrow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rightarrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rightarrowMouseClicked(evt);
            }
        });

        leftarrow.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        leftarrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leftarrowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(leftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productimg, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(productimg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(117, 117, 117))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(211, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(rightarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(272, 272, 272))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(leftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(274, 274, 274)))))
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        invoices.add(jPanel14);

        jPanel12.setBackground(new java.awt.Color(229, 229, 229));
        jPanel12.setPreferredSize(new java.awt.Dimension(600, 675));

        productdesc1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        productdesc1.setForeground(new java.awt.Color(10, 64, 83));
        productdesc1.setText("<product description>");
        productdesc1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        productdesc1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        productname1.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 48)); // NOI18N
        productname1.setForeground(new java.awt.Color(10, 64, 83));
        productname1.setText("<product>");

        productqty.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));
        productqty.setMinimumSize(new java.awt.Dimension(50, 22));
        productqty.setPreferredSize(new java.awt.Dimension(50, 22));

        productprice1.setBackground(new java.awt.Color(255, 255, 255));
        productprice1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        productprice1.setForeground(new java.awt.Color(34, 73, 87));
        productprice1.setText("Php 35.00");

        jLabelPrice.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabelPrice.setForeground(new java.awt.Color(10, 64, 83));
        jLabelPrice.setText("Price:");

        orderbtn.setBackground(new java.awt.Color(10, 64, 83));
        orderbtn.setForeground(new java.awt.Color(255, 255, 255));
        orderbtn.setText("View Order");
        orderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderbtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));
        jPanel1.add(jLabel9);

        selectOrder.setText("Select");
        selectOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectOrderActionPerformed(evt);
            }
        });

        stockLabel.setFont(new java.awt.Font("Source Sans Pro Light", 2, 14)); // NOI18N
        stockLabel.setForeground(new java.awt.Color(10, 64, 83));
        stockLabel.setText("In stock");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(productqty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockLabel))
                    .addComponent(productname1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productdesc1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(orderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addComponent(jLabelPrice)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(productprice1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(165, 165, 165))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(productname1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productdesc1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productqty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productprice1)
                    .addComponent(jLabelPrice))
                .addGap(18, 18, 18)
                .addComponent(selectOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(orderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
        );

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        invoices.add(jPanel12);

        tabcontent.add(invoices, "card1");
        invoices.getAccessibleContext().setAccessibleName("Transactions");

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setPreferredSize(new java.awt.Dimension(1250, 625));
        form.setLayout(new javax.swing.BoxLayout(form, javax.swing.BoxLayout.LINE_AXIS));

        jPanel16.setBackground(new java.awt.Color(229, 229, 229));
        jPanel16.setPreferredSize(new java.awt.Dimension(600, 625));

        jLabel11.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(34, 73, 87));
        jLabel11.setText("Last name");

        jLabel12.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(34, 73, 87));
        jLabel12.setText("First name");

        jLabel13.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(34, 73, 87));
        jLabel13.setText("Barangay/Municipality");

        jLabel14.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(34, 73, 87));
        jLabel14.setText("House/Unit no., Street");

        jLabel15.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(34, 73, 87));
        jLabel15.setText("Mobile no.");

        jLabel16.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(34, 73, 87));
        jLabel16.setText("City");

        buttonGroup1.add(radiowalkin);
        radiowalkin.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        radiowalkin.setForeground(new java.awt.Color(34, 73, 87));
        radiowalkin.setSelected(true);
        radiowalkin.setText("for Walk-in");
        radiowalkin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderFormRadioChange(evt);
            }
        });
        radiowalkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiowalkinActionPerformed(evt);
            }
        });

        buttonGroup1.add(radiodeliver);
        radiodeliver.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        radiodeliver.setForeground(new java.awt.Color(34, 73, 87));
        radiodeliver.setText("for Delivery");
        radiodeliver.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                orderFormRadioChange(evt);
            }
        });

        submitform.setBackground(new java.awt.Color(140, 208, 218));
        submitform.setForeground(new java.awt.Color(34, 73, 87));
        submitform.setText("Submit");
        submitform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitformActionPerformed(evt);
            }
        });

        customerdetails.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        customerdetails.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customerdetailsItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 73, 87));
        jLabel2.setText("Existing Customer?");

        cancelorderbtn.setText("Cancel");
        cancelorderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelorderbtnActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(34, 73, 87));
        jLabel17.setText("Amount Paid");

        paymentcheckbox.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        paymentcheckbox.setForeground(new java.awt.Color(34, 73, 87));
        paymentcheckbox.setSelected(true);
        paymentcheckbox.setText("Full Payment");
        paymentcheckbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paymentcheckboxItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(34, 73, 87));
        jLabel18.setText("Amount Paid");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radiowalkin, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radiodeliver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(housefield, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel16))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(115, 115, 115)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mobilefield, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(brgyfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cityfield)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(amount2field, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(cancelorderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(customerdetails, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(0, 160, Short.MAX_VALUE)
                                    .addComponent(submitform, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(paymentcheckbox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(amount1field, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(paymentcheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amount1field, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radiowalkin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radiodeliver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerdetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(5, 5, 5)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(housefield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(brgyfield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cityfield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mobilefield, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amount2field, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelorderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitform, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        form.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        formsimg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(formsimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(formsimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        form.add(jPanel15);

        tabcontent.add(form, "card2");
        form.getAccessibleContext().setAccessibleName("Deliveries");

        pendingtransac.setBackground(new java.awt.Color(255, 255, 255));
        pendingtransac.setPreferredSize(new java.awt.Dimension(1250, 625));

        completebtn1.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        completebtn1.setForeground(new java.awt.Color(10, 64, 83));
        completebtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn1.setText("Completed");
        completebtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        completebtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                completebtn1MouseClicked(evt);
            }
        });

        pendingtransactbl.setBackground(new java.awt.Color(229, 229, 229));
        pendingtransactbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        pendingtransactbl.setForeground(new java.awt.Color(10, 64, 83));
        pendingtransactbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pendingtransactbl.getTableHeader().setReorderingAllowed(false);
        pendingtransactbl.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        pendingtransactbl.setRowHeight(45);
        jScrollPane1.setViewportView(pendingtransactbl);
        pendingtransactbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (pendingtransactbl.getColumnModel().getColumnCount() > 0) {
            pendingtransactbl.getColumnModel().getColumn(0).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            pendingtransactbl.getColumnModel().getColumn(1).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(2).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            pendingtransactbl.getColumnModel().getColumn(3).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(4).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(5).setResizable(false);
            pendingtransactbl.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        pendingbtn1.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        pendingbtn1.setForeground(new java.awt.Color(10, 64, 83));
        pendingbtn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingbtn1.setText("Pending");
        pendingbtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox1.setBackground(new java.awt.Color(10, 64, 83));
        jComboBox1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Orders", "Walk-ins", "Deliveries" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TransactComboBoxItemStateChanged(evt);
            }
        });

        generatebtn.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn.setForeground(new java.awt.Color(34, 73, 87));
        generatebtn.setText("Generate");
        generatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatebtnActionPerformed(evt);
            }
        });

        editmodule1.setBackground(new java.awt.Color(140, 208, 218));
        editmodule1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule1.setForeground(new java.awt.Color(34, 73, 87));
        editmodule1.setText("Edit Module");

        updatebtn1.setBackground(new java.awt.Color(34, 73, 87));
        updatebtn1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        updatebtn1.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn1.setText("Update");
        updatebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background3Layout = new javax.swing.GroupLayout(background3);
        background3.setLayout(background3Layout);
        background3Layout.setHorizontalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background3Layout.setVerticalGroup(
            background3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pendingtransacLayout = new javax.swing.GroupLayout(pendingtransac);
        pendingtransac.setLayout(pendingtransacLayout);
        pendingtransacLayout.setHorizontalGroup(
            pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingtransacLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingtransacLayout.createSequentialGroup()
                        .addComponent(pendingbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(completebtn1))
                    .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGroup(pendingtransacLayout.createSequentialGroup()
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(generatebtn)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(updatebtn1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editmodule1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(85, 85, 85))
            .addComponent(background3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pendingtransacLayout.setVerticalGroup(
            pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingtransacLayout.createSequentialGroup()
                .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingtransacLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(completebtn1)
                            .addComponent(pendingbtn1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingtransacLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatebtn)
                    .addComponent(editmodule1)
                    .addComponent(updatebtn1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(pendingtransac, "card3");
        pendingtransac.getAccessibleContext().setAccessibleName("Authenticate");

        completetransac.setBackground(new java.awt.Color(255, 255, 255));
        completetransac.setPreferredSize(new java.awt.Dimension(1250, 625));

        completebtn2.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        completebtn2.setForeground(new java.awt.Color(10, 64, 83));
        completebtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn2.setText("Completed");
        completebtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        completetransactbl.setBackground(new java.awt.Color(229, 229, 229));
        completetransactbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        completetransactbl.setForeground(new java.awt.Color(10, 64, 83));
        completetransactbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Amount Paid", "Time/Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        completetransactbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        completetransactbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        completetransactbl.setFocusable(false);
        completetransactbl.setGridColor(new java.awt.Color(0, 0, 0));
        completetransactbl.setRequestFocusEnabled(false);
        completetransactbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        completetransactbl.setShowGrid(false);
        completetransactbl.getTableHeader().setReorderingAllowed(false);
        completetransactbl.setUpdateSelectionOnSort(false);
        completetransactbl.setVerifyInputWhenFocusTarget(false);
        completetransactbl.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        completetransactbl.setRowHeight(45);
        jScrollPane3.setViewportView(completetransactbl);
        completetransactbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (completetransactbl.getColumnModel().getColumnCount() > 0) {
            completetransactbl.getColumnModel().getColumn(0).setResizable(false);
            completetransactbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            completetransactbl.getColumnModel().getColumn(1).setResizable(false);
            completetransactbl.getColumnModel().getColumn(1).setPreferredWidth(50);
            completetransactbl.getColumnModel().getColumn(2).setResizable(false);
            completetransactbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            completetransactbl.getColumnModel().getColumn(3).setResizable(false);
            completetransactbl.getColumnModel().getColumn(3).setPreferredWidth(3);
            completetransactbl.getColumnModel().getColumn(4).setResizable(false);
            completetransactbl.getColumnModel().getColumn(4).setPreferredWidth(4);
            completetransactbl.getColumnModel().getColumn(5).setResizable(false);
            completetransactbl.getColumnModel().getColumn(5).setPreferredWidth(50);
            completetransactbl.getColumnModel().getColumn(6).setResizable(false);
            completetransactbl.getColumnModel().getColumn(6).setPreferredWidth(5);
        }

        pendingbtn2.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        pendingbtn2.setForeground(new java.awt.Color(10, 64, 83));
        pendingbtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingbtn2.setText("Pending");
        pendingbtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pendingbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingbtn2MouseClicked(evt);
            }
        });

        jComboBox3.setBackground(new java.awt.Color(10, 64, 83));
        jComboBox3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setMaximumRowCount(3);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Orders", "Walk-ins", "Deliveries" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TransactComboBoxItemStateChanged(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        generatebtn1.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn1.setForeground(new java.awt.Color(34, 73, 87));
        generatebtn1.setText("Generate");
        generatebtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatebtn1ActionPerformed(evt);
            }
        });

        editmodule2.setBackground(new java.awt.Color(140, 208, 218));
        editmodule2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule2.setForeground(new java.awt.Color(34, 73, 87));
        editmodule2.setText("Edit Module");

        javax.swing.GroupLayout background4Layout = new javax.swing.GroupLayout(background4);
        background4.setLayout(background4Layout);
        background4Layout.setHorizontalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background4Layout.setVerticalGroup(
            background4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout completetransacLayout = new javax.swing.GroupLayout(completetransac);
        completetransac.setLayout(completetransacLayout);
        completetransacLayout.setHorizontalGroup(
            completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, completetransacLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completetransacLayout.createSequentialGroup()
                        .addComponent(pendingbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(completebtn2))
                    .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGroup(completetransacLayout.createSequentialGroup()
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(generatebtn1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editmodule2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(85, 85, 85))
            .addComponent(background4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        completetransacLayout.setVerticalGroup(
            completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completetransacLayout.createSequentialGroup()
                .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completetransacLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(completebtn2)
                            .addComponent(pendingbtn2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, completetransacLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatebtn1)
                    .addComponent(editmodule2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(completetransac, "card4");

        pendingdeliver.setBackground(new java.awt.Color(255, 255, 255));
        pendingdeliver.setPreferredSize(new java.awt.Dimension(1250, 625));

        pendingbtn3.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        pendingbtn3.setForeground(new java.awt.Color(10, 64, 83));
        pendingbtn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingbtn3.setText("Pending");
        pendingbtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingbtn3MouseClicked(evt);
            }
        });

        pendingdelivertbl.setBackground(new java.awt.Color(229, 229, 229));
        pendingdelivertbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        pendingdelivertbl.setForeground(new java.awt.Color(10, 64, 83));
        pendingdelivertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Delivery", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pendingdelivertbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        pendingdelivertbl.getTableHeader().setReorderingAllowed(false);
        pendingdelivertbl.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        pendingdelivertbl.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());
        pendingdelivertbl.setRowHeight(45);
        jScrollPane5.setViewportView(pendingdelivertbl);
        if (pendingdelivertbl.getColumnModel().getColumnCount() > 0) {
            pendingdelivertbl.getColumnModel().getColumn(0).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(0).setPreferredWidth(75);
            pendingdelivertbl.getColumnModel().getColumn(1).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(1).setPreferredWidth(200);
            pendingdelivertbl.getColumnModel().getColumn(2).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(3).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(4).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(4).setPreferredWidth(200);
            pendingdelivertbl.getColumnModel().getColumn(5).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(6).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(6).setPreferredWidth(40);
            pendingdelivertbl.getColumnModel().getColumn(7).setResizable(false);
            pendingdelivertbl.getColumnModel().getColumn(7).setPreferredWidth(40);
        }

        completebtn3.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        completebtn3.setForeground(new java.awt.Color(10, 64, 83));
        completebtn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn3.setText("Completed");
        completebtn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        completebtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                completebtn3MouseClicked(evt);
            }
        });

        jComboBox5.setBackground(new java.awt.Color(10, 64, 83));
        jComboBox5.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox5.setMaximumRowCount(3);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "On-going" }));
        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        generatebtn2.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn2.setForeground(new java.awt.Color(10, 64, 83));
        generatebtn2.setText("Generate");
        generatebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatebtn2ActionPerformed(evt);
            }
        });

        editmodule3.setBackground(new java.awt.Color(140, 208, 218));
        editmodule3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule3.setForeground(new java.awt.Color(34, 73, 87));
        editmodule3.setText("Edit Module");

        javax.swing.GroupLayout background5Layout = new javax.swing.GroupLayout(background5);
        background5.setLayout(background5Layout);
        background5Layout.setHorizontalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background5Layout.setVerticalGroup(
            background5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        updatebtn2.setBackground(new java.awt.Color(34, 73, 87));
        updatebtn2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        updatebtn2.setForeground(new java.awt.Color(255, 255, 255));
        updatebtn2.setText("Update");
        updatebtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pendingdeliverLayout = new javax.swing.GroupLayout(pendingdeliver);
        pendingdeliver.setLayout(pendingdeliverLayout);
        pendingdeliverLayout.setHorizontalGroup(
            pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingdeliverLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pendingdeliverLayout.createSequentialGroup()
                        .addComponent(pendingbtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(completebtn3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pendingdeliverLayout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generatebtn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatebtn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editmodule3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
            .addComponent(background5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pendingdeliverLayout.setVerticalGroup(
            pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingdeliverLayout.createSequentialGroup()
                .addGroup(pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingdeliverLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(completebtn3)
                            .addComponent(pendingbtn3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingdeliverLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generatebtn2)
                    .addComponent(editmodule3)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatebtn2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(pendingdeliver, "card5");
        pendingdeliver.getAccessibleContext().setAccessibleName("Invoices");

        completedeliver.setBackground(new java.awt.Color(255, 255, 255));

        completedelivertbl.setBackground(new java.awt.Color(229, 229, 229));
        completedelivertbl.setForeground(new java.awt.Color(10, 64, 83));
        completedelivertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price", "Time/Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        completedelivertbl.getTableHeader().setReorderingAllowed(false);
        completedelivertbl.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        completedelivertbl.setRowHeight(45);
        jScrollPane4.setViewportView(completedelivertbl);
        if (completedelivertbl.getColumnModel().getColumnCount() > 0) {
            completedelivertbl.getColumnModel().getColumn(0).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            completedelivertbl.getColumnModel().getColumn(1).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(2).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            completedelivertbl.getColumnModel().getColumn(3).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(4).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(5).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(5).setPreferredWidth(5);
            completedelivertbl.getColumnModel().getColumn(6).setResizable(false);
            completedelivertbl.getColumnModel().getColumn(6).setPreferredWidth(5);
        }

        pendingbtn4.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        pendingbtn4.setForeground(new java.awt.Color(10, 64, 83));
        pendingbtn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingbtn4.setText("Pending");
        pendingbtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pendingbtn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pendingbtn4MouseClicked(evt);
            }
        });

        completebtn4.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        completebtn4.setForeground(new java.awt.Color(10, 64, 83));
        completebtn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn4.setText("Completed");
        completebtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        generatebtn3.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn3.setForeground(new java.awt.Color(10, 64, 83));
        generatebtn3.setText("Generate");
        generatebtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatebtn3ActionPerformed(evt);
            }
        });

        editmodule4.setBackground(new java.awt.Color(140, 208, 218));
        editmodule4.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule4.setForeground(new java.awt.Color(34, 73, 87));
        editmodule4.setText("Edit Module");

        javax.swing.GroupLayout background6Layout = new javax.swing.GroupLayout(background6);
        background6.setLayout(background6Layout);
        background6Layout.setHorizontalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background6Layout.setVerticalGroup(
            background6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout completedeliverLayout = new javax.swing.GroupLayout(completedeliver);
        completedeliver.setLayout(completedeliverLayout);
        completedeliverLayout.setHorizontalGroup(
            completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completedeliverLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completedeliverLayout.createSequentialGroup()
                        .addComponent(pendingbtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(completebtn4))
                    .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(completedeliverLayout.createSequentialGroup()
                            .addComponent(generatebtn3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editmodule4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)))
                .addGap(85, 85, 85))
            .addComponent(background6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        completedeliverLayout.setVerticalGroup(
            completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completedeliverLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completedeliverLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(completebtn4)
                        .addComponent(pendingbtn4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generatebtn3)
                    .addComponent(editmodule4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(completedeliver, "card6");

        authreports.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(10, 64, 83));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Generate Reports");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        genreport4.setBackground(new java.awt.Color(140, 208, 218));
        genreport4.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 14)); // NOI18N
        genreport4.setForeground(new java.awt.Color(34, 73, 87));
        genreport4.setText("Deliveries");
        genreport4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 208, 218), 1, true));
        genreport4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreport4ActionPerformed(evt);
            }
        });

        genreport5.setBackground(new java.awt.Color(140, 208, 218));
        genreport5.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 14)); // NOI18N
        genreport5.setForeground(new java.awt.Color(34, 73, 87));
        genreport5.setText("Transactions");
        genreport5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 208, 218), 1, true));
        genreport5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreport5ActionPerformed(evt);
            }
        });

        genreport6.setBackground(new java.awt.Color(140, 208, 218));
        genreport6.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 14)); // NOI18N
        genreport6.setForeground(new java.awt.Color(34, 73, 87));
        genreport6.setText("Finances");
        genreport6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(140, 208, 218), 1, true));
        genreport6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreport6ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly", "Yearly" }));

        javax.swing.GroupLayout background7Layout = new javax.swing.GroupLayout(background7);
        background7.setLayout(background7Layout);
        background7Layout.setHorizontalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background7Layout.setVerticalGroup(
            background7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout authreportsLayout = new javax.swing.GroupLayout(authreports);
        authreports.setLayout(authreportsLayout);
        authreportsLayout.setHorizontalGroup(
            authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authreportsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(authreportsLayout.createSequentialGroup()
                        .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(transacicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genreport5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(114, 114, 114)
                        .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(delivericon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genreport4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGap(114, 114, 114)
                        .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(genreport6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(financeicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(authreportsLayout.createSequentialGroup()
                .addGap(587, 587, 587)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(586, Short.MAX_VALUE))
            .addComponent(background7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        authreportsLayout.setVerticalGroup(
            authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authreportsLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(financeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transacicon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delivericon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genreport4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreport5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreport6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(background7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(authreports, "card7");

        dborders.setBackground(new java.awt.Color(255, 255, 255));
        dborders.setPreferredSize(new java.awt.Dimension(1250, 625));

        deliveriesbtndb.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        deliveriesbtndb.setForeground(new java.awt.Color(10, 64, 83));
        deliveriesbtndb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveriesbtndb.setText("Deliveries");
        deliveriesbtndb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deliveriesbtndb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliveriesdbmouseclicked(evt);
            }
        });

        dborderstbl.setBackground(new java.awt.Color(229, 229, 229));
        dborderstbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        dborderstbl.setForeground(new java.awt.Color(10, 64, 83));
        dborderstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Customer ID", "ProductNames", "ProductQTY", "TotalPrice", "AmountPaid", "FullyPaid", "DateOrdered", "DatePaid"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dborderstbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dborderstbl.setColumnSelectionAllowed(true);
        dborderstbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dborderstbl.setFocusable(false);
        dborderstbl.setGridColor(new java.awt.Color(0, 0, 0));
        dborderstbl.setRequestFocusEnabled(false);
        dborderstbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        dborderstbl.setShowGrid(false);
        dborderstbl.getTableHeader().setReorderingAllowed(false);
        dborderstbl.setUpdateSelectionOnSort(false);
        dborderstbl.setVerifyInputWhenFocusTarget(false);
        dborderstbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dborderstblMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(dborderstbl);
        dborderstbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (dborderstbl.getColumnModel().getColumnCount() > 0) {
            dborderstbl.getColumnModel().getColumn(0).setResizable(false);
            dborderstbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            dborderstbl.getColumnModel().getColumn(1).setResizable(false);
            dborderstbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            dborderstbl.getColumnModel().getColumn(2).setResizable(false);
            dborderstbl.getColumnModel().getColumn(2).setPreferredWidth(50);
            dborderstbl.getColumnModel().getColumn(3).setResizable(false);
            dborderstbl.getColumnModel().getColumn(3).setPreferredWidth(3);
            dborderstbl.getColumnModel().getColumn(4).setResizable(false);
            dborderstbl.getColumnModel().getColumn(4).setPreferredWidth(4);
            dborderstbl.getColumnModel().getColumn(5).setResizable(false);
            dborderstbl.getColumnModel().getColumn(5).setPreferredWidth(50);
            dborderstbl.getColumnModel().getColumn(6).setResizable(false);
            dborderstbl.getColumnModel().getColumn(6).setPreferredWidth(5);
            dborderstbl.getColumnModel().getColumn(7).setResizable(false);
            dborderstbl.getColumnModel().getColumn(7).setPreferredWidth(5);
            dborderstbl.getColumnModel().getColumn(8).setResizable(false);
            dborderstbl.getColumnModel().getColumn(8).setPreferredWidth(5);
        }

        ordersbtndb.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 1, 36)); // NOI18N
        ordersbtndb.setForeground(new java.awt.Color(10, 64, 83));
        ordersbtndb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersbtndb.setText("Orders");
        ordersbtndb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout background8Layout = new javax.swing.GroupLayout(background8);
        background8.setLayout(background8Layout);
        background8Layout.setHorizontalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background8Layout.setVerticalGroup(
            background8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        customersbtndb.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        customersbtndb.setForeground(new java.awt.Color(10, 64, 83));
        customersbtndb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customersbtndb.setText("Customers");
        customersbtndb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customersbtndb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersdbmouseclicked(evt);
            }
        });

        usersbtndb.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        usersbtndb.setForeground(new java.awt.Color(10, 64, 83));
        usersbtndb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersbtndb.setText("Users");
        usersbtndb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersbtndb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersdbmouseclicked(evt);
            }
        });

        productsbtndb.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        productsbtndb.setForeground(new java.awt.Color(10, 64, 83));
        productsbtndb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsbtndb.setText("Products");
        productsbtndb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productsbtndb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout dbordersLayout = new javax.swing.GroupLayout(dborders);
        dborders.setLayout(dbordersLayout);
        dbordersLayout.setHorizontalGroup(
            dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dbordersLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbordersLayout.createSequentialGroup()
                        .addComponent(ordersbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(deliveriesbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(customersbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(usersbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(productsbtndb)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dbordersLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        dbordersLayout.setVerticalGroup(
            dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbordersLayout.createSequentialGroup()
                .addGroup(dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbordersLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(deliveriesbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbordersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbordersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(customersbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usersbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productsbtndb, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ordersbtndb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(dborders, "card8");

        dbdeliveries.setBackground(new java.awt.Color(255, 255, 255));
        dbdeliveries.setPreferredSize(new java.awt.Dimension(1250, 625));

        deliveriesbtndb1.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        deliveriesbtndb1.setForeground(new java.awt.Color(10, 64, 83));
        deliveriesbtndb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveriesbtndb1.setText("Deliveries");
        deliveriesbtndb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        dbdeliveriestbl.setBackground(new java.awt.Color(229, 229, 229));
        dbdeliveriestbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        dbdeliveriestbl.setForeground(new java.awt.Color(10, 64, 83));
        dbdeliveriestbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "DeliveryStatus", "DeliveryMan", "DeliveryDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dbdeliveriestbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dbdeliveriestbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dbdeliveriestbl.setFocusable(false);
        dbdeliveriestbl.setGridColor(new java.awt.Color(0, 0, 0));
        dbdeliveriestbl.setRequestFocusEnabled(false);
        dbdeliveriestbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        dbdeliveriestbl.setShowGrid(false);
        dbdeliveriestbl.getTableHeader().setReorderingAllowed(false);
        dbdeliveriestbl.setUpdateSelectionOnSort(false);
        dbdeliveriestbl.setVerifyInputWhenFocusTarget(false);
        jScrollPane7.setViewportView(dbdeliveriestbl);
        dbdeliveriestbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (dbdeliveriestbl.getColumnModel().getColumnCount() > 0) {
            dbdeliveriestbl.getColumnModel().getColumn(0).setResizable(false);
            dbdeliveriestbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            dbdeliveriestbl.getColumnModel().getColumn(1).setResizable(false);
            dbdeliveriestbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            dbdeliveriestbl.getColumnModel().getColumn(2).setResizable(false);
            dbdeliveriestbl.getColumnModel().getColumn(2).setPreferredWidth(50);
            dbdeliveriestbl.getColumnModel().getColumn(3).setResizable(false);
        }

        ordersbtndb1.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        ordersbtndb1.setForeground(new java.awt.Color(10, 64, 83));
        ordersbtndb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersbtndb1.setText("Orders");
        ordersbtndb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersbtndb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout background9Layout = new javax.swing.GroupLayout(background9);
        background9.setLayout(background9Layout);
        background9Layout.setHorizontalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background9Layout.setVerticalGroup(
            background9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        customersbtndb1.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        customersbtndb1.setForeground(new java.awt.Color(10, 64, 83));
        customersbtndb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customersbtndb1.setText("Customers");
        customersbtndb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customersbtndb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersdbmouseclicked(evt);
            }
        });

        usersbtndb1.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        usersbtndb1.setForeground(new java.awt.Color(10, 64, 83));
        usersbtndb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersbtndb1.setText("Users");
        usersbtndb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersbtndb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersdbmouseclicked(evt);
            }
        });

        productsbtndb1.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        productsbtndb1.setForeground(new java.awt.Color(10, 64, 83));
        productsbtndb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsbtndb1.setText("Products");
        productsbtndb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productsbtndb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout dbdeliveriesLayout = new javax.swing.GroupLayout(dbdeliveries);
        dbdeliveries.setLayout(dbdeliveriesLayout);
        dbdeliveriesLayout.setHorizontalGroup(
            dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dbdeliveriesLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbdeliveriesLayout.createSequentialGroup()
                        .addComponent(ordersbtndb1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(deliveriesbtndb1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(customersbtndb1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(usersbtndb1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(productsbtndb1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dbdeliveriesLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        dbdeliveriesLayout.setVerticalGroup(
            dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbdeliveriesLayout.createSequentialGroup()
                .addGroup(dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbdeliveriesLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deliveriesbtndb1)
                            .addComponent(ordersbtndb1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbdeliveriesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbdeliveriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersbtndb1)
                            .addComponent(usersbtndb1)
                            .addComponent(productsbtndb1))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(dbdeliveries, "card9");

        dbcustomers.setBackground(new java.awt.Color(255, 255, 255));
        dbcustomers.setPreferredSize(new java.awt.Dimension(1250, 625));

        deliveriesbtndb2.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        deliveriesbtndb2.setForeground(new java.awt.Color(10, 64, 83));
        deliveriesbtndb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveriesbtndb2.setText("Deliveries");
        deliveriesbtndb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deliveriesbtndb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliveriesdbmouseclicked(evt);
            }
        });

        dbcustomerstbl.setBackground(new java.awt.Color(229, 229, 229));
        dbcustomerstbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        dbcustomerstbl.setForeground(new java.awt.Color(10, 64, 83));
        dbcustomerstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "LastName", "FirstName", "Street", "Barangay", "City", "MobileNumber"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dbcustomerstbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dbcustomerstbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dbcustomerstbl.setFocusable(false);
        dbcustomerstbl.setGridColor(new java.awt.Color(0, 0, 0));
        dbcustomerstbl.setRequestFocusEnabled(false);
        dbcustomerstbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        dbcustomerstbl.setShowGrid(false);
        dbcustomerstbl.getTableHeader().setReorderingAllowed(false);
        dbcustomerstbl.setUpdateSelectionOnSort(false);
        dbcustomerstbl.setVerifyInputWhenFocusTarget(false);
        jScrollPane8.setViewportView(dbcustomerstbl);
        dbcustomerstbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (dbcustomerstbl.getColumnModel().getColumnCount() > 0) {
            dbcustomerstbl.getColumnModel().getColumn(0).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(0).setPreferredWidth(5);
            dbcustomerstbl.getColumnModel().getColumn(1).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(1).setPreferredWidth(5);
            dbcustomerstbl.getColumnModel().getColumn(2).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(2).setPreferredWidth(5);
            dbcustomerstbl.getColumnModel().getColumn(3).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(3).setPreferredWidth(5);
            dbcustomerstbl.getColumnModel().getColumn(4).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(4).setPreferredWidth(5);
            dbcustomerstbl.getColumnModel().getColumn(5).setResizable(false);
            dbcustomerstbl.getColumnModel().getColumn(5).setPreferredWidth(10);
            dbcustomerstbl.getColumnModel().getColumn(6).setResizable(false);
        }

        ordersbtndb2.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        ordersbtndb2.setForeground(new java.awt.Color(10, 64, 83));
        ordersbtndb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersbtndb2.setText("Orders");
        ordersbtndb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersbtndb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout background10Layout = new javax.swing.GroupLayout(background10);
        background10.setLayout(background10Layout);
        background10Layout.setHorizontalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background10Layout.setVerticalGroup(
            background10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        customersbtndb2.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        customersbtndb2.setForeground(new java.awt.Color(10, 64, 83));
        customersbtndb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customersbtndb2.setText("Customers");
        customersbtndb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        usersbtndb2.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        usersbtndb2.setForeground(new java.awt.Color(10, 64, 83));
        usersbtndb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersbtndb2.setText("Users");
        usersbtndb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersbtndb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersdbmouseclicked(evt);
            }
        });

        productsbtndb2.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        productsbtndb2.setForeground(new java.awt.Color(10, 64, 83));
        productsbtndb2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsbtndb2.setText("Products");
        productsbtndb2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productsbtndb2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout dbcustomersLayout = new javax.swing.GroupLayout(dbcustomers);
        dbcustomers.setLayout(dbcustomersLayout);
        dbcustomersLayout.setHorizontalGroup(
            dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dbcustomersLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbcustomersLayout.createSequentialGroup()
                        .addComponent(ordersbtndb2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(deliveriesbtndb2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(customersbtndb2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(usersbtndb2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(productsbtndb2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dbcustomersLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        dbcustomersLayout.setVerticalGroup(
            dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbcustomersLayout.createSequentialGroup()
                .addGroup(dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbcustomersLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deliveriesbtndb2)
                            .addComponent(ordersbtndb2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbcustomersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbcustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersbtndb2)
                            .addComponent(usersbtndb2)
                            .addComponent(productsbtndb2))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(dbcustomers, "card10");

        dbusers.setBackground(new java.awt.Color(255, 255, 255));
        dbusers.setPreferredSize(new java.awt.Dimension(1250, 625));

        deliveriesbtndb3.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        deliveriesbtndb3.setForeground(new java.awt.Color(10, 64, 83));
        deliveriesbtndb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveriesbtndb3.setText("Deliveries");
        deliveriesbtndb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deliveriesbtndb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliveriesdbmouseclicked(evt);
            }
        });

        dbuserstbl.setBackground(new java.awt.Color(229, 229, 229));
        dbuserstbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        dbuserstbl.setForeground(new java.awt.Color(10, 64, 83));
        dbuserstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Password", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dbuserstbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dbuserstbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dbuserstbl.setFocusable(false);
        dbuserstbl.setGridColor(new java.awt.Color(0, 0, 0));
        dbuserstbl.setRequestFocusEnabled(false);
        dbuserstbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        dbuserstbl.setShowGrid(false);
        dbuserstbl.getTableHeader().setReorderingAllowed(false);
        dbuserstbl.setUpdateSelectionOnSort(false);
        dbuserstbl.setVerifyInputWhenFocusTarget(false);
        jScrollPane9.setViewportView(dbuserstbl);
        dbuserstbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (dbuserstbl.getColumnModel().getColumnCount() > 0) {
            dbuserstbl.getColumnModel().getColumn(0).setResizable(false);
            dbuserstbl.getColumnModel().getColumn(1).setResizable(false);
            dbuserstbl.getColumnModel().getColumn(2).setResizable(false);
        }

        ordersbtndb3.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        ordersbtndb3.setForeground(new java.awt.Color(10, 64, 83));
        ordersbtndb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersbtndb3.setText("Orders");
        ordersbtndb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersbtndb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout background11Layout = new javax.swing.GroupLayout(background11);
        background11.setLayout(background11Layout);
        background11Layout.setHorizontalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background11Layout.setVerticalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        customersbtndb3.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        customersbtndb3.setForeground(new java.awt.Color(10, 64, 83));
        customersbtndb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customersbtndb3.setText("Customers");
        customersbtndb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customersbtndb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersdbmouseclicked(evt);
            }
        });

        usersbtndb3.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        usersbtndb3.setForeground(new java.awt.Color(10, 64, 83));
        usersbtndb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersbtndb3.setText("Users");
        usersbtndb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        productsbtndb3.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        productsbtndb3.setForeground(new java.awt.Color(10, 64, 83));
        productsbtndb3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsbtndb3.setText("Products");
        productsbtndb3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        productsbtndb3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productsdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout dbusersLayout = new javax.swing.GroupLayout(dbusers);
        dbusers.setLayout(dbusersLayout);
        dbusersLayout.setHorizontalGroup(
            dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dbusersLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbusersLayout.createSequentialGroup()
                        .addComponent(ordersbtndb3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(deliveriesbtndb3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(customersbtndb3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(usersbtndb3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(productsbtndb3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dbusersLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        dbusersLayout.setVerticalGroup(
            dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbusersLayout.createSequentialGroup()
                .addGroup(dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbusersLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deliveriesbtndb3)
                            .addComponent(ordersbtndb3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbusersLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbusersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersbtndb3)
                            .addComponent(usersbtndb3)
                            .addComponent(productsbtndb3))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(dbusers, "card11");

        dbproducts.setBackground(new java.awt.Color(255, 255, 255));
        dbproducts.setPreferredSize(new java.awt.Dimension(1250, 625));

        deliveriesbtndb4.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        deliveriesbtndb4.setForeground(new java.awt.Color(10, 64, 83));
        deliveriesbtndb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveriesbtndb4.setText("Deliveries");
        deliveriesbtndb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deliveriesbtndb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deliveriesdbmouseclicked(evt);
            }
        });

        dbproductstbl.setBackground(new java.awt.Color(229, 229, 229));
        dbproductstbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        dbproductstbl.setForeground(new java.awt.Color(10, 64, 83));
        dbproductstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "ProductName", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dbproductstbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        dbproductstbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dbproductstbl.setFocusable(false);
        dbproductstbl.setGridColor(new java.awt.Color(0, 0, 0));
        dbproductstbl.setRequestFocusEnabled(false);
        dbproductstbl.setSelectionBackground(new java.awt.Color(255, 255, 255));
        dbproductstbl.setShowGrid(false);
        dbproductstbl.getTableHeader().setReorderingAllowed(false);
        dbproductstbl.setUpdateSelectionOnSort(false);
        dbproductstbl.setVerifyInputWhenFocusTarget(false);
        jScrollPane10.setViewportView(dbproductstbl);
        dbproductstbl.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (dbproductstbl.getColumnModel().getColumnCount() > 0) {
            dbproductstbl.getColumnModel().getColumn(0).setResizable(false);
            dbproductstbl.getColumnModel().getColumn(1).setResizable(false);
            dbproductstbl.getColumnModel().getColumn(2).setResizable(false);
        }

        ordersbtndb4.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        ordersbtndb4.setForeground(new java.awt.Color(10, 64, 83));
        ordersbtndb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersbtndb4.setText("Orders");
        ordersbtndb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersbtndb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersdbmouseclicked(evt);
            }
        });

        javax.swing.GroupLayout background12Layout = new javax.swing.GroupLayout(background12);
        background12.setLayout(background12Layout);
        background12Layout.setHorizontalGroup(
            background12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        background12Layout.setVerticalGroup(
            background12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        customersbtndb4.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        customersbtndb4.setForeground(new java.awt.Color(10, 64, 83));
        customersbtndb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        customersbtndb4.setText("Customers");
        customersbtndb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customersbtndb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersdbmouseclicked(evt);
            }
        });

        usersbtndb4.setFont(new java.awt.Font("Source Sans Pro ExtraLight", 0, 36)); // NOI18N
        usersbtndb4.setForeground(new java.awt.Color(10, 64, 83));
        usersbtndb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usersbtndb4.setText("Users");
        usersbtndb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usersbtndb4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersdbmouseclicked(evt);
            }
        });

        productsbtndb4.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 36)); // NOI18N
        productsbtndb4.setForeground(new java.awt.Color(10, 64, 83));
        productsbtndb4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productsbtndb4.setText("Products");
        productsbtndb4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout dbproductsLayout = new javax.swing.GroupLayout(dbproducts);
        dbproducts.setLayout(dbproductsLayout);
        dbproductsLayout.setHorizontalGroup(
            dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(dbproductsLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbproductsLayout.createSequentialGroup()
                        .addComponent(ordersbtndb4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(deliveriesbtndb4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(customersbtndb4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(usersbtndb4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(productsbtndb4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(dbproductsLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                        .addGap(85, 85, 85))))
        );
        dbproductsLayout.setVerticalGroup(
            dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dbproductsLayout.createSequentialGroup()
                .addGroup(dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dbproductsLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deliveriesbtndb4)
                            .addComponent(ordersbtndb4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dbproductsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dbproductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersbtndb4)
                            .addComponent(usersbtndb4)
                            .addComponent(productsbtndb4))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(background12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabcontent.add(dbproducts, "card12");

        getContentPane().add(tabcontent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void orderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderbtnActionPerformed
        if (cart.isEmpty()) {
            showMsg("No Products Added");
            return;
        }
        OrderPopup obj = new OrderPopup(cart);
        obj.confirm((ActionEvent ae) -> {
            GlassPanePopup.closePopupLast();
            if (cart.isEmpty()) {
                showMsg("No Products Added");
                return;
            }
            showCard(form);
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_orderbtnActionPerformed

    private void transacbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transacbtnMouseClicked
        showCard(pendingtransac);
        boldCard(transacbtn);
        refreshPendingTransact();
        refreshCompleteTransact();
    }//GEN-LAST:event_transacbtnMouseClicked
    private void deliverbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliverbtnMouseClicked
        showCard(pendingdeliver);
        boldCard(deliverbtn);
        refreshPendingDelivery();
        refreshCompleteDelivery();
    }//GEN-LAST:event_deliverbtnMouseClicked

    private void authenticbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authenticbtnMouseClicked
        if(isAuth) {
            showCard(authreports);
            boldCard(authenticbtn);
            return;
        }
        AuthPopup obj = new AuthPopup();
        obj.authenticate((ActionEvent ae) -> {
            Database db = new Database();
            boolean auth = false;
            try {
                char[] pass = obj.getPass();
                auth = db.authenticate(String.valueOf(pass));
            } finally {
                db.closeConnection();
            }
            if (!auth) {
                GlassPanePopup.closePopupLast();
                showMsg("Wrong Password");
                return;
            }
            isAuth = true;
            editmodule1.setVisible(true);
            editmodule2.setVisible(true);
            editmodule3.setVisible(true);
            editmodule4.setVisible(true);
            databasebtn.setVisible(true);
            
            showCard(authreports);
            boldCard(authenticbtn);
            GlassPanePopup.closePopupLast();
        });
        GlassPanePopup.showPopup(obj);     
    }//GEN-LAST:event_authenticbtnMouseClicked

    private void genreport5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreport5ActionPerformed
        String range = (String) jComboBox2.getSelectedItem();
        try {
            PDFGenerator.createReport("Transactions Report", range,
                    "pendingwalkin-order",
                    "pendingdelivery-order",
                    "completewalkin-order",
                    "completedelivery-order"
            );
            showMsg("Generated " + range + " Transactions Report");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_genreport5ActionPerformed

    private void genreport6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreport6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreport6ActionPerformed

    private void submitformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitformActionPerformed
        // get products and price
        String productNames = "";
        String productQTY = "";
        float price = 0;
        for (Object[] row : cart) {//ID-name-qty-price
            productNames += row[1] + ",";
            productQTY += row[2] + ",";
            price += (float) row[3];
        }
        // get amount paid
        float amountPaid;
        if (!paymentcheckbox.isSelected() || radiodeliver.isSelected()) {
            if (amount2field.getText().equals("")) amount2field.setText("0");
            amountPaid = Float.parseFloat(amount2field.getText());
        } else {
            if (amount1field.getText().equals("")) amount1field.setText("0");
            amountPaid = Float.parseFloat(amount1field.getText());
        }
        //check if fully paid matches amount given if full payment is selected
        if (paymentcheckbox.isSelected() && amountPaid < price) {
            showMsg("Not Enough Amount Paid");
            return;
        }
        // prepare now the final strings
        final String prodNames = productNames.substring(0, productNames.length()-1);
        final String prodQTY = productQTY.substring(0, productQTY.length()-1);
        final float totPrice = price;
        ConfirmOrder obj = new ConfirmOrder();
        obj.confirmOrder((ActionEvent ae) -> {
            boolean isWalkIn = paymentcheckbox.isSelected() && radiowalkin.isSelected();
            String cID = null;
            Database db = new Database();
            //check if new customer && not a fully paid walk in; insert into customer table if new
            if (customerdetails.getSelectedIndex() == 0 && !isWalkIn) {
                //check details
                if (fnamefield.getText().trim().equals("") ||
                        lnamefield.getText().trim().equals("") ||
                        housefield.getText().trim().equals("") ||
                        brgyfield.getText().trim().equals("") ||
                        cityfield.getText().trim().equals("")) {
                    showMsg("Incomplete Details");
                    return;
                }
                //add new customer
                Integer mobileNumber = mobilefield.getText().equals("") ? null : Integer.valueOf(mobilefield.getText());
                cID = db.addCustomer(lnamefield.getText().trim(),
                        fnamefield.getText().trim(),
                        housefield.getText().trim(),
                        brgyfield.getText().trim(),
                        cityfield.getText().trim(),
                        mobileNumber);
                if (cID == null) {// AN ERR OCCURRED
                    showMsg("error while adding customer");
                    return;
                }
            } else if (!isWalkIn) {
                //existing customer
                cID = (String) customers.get(customerdetails.getSelectedIndex()-1)[0];
            }
            //place now the order also check if for delivery
            if (radiodeliver.isSelected()) {
                db.placeForDelivery(db.placeOrder(isWalkIn, cID, prodNames, prodQTY, totPrice, amountPaid));
            } else {
                db.placeOrder(isWalkIn, cID, prodNames, prodQTY, totPrice, amountPaid);
            }
            db.closeConnection();
            GlassPanePopup.closePopupLast();
            resetForm();
            cart.clear();
            refreshPendingTransact();
            showCard(pendingtransac);
            boldCard(transacbtn);
        });
        GlassPanePopup.showPopup(obj); 
        
    }//GEN-LAST:event_submitformActionPerformed

    private void signoutbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signoutbtnMouseClicked
        Login login = new Login();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_signoutbtnMouseClicked

    private void completebtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completebtn1MouseClicked
        pendingtransac.setVisible(false);
        completetransac.setVisible(true);
        refreshCompleteTransact();
    }//GEN-LAST:event_completebtn1MouseClicked

    private void pendingbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingbtn2MouseClicked
        completetransac.setVisible(false);
        pendingtransac.setVisible(true);
        refreshPendingTransact();
    }//GEN-LAST:event_pendingbtn2MouseClicked

    private void pendingbtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingbtn4MouseClicked
        completedeliver.setVisible(false);
        pendingdeliver.setVisible(true);
        refreshPendingDelivery();
    }//GEN-LAST:event_pendingbtn4MouseClicked

    private void completebtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completebtn3MouseClicked
        pendingdeliver.setVisible(false);
        completedeliver.setVisible(true);
        refreshCompleteDelivery();
    }//GEN-LAST:event_completebtn3MouseClicked

    private void updatebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn1ActionPerformed
        int row = pendingtransactbl.getSelectedRow();
        if (row == -1) return;
        TableModel model = pendingtransactbl.getModel();
        TransacEditPopup obj = new TransacEditPopup( new String[] {
            ""+model.getValueAt(row, 0),
            ""+model.getValueAt(row, 1),
            ""+model.getValueAt(row, 2),
            ""+model.getValueAt(row, 3),
            ""+model.getValueAt(row, 4),
            ""+model.getValueAt(row, 5)
        }, isAuth );
        obj.editSave(event -> {
            GlassPanePopup.closePopupAll();
            refreshPendingTransact();
        });
        obj.confirm(event -> {
            pendingtransac.setVisible(false);
            completetransac.setVisible(true);
            GlassPanePopup.closePopupAll();
            showMsg("Transaction Completed");
            refreshCompleteTransact();
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_updatebtn1ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        Database db = new Database();
        populateTable(pendingdelivertbl, db.getDeliveries(jComboBox5.getSelectedIndex()));
        db.closeConnection();
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void TransactComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TransactComboBoxItemStateChanged
        if (evt.getStateChange() == 2) return;
        Database db = new Database();
        int status = evt.getSource() == jComboBox1 ? 0 : 1;
        populateTable(status == 0 ? pendingtransactbl : completetransactbl, db.getTransactions(status, ((JComboBox) evt.getSource()).getSelectedIndex()));
        db.closeConnection();
    }//GEN-LAST:event_TransactComboBoxItemStateChanged

    private void rightarrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightarrowMouseClicked
        productNum = ++productNum % 2;
        scaleProducts();
    }//GEN-LAST:event_rightarrowMouseClicked

    private void leftarrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftarrowMouseClicked
        productNum = --productNum % 2;
        scaleProducts();
    }//GEN-LAST:event_leftarrowMouseClicked

    private void selectOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectOrderActionPerformed
        int prodID = this.productNum + 1;
        int qty = (int) productqty.getValue();
        addToCart(prodID, qty);
        showMsg("Successfully Added Product");
        System.out.println("Added Product:\n" + Arrays.toString(cart.get(cart.size()-1)));
        productqty.setValue(1); // reset value to 1
    }//GEN-LAST:event_selectOrderActionPerformed
    private void addToCart(int prodID, int qty) {
        Database db = new Database();
        for (Object[] o : cart) {
            if(o[0].equals(prodID)) {
                Float price = (Float) o[3] / (int) o[2];
                o[2] = qty + (int) o[2];
                o[3] = price * (int) o[2];
                return;
            }
        }
        
        cart.add(new Object[] {
            prodID,
            db.getProductName(prodID),
            qty,
            db.getProductPrice(prodID) * qty
        });
        
    }
    private void invoicebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoicebtnMouseClicked
        showCard(invoices);
        boldCard(invoicebtn);
    }//GEN-LAST:event_invoicebtnMouseClicked
    private void cancelorderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelorderbtnActionPerformed
        CancelOrder obj = new CancelOrder();
        obj.cancelOrder((ActionEvent ae) -> {
            GlassPanePopup.closePopupLast();
            cart.clear();
            resetForm();
            form.setVisible(false);
            invoices.setVisible(true);
        });
        GlassPanePopup.showPopup(obj);  
    }//GEN-LAST:event_cancelorderbtnActionPerformed

    private void jPanel14ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel14ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel14ComponentAdded

    private void radiowalkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiowalkinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radiowalkinActionPerformed

    private void paymentcheckboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paymentcheckboxItemStateChanged
        refreshFormVisibility();
    }//GEN-LAST:event_paymentcheckboxItemStateChanged

    private void orderFormRadioChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_orderFormRadioChange
        if(evt.getStateChange() == 1) {
            refreshFormVisibility();
        }
    }//GEN-LAST:event_orderFormRadioChange

    private void customerdetailsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customerdetailsItemStateChanged
        if (evt.getStateChange() == 1) {
            emptyForm();
            int row = customerdetails.getSelectedIndex();
            setFormEnabled(row == 0);
            if (row == -1 || row == 0) return;
            Object[] customer = customers.get(row-1);
            lnamefield.setText((String) customer[1]);
            fnamefield.setText((String) customer[2]);
            housefield.setText((String) customer[3]);
            brgyfield.setText((String) customer[4]);
            cityfield.setText((String) customer[5]);
            
        }
    }//GEN-LAST:event_customerdetailsItemStateChanged

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed
    
    private void updatebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn2ActionPerformed
        int row = pendingdelivertbl.getSelectedRow();
        if (row == -1) return;
        TableModel model = pendingdelivertbl.getModel();
        Long orderID = (Long) model.getValueAt(row, 0);
        if (jComboBox5.getSelectedIndex() == 0) {
            System.out.println("currently updating " + orderID);
            DeliveryPopup obj = new DeliveryPopup(orderID);
            obj.addConfirmAction(event -> refreshPendingDelivery()); // REFRESH TABLE AFTER UPDATE
            GlassPanePopup.showPopup(obj);
        } else {
            
            DeliveryUpdatePopup obj = new DeliveryUpdatePopup();
            obj.fail((ActionListener) -> {
                Database db = new Database();
                db.updateDelivery(orderID, 0, "Awaiting..");
                GlassPanePopup.closePopupAll();
                refreshPendingDelivery();
                db.closeConnection();
            });
            obj.complete((ActionListener) -> {
                Database db = new Database();
                db.updateDelivery(orderID, 2, null);
                GlassPanePopup.closePopupAll();
                refreshCompleteDelivery();
                pendingdeliver.setVisible(false);
                completedeliver.setVisible(true);
                db.closeConnection();
            });
            GlassPanePopup.showPopup(obj);
        }
    }//GEN-LAST:event_updatebtn2ActionPerformed

    private void genreport4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreport4ActionPerformed
        String range = (String) jComboBox2.getSelectedItem();
        try {
            PDFGenerator.createReport("Deliveries Report", range,
                "pending-delivery",
                "ongoing-delivery",
                "complete-delivery"
            );
            showMsg("Generated " + range + " Deliveries Report");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_genreport4ActionPerformed

    private void generatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatebtnActionPerformed
        try {
            String title = null;
            String table = null;
            switch (jComboBox1.getSelectedIndex()) {
                case (0) -> {
                    title = "Unpaid Transactions";
                    table = "pendingall-order";
                }
                case (1) -> {
                    title = "Unpaid Walk-Ins";
                    table = "pendingwalkin-order";
                }
                case (2) -> {
                    title = "Unpaid Deliveries";
                    table = "pendingdelivery-order";
                }
            }
            if (title != null) PDFGenerator.createReport(title, "", table);
            showMsg("Generated " + title);
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generatebtnActionPerformed

    private void generatebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatebtn1ActionPerformed
        try {
            String title = null;
            String table = null;
            switch (jComboBox1.getSelectedIndex()) {
                case (0) -> {
                    title = "Paid Transactions";
                    table = "completegall-order";
                }
                case (1) -> {
                    title = "Paid Walk-Ins";
                    table = "completewalkin-order";
                }
                case (2) -> {
                    title = "Paid Deliveries";
                    table = "completedelivery-order";
                }
            }
            if (title != null) PDFGenerator.createReport(title, "", table);
            showMsg("Generated " + title);
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generatebtn1ActionPerformed

    private void generatebtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatebtn2ActionPerformed
        try {
            String title = null;
            String table = null;
            switch (jComboBox5.getSelectedIndex()) {
                case (0) -> {
                    title = "Pending Deliveries";
                    table = "completegall-order";
                }
                case (1) -> {
                    title = "Ongoing Deliveries";
                    table = "completewalkin-order";
                }
            }
            if (title != null) PDFGenerator.createReport(title, "", table);
            showMsg("Generated " + title);
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generatebtn2ActionPerformed

    private void generatebtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatebtn3ActionPerformed
        try {
            PDFGenerator.createReport("Complete Deliveries", "", "complete-delivery");
            showMsg("Generated " + "Complete Deliveries");
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generatebtn3ActionPerformed

    private void pendingbtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingbtn3MouseClicked
        javax.swing.table.TableColumnModel cm = pendingdelivertbl.getColumnModel();
        for (int i = 0; i < 8; i++) {
            javax.swing.table.TableColumn c = cm.getColumn(0);
            System.out.println(c.getWidth());
        }
    }//GEN-LAST:event_pendingbtn3MouseClicked
    
    private void databasebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databasebtnMouseClicked
        showCard(dborders);
        boldCard(databasebtn);
        refreshDBOrders();
    }//GEN-LAST:event_databasebtnMouseClicked

    private void ordersdbmouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersdbmouseclicked
        showDBTable(dborders);
        refreshDBOrders();
    }//GEN-LAST:event_ordersdbmouseclicked

    private void deliveriesdbmouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliveriesdbmouseclicked
        showDBTable(dbdeliveries);
        refreshDBDeliveries();
    }//GEN-LAST:event_deliveriesdbmouseclicked

    private void customersdbmouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersdbmouseclicked
        showDBTable(dbcustomers);
        refreshDBCustomers();
    }//GEN-LAST:event_customersdbmouseclicked

    private void usersdbmouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersdbmouseclicked
        showDBTable(dbusers);
        refreshDBUsers();
    }//GEN-LAST:event_usersdbmouseclicked

    private void productsdbmouseclicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsdbmouseclicked
        showDBTable(dbproducts);
        refreshDBProducts();
    }//GEN-LAST:event_productsdbmouseclicked

    private void dborderstblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dborderstblMouseClicked
        System.out.println("table clicked " + dborderstbl.getSelectedColumn());
        int col = dborderstbl.getSelectedColumn();
        int row = dborderstbl.getSelectedRow();
        Long orderID = (Long) dborderstbl.getValueAt(row, 0);
        String header = dborderstbl.getColumnModel().getColumn(col).getHeaderValue().toString();
        switch(col) {
            case 2, 3 -> {
                TextEditPopup obj = new TextEditPopup(header,
                        "Editing " + header + " of OrderID " + orderID,
                        orderID,
                        (String) dborderstbl.getValueAt(row, col));
                obj.save(event -> refreshDBOrders());
                GlassPanePopup.showPopup(obj);
            }
            case 4, 5 -> {
                TextEditPopup obj = new TextEditPopup(header,
                        "Editing " + header + " of OrderID " + orderID,
                        orderID,
                        (Float) dborderstbl.getValueAt(row, col));
                obj.save(event -> refreshDBOrders());
                GlassPanePopup.showPopup(obj);
            }
            case 6 -> {
                BooleanEditPopup obj = new BooleanEditPopup(header,
                        "Editing " + header + " of OrderID " + orderID,
                        orderID,
                        (Boolean) dborderstbl.getValueAt(row, col));
                obj.save(event -> refreshDBOrders());
                GlassPanePopup.showPopup(obj);
            }
            case 7, 8 -> {
                DateEditPopup obj = new DateEditPopup(header,
                        "Editing " + header + " of OrderID " + orderID,
                        orderID,
                        (Date) dborderstbl.getValueAt(row, col));
                obj.save(event -> refreshDBOrders());
                GlassPanePopup.showPopup(obj);
            }
            default -> showMsg(header + " cannot be changed");
        }
    }//GEN-LAST:event_dborderstblMouseClicked
    private void showDBTable(javax.swing.JPanel p) {
        dborders.setVisible(false);
        dbdeliveries.setVisible(false);
        dbcustomers.setVisible(false);
        dbusers.setVisible(false);
        dbproducts.setVisible(false);
        p.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main("owner","dev").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount1field;
    private javax.swing.JTextField amount2field;
    private javax.swing.JLabel authenticbtn;
    private javax.swing.JPanel authentictab;
    private javax.swing.JPanel authreports;
    private software1.Background background1;
    private software1.Background background10;
    private software1.Background background11;
    private software1.Background background12;
    private software1.Background background2;
    private software1.Background background3;
    private software1.Background background4;
    private software1.Background background5;
    private software1.Background background6;
    private software1.Background background7;
    private software1.Background background8;
    private software1.Background background9;
    private javax.swing.JTextField brgyfield;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelorderbtn;
    private javax.swing.JTextField cityfield;
    private javax.swing.JLabel completebtn1;
    private javax.swing.JLabel completebtn2;
    private javax.swing.JLabel completebtn3;
    private javax.swing.JLabel completebtn4;
    private javax.swing.JPanel completedeliver;
    private javax.swing.JTable completedelivertbl;
    private javax.swing.JPanel completetransac;
    private javax.swing.JTable completetransactbl;
    private javax.swing.JComboBox<String> customerdetails;
    private javax.swing.JLabel customersbtndb;
    private javax.swing.JLabel customersbtndb1;
    private javax.swing.JLabel customersbtndb2;
    private javax.swing.JLabel customersbtndb3;
    private javax.swing.JLabel customersbtndb4;
    private javax.swing.JLabel databasebtn;
    private javax.swing.JPanel dbcustomers;
    private javax.swing.JTable dbcustomerstbl;
    private javax.swing.JPanel dbdeliveries;
    private javax.swing.JTable dbdeliveriestbl;
    private javax.swing.JPanel dborders;
    private javax.swing.JTable dborderstbl;
    private javax.swing.JPanel dbproducts;
    private javax.swing.JTable dbproductstbl;
    private javax.swing.JPanel dbusers;
    private javax.swing.JTable dbuserstbl;
    private javax.swing.JLabel deliverbtn;
    private javax.swing.JLabel delivericon;
    private javax.swing.JLabel deliveriesbtndb;
    private javax.swing.JLabel deliveriesbtndb1;
    private javax.swing.JLabel deliveriesbtndb2;
    private javax.swing.JLabel deliveriesbtndb3;
    private javax.swing.JLabel deliveriesbtndb4;
    private javax.swing.JPanel delivertab;
    private javax.swing.JButton editmodule1;
    private javax.swing.JButton editmodule2;
    private javax.swing.JButton editmodule3;
    private javax.swing.JButton editmodule4;
    private javax.swing.JLabel financeicon;
    private javax.swing.JTextField fnamefield;
    private javax.swing.JPanel form;
    private javax.swing.JLabel formsimg1;
    private javax.swing.JButton generatebtn;
    private javax.swing.JButton generatebtn1;
    private javax.swing.JButton generatebtn2;
    private javax.swing.JButton generatebtn3;
    private javax.swing.JButton genreport4;
    private javax.swing.JButton genreport5;
    private javax.swing.JButton genreport6;
    private javax.swing.JTextField housefield;
    private javax.swing.JLabel invoicebtn;
    private javax.swing.JPanel invoices;
    private javax.swing.JPanel invoicetab;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel leftarrow;
    private javax.swing.JTextField lnamefield;
    private javax.swing.JPanel maintabs;
    private javax.swing.JTextField mobilefield;
    private javax.swing.JButton orderbtn;
    private javax.swing.JLabel ordersbtndb;
    private javax.swing.JLabel ordersbtndb1;
    private javax.swing.JLabel ordersbtndb2;
    private javax.swing.JLabel ordersbtndb3;
    private javax.swing.JLabel ordersbtndb4;
    private javax.swing.JCheckBox paymentcheckbox;
    private javax.swing.JLabel pendingbtn1;
    private javax.swing.JLabel pendingbtn2;
    private javax.swing.JLabel pendingbtn3;
    private javax.swing.JLabel pendingbtn4;
    private javax.swing.JPanel pendingdeliver;
    private javax.swing.JTable pendingdelivertbl;
    private javax.swing.JPanel pendingtransac;
    private javax.swing.JTable pendingtransactbl;
    private javax.swing.JLabel productdesc1;
    private javax.swing.JLabel productimg;
    private javax.swing.JLabel productname1;
    private javax.swing.JLabel productprice1;
    private javax.swing.JSpinner productqty;
    private javax.swing.JLabel productsbtndb;
    private javax.swing.JLabel productsbtndb1;
    private javax.swing.JLabel productsbtndb2;
    private javax.swing.JLabel productsbtndb3;
    private javax.swing.JLabel productsbtndb4;
    private javax.swing.JPanel qnb;
    private javax.swing.JRadioButton radiodeliver;
    private javax.swing.JRadioButton radiowalkin;
    private javax.swing.JLabel rightarrow;
    private javax.swing.JButton selectOrder;
    private javax.swing.JPanel signout;
    private javax.swing.JLabel signoutbtn;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JButton submitform;
    private javax.swing.JPanel tabcontent;
    private javax.swing.JLabel transacbtn;
    private javax.swing.JLabel transacicon;
    private javax.swing.JPanel transactab;
    private javax.swing.JButton updatebtn1;
    private javax.swing.JButton updatebtn2;
    private javax.swing.JLabel usersbtndb;
    private javax.swing.JLabel usersbtndb1;
    private javax.swing.JLabel usersbtndb2;
    private javax.swing.JLabel usersbtndb3;
    private javax.swing.JLabel usersbtndb4;
    // End of variables declaration//GEN-END:variables
}
