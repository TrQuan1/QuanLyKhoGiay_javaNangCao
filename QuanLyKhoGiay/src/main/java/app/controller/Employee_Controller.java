/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.Employee;
import app.DAO.User_DAO;
import app.view.EmployeeList_View;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class Employee_Controller {
    private EmployeeList_View view;
    private User_DAO userDAO;
    private List<Employee> employeeList;

    public Employee_Controller(EmployeeList_View view) {
        this.view = view;
        this.userDAO = new User_DAO();

        loadEmployeeList();
        setupListeners();
    }

    private void loadEmployeeList() {
        employeeList = userDAO.getAllEmployees();
        view.setEmployeeList(employeeList);
    }

    private void setupListeners() {
        view.addEmployeeSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = view.getSelectedEmployeeIndex();
                if (selectedIndex >= 0 && selectedIndex < employeeList.size()) {
                    Employee selected = employeeList.get(selectedIndex);
                    view.showEmployeeDetail(selected);
                }
            }
        });
    }
}
