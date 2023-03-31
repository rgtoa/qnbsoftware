
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;

public class Message extends javax.swing.JPanel {

    public Message(String msg) {
        initComponents();
        message.setText(msg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        message = new javax.swing.JLabel();
        messagebtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(229, 229, 229));

        message.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        message.setForeground(new java.awt.Color(34, 73, 87));
        message.setText("Wrong Password!");

        messagebtn.setText("Okay");
        messagebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messagebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(message)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void messagebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messagebtnActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_messagebtnActionPerformed
    public void okay(ActionListener event) {
        messagebtn.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel message;
    private javax.swing.JButton messagebtn;
    // End of variables declaration//GEN-END:variables
}
