package app.view;

import app.controller.ThongKeKho_Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ThongKeKho_View extends JPanel {
    private JButton btnIn, btnCapNhat;
    private JTable table;
    private JComboBox<String> comboKho;
    private DefaultComboBoxModel modelKho;
    private ThongKeKho_Controller controller;

    public ThongKeKho_View() {
        setLayout(new BorderLayout());

        // Panel trên cùng chứa combobox chọn kho
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Chọn kho:"));
        add(topPanel, BorderLayout.NORTH);
        
        // Panel dưới cùng chứa các nút
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnIn = new JButton("In");
        btnCapNhat = new JButton("Cập nhật");
        bottomPanel.add(btnCapNhat);
        bottomPanel.add(btnIn);
        
        // Tiêu đề bảng
        String[] columns = {"ID Kho", "ID Sản phẩm", "Tên sản phẩm", "Size", "Số lượng tồn"};
        table = new JTable();
        table.setModel(new DefaultTableModel(columns, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        //quân thêm (hiển thị tên kho ra JComboBox)
        modelKho = new DefaultComboBoxModel();
        controller = new ThongKeKho_Controller(this);
        controller.getAllKho(modelKho);
        comboKho = new JComboBox<>(modelKho);

        
        
        topPanel.add(comboKho);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnIn() {
        return btnIn;
    }

    public JButton getBtnCapNhat() {
        return btnCapNhat;
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox<String> getComboKho() {
        return comboKho;
    }
    
}
