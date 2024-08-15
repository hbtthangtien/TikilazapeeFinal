/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Asus
 */
public class BrandDAO extends dbConfig {
    public BrandDAO(){
    super();
    }
    
    
    public String getNameBrandByID(int idBrand){
        String sql = "SELECT Brands.brandName FROM [Brands] WHERE Brands.brand_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBrand);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(new BrandDAO().getNameBrandByID(1));
    }
}
