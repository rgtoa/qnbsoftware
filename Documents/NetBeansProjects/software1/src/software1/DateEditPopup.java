
package software1;

import glasspanepopup.GlassPanePopup;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.text.PlainDocument;

public class DateEditPopup extends javax.swing.JPanel {
    private final Long orderID;
    private final String colname;
    
    public DateEditPopup(String title, String subtitle, Long orderID, Date d) {
        initComponents();
        this.orderID = orderID;
        this.colname = title;
        lbltitle.setText(title);
        lblsubtitle.setText(subtitle);
        if (d != null) {
            cbMonth.setSelectedIndex(d.getMonth());
            cbDay.setSelectedIndex(d.getDate()-1);
            cbYear.setSelectedIndex(d.getYear()-123);
        } else {
            LocalDate ld = LocalDate.now();
            cbMonth.setSelectedIndex(ld.getMonthValue()-1);
            cbDay.setSelectedIndex(ld.getDayOfMonth()-1);
            cbYear.setSelectedIndex(ld.getYear()-2023);
        }
        removebtn.setVisible(!colname.equals("DateOrdered") && d != null);
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
        cbMonth = new javax.swing.JComboBox<>();
        cbYear = new javax.swing.JComboBox<>();
        cbDay = new javax.swing.JComboBox<>();
        removebtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(229, 229, 229));

        lbltitle.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 24)); // NOI18N
        lbltitle.setForeground(new java.awt.Color(34, 73, 87));
        lbltitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle.setText("<title>");
        lbltitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblsubtitle.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 12)); // NOI18N
        lblsubtitle.setForeground(new java.awt.Color(34, 73, 87));
        lblsubtitle.setText("<subtitle>");

        cancelbtn.setBackground(new java.awt.Color(235, 89, 89));
        cancelbtn.setText("Cancel");
        cancelbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelbtnActionPerformed(evt);
            }
        });

        savebtn.setBackground(new java.awt.Color(34, 73, 87));
        savebtn.setForeground(new java.awt.Color(255, 255, 255));
        savebtn.setText("Save");

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cbMonth.setLightWeightPopupEnabled(false);

        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053" }));
        cbYear.setLightWeightPopupEnabled(false);

        cbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbDay.setLightWeightPopupEnabled(false);

        removebtn.setBackground(new java.awt.Color(102, 102, 102));
        removebtn.setForeground(new java.awt.Color(255, 255, 255));
        removebtn.setText("Remove");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

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
                    .addComponent(lblsubtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbltitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblsubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelbtnActionPerformed
        GlassPanePopup.closePopupLast();
    }//GEN-LAST:event_cancelbtnActionPerformed

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removebtnActionPerformed
    private void showMsg(String msg) {
        GlassPanePopup.showPopup(new Message(msg));
    }
    public void save(ActionListener al) {
        savebtn.addActionListener(al);
        savebtn.addActionListener((ActionListener) -> {
            try {
                //Test Date
                LocalDate ld = LocalDate.of(cbYear.getSelectedIndex()+2023, cbMonth.getSelectedIndex()+1, cbDay.getSelectedIndex()+1);
                System.out.println("new date: " + ld.toString());
                Database db = new Database();
                switch(colname) {
                    case "DateOrdered" -> db.updateDateOrdered(orderID, ld);
                    case "DatePaid" -> db.updateDatePaid(orderID, ld);
                    case "DeliveryDate" -> db.updateDeliveryDate(orderID, ld);
                }
                GlassPanePopup.closePopupAll();
                db.closeConnection();
            } catch (DateTimeException e) {
                showMsg("Invalid Date");
            }
        });
    }
    public void remove(ActionListener al) {
        removebtn.addActionListener(al);
        removebtn.addActionListener((ActionListener) -> {
            System.out.println("remove btn clicked " + colname);
            Database db = new Database();
            if (colname.equals("DatePaid")) db.updateDatePaid(orderID, null);
            else db.updateDeliveryDate(orderID, null);
            GlassPanePopup.closePopupAll();
            db.closeConnection();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelbtn;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private javax.swing.JLabel lblsubtitle;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JButton removebtn;
    private javax.swing.JButton savebtn;
    // End of variables declaration//GEN-END:variables
}
