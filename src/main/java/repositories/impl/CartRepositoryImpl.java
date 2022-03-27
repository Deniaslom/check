package repositories.impl;

import connection.ConnectionDB;
import lombok.Cleanup;
import lombok.SneakyThrows;
import model.DiscountCard;
import repositories.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CartRepositoryImpl implements CartRepository {
    private static final Connection cn = ConnectionDB.getConnection();

    private static final String GET_CART_BY_ID = "SELECT * FROM cart WHERE cart_id = ?";
    private static final String ADD_CART = "INSERT INTO cart(discount) VALUES (?)";
    private static final String DELETE_CART_BY_ID = "DELETE FROM cart WHERE cart_id = ?";
    private static final String UPDATE_CART_BY_ID = "UPDATE cart SET discount = ? WHERE cart_id = ?";


    @SneakyThrows
    @Override
    public DiscountCard getCartById(int id) {
        DiscountCard cart = new DiscountCard();

        @Cleanup PreparedStatement statement = cn.prepareStatement(GET_CART_BY_ID);
        statement.setInt(1, id);
        @Cleanup ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            cart.setNumber(resultSet.getInt(1));
            cart.setDiscount(resultSet.getDouble(2));
        }
        return cart;
    }

    @SneakyThrows
    @Override
    public DiscountCard save(DiscountCard cart) {
        @Cleanup PreparedStatement statement = cn.prepareStatement(ADD_CART, Statement.RETURN_GENERATED_KEYS);
        statement.setDouble(1, cart.getDiscount());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        cart.setNumber(new Integer(generatedKeys.getObject("cart_id").toString()));
        return cart;
    }

    @SneakyThrows
    @Override
    public void delete(int id) {
            @Cleanup PreparedStatement statement = cn.prepareStatement(DELETE_CART_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public void update(int id, DiscountCard cart) {
        @Cleanup PreparedStatement statement = cn.prepareStatement(UPDATE_CART_BY_ID);
        statement.setDouble(1, cart.getDiscount());
        statement.setInt(2, id);
        statement.executeUpdate();
    }

}
