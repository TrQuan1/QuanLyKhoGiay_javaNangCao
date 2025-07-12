
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;

import app.controller.Login_Controller;
import app.view.Login_View;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login_View loginView = new Login_View();
            new Login_Controller(loginView);
            loginView.setVisible(true);
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            System.out.println("Không thể đặt giao diện hệ điều hành.");
//        }
        });
    }
}
