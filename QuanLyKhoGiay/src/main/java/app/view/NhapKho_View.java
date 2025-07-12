package app.view;


import app.controller.Kho_Controller;
import app.controller.NhapKho_Controller;
import app.model.Kho;
import app.model.SanPham;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhapKho_View extends JPanel {
    private Kho_Controller controllerKho;
    private NhapKho_Controller nhapController;
    private DefaultComboBoxModel modelKho, modelSanPham;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lbTotalQty, lbTotalValue;
    private JComboBox<Kho> khoComboBox;
    private JComboBox<SanPham> ComboBoxSanPham;
    private JTextField txtSoLuong;
    private JTextField txtNgayLap;
    private JTextField txtNguoiLap;
    private JButton btnAdd, btnSave, btnRemove;
    

    public NhapKho_View() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // === Tiêu đề ===
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);

        JLabel title = new JLabel("PHIẾU NHẬP KHO", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        topWrapper.add(title, BorderLayout.NORTH);

        // === Thông tin phiếu + chọn sản phẩm ===
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        topPanel.setBackground(Color.WHITE);

        // --- Panel thông tin phiếu ---
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setPreferredSize(new Dimension(350, 130));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin phiếu"));
        infoPanel.setBackground(Color.WHITE);

        txtNguoiLap = new JTextField();
        txtNgayLap = new JTextField(java.time.LocalDate.now().toString());
        
        
        modelKho = new DefaultComboBoxModel();
        controllerKho = new Kho_Controller();
        controllerKho.getAllKho(modelKho);
        khoComboBox = new JComboBox<>(modelKho);

        infoPanel.add(new JLabel("Người lập phiếu:"));
        infoPanel.add(txtNguoiLap);
        infoPanel.add(new JLabel("Ngày lập phiếu:"));
        infoPanel.add(txtNgayLap);
        infoPanel.add(new JLabel("Kho nhập:"));
        infoPanel.add(khoComboBox);

        // --- Panel chọn sản phẩm ---
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        selectPanel.setPreferredSize(new Dimension(500, 130));
        selectPanel.setBorder(BorderFactory.createTitledBorder("Chọn sản phẩm nhập"));
        selectPanel.setBackground(Color.WHITE);
        
        btnAdd = new JButton("Thêm sản phẩm");
        btnSave = new JButton("Lưu");
        btnRemove = new JButton("Xóa");
        modelSanPham = new DefaultComboBoxModel();
        nhapController = new NhapKho_Controller(this);
        nhapController.getAllSanPham(modelSanPham);
        ComboBoxSanPham = new JComboBox<>(modelSanPham);
        ComboBoxSanPham.setPreferredSize(new Dimension(200, 25));
        txtSoLuong = new JTextField(5);

        selectPanel.add(new JLabel("Sản phẩm:"));
        selectPanel.add(ComboBoxSanPham);
        selectPanel.add(new JLabel("Số lượng:"));
        selectPanel.add(txtSoLuong);
        selectPanel.add(btnAdd);

        topPanel.add(infoPanel);
        topPanel.add(selectPanel);
        topWrapper.add(topPanel, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH);

        // === Bảng sản phẩm ===
        String[] columns = {"ID", "Tên sản phẩm", "Size", "Số lượng", "Đơn giá", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm nhập kho"));
        add(scrollPane, BorderLayout.CENTER);

        // === Tổng cộng + lưu ===
        lbTotalQty = new JLabel("Tổng số lượng: 0 sản phẩm");
        lbTotalValue = new JLabel("Tổng giá trị: 0 VND");
        lbTotalQty.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbTotalValue.setFont(new Font("SansSerif", Font.BOLD, 14));

        

        btnSave.setPreferredSize(new Dimension(100, 30));

        JPanel totalPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        totalPanel.setBackground(Color.WHITE);
        totalPanel.add(lbTotalQty);
        totalPanel.add(lbTotalValue);
        totalPanel.add(btnSave);
        totalPanel.add(btnRemove);

        add(totalPanel, BorderLayout.SOUTH);
    }



    // === Getter cho Controller ===
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JLabel getLbTotalQty() { return lbTotalQty; }
    public JLabel getLbTotalValue() { return lbTotalValue; }
    public JTextField getTxtNguoiLap() { return txtNguoiLap; }
    public JComboBox<Kho> getKhoComboBox() { return khoComboBox; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnSave() { return btnSave; }
    public DefaultComboBoxModel getModelKho() {return modelKho;}
    public DefaultComboBoxModel getModelSanPham() {return modelSanPham;}
    public JComboBox<SanPham> getComboBoxSanPham() {return ComboBoxSanPham;}
    public JTextField getTxtSoLuong() {return txtSoLuong;}
    public JTextField getTxtNgayLap() {return txtNgayLap;}
    public JButton getBtnRemove() {return btnRemove;}
    
}
