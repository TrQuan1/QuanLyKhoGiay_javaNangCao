/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view;

import app.controller.PhanLoai_Controller;
import app.model.PhanLoai;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Admin
 */
public class AddSanPham_View extends JFrame{
    private String imagePath;
    private JLabel lblImagePreview;
    private JButton btnLuu, btnThoat, btnChonAnh;
    private JTextField txtMaSp, txtTenSp, txtSoLuong, txtThuongHieu, txtSize, txtGiaNhap, txtGiaBan;
    private JTextArea txtMoTa;
    private DefaultComboBoxModel model;
    private PhanLoai_Controller  controller;
    private JComboBox<PhanLoai> cbbPhanLoai;
    private JComboBox<String> cbbMau, cbbSize;
    
    public AddSanPham_View(){
        initUI();
        
    }
    private void initUI() {
        this.setTitle("Quản lý sản phẩm");
        this.setSize(820, 320);
        this.setLocationRelativeTo(null);
        
        JPanel pannel = new JPanel();
        pannel.setLayout(new BorderLayout());
        
        
        
//Tạo North---------------------------------------------------------------------
        JPanel pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("Thêm sản phẩm");
        Font font = new Font("Tahoma", Font.BOLD, 22);
        lblTieuDe.setFont(font);
        lblTieuDe.setForeground(Color.blue);
        pnNorth.add(lblTieuDe);
        
//Tạo Center--------------------------------------------------------------------
        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.X_AXIS));
        Border bdNhapThongTin = BorderFactory.createLineBorder(Color.black);
        TitledBorder tltNhapThongTin = new TitledBorder(bdNhapThongTin, "Nhập thông tin");
        pnCenter.setBorder(tltNhapThongTin);
        
        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1, BoxLayout.Y_AXIS));
        
        JPanel pnMaSp = new JPanel();
        JLabel lblMaSp = new JLabel("Mã sản phẩm: ");
        txtMaSp = new JTextField(10);
        pnMaSp.add(lblMaSp);
        pnMaSp.add(txtMaSp);
        
        JPanel pnTenSp = new JPanel();
        JLabel lblTenSp = new JLabel("Tên sản phẩm:");
        txtTenSp = new JTextField(10);
        pnTenSp.add(lblTenSp);
        pnTenSp.add(txtTenSp);
        

        
        JPanel pnThuongHieu = new JPanel();
        JLabel lblThuongHieu = new JLabel("Thương hiệu: ");
        txtThuongHieu = new JTextField(10);
        pnThuongHieu.add(lblThuongHieu);
        pnThuongHieu.add(txtThuongHieu);
        
        pn1.add(pnMaSp);
        pn1.add(pnTenSp);
        pn1.add(pnThuongHieu);
        
        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2, BoxLayout.Y_AXIS));
        
        JPanel pnSize = new JPanel();
        JLabel lblSize = new JLabel("Size:         ");
        String[] Size = {"36", "37", "38", "39", "40", "41", "42                            "};
        cbbSize = new JComboBox(Size);
        pnSize.add(lblSize);
        pnSize.add(cbbSize);
        
        
        JPanel pnMau = new JPanel();
        JLabel lblMau = new JLabel("Màu:         ");
        String[] mau = {"Đen", "Trắng" ,"Đỏ" ,"Vàng" ,"Hồng                       "};
        cbbMau = new JComboBox(mau);
        pnMau.add(lblMau);
        pnMau.add(cbbMau);
        
        JPanel pnPhanLoai = new JPanel();
        JLabel lblPhanLoai = new JLabel("Phân loại:");
        model = new DefaultComboBoxModel();
        controller = new PhanLoai_Controller();
        controller.getAllPhanLoai(model);
        cbbPhanLoai = new JComboBox<>(model);
        pnPhanLoai.add(lblPhanLoai);
        pnPhanLoai.add(cbbPhanLoai);
        
        JPanel pnMoTa = new JPanel();
        JLabel lblMoTa = new JLabel("Mô tả:      ");
        txtMoTa = new JTextArea(4, 12);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        pnMoTa.add(lblMoTa);
        pnMoTa.add(txtMoTa);
        
        pn2.add(pnSize);
        pn2.add(pnMau);
        pn2.add(pnPhanLoai);
        pn2.add(pnMoTa);
        
        
        JPanel pn3 = new JPanel();
        pn3.setLayout(new BoxLayout(pn3, BoxLayout.Y_AXIS));
        
        JPanel pnGiaNhap = new JPanel();
        JLabel lblGiaNhap = new JLabel("Giá nhập: ");
        txtGiaNhap = new JTextField(10);
        pnGiaNhap.add(lblGiaNhap);
        pnGiaNhap.add(txtGiaNhap);
        
        JPanel pnGiaBan = new JPanel();
        JLabel lblGiaBan = new JLabel("Giá bán:   ");
        txtGiaBan = new JTextField(10);
        pnGiaBan.add(lblGiaBan);
        pnGiaBan.add(txtGiaBan);
        
        JPanel pnAnh = new JPanel();
        JLabel lblAnh = new JLabel("Hình ảnh:  ");
        lblImagePreview = new JLabel();
        lblImagePreview.setPreferredSize(new Dimension(100, 100));
        btnChonAnh = new JButton("Chọn ảnh");
        pnAnh.add(lblAnh);
        pnAnh.add(lblImagePreview);
        pnAnh.add(btnChonAnh);
        
        
        pn3.add(pnGiaNhap);
        pn3.add(pnGiaBan);
        pn3.add(pnAnh);
        
        
        
        pnCenter.add(pn1);
        pnCenter.add(pn2);
        pnCenter.add(pn3);
//Tạo South---------------------------------------------------------------------        
        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));
        
        JPanel pn11 = new JPanel();
        JPanel pn12 = new JPanel();
        
        JPanel pnChucNang = new JPanel();
        btnLuu = new JButton("Lưu");
        btnThoat = new JButton("Thoát");
        pnChucNang.add(btnLuu);
        pnChucNang.add(btnThoat);
        
        pnSouth.add(pn11);
        pnSouth.add(pn12);
        pnSouth.add(pnChucNang);
        
        pannel.add(pnNorth, BorderLayout.NORTH);
        pannel.add(pnCenter, BorderLayout.CENTER);
        pannel.add(pnSouth, BorderLayout.SOUTH);
        this.add(pannel);
    }

    public JButton getBtnLuu() {return btnLuu;}
    public void setBtnLuu(JButton btnLuu) {this.btnLuu = btnLuu;}
    public JButton getBtnThoat() {return btnThoat;}
    public void setBtnThoat(JButton btnThoat) {this.btnThoat = btnThoat;}
    public JTextField getTxtMaSp() {return txtMaSp;}
    public void setTxtMaSp(JTextField txtMaSp) {this.txtMaSp = txtMaSp;}
    public JTextField getTxtTenSp() {return txtTenSp;}
    public void setTxtTenSp(JTextField txtTenSp) {this.txtTenSp = txtTenSp;}
    public JTextField getTxtSoLuong() {return txtSoLuong;}
    public void setTxtSoLuong(JTextField txtSoLuong) {this.txtSoLuong = txtSoLuong;}
    public JTextField getTxtThuongHieu() {return txtThuongHieu;}
    public void setTxtThuongHieu(JTextField txtThuongHieu) {this.txtThuongHieu = txtThuongHieu;}
    public JTextField getTxtSize() {return txtSize;}
    public void setTxtSize(JTextField txtSize) {this.txtSize = txtSize;}
    public JTextField getTxtGiaNhap() {return txtGiaNhap;}
    public void setTxtGiaNhap(JTextField txtGiaNhap) {this.txtGiaNhap = txtGiaNhap;}
    public JTextField getTxtGiaBan() {return txtGiaBan;}
    public void setTxtGiaBan(JTextField txtGiaBan) {this.txtGiaBan = txtGiaBan;}
    public JTextArea getTxtMoTa() {return txtMoTa;}
    public void setTxtMoTa(JTextArea txtMoTa) {this.txtMoTa = txtMoTa;}
    public DefaultComboBoxModel getModel() {return model;}
    public void setModel(DefaultComboBoxModel model) {this.model = model;}
    public JComboBox<PhanLoai> getCbbPhanLoai() {return cbbPhanLoai;}
    public void setCbbPhanLoai(JComboBox<PhanLoai> cbbPhanLoai) {this.cbbPhanLoai = cbbPhanLoai;}
    public String getImagePath() {return imagePath;}
    public void setImagePath(String imagePath) {this.imagePath = imagePath;}
    public JButton getBtnChonAnh() {return btnChonAnh;}
    public void setBtnChonAnh(JButton btnChonAnh) {this.btnChonAnh = btnChonAnh;}
    public JLabel getLblImagePreview() {return lblImagePreview;}
    public void setLblImagePreview(JLabel lblImagePreview) {this.lblImagePreview = lblImagePreview;}
    public JComboBox<String> getCbbMau() {return cbbMau;}
    public void setCbbMau(JComboBox<String> cbbMau) {this.cbbMau = cbbMau;}
    public JComboBox<String> getCbbSize() {return cbbSize;}
    public void setCbbSize(JComboBox<String> cbbSize) {this.cbbSize = cbbSize;}
}
