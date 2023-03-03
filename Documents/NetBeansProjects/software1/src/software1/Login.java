/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package software1;
import javax.swing.ImageIcon;
import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author 97433
 */
public class Login extends javax.swing.JFrame {
    
    //ImageIcon loginwv = new ImageIcon("try1.png");
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        GlassPanePopup.install(this);
        this.setLocationRelativeTo(null);
        jLabel1.setText("");
        //jLabel1.setIcon(loginwv);
        //jLabel8.setIcon(loginwv);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        userfield2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        passfield2 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jPanel20 = new javax.swing.JPanel();
        loginbtn = new javax.swing.JButton();
        signupbtn = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        signup = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        userfield1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPasswordField2 = new javax.swing.JPasswordField();
        passfield3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        registerbtn = new javax.swing.JButton();
        loginbtn2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        passfield1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

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

        jLabel9.setFont(new java.awt.Font("Source Sans Pro Light", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(10, 64, 83));
        jLabel9.setText("QNB");

        jLabel10.setForeground(new java.awt.Color(10, 64, 83));
        jLabel10.setText("Log in to start your water transactions!");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(285, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(288, 288, 288))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(344, 344, 344))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10))
        );

        login.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(229, 229, 229));
        jPanel17.setPreferredSize(new java.awt.Dimension(775, 175));

        jPanel18.setBackground(new java.awt.Color(229, 229, 229));
        jPanel18.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel18.setLayout(new java.awt.BorderLayout());

        userfield2.setBackground(new java.awt.Color(23, 90, 115));
        userfield2.setForeground(new java.awt.Color(255, 255, 255));
        userfield2.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        userfield2.setCaretColor(new java.awt.Color(23, 90, 115));
        userfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userfield2ActionPerformed(evt);
            }
        });
        jPanel18.add(userfield2, java.awt.BorderLayout.CENTER);

        jLabel11.setForeground(new java.awt.Color(10, 64, 83));
        jLabel11.setText("Username");
        jPanel18.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        jPanel17.add(jPanel18);

        jPanel19.setBackground(new java.awt.Color(229, 229, 229));
        jPanel19.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel19.setLayout(new java.awt.BorderLayout());

        passfield2.setForeground(new java.awt.Color(10, 64, 83));
        passfield2.setText("Password");
        jPanel19.add(passfield2, java.awt.BorderLayout.PAGE_START);

        jPasswordField3.setBackground(new java.awt.Color(23, 90, 115));
        jPasswordField3.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPasswordField3.setCaretColor(new java.awt.Color(23, 90, 115));
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });
        jPanel19.add(jPasswordField3, java.awt.BorderLayout.CENTER);

        jPanel17.add(jPanel19);

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

        jPanel17.add(jPanel20);

        login.add(jPanel17);

        jPanel21.setBackground(new java.awt.Color(229, 229, 229));
        jPanel21.setPreferredSize(new java.awt.Dimension(784, 125));

        jLabel1.setText("jLabel1");
        jPanel21.add(jLabel1);

        login.add(jPanel21);

        mainlogin.add(login, "card2");

        signup.setBackground(new java.awt.Color(229, 229, 229));
        signup.setPreferredSize(new java.awt.Dimension(775, 440));

        jPanel12.setBackground(new java.awt.Color(229, 229, 229));
        jPanel12.setPreferredSize(new java.awt.Dimension(775, 138));

        jLabel3.setFont(new java.awt.Font("Source Sans Pro Light", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(10, 64, 83));
        jLabel3.setText("QNB");

        jLabel7.setForeground(new java.awt.Color(10, 64, 83));
        jLabel7.setText("Log in to start your water transactions!");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(285, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(288, 288, 288))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(344, 344, 344))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7))
        );

        signup.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(229, 229, 229));
        jPanel13.setPreferredSize(new java.awt.Dimension(775, 175));

        jPanel4.setBackground(new java.awt.Color(229, 229, 229));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel4.setLayout(new java.awt.BorderLayout());

        userfield1.setBackground(new java.awt.Color(23, 90, 115));
        userfield1.setForeground(new java.awt.Color(255, 255, 255));
        userfield1.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        userfield1.setCaretColor(new java.awt.Color(23, 90, 115));
        userfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userfield1ActionPerformed(evt);
            }
        });
        jPanel4.add(userfield1, java.awt.BorderLayout.CENTER);

        jLabel4.setForeground(new java.awt.Color(10, 64, 83));
        jLabel4.setText("Username");
        jPanel4.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        jPanel13.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(229, 229, 229));
        jPanel5.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPasswordField2.setBackground(new java.awt.Color(23, 90, 115));
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPasswordField2.setCaretColor(new java.awt.Color(23, 90, 115));
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        jPanel5.add(jPasswordField2, java.awt.BorderLayout.CENTER);

        passfield3.setForeground(new java.awt.Color(10, 64, 83));
        passfield3.setText("Password");
        jPanel5.add(passfield3, java.awt.BorderLayout.PAGE_START);

        jPanel13.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(229, 229, 229));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 75));

        registerbtn.setBackground(new java.awt.Color(66, 155, 186));
        registerbtn.setForeground(new java.awt.Color(255, 255, 255));
        registerbtn.setText("Register");
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

        loginbtn2.setForeground(new java.awt.Color(10, 64, 83));
        loginbtn2.setText("Already have an account?");
        loginbtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginbtn2MouseClicked(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(23, 90, 115));
        jComboBox1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Staff", "Delivery" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        passfield1.setForeground(new java.awt.Color(10, 64, 83));
        passfield1.setText("Role:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(passfield1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 137, Short.MAX_VALUE)
                .addComponent(loginbtn2)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passfield1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(registerbtn)
                .addGap(0, 0, 0)
                .addComponent(loginbtn2)
                .addContainerGap())
        );

        jPanel13.add(jPanel6);

        signup.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(229, 229, 229));
        jPanel14.setPreferredSize(new java.awt.Dimension(784, 125));

        jLabel8.setPreferredSize(new java.awt.Dimension(775, 145));
        jPanel14.add(jLabel8);

        signup.add(jPanel14);

        mainlogin.add(signup, "card3");

        getContentPane().add(mainlogin);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userfield1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userfield1ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void registerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerbtnActionPerformed
        RegisterAuth obj = new RegisterAuth(
                userfield1.getText(),
                jPasswordField2.getPassword(),
                jComboBox1.getItemAt(jComboBox1.getSelectedIndex()).toLowerCase()
        );
        userfield1.setText("");
        jPasswordField2.setText("");
        jComboBox1.setSelectedIndex(0);
        obj.register(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
                signup.setVisible(false);
                login.setVisible(true);
                System.out.println("asdsadadsdas");
            }
        });
        GlassPanePopup.showPopup(obj);
    }//GEN-LAST:event_registerbtnActionPerformed

    private void loginbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginbtn2MouseClicked
        
    }//GEN-LAST:event_loginbtn2MouseClicked

    private void userfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userfield2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userfield2ActionPerformed

    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField3ActionPerformed

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        // TODO add your handling code here:
        System.out.println("login button clicked");
        String name = userfield2.getText();
        String pass = jPasswordField3.getText(); // set each char to 0 after for security
        
        System.out.println("name: " + name);
        System.out.println("pass: " + pass);
        // Database details
        Database db = new Database();
        if (db.verifyUser(name, pass)) {
            System.out.println("VERIFY SUCCESS");
            Main main = new Main(db.getRole(name), name);
            main.setVisible(true);
            this.dispose();
        }
        else {
            System.out.println("WRONG USERNAME OR PASSWORD");
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPanel login;
    private javax.swing.JButton loginbtn;
    private javax.swing.JLabel loginbtn2;
    private javax.swing.JPanel mainlogin;
    private javax.swing.JLabel passfield1;
    private javax.swing.JLabel passfield2;
    private javax.swing.JLabel passfield3;
    private javax.swing.JButton registerbtn;
    private javax.swing.JPanel signup;
    private javax.swing.JLabel signupbtn;
    private javax.swing.JTextField userfield1;
    private javax.swing.JTextField userfield2;
    // End of variables declaration//GEN-END:variables
}
