package app.controller;

import app.model.User;
import app.DAO.User_DAO;
import app.view.UserManagement_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserManagement_Controller {
    private final UserManagement_View view;
    private final User_DAO dao;

    public UserManagement_Controller(UserManagement_View view) {
        this.view = view;
        this.dao = new User_DAO();

        view.getBtnUpdate().addActionListener(e -> updatePermissions());
    }

    private void updatePermissions() {
        DefaultTableModel model = (DefaultTableModel) view.getUserTable().getModel();
        int rowCount = model.getRowCount();
        int updated = 0;

        for (int i = 0; i < rowCount; i++) {
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            boolean canManage = (Boolean) model.getValueAt(i, 3);

            if (dao.updatePermission(id, canManage)) {
                updated++;
            }
        }

        JOptionPane.showMessageDialog(view, "Cập nhật quyền cho " + updated + " người dùng thành công.");
    }

    public static void openIfManager(User user) {
        if (!"manager".equals(user.getRole()) && !user.isCanManage()) {
            JOptionPane.showMessageDialog(null, "Bạn không có quyền truy cập chức năng này.");
            return;
        }

        User_DAO dao = new User_DAO();
        List<User> users = dao.getAllUsers();
        UserManagement_View view = new UserManagement_View(users);
        new UserManagement_Controller(view);
        view.setVisible(true);
    }
}
