
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;

public class DeliveryPopup extends javax.swing.JPanel {
    private final Long orderID;
    /**
     * Creates new form DeliveryPopup
     */
    public DeliveryPopup(Long orderID) {
        initComponents();
        this.orderID = orderID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        deliverynamefield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        backbtn2 = new javax.swing.JButton();
        setOngoingbtn = new javax.swing.JButton();
        deliverynamefield2 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(229, 229, 229));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(34, 73, 87));
        jLabel1.setText("Set On-going Delivery?");

        deliverynamefield.setForeground(new java.awt.Color(34, 73, 87));

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 73, 87));
        jLabel2.setText("Enter Delivery Men Names:");

        backbtn2.setBackground(new java.awt.Color(140, 208, 218));
        backbtn2.setForeground(new java.awt.Color(34, 73, 87));
        backbtn2.setText("Back");
        backbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtn2ActionPerformed(evt);
            }
        });

        setOngoingbtn.setBackground(new java.awt.Color(34, 73, 87));
        setOngoingbtn.setForeground(new java.awt.Color(255, 255, 255));
        setOngoingbtn.setText("Confirm");

        deliverynamefield2.setForeground(new java.awt.Color(34, 73, 87));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deliverynamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliverynamefield2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(setOngoingbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliverynamefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliverynamefield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backbtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setOngoingbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtn2ActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_backbtn2ActionPerformed
    
    public void addConfirmAction(ActionListener evt) {
        setOngoingbtn.addActionListener(evt);
        setOngoingbtn.addActionListener(event -> {
            System.out.println("updating order");
            //GET NAMES
            String names = deliverynamefield.getText().trim();
            String n = deliverynamefield2.getText().trim();
            if (!n.equals("")) names += "," + n;
            //CHECK FOR NAMES
            if (names.equals("")) {
                GlassPanePopup.showPopup(new Message("Please fill in required field"));
                return;
            }
            // CHECK DONE PROCEED TO UPDATE
            Database db = new Database();
            db.updateDelivery(orderID, 1, names);
            db.closeConnection();
            GlassPanePopup.closePopupLast();
            GlassPanePopup.showPopup(new Message("Update Success"));
            System.out.println("update complete");
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn2;
    private javax.swing.JTextField deliverynamefield;
    private javax.swing.JTextField deliverynamefield2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton setOngoingbtn;
    // End of variables declaration//GEN-END:variables
}
