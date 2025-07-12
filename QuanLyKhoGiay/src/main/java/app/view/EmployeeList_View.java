/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.view;

import app.model.Employee;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class EmployeeList_View extends JFrame{
    private DefaultListModel<Employee> listModel;
    private JList<Employee> employeeJList;
    private JPanel detailPanel;
    private JLabel lblName, lblEmail, lblPhone, lblRole;
    private JLabel lblCanManage;
    
    public EmployeeList_View(){
        setTitle("Danh sách nhân viên");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        

        // JList bên trái
        listModel = new DefaultListModel<>();
        employeeJList = new JList<>(listModel);
        JScrollPane listScroll = new JScrollPane(employeeJList);
        listScroll.setPreferredSize(new Dimension(250, 400));
        add(listScroll, BorderLayout.WEST);

        // Panel chi tiết bên phải
        detailPanel = new JPanel(new GridLayout(5, 1));
        detailPanel.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));

        lblName = new JLabel("Họ tên: ");
        lblEmail = new JLabel("Email: ");
        lblPhone = new JLabel("SĐT: ");
        lblRole = new JLabel("Vai trò: ");
        lblCanManage = new JLabel("Quyền quản lý: ");
       

        detailPanel.add(lblName);
        detailPanel.add(lblEmail);
        detailPanel.add(lblPhone);
        detailPanel.add(lblRole);
        detailPanel.add(lblCanManage);
        add(detailPanel, BorderLayout.CENTER);
    }

    public void setEmployeeList(List<Employee> employees) {
        listModel.clear();
        for (Employee emp : employees) {
            listModel.addElement(emp);
        }
    }

    public void addEmployeeSelectionListener(ListSelectionListener listener) {
        employeeJList.addListSelectionListener(listener);
    }

    public int getSelectedEmployeeIndex() {
        return employeeJList.getSelectedIndex();
    }

    public void showEmployeeDetail(Employee emp) {
        lblName.setText("Họ tên: " + emp.getFullName());
        lblEmail.setText("Email: " + emp.getEmail());
        lblPhone.setText("SĐT: " + emp.getPhone());
        lblRole.setText("Vai trò: " + emp.getRole());
        lblCanManage.setText("Quyền quản lý: " + (emp.isCanManage() ? "Có" : "Không"));
    }
    
}
