/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.dbConfig.con;
import Model.Product.Product;
import java.util.List;
import Model.Image.ImageProduct;
import Model.Product.Color;
import Model.Product.ProductTypeColor;
import Model.Product.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends dbConfig {

    public ProductDAO() {
        super();
    }

    public int quantityOfProductById(int id_product) {
        int quantity;
        String sql = "select sum(pt.quantity) \n"
                + "from [ProductTypeColor] pt\n"
                + "where pt.product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_product);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt(1);
                return quantity;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getStockByProductCharacterics(int product_id, int type_id, int color_id) throws SQLException {

        String sql = "select sum(quantity) as stock\n"
                + "from [ProductTypeColor]\n"
                + "where product_id = ? and type_id = ? and color_id = ?;";
        PreparedStatement ppsm = con.prepareStatement(sql);
        ppsm.setInt(1, product_id);
        ppsm.setInt(2, type_id);
        ppsm.setInt(3, color_id);
        ResultSet rs = ppsm.executeQuery();
        int stock = 0;
        while (rs.next()) {
            stock = rs.getInt("stock");
        }
        return stock;
    }

    public Product getProductByIdProduct(int product_id) {
        String sql = "select p.product_id , p.category_id , p.product_name , p.brand_id , p.product_originPrice , p.product_percentSale ,  p.product_describes , p.store_id , i.imageProduct_url ,p.filter_id from [Products] p\n"
                + "join ImageProducts i on i.product_id = p.product_id\n"
                + "WHERE p.product_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String product_name = rs.getString(3);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                int filter_id = rs.getInt(10);
                String product_describes = rs.getString(7);
                String imageProduct = rs.getString(9);
                Product product = new Product();
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                 TypeDAO type = new TypeDAO();
                List<Type> listT = type.getListTypeByProductID(product_id);
                ColorDAO color = new ColorDAO();
                List<Color> listColor = color.getListColorByProductID(product_id);
                ProductTypeColorDAO productdetail = new ProductTypeColorDAO();
                List<ProductTypeColor> productd = productdetail.getAllOfProductTypeColor(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listImage);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListType(listT);
                product.setListColor(listColor);

                product.setProduct_Describes(product_describes);
                product.setFilter_id(filter_id);

                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Product> GetProductofSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "                         SELECT \n"
                + "                             p.product_id, \n"
                + "                             p.product_name,  \n"
                + "                             i.imageProduct_url,\n"
                + "                     p.product_originPrice,\n"
                + "                     pt.quantity,\n"
                + "                     p.product_percentSale,\n"
                + "                         c.name,\n"
                + "                             ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                         FROM \n"
                + "                             [Products] p\n"
                + "                             join \n"
                + "                     Stores s on p.store_id = s.store_id\n"
                + "                             join \n"
                + "                     Users u on s.store_id = u.user_id\n"
                + "                     JOIN \n"
                + "                             [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                     JOIN\n"
                + "                     Categories c on p.category_id = c.category_id\n"
                + "					 Join\n"
                + "					 ProductTypeColor pt on p.product_id = pt.product_id\n"
                + "                     WHERE u.user_id = ?\n"
                + "                         \n"
                + "                     )\n"
                + "                     SELECT Top 100\n"
                + "                         product_id, \n"
                + "                         product_name,\n"
                + "                     name,\n"
                + "                     imageProduct_url,\n"
                + "                         product_originPrice,\n"
                + "                     product_percentSale,\n"
                + "                     quantity\n"
                + "                     FROM\n"
                + "                         OrderedImages\n"
                + "                     WHERE \n"
                + "                         rn = 1;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                int product_quantity = rs.getInt(7);
                ImageDAO idb = new ImageDAO();
                String imageProduct = rs.getString(4);
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setCategory_id(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);

                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public void updateProduct(int product_id, String product_name, String category_name, long product_originPrice, int product_percentSale, int product_quantity) throws SQLException {
    }

    public List<Product> getListProductForHomePage() {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH RankedProducts AS (\n"
                + "                    SELECT \n"
                + "                        p.product_id, \n"
                + "                        p.product_name, \n"
                + "                        p.category_id, \n"
                + "                        p.product_originPrice,\n"
                + "                        p.product_percentSale,\n"
                + "                        c.[name] AS category_name,\n"
                + "                        i.imageProduct_url,\n"
                + "                        ROW_NUMBER() OVER (PARTITION BY p.category_id ORDER BY p.product_id) AS rn\n"
                + "                    FROM \n"
                + "                        [Products] p\n"
                + "                    JOIN \n"
                + "                        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                    JOIN \n"
                + "                        [Categories] c ON p.category_id = c.category_id\n"
                + "                ),\n"
                + "                UniqueCategoryProducts AS (\n"
                + "                    SELECT \n"
                + "                        product_id, \n"
                + "                        product_name, \n"
                + "                        category_id, \n"
                + "                        product_originPrice,\n"
                + "                        product_percentSale,\n"
                + "                        category_name,\n"
                + "                        imageProduct_url\n"
                + "                    FROM \n"
                + "                        RankedProducts\n"
                + "                    WHERE \n"
                + "                        rn = 1\n"
                + "                )\n"
                + "                SELECT TOP 20\n"
                + "                    product_id, \n"
                + "                    product_name, \n"
                + "                	category_id,\n"
                + "                    product_originPrice,\n"
                + "                    product_percentSale,\n"
                + "                    imageProduct_url\n"
                + "                FROM \n"
                + "                    UniqueCategoryProducts;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListTopSalesProductForHomePage() {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "        p.product_name, \n"
                + "        p.category_id, \n"
                + "        p.product_originPrice,\n"
                + "		p.product_percentSale,\n"
                + "		p.quantity,\n"
                + "        i.imageProduct_url,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		\n"
                + ")\n"
                + "SELECT Top 10\n"
                + "    product_id, \n"
                + "    product_name, \n"
                + "    category_id, \n"
                + "    product_originPrice,\n"
                + "	product_percentSale,\n"
                + "	quantity,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "\n"
                + "WHERE \n"
                + "    rn = 1\n"
                + "	ORDER BY product_percentSale DESC;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                int quantity = rs.getInt(6);
                String imageUrl = rs.getString(7);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListWishList(int userId) {
        List<Product> listProduct = new ArrayList();

        String sql = " SELECT             p.product_id, \n"
                + "                        p.product_name, \n"
                + "                        p.category_id, \n"
                + "                        p.product_originPrice,\n"
                + "                        p.product_percentSale,\n"
                + "                        c.[name] AS category_name,\n"
                + "                        ( select top 1 imageProduct_url from ImageProducts where product_id = p.product_id)\n"
                + "                    FROM \n"
                + "                        [Products] p\n"
                + "                    JOIN \n"
                + "                        [Categories] c ON p.category_id = c.category_id\n"
                + "						where p.product_id in (select product_id from Wishlist where customer_id = ?)";
        try {
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                  int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(7);
                System.out.println(originPrice);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public int avgStarRate(int product_id) {
        String sql = "SELECT AVG([feedback_rateStars]) AS AverageRateStars\n"
                + "FROM [dbo].[Feedbacks]\n"
                + "WHERE [product_id] = ?;";
        int avg = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                avg = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return avg;
    }

    public int totalProductOfCategory(int category_id) {
        String sql = "SELECT COUNT(*) AS TotalProducts\n"
                + "FROM [dbo].[Products]\n"
                + "WHERE [category_id] = ?;";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public int addWishList(int userId, String pid) {
        String sql = "INSERT INTO [dbo].[Wishlist]    ([customer_id] ,[product_id])  VALUES  (?,?)";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, pid);
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }

    public int deleteWishList(int userId, String pid) {
        String sql = "DELETE FROM [dbo].[Wishlist]  WHERE customer_id =? and product_id = ?";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, pid);
            return ps.executeUpdate();
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberPageForListAllProduct() {
        String sql = " select COUNT(*) from Products";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public int getNumberPageForListAllBlog() {
        String sql = " select COUNT(*) from Blog";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 8;
                if (total % 8 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberPageForListSaleProduct() {
        String sql = "select COUNT(*) from Products\n"
                + "where product_percentSale >=20";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = total / 12;
                if (total % 12 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getNumberOfProduct() {
        List<Product> listProduct = new ArrayList();
        String sql = "select * from [Products]";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct.size();
    }

    public List<Product> getListProductForAdmin() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY product_id";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByPrice() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY product_originPrice";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByNameAZ() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY product_name ASC";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> getListProductSortByQuantity() {
        List<Product> listProduct = new ArrayList();
        try {

            String sql = "select product_id, [product_name], product_originPrice, product_percentSale, category_id, brand_id, product_importDate, quantity\n"
                    + "                  from [Products] join [Stores]\n"
                    + "                 on [Products].store_id = [Stores].store_id\n"
                    + "                \n"
                    + "                 ORDER BY quantity";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String nameProduct = rs.getString(2);
                long originPrice = rs.getInt(3);
                int salePercent = rs.getInt(4);
                int id_Brand = rs.getInt(6);
                int id_Cata = rs.getInt(5);
                int quantity = rs.getInt(8);
                java.sql.Date inputDay = rs.getDate(7);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                product.setProduct_importDate((java.sql.Date) inputDay);
                product.setBrand_id(id_Brand);
                product.setCategory_id(id_Cata);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public Product getProduct(int productId) throws SQLException {
        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "		p.category_id,\n"
                + "        p.product_name, \n"
                + "         \n"
                + "        p.product_originPrice, \n"
                + "		p.product_percentSale,\n"
                + "        i.imageProduct_url,\n"
                + "\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		Where p.product_id = ?\n"
                + ")\n"
                + "SELECT \n"
                + "    product_id, \n"
                + "	category_id, \n"
                + "    product_name, \n"
                + "    \n"
                + "    product_originPrice, \n"
                + "	product_percentSale,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "WHERE \n"
                + "    rn = 1;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        Product product = null;
        while (rs.next()) {
            int product_id = rs.getInt(1);
            int categoryid = rs.getInt(2);
            String product_name = rs.getString(3);
            int product_originPrice = rs.getInt(4);
            int product_percentSale = rs.getInt(5);

            ImageDAO image = new ImageDAO();
            List<ImageProduct> listImage = image.getListImage(product_id);

            product = new Product();
            product.setProduct_id(product_id);
            product.setCategory_id(categoryid);
            product.setProduct_name(product_name);
            product.setProduct_originPrice(product_originPrice);
            product.setProduct_percenSale(product_percentSale);
            product.setListImage(listImage);

        }
        return product;
    }

    public List<Product> getListProductByCategoriesId(int category_id) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "    SELECT \n"
                + "        p.product_id, \n"
                + "		p.category_id,\n"
                + "        p.product_name, \n"
                + "         \n"
                + "        p.product_originPrice, \n"
                + "		p.product_percentSale,\n"
                + "        i.imageProduct_url,\n"
                + "\n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "    FROM \n"
                + "        [Products] p\n"
                + "    JOIN \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "		Where category_id = ?\n"
                + ")\n"
                + "SELECT \n"
                + "    product_id, \n"
                + "	category_id, \n"
                + "    product_name, \n"
                + "    \n"
                + "    product_originPrice, \n"
                + "	product_percentSale,\n"
                + "    imageProduct_url\n"
                + "FROM \n"
                + "    OrderedImages\n"
                + "WHERE \n"
                + "    rn = 1;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(2);
                String product_name = rs.getString(3);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getAllProductForPaging(int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (  \n"
                + "  SELECT   \n"
                + "           p.product_id,   \n"
                + "           p.product_name,   \n"
                + "           p.category_id,   \n"
                + "           p.product_originPrice,  \n"
                + "           p.product_percentSale,  \n"
                + "           i.imageProduct_url,  \n"
                + "           ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn  \n"
                + "           FROM   \n"
                + "             [Products] p  \n"
                + "           JOIN   \n"
                + "              [ImageProducts] i ON p.product_id = i.product_id  \n"
                + "                  	               )  \n"
                + "  SELECT  \n"
                + "           product_id,   \n"
                + "           product_name,   \n"
                + "           category_id,   \n"
                + "           product_originPrice,  \n"
                + "           product_percentSale,  \n"
                + "           imageProduct_url  \n"
                + " FROM   \n"
                + "            OrderedImages  \n"
                + " WHERE   \n"
                + "       rn = 1\n"
                + "	Order by product_id\n"
                + "	Offset ? rows\n"
                + "	FETCH NEXT 12 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(3);
                String product_name = rs.getString(2);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getListTopSaleProduct(int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (  \n"
                + "  SELECT   \n"
                + "        p.product_id,   \n"
                + "        p.product_name,   \n"
                + "        p.category_id,   \n"
                + "        p.product_originPrice,  \n"
                + "         		p.product_percentSale,  \n"
                + "        i.imageProduct_url,  \n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn  \n"
                + "    FROM   \n"
                + "        [Products] p  \n"
                + "    JOIN   \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id  \n"
                + "         	      )  \n"
                + "         SELECT  \n"
                + "    product_id,   \n"
                + "    product_name,   \n"
                + "    category_id,   \n"
                + "    product_originPrice,  \n"
                + "    product_percentSale,  \n"
                + "    imageProduct_url  \n"
                + "         FROM   \n"
                + "    OrderedImages  \n"
                + "         WHERE   \n"
                + "    rn = 1 and product_percentSale >=20\n"
                + "		 Order by product_percentSale DESC\n"
                + "		 Offset ? rows\n"
                + "		 FETCH NEXT 12 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int categoryid = rs.getInt(3);
                String product_name = rs.getString(2);
                int product_originPrice = rs.getInt(4);
                int product_percentSale = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(categoryid);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public Product getProductByID(int product_id) {
        TypeDAO db = new TypeDAO();
        ColorDAO dbc = new ColorDAO();
        ProductTypeColorDAO ptc = new ProductTypeColorDAO();
        String sql = "with productnew as(\n"
                + "select p.product_id,sum(ptc.quantity) as product_quantity from Products p join ProductTypeColor ptc \n"
                + "on p.product_id = ptc.product_id\n"
                + "group by p.product_id\n"
                + ")\n"
                + "select p.product_name, product_originPrice, product_percentSale, productnew.product_quantity, aa.imageProduct_url from [Products] p join productnew\n"
                + "on p.product_id = productnew.product_id\n"
                + "join (\n"
                + "	select top 1 i.product_id ,i.imageProduct_url from ImageProducts i\n"
                + "	where i.product_id = ?\n"
                + ") aa\n"
                + "on p.product_id = aa.product_id\n"
                + "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String product_name = rs.getString(1);
                long product_originPrice = rs.getLong(2);
                int product_percentSale = rs.getInt(3);
                int product_quantity = rs.getInt(4);
                String imageProduct = rs.getString(5);
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                List<Type> listType = db.getListTypeByProductID(product_id + "");
                List<Color> listColor = dbc.getListColorByProductID(product_id);
                List<ProductTypeColor> list= ptc.getProductTypeColorByProduct_ID(product_id);
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setListType(listType);
                product.setListColor(listColor);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setProduct_quantity(product_quantity);
                product.setListProductTypeColor(list);
                return product;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAO db = new ProductDAO();
        List<Product> list = new ArrayList<>();
        list = db.getListWishList(6);
        System.out.println(list.size());
    }

    public List<Product> getListProductByFilter(int filter_id) {
        List<Product> listProduct = new ArrayList();
        String sql = "select Products.product_id\n"
                + "from Products\n"
                + "where filter_id = ?\n"
                + "ORDER BY [Products].product_id;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, filter_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                Product product = this.getProduct(product_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> listProduct = new ArrayList();

        String sql = "select * from [Products] \n"
                + "where [Products].product_name like ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                int category_id = rs.getInt(2);
                int filter_id = rs.getInt(3);
                int brand_id = rs.getInt(4);
                int store_id = rs.getInt(5);
                String product_name = rs.getString(6);
                long product_originPrice = rs.getLong(7);
                int product_percenSale = rs.getInt(8);

                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);

                Product product = new Product();
                product.setProduct_id(product_id);
                product.setCategory_id(category_id);
                product.setFilter_id(filter_id);

                product.setBrand_id(brand_id);
                product.setStore_id(store_id);
                product.setProduct_name(product_name);

                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);

                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> getListProductForViewStore(int user_id, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (  \n"
                + "  SELECT   \n"
                + "        p.product_id,   \n"
                + "        p.product_name,   \n"
                + "        p.category_id,   \n"
                + "        p.product_originPrice,  \n"
                + "        p.product_percentSale,  \n"
                + "        i.imageProduct_url,  \n"
                + "        ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn  \n"
                + "    FROM   \n"
                + "        [Products] p  \n"
                + "    JOIN   \n"
                + "        [ImageProducts] i ON p.product_id = i.product_id  \n"
                + "			where p.store_id = ?\n"
                + "         	      )  \n"
                + "         SELECT  \n"
                + "    product_id,   \n"
                + "    product_name,   \n"
                + "    category_id,   \n"
                + "    product_originPrice,  \n"
                + "    product_percentSale,  \n"
                + "    imageProduct_url  \n"
                + "         FROM   \n"
                + "    OrderedImages  \n"
                + "         WHERE   \n"
                + "    rn = 1 \n"
                + "		 Order by product_id\n"
                + "		 Offset ? rows\n"
                + "		 FETCH NEXT 9 Rows only";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, (index - 1) * 9);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> searchByNameforGuest(String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                        SELECT
                     \t\t\t\t        
                                             p.product_id, 
                                             p.product_name,  
                                             p.product_originPrice,
                                             p.product_percentSale,
                                             p.quantity,
                                             i.imageProduct_url,
                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                    FROM 
                                            [Products] p
                                         JOIN
                                         [ImageProducts] i ON p.product_id = i.product_id
                     \t\t\t\t
                     \t\t\t\t\t where p.product_name like ? 
                                     )
                                     
                                      SELECT top 100
                     \t\t\t\t       
                                            product_id,
                                            product_name,
                                           product_originPrice,
                                             product_percentSale,
                                             quantity,
                                            imageProduct_url
                                        FROM
                                            RankedProducts
                                       WHERE 
                                            rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                int product_quantity = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setProduct_quantity(product_quantity);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return listProduct;
    }

    public List<Product> getListTopSalesProductForViewStore(int user_id) {
        List<Product> listProduct = new ArrayList();

        String sql = "WITH OrderedImages AS (\n"
                + "                   SELECT \n"
                + "                       p.product_id,\n"
                + "					   p.store_id,\n"
                + "                        p.product_name, \n"
                + "                       p.category_id, \n"
                + "                       p.product_originPrice,\n"
                + "                        p.product_percentSale,\n"
                + "                        i.imageProduct_url,\n"
                + "                           ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                 FROM \n"
                + "                       [Products] p\n"
                + "                  JOIN \n"
                + "                        [ImageProducts] i ON p.product_id = i.product_id\n"
                + "						where p.store_id = ?\n"
                + "                )\n"
                + "                SELECT\n"
                + "                  product_id, \n"
                + "                    product_name, \n"
                + "					category_id,\n"
                + "                   product_originPrice,\n"
                + "                    product_percentSale,\n"
                + "                    imageProduct_url\n"
                + "                FROM \n"
                + "                  OrderedImages\n"
                + "                WHERE\n"
                + "                rn = 1\n"
                + "				order by product_percentSale DESC";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt(1);
                String nameProduct = rs.getString(2);
                int category_id = rs.getInt(3);
                long originPrice = rs.getInt(4);
                int salePercent = rs.getInt(5);
                String imageUrl = rs.getString(6);
                List<ImageProduct> listImage = new ArrayList();
                Product product = new Product();
                product.setProduct_id(idProduct);
                product.setProduct_name(nameProduct);
                product.setProduct_originPrice(originPrice);
                product.setProduct_percenSale(salePercent);
                ImageProduct imageProduct = new ImageProduct();
                imageProduct.setImageProduct_url(imageUrl);
                listImage.add(imageProduct);
                product.setListImage(listImage);
                product.setCategory_id(category_id);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }

    public List<Product> searchByFilterforGuest(int filter_id) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                        SELECT
                     \t\t\t\t        
                                             p.product_id, 
                                             p.product_name,  
                                             p.product_originPrice,
                                             p.product_percentSale,
                                             p.quantity,
                                             i.imageProduct_url,
                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                    FROM 
                                            [Products] p
                                         JOIN
                                         [ImageProducts] i ON p.product_id = i.product_id
                     \t\t\t\t\tJOIN 
                     \t\t\t\t\t[Filters] f ON P.filter_id = f.filter_id
                     \t\t\t\t\t where f.filter_id  in ?
                                     )
                                     
                                      SELECT top 100
                     \t\t\t\t       
                                            product_id,
                                            product_name,
                                           product_originPrice,
                                             product_percentSale,
                                             quantity,
                                            imageProduct_url
                                        FROM
                                            RankedProducts
                                       WHERE 
                                            rn = 1;
                                            
                     """;
        try {

//            int list_id[] list_id[filter_id];
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, "(" + Arrays.toString(list_id) + ")");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                int product_quantity = rs.getInt(5);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setProduct_quantity(product_quantity);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public int totalProductOfStore(int user_id) {
        String sql = "SELECT COUNT(*) AS TotalProducts\n"
                + "FROM [dbo].[Products]\n"
                + "WHERE [store_id] = ?;;";
        int total = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return total;
    }

    public List<Product> sortByPriceMinforGuest(String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH RankedProducts AS (\n"
                + "                                        SELECT\n"
                + "                           \n"
                + "                                             p.product_id, \n"
                + "                                             p.product_name,  \n"
                + "                                             p.product_originPrice,\n"
                + "                                             p.product_percentSale,\n"
                + "                                             i.imageProduct_url,\n"
                + "                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn\n"
                + "                                    FROM \n"
                + "                                            [Products] p\n"
                + "                                         JOIN\n"
                + "                                         [ImageProducts] i ON p.product_id = i.product_id\n"
                + "\n"
                + "                      where p.product_name like ?\n"
                + "                                     )\n"
                + "                                     \n"
                + "                                      SELECT top 100\n"
                + "   \n"
                + "                                            product_id,\n"
                + "                                            product_name,\n"
                + "                                           product_originPrice,\n"
                + "                                             product_percentSale,\n"
                + "                                            imageProduct_url\n"
                + "                                        FROM\n"
                + "                                            RankedProducts\n"
                + "                                       WHERE \n"
                + "                                            rn = 1\n"
                + "                     order by product_originPrice asc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMaxforGuest(String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH RankedProducts AS (\n"
                + "                                        SELECT\n"
                + "                           \n"
                + "                                             p.product_id, \n"
                + "                                             p.product_name,  \n"
                + "                                             p.product_originPrice,\n"
                + "                                             p.product_percentSale,\n"
                + "                                             i.imageProduct_url,\n"
                + "                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn\n"
                + "                                    FROM \n"
                + "                                            [Products] p\n"
                + "                                         JOIN\n"
                + "                                         [ImageProducts] i ON p.product_id = i.product_id\n"
                + "\n"
                + "                      where p.product_name like ?\n"
                + "                                     )\n"
                + "                                     \n"
                + "                                      SELECT top 100\n"
                + "   \n"
                + "                                            product_id,\n"
                + "                                            product_name,\n"
                + "                                           product_originPrice,\n"
                + "                                             product_percentSale,\n"
                + "                                            imageProduct_url\n"
                + "                                        FROM\n"
                + "                                            RankedProducts\n"
                + "                                       WHERE \n"
                + "                                            rn = 1\n"
                + "                     order by product_originPrice desc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByTopSalesforGuest(String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH RankedProducts AS (
                                        SELECT
                     \t\t\t\t        
                                             p.product_id, 
                                             p.product_name,  
                                             p.product_originPrice,
                                             p.product_percentSale,
                                             i.imageProduct_url,
                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                    FROM 
                                            [Products] p
                                         JOIN
                                         [ImageProducts] i ON p.product_id = i.product_id
                     \t\t\t\t
                     \t\t\t\t\t where p.product_name like ? 
                                     )
                                     
                                      SELECT top 100
                     \t\t\t\t       
                                            product_id,
                                            product_name,
                                           product_originPrice,
                                             product_percentSale,
                                            imageProduct_url
                                        FROM
                                            RankedProducts
                                       WHERE 
                                            rn = 1
                     order by product_percentSale desc;
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public int count(String txt) {
        String sql = """
                   WITH RankedProducts AS (
                                      SELECT
                   \t\t\t\t        
                                           p.product_id, 
                                           p.product_name,  
                                           p.product_originPrice,
                                           p.product_percentSale,
                                           i.imageProduct_url,
                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn
                                  FROM 
                                          [Products] p
                                       JOIN
                                       [ImageProducts] i ON p.product_id = i.product_id
                   \t\t\t\t\twhere p.product_name like ?
                                   )
                                   
                                    SELECT 
                   \t\t\t\t       
                                          count(*)
                                      FROM
                                          RankedProducts
                                     WHERE 
                                          rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> PagingSearchForGuest(String txt, int index) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH RankedProducts AS (\n"
                + "                                        SELECT\n"
                + "                            \n"
                + "                                             p.product_id, \n"
                + "                                             p.product_name,  \n"
                + "                                             p.product_originPrice,\n"
                + "                                             p.product_percentSale,\n"
                + "                                             i.imageProduct_url,\n"
                + "                                         ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.product_id) AS rn\n"
                + "                     \n"
                + "                                    FROM \n"
                + "                                            [Products] p\n"
                + "                                         JOIN\n"
                + "                                         [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                     Join\n"
                + "                     ProductTypeColor pt on p.product_id = pt.product_id\n"
                + "                    \n"
                + "                     where p.product_name like ?\n"
                + "                                     )\n"
                + "                                     select product_id, \n"
                + "                                                             product_name,  \n"
                + "                                                             product_originPrice,\n"
                + "                                                             product_percentSale,\n"
                + "                                                             imageProduct_url from  \n"
                + "                                      (SELECT \n"
                + "                           ROW_NUMBER() OVER (order by product_id) as r, \n"
                + "                                           \n"
                + "                                       * FROM\n"
                + "                                            RankedProducts \n"
                + "                        where rn = 1 ) as x\n"
                + "                                       WHERE \n"
                + "                                            rn = 1 and \n"
                + "                        r between ?*6-5 and ?*6 ;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                long product_originPrice = rs.getLong(3);
                int product_percenSale = rs.getInt(4);
                ImageDAO image = new ImageDAO();
                List<ImageProduct> listImage = image.getListImage(product_id);
                Product product = new Product();
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percenSale);
                product.setListImage(listImage);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> searchByNameforSeller(int id_user, String txt) {
        List<Product> listProduct = new ArrayList();
        String sql = """
                     WITH OrderedImages AS (
                                              SELECT 
                                                  p.product_id, 
                                                  p.product_name,  
                                                  i.imageProduct_url,
                                                  p.product_originPrice,
                                                  pt.quantity,
                                                  p.product_percentSale,
                                                  c.name,
                                                  ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn
                                              FROM 
                                                  [Products] p
                                                  join 
                                          Stores s on p.store_id = s.store_id
                                                  join 
                                         Users u on s.store_id = u.user_id
                                         JOIN 
                                                  [ImageProducts] i ON p.product_id = i.product_id
                                          JOIN
                                         Categories c on p.category_id = c.category_id
                                         JOIN 
                                         ProductTypeColor pt on p.product_id = pt.product_id
                                          WHERE u.user_id = ? and p.product_name like ?
                                              
                                          )
                                          SELECT Top 100
                                              product_id, 
                                              product_name,
                                          name,
                                          imageProduct_url,
                                          product_originPrice,
                                          product_percentSale,
                                          quantity
                                          FROM
                                              OrderedImages
                                          WHERE 
                                              rn = 1;""";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ps.setString(2, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                int product_quantity = rs.getInt(7);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setCategory_id(product_id);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                product.setProduct_quantity(product_quantity);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMinforSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "                                                                   SELECT \n"
                + "                                                                       p.product_id, \n"
                + "                                                                       p.product_name,  \n"
                + "                                                                       i.imageProduct_url,\n"
                + "                                                                       p.product_originPrice,\n"
                + "                                                                       p.product_percentSale,\n"
                + "                                                                       c.name,\n"
                + "                                                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                                                                   FROM \n"
                + "                                                                       [Products] p\n"
                + "                                                                       join \n"
                + "                                                               Stores s on p.store_id = s.store_id\n"
                + "                                                                       join \n"
                + "                                                              Users u on s.store_id = u.user_id\n"
                + "                                                              JOIN \n"
                + "                                                                       [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                                                               JOIN\n"
                + "                                                              Categories c on p.category_id = c.category_id\n"
                + "                                                               WHERE u.user_id = ?\n"
                + "                                                                   \n"
                + "                                                               )\n"
                + "                                                               SELECT Top 100\n"
                + "                                                                   product_id, \n"
                + "                                                                   product_name,\n"
                + "                                                               name,\n"
                + "                                                               imageProduct_url,\n"
                + "                                                               product_originPrice,\n"
                + "                                                               product_percentSale\n"
                + "                                                               FROM\n"
                + "                                                                   OrderedImages\n"
                + "                                                               WHERE \n"
                + "                                                                   rn = 1\n"
                + "                                          order by product_originPrice asc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public List<Product> sortByPriceMaxforSeller(int id_user) {
        List<Product> listProduct = new ArrayList();
        String sql = "WITH OrderedImages AS (\n"
                + "                                                                   SELECT \n"
                + "                                                                       p.product_id, \n"
                + "                                                                       p.product_name,  \n"
                + "                                                                       i.imageProduct_url,\n"
                + "                                                                       p.product_originPrice,\n"
                + "                                                                       p.product_percentSale,\n"
                + "                                                                       c.name,\n"
                + "                                                                       ROW_NUMBER() OVER (PARTITION BY p.product_id ORDER BY i.imageProcduct_id) AS rn\n"
                + "                                                                   FROM \n"
                + "                                                                       [Products] p\n"
                + "                                                                       join \n"
                + "                                                               Stores s on p.store_id = s.store_id\n"
                + "                                                                       join \n"
                + "                                                              Users u on s.store_id = u.user_id\n"
                + "                                                              JOIN \n"
                + "                                                                       [ImageProducts] i ON p.product_id = i.product_id\n"
                + "                                                               JOIN\n"
                + "                                                              Categories c on p.category_id = c.category_id\n"
                + "                                                               WHERE u.user_id = ?\n"
                + "                                                                   \n"
                + "                                                               )\n"
                + "                                                               SELECT Top 100\n"
                + "                                                                   product_id, \n"
                + "                                                                   product_name,\n"
                + "                                                               name,\n"
                + "                                                               imageProduct_url,\n"
                + "                                                               product_originPrice,\n"
                + "                                                               product_percentSale\n"
                + "                                                               FROM\n"
                + "                                                                   OrderedImages\n"
                + "                                                               WHERE \n"
                + "                                                                   rn = 1\n"
                + "                                          order by product_originPrice desc;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String imageProduct = rs.getString(4);
                long product_originPrice = rs.getLong(5);
                int product_percentSale = rs.getInt(6);
                ImageDAO idb = new ImageDAO();
                Product product = new Product();
                List<ImageProduct> listIP = new ArrayList();
                ImageProduct imageP = new ImageProduct();
                imageP.setImageProduct_url(imageProduct);
                listIP.add(imageP);
                product.setProduct_id(product_id);
                product.setProduct_name(product_name);
                product.setListImage(listIP);
                product.setProduct_originPrice(product_originPrice);
                product.setProduct_percenSale(product_percentSale);
                listProduct.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

//    public int quantityOfProductById(int id_product) {
//        int quantity;
//        String sql = "select sum(pt.quantity) \n"
//                + "from [ProductTypeColor] pt\n"
//                + "where pt.product_id = ?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id_product);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//              quantity = rs.getInt(1);
//              return quantity;
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    
    public int getStoreIDByProductID(int product_id){
        String sql = "select store_id from Products where product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
