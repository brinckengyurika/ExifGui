/**
 *
 * @author Gyorgy Brincken brinckengyurika@gmail.com
 * https://github.com/brinckengyurika/ExifGui
 * 
 */
package own.exifgui;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import java.awt.Graphics;
import java.awt.image.BufferStrategy; 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.apache.commons.imaging.common.ImageMetadata.ImageMetadataItem;


public final class ExifGui2 extends javax.swing.JFrame {

    public ExifGui2() {
        initComponents();
        this.location_filename = "Locations.txt";
        this.comment_filename = "UserComments.txt";
        this.actualImagePath = new Vector();
        this.allSelectedImagePath = new Vector();
        this.Canvas4Image.createBufferStrategy(1);
        this.bufferStrategy = this.Canvas4Image.getBufferStrategy();
        this.graphics = bufferStrategy.getDrawGraphics();
        this.selectedImageIndex = -1;
        this.currentMetaData = new ArrayList();
        this.locations_latlonobj = new Vector();
        this.usercomments_loaded = new Vector();
        this.locations_decoded = new Vector();
        this.usercomments_decoded = new Vector();
        this.LoadLocationList();
        this.LoadUserCommentsList();
        this.locationselectionindex = -1;
        this.commentselectionindex = -1;
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        ExitDialog = new javax.swing.JDialog();
        ExitjButton = new javax.swing.JButton();
        CanceljButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        LocationjList = new javax.swing.JList<>();
        AppendPlaceToSelectedjButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ImageExifsjList = new javax.swing.JList<>();
        jButtonAddNewPlace1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4ImageNames = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        SelectedImageNamesjList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonAppendPlaceToSelected1 = new javax.swing.JButton();
        jButtonAppendPlaceToSelected2 = new javax.swing.JButton();
        Canvas4Image = new java.awt.Canvas();
        jScrollPane5 = new javax.swing.JScrollPane();
        UserCommentsjList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButtonAddNewPlace2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        AbsolutePathjTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DeleteLocationjButton = new javax.swing.JButton();
        DeleteCommentjButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        LocationjComboBox = new javax.swing.JComboBox<>();
        StatusjTextField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        ExitjButton.setText("Exit");
        ExitjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitjButtonActionPerformed(evt);
            }
        });

        CanceljButton.setText("Cancel");
        CanceljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanceljButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExitDialogLayout = new javax.swing.GroupLayout(ExitDialog.getContentPane());
        ExitDialog.getContentPane().setLayout(ExitDialogLayout);
        ExitDialogLayout.setHorizontalGroup(
            ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitDialogLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(CanceljButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExitDialogLayout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addComponent(ExitjButton)
                .addGap(29, 29, 29))
        );
        ExitDialogLayout.setVerticalGroup(
            ExitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExitDialogLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(CanceljButton)
                .addGap(136, 136, 136)
                .addComponent(ExitjButton)
                .addGap(50, 50, 50))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMaximumSize(null);
        setMinimumSize(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        LocationjList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        LocationjList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LocationjListMouseClicked(evt);
            }
        });
        LocationjList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                LocationjListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(LocationjList);

        AppendPlaceToSelectedjButton.setText("Append the selected location and comment to the selected files");
        AppendPlaceToSelectedjButton.setEnabled(false);
        AppendPlaceToSelectedjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppendPlaceToSelectedjButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(ImageExifsjList);

        jButtonAddNewPlace1.setText("Add");
        jButtonAddNewPlace1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNewPlace1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Locations:");

        jLabel2.setText("Current Exif data:");

        jScrollPane4.setViewportView(jList4ImageNames);

        SelectedImageNamesjList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                SelectedImageNamesjListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(SelectedImageNamesjList);

        jButton1.setText("Remove from list");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonAppendPlaceToSelected1.setText("Up");
        jButtonAppendPlaceToSelected1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppendPlaceToSelected1ActionPerformed(evt);
            }
        });

        jButtonAppendPlaceToSelected2.setText("Down");
        jButtonAppendPlaceToSelected2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppendPlaceToSelected2ActionPerformed(evt);
            }
        });

        Canvas4Image.setBackground(new java.awt.Color(255, 255, 255));
        Canvas4Image.setPreferredSize(new java.awt.Dimension(110, 110));

        UserCommentsjList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UserCommentsjList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UserCommentsjListMouseClicked(evt);
            }
        });
        UserCommentsjList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                UserCommentsjListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(UserCommentsjList);

        jLabel3.setText("Comments:");

        jButtonAddNewPlace2.setText("Add");
        jButtonAddNewPlace2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNewPlace2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Directory:");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        AbsolutePathjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AbsolutePathjTextFieldKeyReleased(evt);
            }
        });

        jLabel5.setText("Selected files:");

        DeleteLocationjButton.setText("Delete");
        DeleteLocationjButton.setEnabled(false);
        DeleteLocationjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteLocationjButtonActionPerformed(evt);
            }
        });

        DeleteCommentjButton.setText("Delete");
        DeleteCommentjButton.setEnabled(false);
        DeleteCommentjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteCommentjButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Add all");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        LocationjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactly", "Circle", "Matrix" }));

        jMenuBar1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jMenuBar1ComponentResized(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Select directory");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAppendPlaceToSelected1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAppendPlaceToSelected2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AbsolutePathjTextField))
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Canvas4Image, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(AppendPlaceToSelectedjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(12, 12, 12)
                                .addComponent(LocationjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAddNewPlace1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteLocationjButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAddNewPlace2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteCommentjButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                    .addComponent(StatusjTextField))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAddNewPlace1)
                            .addComponent(DeleteLocationjButton)
                            .addComponent(LocationjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AbsolutePathjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Canvas4Image, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAddNewPlace2)
                        .addComponent(DeleteCommentjButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAppendPlaceToSelected1)
                    .addComponent(jButtonAppendPlaceToSelected2)
                    .addComponent(jButton1)
                    .addComponent(AppendPlaceToSelectedjButton)
                    .addComponent(StatusjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddNewPlace1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNewPlace1ActionPerformed
        AddNewLocation locationframe = new AddNewLocation(this);
        locationframe.pack();
        locationframe.setVisible(true);
        this.LoadLocationList();
    }//GEN-LAST:event_jButtonAddNewPlace1ActionPerformed

    private void openExitDialog() {
        int code = JOptionPane.showConfirmDialog(null, "Exit?", "Select", JOptionPane.YES_NO_OPTION);
        if (code == 0) {
            System.exit(0);
        }
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.openExitDialog();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void handlingNewPathSelected(File file) {
        String absolute_path = file.getAbsolutePath();
        String[] splitted_filename;
        String ext;
        String full_filepath;
        Vector<String> fileVector = new Vector();
        this.actualImagePath.clear();
        try (
                DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(absolute_path))) {
                for (Path path : stream) {
                    if (!Files.isDirectory(path)) {
                        splitted_filename = path.getFileName().toString().split("\\.");                            
                        if (splitted_filename.length == 2) {
                            ext = splitted_filename[1].trim();
                            if (ext.compareToIgnoreCase("jpg") == 0 || ext.compareToIgnoreCase("jpeg") == 0) {
                                full_filepath = Path.of(absolute_path, path.getFileName().toString()).toString();
                                fileVector.add(full_filepath);
                                this.actualImagePath.add(full_filepath);
                            }
                        }
                    }
                }
                this.jList4ImageNames.setListData(fileVector);
                this.AbsolutePathjTextField.setText(absolute_path);
        } catch (IOException ex) {
            Logger.getLogger(ExifGui2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void openFileChooser() {
        fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        this.fileChooser.setToolTipText("Only direcory is selectable.");
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.handlingNewPathSelected(file);
        }
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.openFileChooser();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ExitjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitjButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitjButtonActionPerformed

    private void CanceljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanceljButtonActionPerformed
        ExitDialog.setVisible(false);
    }//GEN-LAST:event_CanceljButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List <String> selected = this.SelectedImageNamesjList.getSelectedValuesList();
        ListIterator<String> li = selected.listIterator();
        String path;
        while(li.hasNext()) {
            path = li.next();
            this.allSelectedImagePath.remove(path);
        }
        this.SelectedImageNamesjList.setListData(this.allSelectedImagePath);       
        this.checkApplyIsExpectable();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void LoadLocationList() {
        try {
            List<String> content = OwnUtils.readFileInList(this.location_filename);
            this.locations_latlonobj.clear();
            this.locations_decoded.clear();
            Iterator<String> lociter = content.iterator();
            LatLonObj llobj;
            String original;
            while(lociter.hasNext()) {
                original = lociter.next();
                llobj = new LatLonObj(original);
                this.locations_latlonobj.add(llobj);                
                this.locations_decoded.add("%s (%s [Lat: %s; Lon: %s])".formatted(llobj.getLocation(), llobj.getLocationName(), llobj.getLatS(), llobj.getLonS()));
            }
            this.LocationjList.setListData(this.locations_decoded);
            this.LocationjList.setSelectedIndex(-1);
            this.DeleteLocationjButton.setEnabled(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void LoadUserCommentsList() {
        try {
            List<String> content = OwnUtils.readFileInList(this.comment_filename);
            this.usercomments_decoded.clear();
            this.usercomments_loaded.clear();
            Iterator<String> commentiter = content.iterator();
            Comment comment;
            String original;
            while(commentiter.hasNext()) {
                original = commentiter.next();
                comment = new Comment(original);
                this.usercomments_loaded.add(comment);
                this.usercomments_decoded.add(comment.getComment());
            }
            this.UserCommentsjList.setListData(this.usercomments_decoded);
            this.UserCommentsjList.setSelectedIndex(-1);
            this.DeleteCommentjButton.setEnabled(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkApplyIsExpectable() {
        if (!this.allSelectedImagePath.isEmpty() && (this.LocationjList.getSelectedIndex() > -1 ||
                this.UserCommentsjList.getSelectedIndex() > -1)) {
            this.AppendPlaceToSelectedjButton.setEnabled(true);
        } else {
            this.AppendPlaceToSelectedjButton.setEnabled(false);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List <String> selected = this.jList4ImageNames.getSelectedValuesList();
        ListIterator<String> li = selected.listIterator();
        String path;
        while(li.hasNext()) {
            path = li.next();
            if (!this.allSelectedImagePath.contains(path)) {
                this.allSelectedImagePath.add(path);
            }
        }
        this.SelectedImageNamesjList.setListData(this.allSelectedImagePath);
        this.checkApplyIsExpectable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void changeselectedAndShowImage() {
        int index = this.SelectedImageNamesjList.getSelectedIndex();
        if (!this.allSelectedImagePath.isEmpty() && index > -1) {
            String path = this.allSelectedImagePath.get(index);
            File file = new File(path);
            try {
                this.actualImage = ImageIO.read(file);
                if (this.actualImage != null) {
                    this.currentMetaData = OwnUtils.readExifdataHashMap(file);
                    Iterator <ImageMetadataItem> entry = this.currentMetaData.iterator();
                    Vector<String> newListitems = new Vector();
                    while (entry.hasNext()) {
                        newListitems.add(entry.next().toString());
                    }
                    this.ImageExifsjList.setListData(newListitems);                    
                    this.PaintToCanvas();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }        
        }
    }
    
    private void PaintToCanvas() {
        this.graphics.clearRect(0,0, this.Canvas4Image.getWidth(), this.Canvas4Image.getHeight());
        if (this.actualImage != null) {
            double canvas_x = (double) this.Canvas4Image.getWidth();
            double canvas_y = (double) this.Canvas4Image.getHeight();
            double img_x = (double) this.actualImage.getWidth();
            double img_y = (double) this.actualImage.getHeight();
            double ratio = Math.min(canvas_x/img_x, canvas_y/img_y);
            this.graphics.drawImage(this.actualImage, 0, 0, (int) Math.round(ratio*img_x), (int)Math.round(ratio*img_y),  this.Canvas4Image);
        }
    }
    
    private void stepSelectedImage(int direction) {
        int uplimit = this.allSelectedImagePath.size();
        int lowlimit = 0;
        if (uplimit == lowlimit) {
            return;
        }
        int newvalue = this.selectedImageIndex + direction;
        if (newvalue < lowlimit) {
            newvalue = lowlimit;
        } else if (newvalue > uplimit -1 ) {
            newvalue = uplimit -1;
        } 
        if (this.selectedImageIndex == newvalue) {
            return;
        } else {
            this.selectedImageIndex = newvalue;
        }
        this.SelectedImageNamesjList.setSelectedIndex(this.selectedImageIndex);
        this.changeselectedAndShowImage();
    }
    
    private void jButtonAppendPlaceToSelected1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAppendPlaceToSelected1ActionPerformed
        this.stepSelectedImage(-1);
    }//GEN-LAST:event_jButtonAppendPlaceToSelected1ActionPerformed

    private void jButtonAppendPlaceToSelected2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAppendPlaceToSelected2ActionPerformed
        this.stepSelectedImage(1);
    }//GEN-LAST:event_jButtonAppendPlaceToSelected2ActionPerformed

    private void SelectedImageNamesjListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_SelectedImageNamesjListValueChanged
        this.changeselectedAndShowImage();
        this.checkApplyIsExpectable();
    }//GEN-LAST:event_SelectedImageNamesjListValueChanged

    private void jMenuBar1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jMenuBar1ComponentResized
        this.PaintToCanvas();
    }//GEN-LAST:event_jMenuBar1ComponentResized

    private void jButtonAddNewPlace2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNewPlace2ActionPerformed
        AddNewUserComment usercommentframe = new AddNewUserComment(this);
        usercommentframe.pack();
        usercommentframe.setVisible(true);
    }//GEN-LAST:event_jButtonAddNewPlace2ActionPerformed

    private void LocationjListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_LocationjListValueChanged
        this.DeleteLocationjButton.setEnabled(true);        // TODO add your handling code here:
        this.checkApplyIsExpectable();
    }//GEN-LAST:event_LocationjListValueChanged

    private void UserCommentsjListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_UserCommentsjListValueChanged
        this.DeleteCommentjButton.setEnabled(true);
        this.checkApplyIsExpectable();
    }//GEN-LAST:event_UserCommentsjListValueChanged

    private void DeleteLocationjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteLocationjButtonActionPerformed
        int selected = this.LocationjList.getSelectedIndex();
        this.locations_latlonobj.remove(selected);
        OwnUtils.saveListVector(this.location_filename, this.locations_latlonobj);        
        this.LoadLocationList();
    }//GEN-LAST:event_DeleteLocationjButtonActionPerformed

    private void LocationjListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LocationjListMouseClicked
        if (this.locationselectionindex == this.LocationjList.getSelectedIndex()) {
            this.LocationjList.clearSelection();
            this.locationselectionindex = -1;
            this.DeleteLocationjButton.setEnabled(false);
        } else {
            this.locationselectionindex = this.LocationjList.getSelectedIndex();
        }
    }//GEN-LAST:event_LocationjListMouseClicked

    private void UserCommentsjListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UserCommentsjListMouseClicked
        if (this.commentselectionindex == this.UserCommentsjList.getSelectedIndex()) {
            this.UserCommentsjList.clearSelection();
            this.commentselectionindex = -1;
            this.DeleteCommentjButton.setEnabled(false);
        } else {
            this.commentselectionindex = this.UserCommentsjList.getSelectedIndex();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_UserCommentsjListMouseClicked

    private void DeleteCommentjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteCommentjButtonActionPerformed
        int selected = this.UserCommentsjList.getSelectedIndex();
        this.usercomments_loaded.remove(selected);
        OwnUtils.saveListVector(this.comment_filename, this.usercomments_loaded);
        this.LoadUserCommentsList();
    }//GEN-LAST:event_DeleteCommentjButtonActionPerformed

    private void AppendPlaceToSelectedjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppendPlaceToSelectedjButtonActionPerformed
        int max = this.allSelectedImagePath.size();
        this.StatusjTextField.setText("");

        Iterator<String> imagepath = this.allSelectedImagePath.iterator();
        String path;
        String comment = null;
        LatLonObj latlonobj = null;
        if (this.UserCommentsjList.getSelectedIndex() > -1) {
            comment = this.UserCommentsjList.getSelectedValue();
        }
        if (this.LocationjList.getSelectedIndex() > -1) {
            latlonobj = this.locations_latlonobj.get(this.LocationjList.getSelectedIndex());
        }
        int progress = 1;
        LatLonObj latlonobj_moved;
        while (imagepath.hasNext()) {
            path = imagepath.next();
            
            this.StatusjTextField.setText("Done: %d / %d (%s)".formatted(max, progress, path));
            
            try {
                if (latlonobj != null)  {
                    latlonobj_moved = latlonobj.movelatlon(this.LocationjComboBox.getSelectedItem(), progress, this.allSelectedImagePath.size());
                } else {
                    latlonobj_moved = null;
                }
                OwnUtils.changeExifMetadata(path, comment, latlonobj_moved);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ++progress;
        }
    }//GEN-LAST:event_AppendPlaceToSelectedjButtonActionPerformed

    private void AbsolutePathjTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbsolutePathjTextFieldKeyReleased
        String currenttext = this.AbsolutePathjTextField.getText();
        if (OwnUtils.DirectoryPathCheck(currenttext)) {
            File file = new File(currenttext);
            this.handlingNewPathSelected(file);            
        }
    }//GEN-LAST:event_AbsolutePathjTextFieldKeyReleased

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.openFileChooser();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.openExitDialog();
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Iterator <String> iter = this.actualImagePath.iterator();
        String path;
        while (iter.hasNext()) {
            path = iter.next();
            if (!this.allSelectedImagePath.contains(path)) {
                this.allSelectedImagePath.add(path);
            }
           
        }
        this.SelectedImageNamesjList.setListData(this.allSelectedImagePath);
        this.checkApplyIsExpectable();        
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExifGui2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AbsolutePathjTextField;
    private javax.swing.JButton AppendPlaceToSelectedjButton;
    private javax.swing.JButton CanceljButton;
    private java.awt.Canvas Canvas4Image;
    private javax.swing.JButton DeleteCommentjButton;
    private javax.swing.JButton DeleteLocationjButton;
    private javax.swing.JDialog ExitDialog;
    private javax.swing.JButton ExitjButton;
    private javax.swing.JList<String> ImageExifsjList;
    private javax.swing.JComboBox<String> LocationjComboBox;
    private javax.swing.JList<String> LocationjList;
    private javax.swing.JList<String> SelectedImageNamesjList;
    private javax.swing.JTextField StatusjTextField;
    private javax.swing.JList<String> UserCommentsjList;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAddNewPlace1;
    private javax.swing.JButton jButtonAddNewPlace2;
    private javax.swing.JButton jButtonAppendPlaceToSelected1;
    private javax.swing.JButton jButtonAppendPlaceToSelected2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList4ImageNames;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private final Vector <String> actualImagePath;
    private final Vector <String> allSelectedImagePath;
    private final BufferStrategy bufferStrategy;
    private final Graphics graphics;
    private BufferedImage actualImage;    
    private int selectedImageIndex;
    private List<ImageMetadataItem> currentMetaData;
    private final Vector<LatLonObj> locations_latlonobj;
    private final Vector<String> locations_decoded;
    private final Vector<Comment> usercomments_loaded;
    private final Vector<String> usercomments_decoded;
    public final String location_filename;
    public final String comment_filename;
    private int locationselectionindex;
    private int commentselectionindex;
}
