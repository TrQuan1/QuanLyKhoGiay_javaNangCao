/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view;

import app.QuanLyQuyen;
import app.controller.Sanpham_Controller;
import app.model.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Admin
 */
public class SanPham_View extends JFrame{
    private JButton btnThemSanPham, btnSuaSanPham, btnXoaSanPham, btnTimKiem, btnLoc;
    private JTextField txtTimKiem, txtTu, txtDen;
    private DefaultTableModel model;
    private JTable tblSanpham;
    private JRadioButton radGiaNhap, radGiaBan;
    private JMenuBar menuBarr;
    private JMenu menu;
    private JMenuItem menuQLSanPham, menuQLNhapXuatKho, menuThongKeBaoCao, menuQuanLyKho;
    private JPanel mainPannel;
    
    public SanPham_View(User user){
        this.setTitle("Trang Quản Lý");
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        
        mainPannel = new JPanel();
        mainPannel.setLayout(new BoxLayout(mainPannel, BoxLayout.Y_AXIS));
        
        menuBarr = new JMenuBar();
        menu = new JMenu("Giao diện");
        menuQLSanPham = new JMenuItem("Quản lý sản phẩm");
        menuQLNhapXuatKho = new JMenuItem("Quản lý Nhập/xuất kho");
        menuQuanLyKho = new  JMenuItem("Quản lý kho");
        menuThongKeBaoCao = new JMenuItem("Thông kê và báo cáo");
        menu.add(menuQLSanPham);
        menu.add(menuQLNhapXuatKho);
        menu.add(menuQuanLyKho);
        menu.add(menuThongKeBaoCao);
        menuBarr.add(menu);
        setJMenuBar(menuBarr);
        
        
        // Áp dụng phân quyền
        QuanLyQuyen authManager = new QuanLyQuyen(user);
        authManager.applyPermissions(menuThongKeBaoCao, menuQuanLyKho);
        
//Tạo North---------------------------------------------------------------------
        JPanel pnNorth = new JPanel();
        JLabel lblTieuDe = new JLabel("Quản lý sản phẩm");
        Font font = new Font("Tahoma", Font.BOLD, 22);
        lblTieuDe.setFont(font);
        lblTieuDe.setForeground(Color.blue);
        pnNorth.add(lblTieuDe);
//End North---------------------------------------------------------------------




//Tạo South---------------------------------------------------------------------
        JPanel pnSouth = new JPanel();
        pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder tltBorder = new TitledBorder(border, "Chức năng");
        pnSouth.setBorder(tltBorder);
        
        JPanel pnTK = new JPanel();
        pnTK.setLayout(new BoxLayout(pnTK, BoxLayout.Y_AXIS));
        
        JPanel pnTimKiem = new JPanel();
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        txtTimKiem = new JTextField(10);
        btnTimKiem = new JButton("Tìm");
        
        
        JPanel pnKhoangGia = new JPanel();
        
        JPanel pnKhoangGia1 = new JPanel();
        pnKhoangGia1.setLayout(new BoxLayout(pnKhoangGia1, BoxLayout.Y_AXIS));
        Border bdKhoangGia = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder tltKhoangGia = new TitledBorder(bdKhoangGia, "Khoảng giá");
        pnKhoangGia1.setBorder(tltKhoangGia);
        
        JPanel pnRadButton = new JPanel();
        radGiaNhap = new JRadioButton("Giá nhập");
        radGiaBan = new JRadioButton("Giá bán");
        pnRadButton.add(radGiaNhap);
        pnRadButton.add(radGiaBan);
        
        JPanel pnNhapLieu = new JPanel();
        JLabel lblTu = new JLabel("Từ:");
        txtTu = new JTextField(7);
        JLabel lblDen = new JLabel("Đến:");
        txtDen = new JTextField(7);
        btnLoc = new JButton("Lọc");
        pnNhapLieu.add(lblTu);
        pnNhapLieu.add(txtTu);
        pnNhapLieu.add(lblDen);
        pnNhapLieu.add(txtDen);
        pnNhapLieu.add(btnLoc);
        

        pnKhoangGia1.add(pnRadButton);
        pnKhoangGia1.add(pnNhapLieu);
        pnKhoangGia.add(pnKhoangGia1);
        
        
        pnTimKiem.add(lblTimKiem);
        pnTimKiem.add(txtTimKiem);
        pnTimKiem.add(btnTimKiem);


        JPanel pnChucNang = new JPanel();
        btnThemSanPham = new JButton("Thêm");
        btnSuaSanPham = new JButton("Sửa");
        btnXoaSanPham = new JButton("Xóa");
        pnChucNang.add(btnThemSanPham);
        pnChucNang.add(btnSuaSanPham);
        pnChucNang.add(btnXoaSanPham);
        
        
        pnTK.add(pnTimKiem);
        pnTK.add(pnKhoangGia);
        
        pnSouth.add(pnTK);
        pnSouth.add(pnChucNang);
//End South---------------------------------------------------------------------        
  



//Tạo Center--------------------------------------------------------------------
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Size");
        model.addColumn("Màu sắc");
        model.addColumn("Mô tả");
        model.addColumn("Ảnh");
        model.addColumn("Thương hiệu");
        model.addColumn("Phân loại");
        model.addColumn("Giá nhập");
        model.addColumn("Giá bán");
        tblSanpham = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho phép chỉnh sửa bất kỳ ô nào
            }
        };
        Sanpham_Controller controller = new Sanpham_Controller(this);
        controller.getAllSanPham();
        styleTable(); 

        JScrollPane pane = new JScrollPane(tblSanpham);
//End Center--------------------------------------------------------------------        
        mainPannel.add(pnNorth);
        mainPannel.add(pane);
        mainPannel.add(pnSouth);
        this.add(mainPannel);    
    }


//getter and setter
    public JButton getBtnThemSanPham() {return btnThemSanPham;}
    public void setBtnThemSanPham(JButton btnThemSanPham) {this.btnThemSanPham = btnThemSanPham;}
    public JButton getBtnSuaSanPham() {return btnSuaSanPham;}
    public void setBtnSuaSanPham(JButton btnSuaSanPham) {this.btnSuaSanPham = btnSuaSanPham;}
    public JButton getBtnXoaSanPham() {return btnXoaSanPham;}
    public void setBtnXoaSanPham(JButton btnXoaSanPham) {this.btnXoaSanPham = btnXoaSanPham;}
    public JTextField getTxtTimKiem() {return txtTimKiem;}
    public void setTxtTimKiem(JTextField txtTimKiem) {this.txtTimKiem = txtTimKiem;}    
    public JButton getBtnTimKiem() {return btnTimKiem;}
    public void setBtnTimKiem(JButton btnTimKiem) {this.btnTimKiem = btnTimKiem;}
    public DefaultTableModel getModel() {return model;}
    public void setModel(DefaultTableModel model) {this.model = model;}
    public JTable getTblSanpham() {return tblSanpham;}
    public void setTblSanpham(JTable tblSanpham) {this.tblSanpham = tblSanpham;}
    public JRadioButton getRadGiaNhap() {return radGiaNhap;}
    public void setRadGiaNhap(JRadioButton radGiaNhap) {this.radGiaNhap = radGiaNhap;}
    public JRadioButton getRadGiaBan() {return radGiaBan;}
    public void setRadGiaBan(JRadioButton radGiaBan) {this.radGiaBan = radGiaBan;}
    public JTextField getTxtTu() {return txtTu;}
    public void setTxtTu(JTextField txtTu) {this.txtTu = txtTu;}
    public JTextField getTxtDen() {return txtDen;}
    public void setTxtDen(JTextField txtDen) {this.txtDen = txtDen;}
    public JButton getBtnLoc() {return btnLoc;}
    public void setBtnLoc(JButton btnLoc) {this.btnLoc = btnLoc;}
    public JMenuItem getMenuQLSanPham() {return menuQLSanPham;}
    public JMenuItem getMenuQLNhapXuatKho() {return menuQLNhapXuatKho;}
    public JMenuBar getMenuBarr() {return menuBarr;}
    public JMenu getMenu() {return menu;}
    public JPanel getMainPannel() {return mainPannel;}
    public JMenuItem getMenuThongKeBaoCao() {return menuThongKeBaoCao;}
    public JMenuItem getMenuQuanLyKho() {    return menuQuanLyKho;}
    
    
    private void styleTable() {
        // ===== HEADER =====
        JTableHeader header = tblSanpham.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(230, 230, 230)); // xám nhạt
        header.setForeground(Color.BLACK);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // ===== BODY =====
        tblSanpham.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tblSanpham.setShowGrid(true);
        tblSanpham.setGridColor(Color.LIGHT_GRAY);
        tblSanpham.setRowHeight(100); // chiều cao dòng
        tblSanpham.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblSanpham.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblSanpham.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblSanpham.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblSanpham.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblSanpham.getColumnModel().getColumn(7).setPreferredWidth(100);
        tblSanpham.getColumnModel().getColumn(8).setPreferredWidth(70);
        tblSanpham.getColumnModel().getColumn(9).setPreferredWidth(70);
       

        // Căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tblSanpham.getColumnCount(); i++) {
            if (i != 5) { // Không áp dụng cho cột ảnh
                tblSanpham.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Hiển thị hình ảnh đúng kích thước ở cột ảnh (cột 5)
        tblSanpham.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                if (value instanceof ImageIcon icon) {
                    Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(image));
                }
                return label;
            }
        });
    }
}
