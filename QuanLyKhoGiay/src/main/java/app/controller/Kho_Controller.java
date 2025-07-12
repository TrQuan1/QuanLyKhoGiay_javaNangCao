/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Kho;
import app.DAO.Kho_DAO;
import app.view.Kho_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Kho_Controller {
    private Kho_DAO khoDAO = new Kho_DAO();
    private Kho_View view;

    public Kho_Controller() {
    }

    public Kho_Controller(Kho_View view) {
        this.view = view;
        this.khoDAO = new Kho_DAO();
        listenActioner();
    }
    //===========================Sự kiện==============================
    private void listenActioner() {
        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themKho();
            }
        });
        view.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaKho();
            }
        });
        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaKho();
            }
        } );
    }
    //================================================================
    public void getAllKho(DefaultComboBoxModel modelKho){
        List<Kho> dsKho = khoDAO.getAll();
        dsKho.sort(Comparator.comparing(Kho::getId));
        
        for(Kho kho : dsKho){
            modelKho.addElement(kho);
        }
    }
    
    private void themKho(){
        String id = JOptionPane.showInputDialog("Nhập mã kho:");
                if (id == null || id.trim().isEmpty()) return;

                String ten = JOptionPane.showInputDialog("Nhập tên kho:");
                if (ten == null || ten.trim().isEmpty()) return;

                Kho kho = new Kho();
                kho.setId(id);
                kho.setTenKho(ten);

                khoDAO.insert(kho);
                loadTable();
    }
    
    private void suaKho(){
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            String id = view.getTableModel().getValueAt(row, 0).toString();
            String ten = JOptionPane.showInputDialog("Nhập tên kho mới:");
            if (ten != null && !ten.trim().isEmpty()) {
                Kho kho = new Kho();
                kho.setId(id);
                kho.setTenKho(ten);
                khoDAO.update(kho);
                loadTable();
                view.getTable().repaint();
            }
        }
    }
    
    private void xoaKho(){
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            String id = view.getTableModel().getValueAt(row, 0).toString();
            try {
                khoDAO.delete(id);
                loadTable();
            } catch (Exception ex) {
                if (ex.getMessage() != null && ex.getMessage().contains("a foreign key constraint fails")) {
                    JOptionPane.showMessageDialog(
                        null,
                        "Không thể xóa kho vì đã có dữ liệu liên quan (phiếu nhập/xuất hoặc sản phẩm tồn kho)!",
                        "Lỗi ràng buộc dữ liệu",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                        null,
                        "Xảy ra lỗi khi xóa kho!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                null,
                "Vui lòng chọn một dòng để xóa!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    
    public void loadTable() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        List<Kho> list = khoDAO.getAll();
        for (Kho k : list) {
            model.addRow(new Object[]{k.getId(), k.getTenKho()});
        }
    }
}
