/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.ChiTietPhieuXuat;
import app.model.Kho;
import app.DAO.NhapXuatKho_DAO;
import app.model.PhieuXuat;
import app.model.SanPham;
import app.view.NhapKho_View;
import app.view.XuatKho_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class XuatKho_Controller {
    private NhapXuatKho_DAO nhapXuatKhoDAO = new NhapXuatKho_DAO();
    private XuatKho_View xuatKhoPanel;

    public XuatKho_Controller(XuatKho_View xuatKhoPanel) {
        this.xuatKhoPanel = xuatKhoPanel;
        listenActioner();
    }
    //=======================Sự kiện============================= 
    private void listenActioner() {
        xuatKhoPanel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPham();
            }
        });
        
        xuatKhoPanel.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuPhieu();
            }
        });
        
        xuatKhoPanel.getBtnRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPham();
            }
        });
        
        //Lấy ra tên người đăng nhập
        xuatKhoPanel.getTxtNguoiLap().setText(app.RememberHelper.currentUser.getFullName());
        xuatKhoPanel.getTxtNguoiLap().setEditable(false);// khóa không cho sửa tên
    }
    //================================end=============================
    
    //hiển thị tên sản phẩm trên JComboBox
    public void getAllSanPham(DefaultComboBoxModel modelSanPham){
        List<SanPham> dsSanPham = nhapXuatKhoDAO.getAll();
        dsSanPham.sort(Comparator.comparing(SanPham::getId));
        
        for(SanPham sp : dsSanPham){
            modelSanPham.addElement(sp);
        }
    }
    
    //Phương thức này để gán vào cái sự kiện nút Add trên kia kìa
    public void themSanPham(){
        try {
            SanPham sp = (SanPham) xuatKhoPanel.getComboBoxSanPham().getSelectedItem();
            int soLuong = Integer.parseInt(xuatKhoPanel.getTxtSoLuong().getText());

            if (sp == null || soLuong <= 0) {
                JOptionPane.showMessageDialog(xuatKhoPanel, "Vui lòng chọn sản phẩm và nhập số lượng > 0.");
                return;
            }
            //định dạng
            DecimalFormat df = new DecimalFormat("#,###");
            double giaBan = sp.getGiaBan();
            double thanhTien = soLuong * giaBan;
            //thêm sản phẩm vào bảng
            DefaultTableModel model = (DefaultTableModel) xuatKhoPanel.getTable().getModel();
            model.addRow(new Object[]{sp.getId(), sp.getTenSanPham(),sp.getSize(), soLuong, df.format(giaBan), df.format(thanhTien)});
            
            //hiển thị số lượng và giá tiền            
            double tongTien = 0;
            int tongSoLuong = 0;
            for(int i = 0; i< model.getRowCount(); i++){
                String thanhTienStr = model.getValueAt(i, 5).toString().replace(",", "");
                tongTien += Double.parseDouble(thanhTienStr);
                
                tongSoLuong += Integer.parseInt(model.getValueAt(i, 3).toString());
            }
            xuatKhoPanel.getLbTotalQty().setText("Tổng số lượng: " + tongSoLuong + " sản phẩm");
            xuatKhoPanel.getLbTotalValue().setText("Tổng giá trị: " + df.format(tongTien) + " VND");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(xuatKhoPanel, "Số lượng không hợp lệ.");
        }
    }
    
    private void xoaSanPham() {
        int selectedRow = xuatKhoPanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) xuatKhoPanel.getTable().getModel();
            model.removeRow(selectedRow);
            //định dạng
            DecimalFormat df = new DecimalFormat("#,###");
            //hiển thị số lượng và giá tiền            
            double tongTien = 0;
            int tongSoLuong = 0;
            for(int i = 0; i< model.getRowCount(); i++){
                String thanhTienStr = model.getValueAt(i, 5).toString().replace(",", "");
                tongTien += Double.parseDouble(thanhTienStr);
                
                tongSoLuong += Integer.parseInt(model.getValueAt(i, 3).toString());
            }
            xuatKhoPanel.getLbTotalQty().setText("Tổng số lượng: " + tongSoLuong + " sản phẩm");
            xuatKhoPanel.getLbTotalValue().setText("Tổng giá trị: " + df.format(tongTien) + " VND");
    } else {
        JOptionPane.showMessageDialog(xuatKhoPanel, "Vui lòng chọn một dòng để xóa!");
    }
}   
    
        
    //này cũng thế gán cho nút Save
    public void luuPhieu() {
        String idPhieu = nhapXuatKhoDAO.sinhMaPhieuXuat();
        String nguoiLap = xuatKhoPanel.getTxtNguoiLap().getText().trim();
        String ngayLapStr = xuatKhoPanel.getTxtNgayLap().getText().trim();
        Kho kho = (Kho) xuatKhoPanel.getKhoComboBox().getSelectedItem();

        if (nguoiLap.isEmpty() || kho == null) {
            JOptionPane.showMessageDialog(xuatKhoPanel, "Vui lòng điền đầy đủ thông tin.");
            return;
        }

        Date ngayLap;
        try {
            ngayLap = new SimpleDateFormat("yyyy-MM-dd").parse(ngayLapStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(xuatKhoPanel, "Ngày không hợp lệ.");
            return;
        }

        List<ChiTietPhieuXuat> chiTietList = new ArrayList<>();
        DefaultTableModel model = xuatKhoPanel.getTableModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String idSP = model.getValueAt(i, 0).toString();
            int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
            double donGia = parseCurrency(model.getValueAt(i, 4).toString());

            chiTietList.add(new ChiTietPhieuXuat(idPhieu, idSP, soLuong, donGia));
        }

        PhieuXuat px = new PhieuXuat(idPhieu, nguoiLap, ngayLap, kho.getId(), "");

        if (nhapXuatKhoDAO.luuPhieuXuat(px, chiTietList)) {
            JOptionPane.showMessageDialog(xuatKhoPanel, "Lưu phiếu xuất thành công.");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(xuatKhoPanel, "Lưu phiếu xuất thất bại.");
            clearForm();
        }
    }
        
    //liên quan đến các cái dấu phẩy ở mấy cái đơn giá
    private double parseCurrency(String value) {
        try {
            value = value.replace(".", "").replace(",", ""); // loại bỏ toàn bộ dấu
            return Double.parseDouble(value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public void clearForm() {
        // Xóa thông tin phiếu
//        txtNgayLap.setText(java.time.LocalDate.now().toString());
        if (xuatKhoPanel.getKhoComboBox().getItemCount() > 0) {
        xuatKhoPanel.getKhoComboBox().setSelectedIndex(0);
        }

        // Xóa phần chọn sản phẩm
        xuatKhoPanel.getTxtSoLuong().setText("");
        if (xuatKhoPanel.getComboBoxSanPham().getItemCount() > 0) {
            xuatKhoPanel.getComboBoxSanPham().setSelectedIndex(0);
        }

        // Xóa bảng sản phẩm đã thêm
        xuatKhoPanel.getTableModel().setRowCount(0);

        // Reset tổng số lượng và giá trị
        xuatKhoPanel.getLbTotalQty().setText("Tổng số lượng: 0 sản phẩm");
        xuatKhoPanel.getLbTotalValue().setText("Tổng giá trị: 0 VND");
    }
    
}
