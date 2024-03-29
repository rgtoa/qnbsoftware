
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;

public class Message extends javax.swing.JPanel {

    public Message(String msg) {
        initComponents();
        message.setText(msg);
    }
    public Message(String msg, String subt) {
        initComponents();
        message.setText(msg);
        subtitle.setText(subt);
        subtitle.setVisible(true);
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
        subtitle = new javax.swing.JLabel();

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

        subtitle.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        subtitle.setForeground(new java.awt.Color(34, 73, 87));
        subtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitle.setText("Wrong Password!");
        subtitle.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitle)
                .addGap(9, 9, 9)
                .addComponent(messagebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
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
    private javax.swing.JLabel subtitle;
    // End of variables declaration//GEN-END:variables
}
