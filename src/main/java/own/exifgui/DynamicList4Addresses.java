/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.util.Vector;

public class DynamicList4Addresses extends javax.swing.JFrame {


    public DynamicList4Addresses(Vector<String> elements, AddNewLocation parent) {
        initComponents();
        this.AddressList.setListData(elements);
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        AddressList = new javax.swing.JList<>();
        CanceldemosttesterjButton1 = new javax.swing.JButton();
        AddSelectedjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AddressList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        AddressList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                AddressListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(AddressList);

        CanceldemosttesterjButton1.setText("Cancel");
        CanceldemosttesterjButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanceldemosttesterjButton1ActionPerformed(evt);
            }
        });

        AddSelectedjButton.setText("Add selected");
        AddSelectedjButton.setEnabled(false);
        AddSelectedjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSelectedjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CanceldemosttesterjButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSelectedjButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CanceldemosttesterjButton1)
                    .addComponent(AddSelectedjButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CanceldemosttesterjButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanceldemosttesterjButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_CanceldemosttesterjButton1ActionPerformed

    private void AddressListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_AddressListValueChanged
        this.AddSelectedjButton.setEnabled(!this.AddressList.isSelectionEmpty());
    }//GEN-LAST:event_AddressListValueChanged

    private void AddSelectedjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSelectedjButtonActionPerformed
        this.parent.setSelectedLatLonObj(this.AddressList.getSelectedIndex());
        this.setVisible(false);
    }//GEN-LAST:event_AddSelectedjButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddSelectedjButton;
    private javax.swing.JList<String> AddressList;
    private javax.swing.JButton CanceldemosttesterjButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    AddNewLocation parent;
}
