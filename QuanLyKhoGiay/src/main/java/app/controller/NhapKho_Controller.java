/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.ChiTietPhieuNhap;
import app.model.Kho;
import app.DAO.NhapXuatKho_DAO;
import app.model.PhieuNhap;
import app.model.SanPham;
import app.view.DanhSachPhieu_View;
import app.view.NhapKho_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static javax.management.Query.value;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhapKho_Controller {
    private NhapXuatKho_DAO nhapXuatKhoDAO = new NhapXuatKho_DAO();
    private NhapKho_View nhapKhoPanel;
    

    public NhapKho_Controller(NhapKho_View nhapKhoPanel) {
        this.nhapKhoPanel = nhapKhoPanel;
        listenActioner();
    }
    //=======================Sự kiện============================= 
    private void listenActioner() {
        nhapKhoPanel.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                themSanPham();
            }
        });
        
        nhapKhoPanel.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luuPhieu();
            }
        });
        
        nhapKhoPanel.getBtnRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaSanPham();
            }
        });
        
        nhapKhoPanel.getTxtNguoiLap().setText(app.RememberHelper.currentUser.getFullName());
        nhapKhoPanel.getTxtNguoiLap().setEditable(false);// khóa không cho sửa tên
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
    private void themSanPham(){
        try {
            SanPham sp = (SanPham) nhapKhoPanel.getComboBoxSanPham().getSelectedItem();
            int soLuong = Integer.parseInt(nhapKhoPanel.getTxtSoLuong().getText());

            if (sp == null || soLuong <= 0) {
                JOptionPane.showMessageDialog(nhapKhoPanel, "Vui lòng chọn sản phẩm và nhập số lượng > 0.");
                return;
            }
            //định dạng
            DecimalFormat df = new DecimalFormat("#,###");
            double giaNhap = sp.getGiaNhap();
            double thanhTien = soLuong * giaNhap;
            //thêm sản phẩm vào bảng
            DefaultTableModel model = (DefaultTableModel) nhapKhoPanel.getTable().getModel();
            model.addRow(new Object[]{sp.getId(), sp.getTenSanPham(),sp.getSize(), soLuong, df.format(giaNhap), df.format(thanhTien)});
            
            //hiển thị số lượng và giá tiền            
            double tongTien = 0;
            int tongSoLuong = 0;
            for(int i = 0; i< model.getRowCount(); i++){
                String thanhTienStr = model.getValueAt(i, 5).toString().replace(",", "");
                tongTien += Double.parseDouble(thanhTienStr);
                
                tongSoLuong += Integer.parseInt(model.getValueAt(i, 3).toString());
            }
            nhapKhoPanel.getLbTotalQty().setText("Tổng số lượng: " + tongSoLuong + " sản phẩm");
            nhapKhoPanel.getLbTotalValue().setText("Tổng giá trị: " + df.format(tongTien) + " VND");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(nhapKhoPanel, "Số lượng không hợp lệ.");
        }
    }
    
    private void xoaSanPham() {
        int selectedRow = nhapKhoPanel.getTable().getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) nhapKhoPanel.getTable().getModel();
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
            nhapKhoPanel.getLbTotalQty().setText("Tổng số lượng: " + tongSoLuong + " sản phẩm");
            nhapKhoPanel.getLbTotalValue().setText("Tổng giá trị: " + df.format(tongTien) + " VND");
    } else {
        JOptionPane.showMessageDialog(nhapKhoPanel, "Vui lòng chọn một dòng để xóa!");
    }
}

    
    //này cũng thế gán cho nút Save
    private void luuPhieu() {
        String idPhieu = nhapXuatKhoDAO.sinhMaPhieuNhap();
        String nguoiLap = nhapKhoPanel.getTxtNguoiLap().getText().trim();  // đúng kiểu String
        String ngayLapStr = nhapKhoPanel.getTxtNgayLap().getText().trim(); // yyyy-MM-dd
        Kho chonKho = (Kho) nhapKhoPanel.getKhoComboBox().getSelectedItem(); // từ combo box
        String idKho = chonKho.getId();

        if (nguoiLap.isEmpty() || idKho == null || idKho.isEmpty()) {
            JOptionPane.showMessageDialog(nhapKhoPanel, "Vui lòng nhập đầy đủ thông tin phiếu!");
            return;
        }

        Date ngayLap;
        try {
            ngayLap = new SimpleDateFormat("yyyy-MM-dd").parse(ngayLapStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(nhapKhoPanel, "Định dạng ngày không hợp lệ!");
            return;
        }

        // Tạo phiếu nhập
        PhieuNhap pn = new PhieuNhap(idPhieu, nguoiLap, ngayLap, idKho, "");

        // Lấy danh sách chi tiết sản phẩm từ JTable
        List<ChiTietPhieuNhap> dsChiTiet = new ArrayList<>();
        DefaultTableModel model = nhapKhoPanel.getTableModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String idSP = model.getValueAt(i, 0).toString();
            int soLuong = (int) parseCurrency(model.getValueAt(i, 3).toString());
            double donGia = parseCurrency(model.getValueAt(i, 4).toString());
            dsChiTiet.add(new ChiTietPhieuNhap(idPhieu, idSP, soLuong, donGia));
        }

        // Gọi DAO để lưu
        boolean success = nhapXuatKhoDAO.luuPhieuNhap(pn, dsChiTiet);

        if (success) {
            JOptionPane.showMessageDialog(nhapKhoPanel, "Lưu phiếu nhập thành công!");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(nhapKhoPanel, "Lưu thất bại!");
            clearForm();
        }
    }
    //liên quan đến các cái dấu phẩy ở mấy cái đơn giá(Định dạng)
    private double parseCurrency(String value) {
        try {
            value = value.replace(".", "").replace(",", ""); // loại bỏ toàn bộ dấu
            return Double.parseDouble(value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    private void clearForm() {
        // Xóa thông tin phiếu
//        txtNgayLap.setText(java.time.LocalDate.now().toString());
        if (nhapKhoPanel.getKhoComboBox().getItemCount() > 0) {
        nhapKhoPanel.getKhoComboBox().setSelectedIndex(0);
        }

        // Xóa phần chọn sản phẩm
        nhapKhoPanel.getTxtSoLuong().setText("");
        if (nhapKhoPanel.getComboBoxSanPham().getItemCount() > 0) {
            nhapKhoPanel.getComboBoxSanPham().setSelectedIndex(0);
        }

        // Xóa bảng sản phẩm đã thêm
        nhapKhoPanel.getTableModel().setRowCount(0);

        // Reset tổng số lượng và giá trị
        nhapKhoPanel.getLbTotalQty().setText("Tổng số lượng: 0 sản phẩm");
        nhapKhoPanel.getLbTotalValue().setText("Tổng giá trị: 0 VND");
    }
    



}
