package com.QLBN.Poly.View;

import com.QLBN.Poly.DAO.ManagerSecurityDAO;
import com.QLBN.Poly.Entity.Benh;
import com.QLBN.Poly.Entity.ManagerSecurity;
import com.QLBN.Poly.Service.BenhServiceImp;
import com.QLBN.Poly.Utils.Auth;
import com.QLBN.Poly.Utils.MsgBox;
import com.QLBN.Poly.Utils.XOther;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BenhJPanel extends javax.swing.JPanel {

    BenhServiceImp bsi = new BenhServiceImp();
    int index = -1;
    List<Benh> list = new ArrayList<>();
    List<Integer> lis = new ArrayList<>();

    public BenhJPanel() {
        initComponents();
    }

    void init() {
        this.FillDataTable(bsi.getAll());
        this.clear();
        if (Auth.isManager()) {
            ckbSelectAll.setVisible(true);
        } else {
            ckbSelectAll.setVisible(false);
        }
        btnDel.setVisible(false);
        btnRestore.setVisible(false);
        this.setStatus(true);
    }

    void FillDataTable(List<Benh> l) {
        String[] columnAdmin = {"STT", "Mã Bệnh", "Tên Bệnh", "Dấu Hiệu", "Xóa"};
        String[] column = {"STT", "Mã Bệnh", "Tên Bệnh", "Dấu Hiệu"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(Auth.isManager() ? columnAdmin : column, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
            }
        };
        boolean isChecked = false;
        tblBenh.setModel(defaultTableModel);
        DefaultTableModel model = (DefaultTableModel) tblBenh.getModel();
        model.setRowCount(0);
        int i = 0;
        for (Benh b : l) {
            Object[] rowData = new Object[5];
            rowData[0] = i + 1;
            rowData[1] = b.getMaBenh();
            rowData[2] = b.getTenBenh();
            rowData[3] = b.getDauHieu();
            rowData[4] = isChecked;
            model.addRow(rowData);
            i++;
        }
        tblBenh.setRowHeight(25);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblBenh.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
    }

    void clear() {
        XOther.isAlphabetic(' ', txtBenh);
        txtBenh.setText("");
        txtBenh.setToolTipText("");
        txtDauHieu.setText("");
        this.setStatus(true);
    }

    boolean checkForm() {
        String[] nameEr = {jLabelTenBenh.getText(), jLabelDauHieu.getText()};
        String[] er = {txtBenh.getText(), txtDauHieu.getText()};
        String mess = "";
        for (int i = 0; i < er.length; i++) {
            if (er[i].isEmpty()) {
                mess += nameEr[i] + ", ";
            }
        }
        if (!mess.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập " + mess.substring(0, mess.lastIndexOf(", ")));
            return false;
        } else if (txtBenh.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Tên bệnh phải là chữ");
            return false;
        } else if (txtDauHieu.getText().matches("^([0-9])*$")) {
            MsgBox.alert(this, "Dấu hiệu bệnh phải là chữ");
            return false;
        } else {
            return true;
        }
    }

    void insert() {
        if (checkForm()) {
            Benh b = getModel();
            if (bsi.insert(b) == 1) {
                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Bệnh", bsi.getRowLast() + "", "Thêm", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(bsi.getAll());
                this.clear();
                MsgBox.alert(this, "Thêm mới thành công");
            } else {
                MsgBox.alert(this, "Thêm mới thất bại");
            }
        }
    }

    void update() {
        if (checkForm()) {
            Benh b = getModel();
            if (bsi.update(b) == 1) {

                ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                        Auth.user.getVaiTro(), "Bệnh", b.getMaBenh() + "", "Cập Nhật", new Date()));
                ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                ManagerSecurityDAO.list.clear();

                this.FillDataTable(bsi.getAll());
                this.clear();
                MsgBox.alert(this, "Cập nhật thành công");
            } else {
                MsgBox.alert(this, "Cập nhật thất bại");
            }
        }
    }

    void setModel(Benh b) {
        txtBenh.setToolTipText(String.valueOf(b.getMaBenh()));
        txtBenh.setText(b.getTenBenh());
        txtDauHieu.setText(b.getDauHieu());
    }

    Benh getModel() {
        Benh b = new Benh();
        if (!txtBenh.getToolTipText().isEmpty()) {
            b.setMaBenh(Integer.parseInt(txtBenh.getToolTipText()));
        }
        b.setTenBenh(XOther.Rename(txtBenh.getText()));
        b.setDauHieu(txtDauHieu.getText());
        return b;
    }

    void setStatus(boolean insertable) {
        btnAdd.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblBenh.getRowCount() - 1;

        btnFirst.setEnabled(!insertable && first);
        btnPreviuos.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }

    void edit() {
        int id = (int) tblBenh.getValueAt(index, 1);
        Benh b = bsi.selectByID(id);
        if (b != null) {
            this.setModel(b);
            this.setStatus(false);
            tblBenh.setRowSelectionInterval(index, index);
            tblBenh.scrollRectToVisible(tblBenh.getCellRect(index, 0, true));
        }
    }

    void getData(int row, List<Benh> l) {
        int maBenh = (int) tblBenh.getValueAt(row, 1);
        String tenBenh = (String) tblBenh.getValueAt(row, 2);
        String dauHieu = (String) tblBenh.getValueAt(row, 3);

        l.add(new Benh(maBenh, tenBenh, dauHieu));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTenBenh = new javax.swing.JLabel();
        txtBenh = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabelDauHieu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDauHieu = new javax.swing.JTextArea();
        btnFirst = new javax.swing.JButton();
        btnPreviuos = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBenh = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnRestore = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        ckbSelectAll = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)), "Cập Nhật", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabelTenBenh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelTenBenh.setText("Tên Bệnh");

        txtBenh.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(64, 148, 94));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(64, 148, 94));
        btnUpdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(64, 148, 94));
        btnNew.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Làm Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabelDauHieu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDauHieu.setText("Dấu Hiệu");

        txtDauHieu.setColumns(20);
        txtDauHieu.setLineWrap(true);
        txtDauHieu.setRows(5);
        txtDauHieu.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDauHieu);

        btnFirst.setBackground(new java.awt.Color(64, 148, 94));
        btnFirst.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPreviuos.setBackground(new java.awt.Color(64, 148, 94));
        btnPreviuos.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnPreviuos.setForeground(new java.awt.Color(255, 255, 255));
        btnPreviuos.setText("<<");
        btnPreviuos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviuosActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(64, 148, 94));
        btnNext.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(64, 148, 94));
        btnLast.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPreviuos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTenBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDauHieu)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtBenh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabelTenBenh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBenh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelDauHieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPreviuos)
                    .addComponent(btnFirst))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 204, 255)), "Danh Sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        tblBenh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblBenh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBenhMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBenh);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

        txtSearch.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(153, 153, 153)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jLabel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(153, 153, 153)));
        jLabel12.setOpaque(true);

        btnRestore.setBackground(new java.awt.Color(64, 148, 94));
        btnRestore.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnRestore.setForeground(new java.awt.Color(255, 255, 255));
        btnRestore.setText("Khôi Phục");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });

        btnDel.setBackground(new java.awt.Color(64, 148, 94));
        btnDel.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        ckbSelectAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        ckbSelectAll.setText("Chọn Tất Cả");
        ckbSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbSelectAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnRestore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckbSelectAll)
                        .addGap(22, 22, 22))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRestore)
                        .addComponent(btnDel)
                        .addComponent(ckbSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QUẢN LÝ BỆNH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviuosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviuosActionPerformed
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPreviuosActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        this.index = tblBenh.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insert();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clear();
        tblBenh.setRowSelectionAllowed(false);
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblBenhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBenhMouseClicked
        tblBenh.setRowSelectionAllowed(true);
        this.index = tblBenh.rowAtPoint(evt.getPoint());
        if (this.index >= 0) {
            edit();
        }
        if (Auth.isManager()) {
            int row = tblBenh.getSelectedRow();
            boolean isChecked = (boolean) tblBenh.getValueAt(row, 4);
            boolean isExist = false;

            for (int i = 0; i < bsi.getMaBenh().size(); i++) {
                int id = Integer.valueOf(tblBenh.getValueAt(row, 1).toString());
                if (bsi.getMaBenh().get(i) == id) {
                    isExist = true;
                }
            }
            if (isChecked) {
                if (isExist) {
                    lis.add((int) tblBenh.getValueAt(row, 1));
                    getData(row, list);
                    btnDel.setVisible(true);
                } else {
                    MsgBox.alert(this, "Không thể xóa");
                    tblBenh.setValueAt(false, row, 4);
                }
            } else {
                int id = Integer.valueOf(tblBenh.getValueAt(row, 1).toString());
                for (int j = 0; j < lis.size(); j++) {
                    if (lis.get(j) == id) {
                        lis.remove(j);
                        list.remove(j);
                    }
                }
                if (lis.isEmpty()) {
                    btnDel.setVisible(false);
                }
                ckbSelectAll.setSelected(false);
            }
        }
    }//GEN-LAST:event_tblBenhMouseClicked

    private void ckbSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbSelectAllActionPerformed
        lis.clear();
        List<Integer> str = bsi.getMaBenh();
        for (int i = 0; i < tblBenh.getRowCount(); i++) {
            for (int j = 0; j < str.size(); j++) {
                if (ckbSelectAll.isSelected()) {
                    int id = Integer.valueOf(tblBenh.getValueAt(i, 1).toString());
                    if (str.get(j) == id) {
                        tblBenh.setValueAt(true, i, 4);
                        lis.add((int) tblBenh.getValueAt(i, 1));
                        getData(i, list);
                        btnDel.setVisible(true);
                    }
                } else {
                    lis.clear();
                    list.clear();
                    tblBenh.setValueAt(false, i, 4);
                    btnDel.setVisible(false);
                }
            }
        }
    }//GEN-LAST:event_ckbSelectAllActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (bsi.delete(lis) == 1) {
            String id = "";
            for (int i = 0; i < lis.size(); i++) {
                id += lis.get(i) + ", ";
            }
            ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                    Auth.user.getVaiTro(), "Bệnh", id.substring(0, id.lastIndexOf(", ")),
                    "Xóa", new Date()));
            ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
            ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
            ManagerSecurityDAO.list.clear();
            btnRestore.setVisible(true);
            btnDel.setVisible(false);
            lis.clear();
            this.FillDataTable(bsi.getAll());
            ckbSelectAll.setSelected(false);
            MsgBox.alert(this, "Xóa thành công");
        }
    }//GEN-LAST:event_btnDelActionPerformed
    int r;
    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        if (list.size() > 1) {
            Object[] obj = {"Khôi phục tất cả", "Khôi phục một dòng"};
            int indexes = JOptionPane.showOptionDialog(this, "Khôi phục " + list.size() + " dòng",
                    "Thông Báo", 0, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
            switch (indexes) {
                case 0: {
                    boolean isSucc = false;
                    String id = "";
                    for (Benh b : list) {
                        bsi.insertRetore(new Benh(b.getMaBenh(), b.getTenBenh(), b.getDauHieu()));
                        id += b.getMaBenh() + ", ";
                        btnRestore.setVisible(false);
                        this.FillDataTable(bsi.getAll());
                        isSucc = true;
                    }
                    if (isSucc) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                                Auth.user.getVaiTro(), "Bệnh", id.substring(0, id.lastIndexOf(", ")),
                                "Khôi Phục", new Date()));
                        ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                        ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                        ManagerSecurityDAO.list.clear();
                        list.clear();
                        id = "";
                    }
                    break;
                }
                case 1: {
                    r = list.size() - 1;
                    bsi.insertRetore(new Benh(list.get(r).getMaBenh(), list.get(r).getTenBenh(), list.get(r).getDauHieu()));
                    this.FillDataTable(bsi.getAll());
                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Bệnh", list.get(r).getMaBenh() + "",
                            "Khôi Phục", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();
                    list.remove(r);
                    if (list.isEmpty()) {
                        MsgBox.alert(this, "Khôi phục thành công");
                        btnRestore.setVisible(false);
                    }
                }
                default:
                    break;
            }
        } else {
            boolean isSucc = false;
            if (MsgBox.confirm(this, "Bạn có muốn khôi phục dữ liệu không?")) {
                for (Benh b : list) {
                    bsi.insertRetore(new Benh(b.getMaBenh(), b.getTenBenh(), b.getDauHieu()));

                    ManagerSecurityDAO.list.add(new ManagerSecurity(Auth.user.getMaNV(), Auth.user.getTenNV(),
                            Auth.user.getVaiTro(), "Bệnh Nhân", b.getMaBenh() + "",
                            "Khôi Phục", new Date()));
                    ManagerSecurityDAO.list.addAll(ManagerSecurityDAO.OpenFileRestore("\\TacVu.txt"));
                    ManagerSecurityDAO.SaveFileRestore(ManagerSecurityDAO.list, "\\TacVu.txt");
                    ManagerSecurityDAO.list.clear();
                    btnRestore.setVisible(false);
                    this.FillDataTable(bsi.getAll());
                    isSucc = true;
                }
                if (isSucc) {
                    MsgBox.alert(this, "Khôi phục thành công");
                    list.clear();
                }
            }
        }
    }//GEN-LAST:event_btnRestoreActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (txtSearch.getText().isEmpty()) {
            this.FillDataTable(bsi.getAll());
        } else {
            this.FillDataTable(bsi.timKiemTenBenh(txtSearch.getText()));
        }
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPreviuos;
    private javax.swing.JButton btnRestore;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox ckbSelectAll;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelDauHieu;
    private javax.swing.JLabel jLabelTenBenh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblBenh;
    private javax.swing.JTextField txtBenh;
    private javax.swing.JTextArea txtDauHieu;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
