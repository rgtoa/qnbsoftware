
package software1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import glasspanepopup.GlassPanePopup;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;

public class Main extends javax.swing.JFrame {
    
    private final String role;
    private final String username;
    private int productNum = 0;
    private ArrayList<Object[]> cart = new ArrayList<>();
    //ImageIcon invoicewv = new ImageIcon("try222.png");
    
    public Main(String role, String username) {
        
        this.role = role;
        this.username = username;
        initComponents();
        GlassPanePopup.install(this);
        this.setLocationRelativeTo(null);
        initTableData();
        System.out.println("owner?"+role.equals("owner"));
        editmodule1.setVisible(role.equals("owner"));
        editmodule2.setVisible(role.equals("owner"));
        editmodule3.setVisible(role.equals("owner"));
        editmodule4.setVisible(role.equals("owner"));
        //jLabel10.setIcon(invoicewv);
        scaleIcons();
        scaleProducts();
    }
//    public void paint(Graphics grphcs) {
//        Graphics2D g2 = (Graphics2D) grphcs;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        int height = 750;
//        g2.setColor(new Color(201,199,255));
//        g2.fill(createShape(height,80,50,100,20,100));
//        g2.setColor(new Color(157,153,255));
//        g2.fill(createShape(height,70,20,60,20,70));
//        g2.dispose();
//        super.paint(grphcs);
//    }
//    
//    private Shape createShape(int location, int startInit, int... points) {
//        int width = getWidth();
//        int height = getHeight();
//        int ss = width / points.length;
//        int size = location;
//        GeneralPath p = new GeneralPath();
//        int space = 0;
//        int xs = 0;
//        int ys = location - startInit;
//        for (int point : points) {
//            point = size - point;
//            int s = space + ss / 2;
//            p.append(new CubicCurve2D.Double(xs, ys, s, ys, s, point, xs + ss, point), true);
//            space += ss;
//            xs += ss;
//            ys = point;
//        }
//        p.lineTo(width, ys);
//        p.lineTo(width, height);
//        p.lineTo(0, height);
//        return p;
//    }
    
    private void scaleIcons(){
        /*ImageIcon icon = new ImageIcon("try222.png");
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel10.getWidth(), jLabel10.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jLabel10.setIcon(scaledIcon);
        
        ImageIcon icon2 = new ImageIcon("try222.png");
        Image img2 = icon2.getImage();
        Image imgScale2 = img2.getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(imgScale2);
        jLabel3.setIcon(scaledIcon2);*/
        
        ImageIcon icon3 = new ImageIcon("arrow.png");
        Image arrow = icon3.getImage();
        Image imgScale3 = arrow.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(imgScale3);
        jLabel4.setIcon(scaledIcon3);
        jLabel5.setIcon(scaledIcon3);
        jLabel6.setIcon(scaledIcon3);
        jLabel7.setIcon(scaledIcon3);
        //jLabel17.setIcon(scaledIcon3);
        
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
        //productdesc1.setText("");
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
        transactab = new javax.swing.JPanel();
        transacbtn = new javax.swing.JLabel();
        delivertab = new javax.swing.JPanel();
        deliverbtn = new javax.swing.JLabel();
        authentictab = new javax.swing.JPanel();
        authenticbtn = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        signout = new javax.swing.JPanel();
        signoutbtn = new javax.swing.JLabel();
        tabcontent = new javax.swing.JPanel();
        invoices = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        productimg = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rightarrow = new javax.swing.JLabel();
        leftarrow = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        productdesc1 = new javax.swing.JLabel();
        productname1 = new javax.swing.JLabel();
        productqty = new javax.swing.JSpinner();
        productprice1 = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        orderbtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        formsimg1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
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
        completetransac = new javax.swing.JPanel();
        completebtn2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pendingtransactbl1 = new javax.swing.JTable();
        pendingbtn2 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        generatebtn1 = new javax.swing.JButton();
        editmodule2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pendingdeliver = new javax.swing.JPanel();
        pendingbtn3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        pendingtransactbl2 = new javax.swing.JTable();
        completebtn3 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        generatebtn2 = new javax.swing.JButton();
        editmodule3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        completedeliver = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pendingdelivertbl1 = new javax.swing.JTable();
        pendingbtn4 = new javax.swing.JLabel();
        completebtn4 = new javax.swing.JLabel();
        generatebtn3 = new javax.swing.JButton();
        editmodule4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        authreports = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        genreport4 = new javax.swing.JButton();
        genreport5 = new javax.swing.JButton();
        genreport6 = new javax.swing.JButton();
        transacicon = new javax.swing.JLabel();
        delivericon = new javax.swing.JLabel();
        financeicon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QNB System");
        setBackground(new java.awt.Color(255, 255, 255));

        maintabs.setBackground(new java.awt.Color(255, 255, 255));
        maintabs.setPreferredSize(new java.awt.Dimension(1250, 75));

        qnb.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(10, 64, 83));
        jLabel1.setText("QNB " + this.role);
        jLabel1.setPreferredSize(new java.awt.Dimension(175, 70));

        javax.swing.GroupLayout qnbLayout = new javax.swing.GroupLayout(qnb);
        qnb.setLayout(qnbLayout);
        qnbLayout.setHorizontalGroup(
            qnbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qnbLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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

        invoicebtn.setFont(new java.awt.Font("Source Sans Pro Semibold", 1, 18)); // NOI18N
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
                .addGap(43, 43, 43)
                .addComponent(invoicebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
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

        transacbtn.setFont(new java.awt.Font("Source Sans Pro Light", 1, 18)); // NOI18N
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
                .addComponent(transacbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
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

        deliverbtn.setFont(new java.awt.Font("Source Sans Pro Light", 1, 18)); // NOI18N
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
                .addContainerGap()
                .addComponent(deliverbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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

        authenticbtn.setFont(new java.awt.Font("Source Sans Pro Light", 1, 18)); // NOI18N
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

        javax.swing.GroupLayout authentictabLayout = new javax.swing.GroupLayout(authentictab);
        authentictab.setLayout(authentictabLayout);
        authentictabLayout.setHorizontalGroup(
            authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authentictabLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(authenticbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        authentictabLayout.setVerticalGroup(
            authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authentictabLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(authentictabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(authenticbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(leftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productimg, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(productimg, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(rightarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(224, 224, 224))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addComponent(leftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(226, 226, 226)))))
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        invoices.add(jPanel14);

        jPanel12.setBackground(new java.awt.Color(229, 229, 229));
        jPanel12.setPreferredSize(new java.awt.Dimension(627, 675));

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
        productqty.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                productqtyStateChanged(evt);
            }
        });

        productprice1.setBackground(new java.awt.Color(255, 255, 255));
        productprice1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        productprice1.setForeground(new java.awt.Color(34, 73, 87));
        productprice1.setText("Php 35.00");

        jLabelPrice.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabelPrice.setForeground(new java.awt.Color(10, 64, 83));
        jLabelPrice.setText("Price:");

        orderbtn.setBackground(new java.awt.Color(10, 64, 83));
        orderbtn.setForeground(new java.awt.Color(255, 255, 255));
        orderbtn.setText("Order");
        orderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderbtnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(229, 229, 229));
        jPanel1.add(jLabel9);

        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        stockLabel.setFont(new java.awt.Font("Source Sans Pro Light", 2, 12)); // NOI18N
        stockLabel.setForeground(new java.awt.Color(10, 64, 83));
        stockLabel.setText("In stock: 4");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(orderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
        );

        invoices.add(jPanel12);

        tabcontent.add(invoices, "card3");
        invoices.getAccessibleContext().setAccessibleName("Transactions");

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setPreferredSize(new java.awt.Dimension(1250, 625));
        form.setLayout(new javax.swing.BoxLayout(form, javax.swing.BoxLayout.LINE_AXIS));

        jPanel16.setBackground(new java.awt.Color(229, 229, 229));
        jPanel16.setPreferredSize(new java.awt.Dimension(623, 625));

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
        radiowalkin.setText("for Walk-in");

        buttonGroup1.add(radiodeliver);
        radiodeliver.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        radiodeliver.setForeground(new java.awt.Color(34, 73, 87));
        radiodeliver.setText("for Delivery");

        submitform.setBackground(new java.awt.Color(140, 208, 218));
        submitform.setForeground(new java.awt.Color(34, 73, 87));
        submitform.setText("Submit");
        submitform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitformActionPerformed(evt);
            }
        });

        customerdetails.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 73, 87));
        jLabel2.setText("Existing Customer?");

        cancelorderbtn.setText("Cancel");
        cancelorderbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelorderbtnActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(34, 73, 87));
        jRadioButton1.setText("Full payment");

        jLabel17.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(34, 73, 87));
        jLabel17.setText("Amount Paid");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(housefield)
                        .addComponent(jLabel14)
                        .addGroup(jPanel16Layout.createSequentialGroup()
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
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(customerdetails, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(115, 115, 115)
                            .addComponent(jLabel11))
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mobilefield, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(brgyfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cityfield)
                                .addComponent(jTextField1)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel17)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radiowalkin, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radiodeliver, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelorderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitform, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelorderbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(submitform, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerdetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(mobilefield, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radiowalkin)
                        .addGap(4, 4, 4)
                        .addComponent(radiodeliver)))
                .addContainerGap(183, Short.MAX_VALUE))
        );

        form.add(jPanel16);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        formsimg1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(formsimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addComponent(formsimg1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        form.add(jPanel15);

        tabcontent.add(form, "card4");
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

        pendingbtn1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 36)); // NOI18N
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

        javax.swing.GroupLayout pendingtransacLayout = new javax.swing.GroupLayout(pendingtransac);
        pendingtransac.setLayout(pendingtransacLayout);
        pendingtransacLayout.setHorizontalGroup(
            pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pendingtransacLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(pendingtransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pendingtransacLayout.createSequentialGroup()
                        .addComponent(pendingbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );

        tabcontent.add(pendingtransac, "card5");
        pendingtransac.getAccessibleContext().setAccessibleName("Authenticate");

        completetransac.setBackground(new java.awt.Color(255, 255, 255));
        completetransac.setPreferredSize(new java.awt.Dimension(1250, 625));

        completebtn2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 36)); // NOI18N
        completebtn2.setForeground(new java.awt.Color(10, 64, 83));
        completebtn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn2.setText("Completed");
        completebtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        pendingtransactbl1.setBackground(new java.awt.Color(229, 229, 229));
        pendingtransactbl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        pendingtransactbl1.setForeground(new java.awt.Color(10, 64, 83));
        pendingtransactbl1.setModel(new javax.swing.table.DefaultTableModel(
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
        pendingtransactbl1.setColumnSelectionAllowed(true);
        pendingtransactbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pendingtransactbl1.setEnabled(false);
        pendingtransactbl1.setFocusable(false);
        pendingtransactbl1.setGridColor(new java.awt.Color(0, 0, 0));
        pendingtransactbl1.setRequestFocusEnabled(false);
        pendingtransactbl1.setRowSelectionAllowed(false);
        pendingtransactbl1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        pendingtransactbl1.setShowGrid(false);
        pendingtransactbl1.getTableHeader().setReorderingAllowed(false);
        pendingtransactbl1.setUpdateSelectionOnSort(false);
        pendingtransactbl1.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(pendingtransactbl1);
        pendingtransactbl1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (pendingtransactbl1.getColumnModel().getColumnCount() > 0) {
            pendingtransactbl1.getColumnModel().getColumn(0).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(0).setPreferredWidth(5);
            pendingtransactbl1.getColumnModel().getColumn(1).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(2).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(2).setPreferredWidth(5);
            pendingtransactbl1.getColumnModel().getColumn(3).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(4).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(5).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(5).setPreferredWidth(5);
            pendingtransactbl1.getColumnModel().getColumn(6).setResizable(false);
            pendingtransactbl1.getColumnModel().getColumn(6).setPreferredWidth(5);
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

        generatebtn1.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn1.setForeground(new java.awt.Color(34, 73, 87));
        generatebtn1.setText("Generate");

        editmodule2.setBackground(new java.awt.Color(140, 208, 218));
        editmodule2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule2.setForeground(new java.awt.Color(34, 73, 87));
        editmodule2.setText("Edit Module");

        javax.swing.GroupLayout completetransacLayout = new javax.swing.GroupLayout(completetransac);
        completetransac.setLayout(completetransacLayout);
        completetransacLayout.setHorizontalGroup(
            completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, completetransacLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(completetransacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completetransacLayout.createSequentialGroup()
                        .addComponent(pendingbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
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
                .addComponent(jScrollPane3)
                .addGap(80, 80, 80))
        );

        tabcontent.add(completetransac, "card5");

        pendingdeliver.setBackground(new java.awt.Color(255, 255, 255));
        pendingdeliver.setPreferredSize(new java.awt.Dimension(1250, 625));

        pendingbtn3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 36)); // NOI18N
        pendingbtn3.setForeground(new java.awt.Color(10, 64, 83));
        pendingbtn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pendingbtn3.setText("Pending");

        pendingtransactbl2.setBackground(new java.awt.Color(229, 229, 229));
        pendingtransactbl2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 229, 229)));
        pendingtransactbl2.setForeground(new java.awt.Color(10, 64, 83));
        pendingtransactbl2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Item/QTY", "Customer ID", "Name", "Address", "Price", "Status"
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
        pendingtransactbl2.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(pendingtransactbl2);
        if (pendingtransactbl2.getColumnModel().getColumnCount() > 0) {
            pendingtransactbl2.getColumnModel().getColumn(0).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(0).setPreferredWidth(5);
            pendingtransactbl2.getColumnModel().getColumn(1).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(2).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(2).setPreferredWidth(5);
            pendingtransactbl2.getColumnModel().getColumn(3).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(4).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(5).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(5).setPreferredWidth(5);
            pendingtransactbl2.getColumnModel().getColumn(6).setResizable(false);
            pendingtransactbl2.getColumnModel().getColumn(6).setPreferredWidth(5);
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

        editmodule3.setBackground(new java.awt.Color(140, 208, 218));
        editmodule3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule3.setForeground(new java.awt.Color(34, 73, 87));
        editmodule3.setText("Edit Module");

        javax.swing.GroupLayout pendingdeliverLayout = new javax.swing.GroupLayout(pendingdeliver);
        pendingdeliver.setLayout(pendingdeliverLayout);
        pendingdeliverLayout.setHorizontalGroup(
            pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pendingdeliverLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(pendingdeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pendingdeliverLayout.createSequentialGroup()
                        .addComponent(pendingbtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(completebtn3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pendingdeliverLayout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generatebtn2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 793, Short.MAX_VALUE)
                        .addComponent(editmodule3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
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
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addGap(80, 80, 80))
        );

        tabcontent.add(pendingdeliver, "card2");
        pendingdeliver.getAccessibleContext().setAccessibleName("Invoices");

        completedeliver.setBackground(new java.awt.Color(255, 255, 255));

        pendingdelivertbl1.setBackground(new java.awt.Color(229, 229, 229));
        pendingdelivertbl1.setForeground(new java.awt.Color(10, 64, 83));
        pendingdelivertbl1.setModel(new javax.swing.table.DefaultTableModel(
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
        pendingdelivertbl1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(pendingdelivertbl1);
        if (pendingdelivertbl1.getColumnModel().getColumnCount() > 0) {
            pendingdelivertbl1.getColumnModel().getColumn(0).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(0).setPreferredWidth(5);
            pendingdelivertbl1.getColumnModel().getColumn(1).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(2).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(2).setPreferredWidth(5);
            pendingdelivertbl1.getColumnModel().getColumn(3).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(4).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(5).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(5).setPreferredWidth(5);
            pendingdelivertbl1.getColumnModel().getColumn(6).setResizable(false);
            pendingdelivertbl1.getColumnModel().getColumn(6).setPreferredWidth(5);
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

        completebtn4.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 36)); // NOI18N
        completebtn4.setForeground(new java.awt.Color(10, 64, 83));
        completebtn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        completebtn4.setText("Completed");
        completebtn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        generatebtn3.setBackground(new java.awt.Color(140, 208, 218));
        generatebtn3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        generatebtn3.setForeground(new java.awt.Color(10, 64, 83));
        generatebtn3.setText("Generate");

        editmodule4.setBackground(new java.awt.Color(140, 208, 218));
        editmodule4.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        editmodule4.setForeground(new java.awt.Color(34, 73, 87));
        editmodule4.setText("Edit Module");

        javax.swing.GroupLayout completedeliverLayout = new javax.swing.GroupLayout(completedeliver);
        completedeliver.setLayout(completedeliverLayout);
        completedeliverLayout.setHorizontalGroup(
            completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completedeliverLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completedeliverLayout.createSequentialGroup()
                        .addComponent(pendingbtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        );
        completedeliverLayout.setVerticalGroup(
            completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(completedeliverLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(completedeliverLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(completebtn4)
                        .addComponent(pendingbtn4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(completedeliverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generatebtn3)
                    .addComponent(editmodule4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );

        tabcontent.add(completedeliver, "card2");

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

        javax.swing.GroupLayout authreportsLayout = new javax.swing.GroupLayout(authreports);
        authreports.setLayout(authreportsLayout);
        authreportsLayout.setHorizontalGroup(
            authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authreportsLayout.createSequentialGroup()
                .addContainerGap(335, Short.MAX_VALUE)
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
                    .addComponent(financeicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(336, Short.MAX_VALUE))
            .addGroup(authreportsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        authreportsLayout.setVerticalGroup(
            authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(authreportsLayout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(52, 52, 52)
                .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(financeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transacicon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delivericon, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(authreportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genreport4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreport6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreport5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        tabcontent.add(authreports, "card6");

        getContentPane().add(tabcontent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void initTableData() {
        Database db = new Database();
        populateTable(pendingtransactbl, db.getTransactions(0, 0));
        populateTable(pendingtransactbl1, db.getTransactions(1, 0));
        populateTable(pendingtransactbl2, db.getDeliveries(0));
        populateTable(pendingdelivertbl1, db.getDeliveries(2));
        db.closeConnection();
    }
    private void populateTable(javax.swing.JTable table, ArrayList<ArrayList<Object>> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // reset the table
        for (ArrayList<Object> n : list) {
            System.out.println("row " + java.util.Arrays.toString(n.toArray()));
            model.addRow(n.toArray());
        }
    }
    private void showCard(java.awt.Component card) {
        transacbtn.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
        invoicebtn.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 18));
        deliverbtn.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
        authenticbtn.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
        
        invoices.setVisible(false);
        form.setVisible(false);
        pendingtransac.setVisible(false);
        completetransac.setVisible(false);
        pendingdeliver.setVisible(false);
        completedeliver.setVisible(false);
        authenticate.setVisible(false);
        authreports.setVisible(false);
        card.setVisible(true);
    }  
    private void orderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderbtnActionPerformed
        OrderPopup obj = new OrderPopup(cart);
        obj.confirm(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
                showCard(form);
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_orderbtnActionPerformed

    private void transacbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transacbtnMouseClicked

        showCard(pendingtransac);
    }//GEN-LAST:event_transacbtnMouseClicked

    private void deliverbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deliverbtnMouseClicked
        showCard(pendingdeliver);
    }//GEN-LAST:event_deliverbtnMouseClicked

    private void authenticbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authenticbtnMouseClicked
        showCard(authenticate);
        AuthPopup obj = new AuthPopup();
        obj.authenticate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
                invoices.setVisible(false);
                form.setVisible(false);
                pendingtransac.setVisible(false);
                completetransac.setVisible(false);
                pendingdeliver.setVisible(false);
                completedeliver.setVisible(false);
                authreports.setVisible(true);
            }
        });
        GlassPanePopup.showPopup(obj);     
    }//GEN-LAST:event_authenticbtnMouseClicked

    private void genreport5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreport5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreport5ActionPerformed

    private void genreport6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreport6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreport6ActionPerformed

    private void submitformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitformActionPerformed
        form.setVisible(false);
        pendingtransac.setVisible(true);
    }//GEN-LAST:event_submitformActionPerformed

    private void signoutbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signoutbtnMouseClicked
        // TODO add your handling code here:
        Login login = new Login();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_signoutbtnMouseClicked

    private void completebtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completebtn1MouseClicked
        pendingtransac.setVisible(false);
        //this.authpassbtn2ActionPerformed(evt);
        completetransac.setVisible(true);
    }//GEN-LAST:event_completebtn1MouseClicked

    private void pendingbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingbtn2MouseClicked
        completetransac.setVisible(false);
        //authpassbtn2ActionPerformed(evt);
        pendingtransac.setVisible(true);
    }//GEN-LAST:event_pendingbtn2MouseClicked

    private void pendingbtn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pendingbtn4MouseClicked
        completedeliver.setVisible(false);
        //this.authpassbtn2ActionPerformed(evt);
        pendingdeliver.setVisible(true);
    }//GEN-LAST:event_pendingbtn4MouseClicked

    private void completebtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completebtn3MouseClicked
        pendingdeliver.setVisible(false);
        //this.authpassbtn2ActionPerformed(evt);
        completedeliver.setVisible(true);
    }//GEN-LAST:event_completebtn3MouseClicked

    private void updatebtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtn1ActionPerformed
        TransacEditPopup obj = new TransacEditPopup();
        /*obj.transacedit(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
                invoices.setVisible(false);
                form.setVisible(true);
            }
        });*/
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_updatebtn1ActionPerformed

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        // TODO add your handling code here:
        Database db = new Database();
        populateTable(pendingtransactbl2, db.getDeliveries(jComboBox5.getSelectedIndex()));
        db.closeConnection();
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void TransactComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TransactComboBoxItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 2) return;
        Database db = new Database();
        int status = evt.getSource() == jComboBox1 ? 0 : 1;
        populateTable(pendingtransactbl, db.getTransactions(status, jComboBox1.getSelectedIndex()));
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Database db = new Database();
        try {
            int prodID = this.productNum + 1;
            int qty = (int) productqty.getValue();
            cart.add(new Object[] {
                prodID,
                db.getProductName(prodID),
                qty,
                qty * db.getProductPrice(prodID)
            });
            Message msg = new Message("Successfully Added Product");
            GlassPanePopup.showPopup(msg);
        } finally {
            System.out.println("Added Product:\n" + Arrays.toString(cart.get(cart.size()-1)));
            productqty.setValue(1); // reset value to 1
            db.closeConnection();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void invoicebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_invoicebtnMouseClicked
        showCard(invoices);
    }//GEN-LAST:event_invoicebtnMouseClicked
    private void cancelorderbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelorderbtnActionPerformed
        form.setVisible(false);
        invoices.setVisible(true);
    }//GEN-LAST:event_cancelorderbtnActionPerformed

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
                new Main("","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authenticbtn;
    private javax.swing.JPanel authentictab;
    private javax.swing.JPanel authreports;
    private javax.swing.JTextField brgyfield;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelorderbtn;
    private javax.swing.JTextField cityfield;
    private javax.swing.JLabel completebtn1;
    private javax.swing.JLabel completebtn2;
    private javax.swing.JLabel completebtn3;
    private javax.swing.JLabel completebtn4;
    private javax.swing.JPanel completedeliver;
    private javax.swing.JPanel completetransac;
    private javax.swing.JComboBox<String> customerdetails;
    private javax.swing.JLabel deliverbtn;
    private javax.swing.JLabel delivericon;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel leftarrow;
    private javax.swing.JTextField lnamefield;
    private javax.swing.JPanel maintabs;
    private javax.swing.JTextField mobilefield;
    private javax.swing.JButton orderbtn;
    private javax.swing.JLabel pendingbtn1;
    private javax.swing.JLabel pendingbtn2;
    private javax.swing.JLabel pendingbtn3;
    private javax.swing.JLabel pendingbtn4;
    private javax.swing.JPanel pendingdeliver;
    private javax.swing.JTable pendingdelivertbl1;
    private javax.swing.JPanel pendingtransac;
    private javax.swing.JTable pendingtransactbl;
    private javax.swing.JTable pendingtransactbl1;
    private javax.swing.JTable pendingtransactbl2;
    private javax.swing.JLabel productdesc1;
    private javax.swing.JLabel productimg;
    private javax.swing.JLabel productname1;
    private javax.swing.JLabel productprice1;
    private javax.swing.JSpinner productqty;
    private javax.swing.JPanel qnb;
    private javax.swing.JRadioButton radiodeliver;
    private javax.swing.JRadioButton radiowalkin;
    private javax.swing.JLabel rightarrow;
    private javax.swing.JPanel signout;
    private javax.swing.JLabel signoutbtn;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JButton submitform;
    private javax.swing.JPanel tabcontent;
    private javax.swing.JLabel transacbtn;
    private javax.swing.JLabel transacicon;
    private javax.swing.JPanel transactab;
    private javax.swing.JButton updatebtn1;
    // End of variables declaration//GEN-END:variables
}
