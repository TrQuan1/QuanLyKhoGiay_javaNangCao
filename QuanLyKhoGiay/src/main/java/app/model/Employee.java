/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class Employee {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;
    private boolean canManage;

    public Employee() {
    }

    public Employee(String username, String fullName, String email, String phone, String role, boolean canManage) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.canManage = canManage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isCanManage() {
        return canManage;
    }

    public void setCanManage(boolean canManage) {
        this.canManage = canManage;
    }
    
    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
