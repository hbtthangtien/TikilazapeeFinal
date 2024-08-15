/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Blog.Blog;
import Model.Product.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class BlogDAO extends dbConfig{

    public BlogDAO() {
        super();
    }
     public List<Blog> getListBlog() 
     {
        List<Blog> listCata = new ArrayList<>();
        String sql = "select * from [Blog]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));

                Category category = new CategoryDAO().getCategoryById(rs.getInt(3));
                b.setCategory(category);
                listCata.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCata;
    }
     public List<Blog> getListBlogForSeller(int user_id) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT [blog_id]\n"
                + "      ,[user_id]\n"
                + "      ,[category_id]\n"
                + "      ,[blog_title]\n"
                + "      ,[blog_content]\n"
                + "      ,[blog_image]\n"
                + "      ,[blog_create_day]\n"
                + "  FROM [Tikilazapee].[dbo].[Blog] \n"
                + "  Where user_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                String blog_image = rs.getString(6);
                Date blog_create_day = rs.getDate(7);
                Blog blog = new Blog(blog_id, user_id, category_id, blog_title, blog_content, blog_image, blog_create_day);
                list.add(blog);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
     }
    
        
    public Blog getBlogByBlogId(int blog_id){
        
        String sql ="select * from Blog where blog_id = ?";
        Blog blog = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blog_id);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog_id = rs.getInt(1);
                int category_id = rs.getInt(3);
                int user_id = rs.getInt(2);
                String blog_title = rs.getString(4);
                String blog_content = rs.getString(5);
                Date blog_create_day = rs.getDate(7);
                String blog_image = rs.getString(6);
                blog = new Blog();
                blog.setBlog_id(blog_id);
                blog.setUser_id(user_id);
                blog.setCategory_id(category_id);
                blog.setBlog_title(blog_title);
                blog.setBlog_content(blog_content);
                blog.setBlog_image(blog_image);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    return blog;
    }
   public List<Blog> getBlogsByPage(int index) {
    List<Blog> listBlog = new ArrayList<>();
    String sql = "SELECT \n"
            + "    [user_id],\n"
            + "    [category_id],\n"
            + "    [blog_title],\n"
            + "    [blog_content],\n"
            + "    [blog_image],\n"
            + "    [blog_create_day]\n"
            + "FROM \n"
            + "    [Blog]\n"
            + "ORDER BY \n"
            + "    [blog_create_day] DESC\n"
            + "OFFSET ? ROWS\n"
            + "FETCH NEXT 8 ROWS ONLY;";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, (index - 1) * 8);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("user_id");
            int categoryId = rs.getInt("category_id");
            String blogTitle = rs.getString("blog_title");
            String blogContent = rs.getString("blog_content");
            String blogImage = rs.getString("blog_image");
            Date blogCreateDay = rs.getDate("blog_create_day");
            
            Blog blog = new Blog();
            blog.setBlog_content(blogContent);
            blog.setBlog_create_day( blogCreateDay);
            blog.setUser_id(userId);
            blog.setBlog_image(blogImage);
            blog.setBlog_title(blogTitle);
            blog.setCategory_id(categoryId);
            
            listBlog.add(blog);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return listBlog;
}
   public List<Blog> getAllBlogs() {
    List<Blog> listBlog = new ArrayList<>();
    String sql = "SELECT \n"
            + "    [user_id],\n"
            + "    [category_id],\n"
            + "    [blog_title],\n"
            + "    [blog_content],\n"
            + "    [blog_image],\n"
            + "    [blog_create_day]\n"
            + "FROM \n"
            + "    [Blog]\n"
            + "ORDER BY \n"
            + "    [blog_create_day] DESC;";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int user_id = rs.getInt("user_id");
            int category_id = rs.getInt("category_id");
            String blog_title = rs.getString("blog_title");
            String blog_content = rs.getString("blog_content");
            String blog_image = rs.getString("blog_image");
            Date blog_create_day = rs.getDate("blog_create_day");
            
            Blog blog = new Blog();
            blog.setBlog_content(blog_content);
            blog.setBlog_create_day( blog_create_day);
            blog.setUser_id(user_id);
            blog.setBlog_image(blog_image);
            blog.setBlog_title(blog_title);
            blog.setCategory_id(category_id);
            
            listBlog.add(blog);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();    }
    return listBlog;
}


    public static void main(String[] args) {
        BlogDAO bl = new BlogDAO();
        System.out.println(bl.getBlogsByPage(1));
    }

}
