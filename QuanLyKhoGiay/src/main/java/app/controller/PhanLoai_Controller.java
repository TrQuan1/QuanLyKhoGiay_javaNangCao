/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller;

import app.model.PhanLoai;
import app.DAO.PhanLoai_DAO;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Admin
 */
public class PhanLoai_Controller {
    private PhanLoai_DAO phanLoaiDAO = new PhanLoai_DAO();    
    
    public void getAllPhanLoai(DefaultComboBoxModel model){
        List<PhanLoai> dsPhanLoai = phanLoaiDAO.getAll();
        dsPhanLoai.sort(Comparator.comparing(PhanLoai::getId));
        
        for(PhanLoai pl : dsPhanLoai){
            model.addElement(pl);
        }
        
    }
}
