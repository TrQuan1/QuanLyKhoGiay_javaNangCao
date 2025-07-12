package app.view;

import app.controller.Kho_Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class Kho_View extends JFrame {
    private JTable table;
    private JButton btnThem, btnSua, btnXoa;
    private DefaultTableModel tableModel;
    private JPanel khoPanel;

    public Kho_View() {
        setTitle("Quản lý kho");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        khoPanel = new JPanel(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID Kho", "Tên Kho"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho phép chỉnh sửa bất kỳ ô nào
            }
        };
        table = new JTable(tableModel);
        khoPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        khoPanel.add(btnPanel, BorderLayout.SOUTH);
        
        Kho_Controller controller = new Kho_Controller(this);
        controller.loadTable();

        add(khoPanel);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getKhoPanel() {
        return khoPanel;
    }


        
    
    
}
