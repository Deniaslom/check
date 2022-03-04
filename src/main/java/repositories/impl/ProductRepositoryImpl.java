package repositories.impl;

import connection.ConnectionDB;
import model.Product;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public final class ProductRepositoryImpl implements ProductRepository {
    private static final Connection cn = ConnectionDB.getConnection();

    private static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE product_id = ?";
    private static final String ADD_PRODUCT = "INSERT INTO product(name, price, isdiscount) VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, isdiscount = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?";

    @Override
    public Map<Integer, Product> getProducts() {
        Map<Integer, Product> products = new HashMap<>();

        try {
            PreparedStatement statement = cn.prepareStatement(GET_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble(3)));
                product.setDiscount(resultSet.getBoolean(4));
                products.put(product.getId(), product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = new Product();

        try {
            PreparedStatement statement = cn.prepareStatement(GET_PRODUCT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(BigDecimal.valueOf(resultSet.getDouble(3)));
                product.setDiscount(resultSet.getBoolean(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean addProduct(Product product) {
        try {
            PreparedStatement statement = cn.prepareStatement(ADD_PRODUCT);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setBoolean(3, product.isDiscount());
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteProductById(int id) {
        try {
            PreparedStatement statement = cn.prepareStatement(DELETE_PRODUCT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product updateProduct(int id, Product product) {
        try {
            PreparedStatement statement = cn.prepareStatement(UPDATE_PRODUCT);
            statement.setInt(4, id);
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.setBoolean(3, product.isDiscount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getProductById(id);
    }

}
