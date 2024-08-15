/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Order.Order;
import Model.Order.OrderDetail;
import Model.Product.Color;
import Model.Product.Product;
import Model.Product.Type;
import Model.ShoppingCart.ItemCart;
import Model.User.Store;
import Model.User.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hbtth
 */
public class OrderDAO extends dbConfig {

    public OrderDAO() {
        super();
    }

    public void placeOrder(String[] list, int payment_method) {
        ShoppingCartDAO db = new ShoppingCartDAO();
        ProductDAO pd = new ProductDAO();
        List<ItemCart> listItem = db.getItems(list);
        Map<ItemCart, Integer> map = new HashMap();
        Map<Integer, Integer> listID = new HashMap();
        Set<Integer> set = new HashSet();
        for (ItemCart i : listItem) {
            int store_id = pd.getStoreIDByProductID(i.getProduct_id());
            set.add(store_id);
            map.put(i, store_id);
        }
        for (int i : set) {
            int order_id = createOrder(listItem.get(0).getShoppingCart_id(), i, payment_method);
            listID.put(i, order_id);
        }
        for (Map.Entry<ItemCart, Integer> i : map.entrySet()) {
            int order_id = listID.get(i.getValue());
            db.deleteItemFromCart(i.getKey().getCartItem_id());
            createOrderDetail(i.getKey(), order_id);
        }
    }

    private int createOrder(int customer_id, int store_id, int payment_method) {
        String sql = "INSERT INTO [dbo].[Orders]([customer_id],[store_id],[order_totalPrice],[order_payment_method])\n"
                + "     VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ps.setInt(2, store_id);
            ps.setInt(3, 0);
            ps.setInt(4, payment_method);
            int x = ps.executeUpdate();
            if (x != 0) {
                return getOrderID();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private int getOrderID() {
        String sql = "select max(Orders.order_id) as id from Orders";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private void createOrderDetail(ItemCart item, int order_id) {
        String sql = "INSERT INTO [dbo].[OrderDetails]([order_id],[product_id],[type_id],[color_id],[unitPrice],[quantityProduct],[intoPrice])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ps.setInt(2, item.getProduct_id());
            ps.setInt(3, item.getType_id());
            ps.setInt(4, item.getColor_id());
            ps.setInt(5, item.getUnitPrice());
            ps.setInt(6, item.getQuantityProduct());
            ps.setLong(7, item.getIntoPrice());
            int x = ps.executeUpdate();
            if (x != 0) {
                long total_order = getTotalOrder(order_id);
                total_order += item.getIntoPrice();
                updateTotalOrder(order_id, total_order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTotalOrder(int order_id, long total_order) {
        String sql = "UPDATE [dbo].[Orders] SET [order_totalPrice] = ? WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, total_order);
            ps.setInt(2, order_id);
            int x = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getTotalOrder(int order_id) {
        String sql = "select order_totalPrice from [Orders] where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public List<Order> getOrderByUserID(int customer_id) {
        List<Order> list = new ArrayList<>();
        UserDAO db = new UserDAO();
        StoreDAO sd = new StoreDAO();
        String sql = "select * from Orders where customer_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int store_id = rs.getInt(3);
                long order_totalPrice = rs.getInt(4);
                Date order_dateOrder = rs.getDate(5);
                int order_status = rs.getInt(6);
                int order_payment_method = rs.getInt(7);
                int order_payment_status = rs.getInt(8);
                List<OrderDetail> listOrderDetail = getListOrderDetailByOrderID(order_id);
                User user = db.getUserById(order_id);
                Store store = sd.getStoreByIdForViewStore(store_id);
                Order order = new Order(order_id, "", customer_id, store_id, order_totalPrice, order_dateOrder, order_status, listOrderDetail);
                order.setOrder_payment_method(order_payment_method);
                order.setOrder_payment_status(order_payment_status);
                order.setUser(user);
                order.setStore(store);
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Order getOrderByOrderID(int order_id) {
        UserDAO db = new UserDAO();
        StoreDAO sd = new StoreDAO();
        String sql = "select * from Orders where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int customer_id = rs.getInt(2);
                int store_id = rs.getInt(3);
                long order_totalPrice = rs.getInt(4);
                Date order_dateOrder = rs.getDate(5);
                int order_status = rs.getInt(6);
                int order_payment_method = rs.getInt(7);
                int order_payment_status = rs.getInt(8);
                List<OrderDetail> listOrderDetail = getListOrderDetailByOrderID(order_id);
                User user = db.getUserById(order_id);
                Store store = sd.getStoreByIdForViewStore(store_id);
                Order order = new Order(order_id, "", customer_id, store_id, order_totalPrice, order_dateOrder, order_status, listOrderDetail);
                order.setOrder_payment_method(order_payment_method);
                order.setOrder_payment_status(order_payment_status);
                order.setUser(user);
                order.setStore(store);
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<OrderDetail> getListOrderDetailByOrderID(int order_id) {
        ProductDAO db = new ProductDAO();
        ColorDAO cd = new ColorDAO();
        TypeDAO td = new TypeDAO();
        List<OrderDetail> list = new ArrayList();
        String sql = "select * from OrderDetails where order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderDetail_id = rs.getInt(1);
                int product_id = rs.getInt(3);
                int type_id = rs.getInt(4);
                int color_id = rs.getInt(5);
                int unitPrice = rs.getInt(6);
                int quantityProduct = rs.getInt(7);
                long intoPrice = rs.getLong(8);
                Product product = db.getProductByID(product_id);
                Type type = td.getTypeByID(type_id);
                Color color = cd.getColorByID(color_id);
                OrderDetail o = new OrderDetail();
                o.setOrderDetail_id(orderDetail_id);
                o.setOrder_id(order_id);
                o.setColor_id(color_id);
                o.setType_id(type_id);
                o.setProduct_id(product_id);
                o.setUnitPrice(unitPrice);
                o.setQuantityProduct(quantityProduct);
                o.setIntoPrice(intoPrice);
                o.setProduct(product);
                o.setColor(color);
                o.setType(type);
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static void main(String[] args) {
        OrderDAO db = new OrderDAO();
        Order o = db.getOrderByOrderID(8);
        System.out.println(o.getListOrderDetail().get(0).getIntoPrice());
    }
}
