/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.util.Base64;

public class AddNewUserComment extends javax.swing.JFrame {

    public AddNewUserComment(ExifGui2 parent) {
        initComponents();
        this.parent = parent;
        SavedjLabel.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        SavejButton = new javax.swing.JButton();
        CommentjTextField = new javax.swing.JTextField();
        SavedjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Add new user comment:");

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        SavejButton.setText("Save");
        SavejButton.setEnabled(false);
        SavejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavejButtonActionPerformed(evt);
            }
        });

        CommentjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CommentjTextFieldKeyTyped(evt);
            }
        });

        SavedjLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CommentjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SavedjLabel)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SavejButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CommentjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SavedjLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(SavejButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SavejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavejButtonActionPerformed
        String commentb64;
        commentb64 = Base64.getEncoder().encodeToString(this.CommentjTextField.getText().getBytes());
        OwnUtils.appendStrToFile(this.parent.comment_filename, commentb64 + System.lineSeparator());
        this.SavedjLabel.setText("Saved");
        this.parent.LoadUserCommentsList();
    }//GEN-LAST:event_SavejButtonActionPerformed

    private void CommentjTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CommentjTextFieldKeyTyped
        this.SavejButton.setEnabled(!this.CommentjTextField.getText().trim().isEmpty());
        this.SavedjLabel.setText("");
    }//GEN-LAST:event_CommentjTextFieldKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CommentjTextField;
    private javax.swing.JLabel SavedjLabel;
    private javax.swing.JButton SavejButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    ExifGui2 parent;
}
