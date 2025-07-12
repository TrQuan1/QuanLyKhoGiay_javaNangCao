/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

/**
 *
 * @author Admin
 */
public class Kho {
    private String id;
    private String tenKho;

    public Kho() {
    }

    public Kho(String id, String tenKho) {
        this.id = id;
        this.tenKho = tenKho;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    @Override
    public String toString() {
        return tenKho;
    }

    
}
