package Database;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
    private String url = "jdbc:mysql://localhost:3306/database_lab_2";
    private String user = "root";
    private String password = "root";

    public Set<Product> findAllProducts() {
        Set<Product> products = new LinkedHashSet<>();
        try(Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Продукты");
            while (resultSet.next()) {
                String vendorCode = resultSet.getString(1);
                String productName = resultSet.getString(2);
                String productColor = resultSet.getString(3);
                Integer productPrice = resultSet.getInt(4);
                Integer productInStock = resultSet.getInt(5);
                Product product = new Product(vendorCode, productName, productColor, productPrice, productInStock);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Set<Product> findProductsById(Integer id) {
        Set<Product> products = new LinkedHashSet<>();
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Продукты LEFT JOIN Позиции_заказа ON Продукты.артикул = Позиции_заказа.артикул_товара WHERE Позиции_заказа.код_записи_заказа=?")){
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String vendorCode = resultSet.getString(1);
                String productName = resultSet.getString(2);
                String productColor = resultSet.getString(3);
                Integer productPrice = resultSet.getInt(4);
                Integer productInStock = resultSet.getInt(5);
                Product product = new Product(vendorCode, productName, productColor, productPrice, productInStock);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public void saveNewOrder(Order order){
        try (Connection connection = DriverManager.getConnection(url,user,password);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Заказы (дата_создания, ФИО_заказчика, контактный_телефон, адрес_эл_почты, адрес_доставки, статус_заказа) VALUES (?,?,?,?,?,?)")) {
            ps.setDate(1,order.getCreationDate());
            ps.setString(2,order.getFullName());
            ps.setString(3, order.getTelephone());
            ps.setString(4, order.getEmail());
            ps.setString(5,order.getDeliveryAddress());
            ps.setString(6,order.getOrderStatus());
            ps.execute();
        } catch (SQLException ex) {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Заказы")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                order.setId(id);
            }
        } catch (SQLException ex) {
        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
    }
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Продукты WHERE артикул = ?")) {
            for (int i = 0; i < order.getOrderItem().getVendorCodeOfProduct().length; i++) {
                ps.setString(1,order.getOrderItem().getVendorCodeOfProduct()[i]);
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    Integer price = resultSet.getInt(4);
                    order.getOrderItem().setPrice(price, i);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection connection = DriverManager.getConnection(url,user,password);
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Позиции_заказа(код_записи_заказа, артикул_товара, цена, количество) VALUES (?,?,?,?)")) {
            for (int i = 0; i < order.getOrderItem().getVendorCodeOfProduct().length; i++) {
                ps.setInt(1,order.getId());
                ps.setString(2,order.getOrderItem().getVendorCodeOfProduct()[i]);
                ps.setInt(3, order.getOrderItem().getPrice()[i]);
                ps.setInt(4, order.getOrderItem().getNumberOfProducts()[i]);
                ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
