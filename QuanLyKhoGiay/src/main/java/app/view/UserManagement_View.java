package app.view;

import app.model.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagement_View extends JFrame {
    private JTable userTable;
    private JButton btnUpdate;
    private DefaultTableModel model;

    public UserManagement_View(List<User> userList) {
        setTitle("Phân quyền người dùng");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Username", "Email", "Quyền quản lý"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // chỉ cho phép sửa cột quyền quản lý
            }
        };

        for (User user : userList) {
            model.addRow(new Object[]{user.getId(), user.getUsername(), user.getEmail(), user.isCanManage()});
        }

        userTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(userTable);

        btnUpdate = new JButton("Cập nhật quyền");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnUpdate, BorderLayout.SOUTH);

        add(panel);
    }

    public JTable getUserTable() {
        return userTable;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }
    
} 

