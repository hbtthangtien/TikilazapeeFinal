///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package DAO;
//
//import DAO.dbConfig;
//import static DAO.dbConfig.con;
//import Model.User.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author ACER
// */
//public class StoresDAO extends dbConfig {
//
//    public StoresDAO() {
//        super();
//    }
//
//    public Store getAllInfoStoresByProductId(int product_id) {
//        String sql = "select s.store_id , s.store_name , s.store_image , s.store_phone , s.store_address from Stores s\n"
//                + "join Products p on p.store_id = s.store_id\n"
//                + "where product_id = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, product_id);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                int store_id = rs.getInt(1);
//                String store_name = rs.getString(2);
//                String store_image = rs.getString(3);
//                String store_phone = rs.getString(4);
//                String store_address = rs.getString(5);
//                Store stores = new Store();
//                stores.getStore_name();
//                stores.getStore_image();
//                stores.getStore_phone();
//                stores.getStore_address();
//
//                return stores;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(StoresDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
//    public static void main(String[] args) {
//        StoresDAO db = new StoresDAO();
//        Store s = db.getAllInfoStoresByProductId(1);
//        
//    }
//}
