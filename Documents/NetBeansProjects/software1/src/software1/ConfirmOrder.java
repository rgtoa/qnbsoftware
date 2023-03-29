/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;

/**
 *
 * @author 97433
 */
public class ConfirmOrder extends javax.swing.JPanel {

    /**
     * Creates new form ConfirmOrder
     */
    public ConfirmOrder() {
        initComponents();
    }
    public ConfirmOrder(String title) {
        confirmOrderLabel.setText(title);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmOrderBtn1 = new javax.swing.JButton();
        orderBackbtn1 = new javax.swing.JButton();
        confirmOrderLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(229, 229, 229));

        confirmOrderBtn1.setBackground(new java.awt.Color(35, 86, 108));
        confirmOrderBtn1.setForeground(new java.awt.Color(255, 255, 255));
        confirmOrderBtn1.setText("Confirm");

        orderBackbtn1.setBackground(new java.awt.Color(140, 208, 218));
        orderBackbtn1.setForeground(new java.awt.Color(34, 73, 87));
        orderBackbtn1.setText("Back");
        orderBackbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderBackbtn1ActionPerformed(evt);
            }
        });

        confirmOrderLabel.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        confirmOrderLabel.setForeground(new java.awt.Color(34, 73, 87));
        confirmOrderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmOrderLabel.setText("Confirm Order?");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(orderBackbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(confirmOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(confirmOrderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(confirmOrderLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderBackbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void orderBackbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderBackbtn1ActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_orderBackbtn1ActionPerformed
    public void confirmOrder(ActionListener event){
        confirmOrderBtn1.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmOrderBtn1;
    private javax.swing.JLabel confirmOrderLabel;
    private javax.swing.JButton orderBackbtn1;
    // End of variables declaration//GEN-END:variables
}
