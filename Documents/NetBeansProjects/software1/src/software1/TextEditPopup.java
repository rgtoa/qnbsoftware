
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;

public class TextEditPopup extends javax.swing.JPanel {
    private final Object id;
    private final String colname;
    
    public TextEditPopup(String title, String subtitle, Long orderID, String s) {
        initComponents();
        this.id = orderID;
        this.colname = title;
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(s);
    }
    public TextEditPopup(String title, String subtitle, Long orderID, Float f) {
        initComponents();
        this.id = orderID;
        this.colname = title;
        ((PlainDocument) editfield.getDocument()).setDocumentFilter(new MyFloatFilter());
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(""+f);
    }
    public TextEditPopup(String title, String subtitle, String customerID, Long l) {
        initComponents();
        this.id = customerID;
        this.colname = title;
        ((PlainDocument) editfield.getDocument()).setDocumentFilter(new MyMobileNumberFilter());
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        if (l == null) editfield.setText("");
        else editfield.setText(""+l);
    }
    public TextEditPopup(String title, String subtitle, String customerID, String s) {
        initComponents();
        this.id = customerID;
        this.colname = title;
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(s);
    }
    public TextEditPopup(String title, String subtitle, Integer productID, Float f) {
        initComponents();
        this.id = productID;
        this.colname = title;
        ((PlainDocument) editfield.getDocument()).setDocumentFilter(new MyFloatFilter());
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(""+f);
    }
    public TextEditPopup(String title, String subtitle, Integer productID, String s) {
        initComponents();
        this.id = productID;
        this.colname = title;
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(s);
    }
    public TextEditPopup(String title, String subtitle, Integer productID, Integer i) {
        initComponents();
        this.id = productID;
        this.colname = title;
        ((PlainDocument) editfield.getDocument()).setDocumentFilter(new MyIntFilter());
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        editfield.setText(""+i);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltitle = new javax.swing.JLabel();
        lblsubtitle = new javax.swing.JLabel();
        cancelbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        editfield = new javax.swing.JTextField();

        setBackground(new java.awt.Color(229, 229, 229));

        lbltitle.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        lbltitle.setForeground(new java.awt.Color(34, 73, 87));
        lbltitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle.setText("<title>");
        lbltitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblsubtitle.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        lblsubtitle.setForeground(new java.awt.Color(34, 73, 87));
        lblsubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsubtitle.setText("<subtitle>");

        cancelbtn.setBackground(new java.awt.Color(235, 89, 89));
        cancelbtn.setForeground(new java.awt.Color(34, 73, 87));
        cancelbtn.setText("Cancel");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        savebtn.setBackground(new java.awt.Color(34, 73, 87));
        savebtn.setForeground(new java.awt.Color(255, 255, 255));
        savebtn.setText("Save");

        editfield.setForeground(new java.awt.Color(34, 73, 87));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editfield)
                    .addComponent(lblsubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbltitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsubtitle)
                .addGap(14, 14, 14)
                .addComponent(editfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelbtnActionPerformed
    
    public void save(ActionListener al) {
        savebtn.addActionListener(al);
        savebtn.addActionListener((ActionListener) -> {
            Database db = new Database();
            String value = editfield.getText();
            switch (colname) {
                case "ProductNames" -> db.updateProductNames((Long)id, value);
                case "ProductQTY" -> db.updateProductQTYs((Long)id, value);
                case "TotalPrice" -> db.updateTotalPrice((Long)id, Float.valueOf(value));
                case "AmountPaid" -> db.updateAmountPaid((Long)id, Float.valueOf(value));
                case "DeliveryMan" -> db.updateDeliveryMan((Long)id, value);
                case "LastName" -> db.updateLastName((String)id, value);
                case "FirstName" -> db.updateFirstName((String)id, value);
                case "Street" -> db.updateStreet((String)id, value);
                case "Barangay" -> db.updateBarangay((String)id, value);
                case "City" -> db.updateCity((String)id, value);
                case "MobileNumber" -> db.updateMobileNumber((String) id, value);
                case "Password" -> db.updatePassword((String) id, value);
                case "ProductName" -> db.updateProductName((Integer) id, value);
                case "Price" -> db.updatePrice((Integer) id, Float.valueOf(value));
                case "Stock" -> db.updateStock((Integer) id, Integer.valueOf(value));
            }
            GlassPanePopup.closePopupAll();
            db.closeConnection();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JTextField editfield;
    private javax.swing.JLabel lblsubtitle;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JButton savebtn;
    // End of variables declaration//GEN-END:variables
}
