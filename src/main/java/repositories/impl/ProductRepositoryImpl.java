package repositories.impl;

import connection.ConnectionDB;
import lombok.Cleanup;
import lombok.SneakyThrows;
import model.Product;
import repositories.ProductRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public final class ProductRepositoryImpl implements ProductRepository {
    private static final Connection cn = ConnectionDB.getConnection();

    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE product_id = ?";
    private static final String ADD_PRODUCT = "INSERT INTO product(name, price, isdiscount) VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, price = ?, isdiscount = ? WHERE product_id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE product_id = ?";


    @SneakyThrows
    @Override
    public Product getProductById(int id) {
        Product product = new Product();
        @Cleanup PreparedStatement statement = cn.prepareStatement(GET_PRODUCT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(BigDecimal.valueOf(resultSet.getDouble(3)));
            product.setDiscount(resultSet.getBoolean(4));
        }
        return product;
    }

    @SneakyThrows
    @Override
    public Product save(Product product) {
        @Cleanup PreparedStatement statement = cn.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getPrice());
        statement.setBoolean(3, product.isDiscount());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        product.setId(new Integer(generatedKeys.getObject("product_id").toString()));
        return product;
    }

    @SneakyThrows
    @Override
    public void delete(int id) {
        @Cleanup PreparedStatement statement = cn.prepareStatement(DELETE_PRODUCT_BY_ID);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public Product update(int id, Product product) {
        @Cleanup PreparedStatement statement = cn.prepareStatement(UPDATE_PRODUCT);
        statement.setInt(4, id);
        statement.setString(1, product.getName());
        statement.setBigDecimal(2, product.getPrice());
        statement.setBoolean(3, product.isDiscount());
        statement.executeUpdate();

        return getProductById(id);
    }

}
