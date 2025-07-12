/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author vumin
 */
import app.model.User;
import java.io.*;

public class RememberHelper {
    private static final String FILE = "remember.txt";
    //lưu toàn bộ thông tin đăng nhập hiện có
    public static User currentUser; 

    public static void saveUsername(String username) {
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadUsername() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}