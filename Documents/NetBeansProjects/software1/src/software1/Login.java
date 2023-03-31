
package software1;

import javax.swing.ImageIcon;
import glasspanepopup.GlassPanePopup;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends javax.swing.JFrame {
    
    public Login() {
        initComponents();
        GlassPanePopup.install(this);
        this.setLocationRelativeTo(null);
        scaleLogo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainlogin = new javax.swing.JPanel();
        login = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        qnbposlogo2 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        userfield1 = new javax.swing.JTextField();
        jLabelUser = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabelPass = new javax.swing.JLabel();
        passfield1 = new javax.swing.JPasswordField();
        jPanel20 = new javax.swing.JPanel();
        loginbtn = new javax.swing.JButton();
        signupbtn = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        signup = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        qnbposlogo = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        passLabel = new javax.swing.JLabel();
        passfield = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        registerbtn = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabelRole = new javax.swing.JLabel();
        loginbtn2 = new javax.swing.JLabel();
        userfield = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        confirmpassLabel = new javax.swing.JLabel();
        confirmpassfield = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QNB System");
        setBackground(new java.awt.Color(229, 229, 229));
        setForeground(null);
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());

        mainlogin.setBackground(new java.awt.Color(229, 229, 229));
        mainlogin.setPreferredSize(new java.awt.Dimension(775, 440));
        mainlogin.setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(229, 229, 229));
        login.setPreferredSize(new java.awt.Dimension(775, 440));

        jPanel16.setBackground(new java.awt.Color(229, 229, 229));
        jPanel16.setPreferredSize(new java.awt.Dimension(775, 138));

        qnbposlogo2.setFont(new java.awt.Font("Source Sans Pro Light", 1, 48)); // NOI18N
        qnbposlogo2.setForeground(new java.awt.Color(10, 64, 83));
        qnbposlogo2.setText(" ");
        qnbposlogo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(qnbposlogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(qnbposlogo2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        login.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(229, 229, 229));
        jPanel17.setPreferredSize(new java.awt.Dimension(775, 250));

        jPanel18.setBackground(new java.awt.Color(229, 229, 229));
        jPanel18.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel18.setLayout(new java.awt.BorderLayout());

        userfield1.setBackground(new java.awt.Color(23, 90, 115));
        userfield1.setForeground(new java.awt.Color(255, 255, 255));
        userfield1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        userfield1.setCaretColor(new java.awt.Color(229, 229, 229));
        userfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        jPanel18.add(userfield1, java.awt.BorderLayout.CENTER);

        jLabelUser.setForeground(new java.awt.Color(10, 64, 83));
        jLabelUser.setText("Username");
        jPanel18.add(jLabelUser, java.awt.BorderLayout.PAGE_START);

        jPanel19.setBackground(new java.awt.Color(229, 229, 229));
        jPanel19.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabelPass.setForeground(new java.awt.Color(10, 64, 83));
        jLabelPass.setText("Password");
        jPanel19.add(jLabelPass, java.awt.BorderLayout.PAGE_START);

        passfield1.setBackground(new java.awt.Color(23, 90, 115));
        passfield1.setForeground(new java.awt.Color(255, 255, 255));
        passfield1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        passfield1.setCaretColor(new java.awt.Color(229, 229, 229));
        passfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        jPanel19.add(passfield1, java.awt.BorderLayout.CENTER);

        jPanel20.setBackground(new java.awt.Color(229, 229, 229));
        jPanel20.setPreferredSize(new java.awt.Dimension(400, 75));

        loginbtn.setBackground(new java.awt.Color(66, 155, 186));
        loginbtn.setForeground(new java.awt.Color(255, 255, 255));
        loginbtn.setText("Login");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });

        signupbtn.setForeground(new java.awt.Color(10, 64, 83));
        signupbtn.setText("Sign up?");
        signupbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signupbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(signupbtn)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signupbtn)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel10.setForeground(new java.awt.Color(10, 64, 83));
        jLabel10.setText("Log in to start your water transactions!");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel10)))
                .addGap(188, 188, 188))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel10)
                .addGap(23, 23, 23)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        login.add(jPanel17);

        mainlogin.add(login, "card2");

        signup.setBackground(new java.awt.Color(229, 229, 229));
        signup.setPreferredSize(new java.awt.Dimension(775, 440));

        jPanel22.setBackground(new java.awt.Color(229, 229, 229));
        jPanel22.setPreferredSize(new java.awt.Dimension(775, 138));

        qnbposlogo.setFont(new java.awt.Font("Source Sans Pro Light", 1, 48)); // NOI18N
        qnbposlogo.setForeground(new java.awt.Color(10, 64, 83));
        qnbposlogo.setText(" ");
        qnbposlogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(qnbposlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(301, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(qnbposlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(229, 229, 229));
        jPanel13.setPreferredSize(new java.awt.Dimension(400, 175));

        jPanel4.setBackground(new java.awt.Color(229, 229, 229));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 45));

        passLabel.setForeground(new java.awt.Color(10, 64, 83));
        passLabel.setText("Password");

        passfield.setBackground(new java.awt.Color(23, 90, 115));
        passfield.setForeground(new java.awt.Color(255, 255, 255));
        passfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        passfield.setCaretColor(new java.awt.Color(229, 229, 229));
        passfield.setPreferredSize(new java.awt.Dimension(30, 24));
        passfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(passLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(passfield, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(passLabel)
                .addGap(1, 1, 1)
                .addComponent(passfield, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(229, 229, 229));
        jPanel5.setPreferredSize(new java.awt.Dimension(400, 45));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(229, 229, 229));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 75));

        registerbtn.setBackground(new java.awt.Color(66, 155, 186));
        registerbtn.setForeground(new java.awt.Color(255, 255, 255));
        registerbtn.setText("Register");
        registerbtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registerbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerbtnMouseClicked(evt);
            }
        });
        registerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(23, 90, 115));
        jComboBox1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff", "Delivery" }));

        jLabelRole.setForeground(new java.awt.Color(10, 64, 83));
        jLabelRole.setText("Role:");

        loginbtn2.setForeground(new java.awt.Color(10, 64, 83));
        loginbtn2.setText("Already have an account?");
        loginbtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginbtn2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabelRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(138, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(registerbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(loginbtn2)
                        .addGap(127, 127, 127))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRole)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(registerbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginbtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        userfield.setBackground(new java.awt.Color(23, 90, 115));
        userfield.setForeground(new java.awt.Color(255, 255, 255));
        userfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        userfield.setCaretColor(new java.awt.Color(229, 229, 229));
        userfield.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });

        userLabel.setForeground(new java.awt.Color(10, 64, 83));
        userLabel.setText("Username");

        confirmpassLabel.setForeground(new java.awt.Color(10, 64, 83));
        confirmpassLabel.setText("Confirm Password");

        confirmpassfield.setBackground(new java.awt.Color(23, 90, 115));
        confirmpassfield.setForeground(new java.awt.Color(255, 255, 255));
        confirmpassfield.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        confirmpassfield.setCaretColor(new java.awt.Color(229, 229, 229));
        confirmpassfield.setPreferredSize(new java.awt.Dimension(30, 24));
        confirmpassfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userfield, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmpassLabel)
                    .addComponent(confirmpassfield, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(userLabel)
                        .addGap(0, 0, 0)
                        .addComponent(userfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(confirmpassLabel)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(confirmpassfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel12.setForeground(new java.awt.Color(10, 64, 83));
        jLabel12.setText("Log in to start your water transactions!");

        javax.swing.GroupLayout signupLayout = new javax.swing.GroupLayout(signup);
        signup.setLayout(signupLayout);
        signupLayout.setHorizontalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(signupLayout.createSequentialGroup()
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        signupLayout.setVerticalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        mainlogin.add(signup, "card3");

        getContentPane().add(mainlogin);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void popupMsg(String msg) {
        GlassPanePopup.closePopupLast();
        GlassPanePopup.showPopup(new Message(msg));
    }
    private void scaleLogo() {
        ImageIcon icon = new ImageIcon(getClass().getResource("login logo.png"));
        Image prod = icon.getImage();
        Image imageScale = prod.getScaledInstance(qnbposlogo.getWidth(), qnbposlogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imageScale);
        qnbposlogo.setIcon(scaledIcon);
        qnbposlogo2.setIcon(scaledIcon);
    }
    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        Database db = new Database();
        String uname = userfield.getText();
        char[] pass = passfield.getPassword();
        char[] cpass = confirmpassfield.getPassword();
        try {
            // CHECK FIRST FOR USERNAME AND PASSWORD MATCH BEFORE AUTHENTICATING
            if (!Arrays.equals(pass, cpass)) {
                popupMsg("passwords do not match");
            }
            else if (db.checkUser(uname)) {
                popupMsg("existing user");
            }
            else {
                RegisterAuth obj = new RegisterAuth(
                        userfield.getText(),
                        passfield.getPassword(),
                        jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toLowerCase()
                );
                userfield.setText("");
                passfield.setText("");
                confirmpassfield.setText("");
                jComboBox1.setSelectedIndex(0);
                GlassPanePopup.showPopup(obj);
            }
        } finally {
            db.closeConnection();
            pass = null;
            cpass = null;
        }
    }//GEN-LAST:event_registerbtnActionPerformed

    private void loginbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbtn2MouseClicked
        signup.setVisible(false);
        login.setVisible(true);
    }//GEN-LAST:event_loginbtn2MouseClicked

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        System.out.println("login button clicked");
        String name = userfield1.getText();
        char[] pass = passfield1.getPassword();
        
        Database db = new Database();
        if (db.verifyUser(name, String.valueOf(pass))) {
            System.out.println("VERIFY SUCCESS");
            Main main = new Main(db.getRole(name), name);
            main.setVisible(true);
            this.dispose();
        }
        else {
            System.out.println("WRONG USERNAME OR PASSWORD");
            popupMsg("invalid username/password");
        }
        db.closeConnection();
        System.out.println("login end");
    }//GEN-LAST:event_loginbtnActionPerformed

    private void signupbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupbtnMouseClicked
        login.setVisible(false);
        signup.setVisible(true);
    }//GEN-LAST:event_signupbtnMouseClicked

    private void registerbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerbtnMouseClicked
        login.setVisible(true);
        signup.setVisible(false);
    }//GEN-LAST:event_registerbtnMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel confirmpassLabel;
    private javax.swing.JPasswordField confirmpassfield;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelRole;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel login;
    private javax.swing.JButton loginbtn;
    private javax.swing.JLabel loginbtn2;
    private javax.swing.JPanel mainlogin;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passfield;
    private javax.swing.JPasswordField passfield1;
    private javax.swing.JLabel qnbposlogo;
    private javax.swing.JLabel qnbposlogo2;
    private javax.swing.JButton registerbtn;
    private javax.swing.JPanel signup;
    private javax.swing.JLabel signupbtn;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userfield;
    private javax.swing.JTextField userfield1;
    // End of variables declaration//GEN-END:variables
}
