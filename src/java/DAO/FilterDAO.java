/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.FilterProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterDAO extends dbConfig {

    public FilterDAO() {
        super();
    }

    public String getFilterOfCategories(int category_id) {
        String sql = "select [Filters].filter_id , [Filters].category_id,[Filters].nameFilter from [Filters] \n"
                + "JOIN [Categories] on [Categories].category_id = [Filters].category_id \n"
                + "where [Categories].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<FilterProduct> getListFilter(int category_id) {
        List<FilterProduct> listF = new ArrayList();

        String sql = "select * from  [Filters]\n"
                + "where [Filters].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int filter_id = rs.getInt(1);
                String nameFilter = rs.getString(3);
            FilterProduct filter = new FilterProduct();
            filter.setCategory_id(category_id);
            filter.setFilter_id(filter_id);
            filter.setNameFilter(nameFilter);
            listF.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listF;
    }
     public List<FilterProduct> getListFilter(int category_id , int filter_id) {
        List<FilterProduct> listf = new ArrayList();

        String sql = "select * from  [Filters]\n"
                + "where [Filters].category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                filter_id = rs.getInt(1);
                String nameFilter = rs.getString(3);
            FilterProduct filter = new FilterProduct();
            filter.setCategory_id(category_id);
            filter.setFilter_id(filter_id);
            filter.setNameFilter(nameFilter);
            listf.add(filter);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listf;
    }
     public List<FilterProduct> getListFilterForSearch(String txt){
        List<FilterProduct> listf = new ArrayList();
            String sql = """
                     WITH SearchProducts AS (
                                        SELECT 
                     \t\t\t\t       distinct p.category_id,
                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                    FROM 
                                            [Products] p
                                         JOIN
                                         [ImageProducts] i ON p.product_id = i.product_id
                     \t\t\t\t    
                     \t\t\t\t\t where p.product_name like ?
                                     )
                                     
                                      SELECT  
                     \t\t\t\t         f.filter_id,
                     \t\t\t\t\t\t f.nameFilter
                                        FROM
                                            SearchProducts s
                     \t\t\t\t\t   JOIN [Filters] f ON s.category_id = f.category_id
                                       WHERE 
                                            rn = 1;""";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            
            int idFilter = rs.getInt(1);
            String nameFilter = rs.getString(2);
            FilterProduct filter = new FilterProduct();
            filter.setNameFilter(nameFilter);
            filter.setFilter_id(idFilter);
            listf.add(filter);
            }
            } catch (SQLException ex){
               ex.printStackTrace();
        }
        return listf;
     }
    public static void main(String[] args) {
         FilterDAO df = new FilterDAO();
        System.out.println(df.getListFilterForSearch("ba").size());
    }
}
