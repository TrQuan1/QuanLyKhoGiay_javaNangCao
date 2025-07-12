package app.view;

import app.controller.DanhSachPhieu_Controller;
import app.model.Kho;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DanhSachPhieu_View extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> comboLoaiPhieu;
    private JComboBox<Kho> comboKho;
    private JTextField txtNgayBatDau, txtNgayKetThuc;
    private JButton btnLoc, btnXoa;
    private DanhSachPhieu_Controller controller;

    public DanhSachPhieu_View() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Tiêu đề
        JLabel title = new JLabel("DANH SÁCH PHIẾU NHẬP/XUẤT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204));
        add(title, BorderLayout.NORTH);

        // Panel lọc
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createTitledBorder("Lọc danh sách"));

        comboLoaiPhieu = new JComboBox<>(new String[]{"Tất cả", "Nhập", "Xuất"});
        comboKho = new JComboBox<>(new DefaultComboBoxModel<>());
        txtNgayBatDau = new JTextField(10);
        txtNgayKetThuc = new JTextField(10);
        btnLoc = new JButton("Lọc");
        btnXoa = new JButton("Xóa phiếu");

        filterPanel.add(new JLabel("Loại phiếu:"));
        filterPanel.add(comboLoaiPhieu);
        filterPanel.add(new JLabel("Kho:"));
        filterPanel.add(comboKho);
        filterPanel.add(new JLabel("Từ ngày (yyyy-MM-dd):"));
        filterPanel.add(txtNgayBatDau);
        filterPanel.add(new JLabel("Đến ngày (yyyy-MM-dd):"));
        filterPanel.add(txtNgayKetThuc);
        filterPanel.add(btnLoc);
        filterPanel.add(btnXoa);

        add(filterPanel, BorderLayout.NORTH);

        // Bảng danh sách phiếu
        String[] columns = {"ID Phiếu", "Loại Phiếu", "Người Lập", "Ngày Lập", "Kho", "Số lượng", "Tổng Giá Trị"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Vô hiệu hóa chỉnh sửa ô
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu"));
        add(scrollPane, BorderLayout.CENTER);

        // Khởi tạo controller
        controller = new DanhSachPhieu_Controller(this);
        controller.loadKhoComboBox(); // Load danh sách kho vào combo box
        comboKho.setSelectedIndex(0); // Chọn mục đầu tiên ("Tất cả")
    }

    // Getters
    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTable() { return table; }
    public JComboBox<String> getComboLoaiPhieu() { return comboLoaiPhieu; }
    public JComboBox<Kho> getComboKho() { return comboKho; }
    public JTextField getTxtNgayBatDau() { return txtNgayBatDau; }
    public JTextField getTxtNgayKetThuc() { return txtNgayKetThuc; }
    public JButton getBtnLoc() { return btnLoc; }
    public JButton getBtnXoa() { return btnXoa; }
}