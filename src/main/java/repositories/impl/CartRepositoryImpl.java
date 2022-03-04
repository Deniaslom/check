package repositories.impl;

import connection.ConnectionDB;
import model.DiscountCard;
import repositories.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CartRepositoryImpl implements CartRepository {
    private static final Connection cn = ConnectionDB.getConnection();

    private static final String GET_ALL_CARTS = "SELECT * FROM cart";
    private static final String GET_CART_BY_ID = "SELECT * FROM cart WHERE cart_id = ?";
    private static final String ADD_CART = "INSERT INTO cart(discount) VALUES (?)";
    private static final String DELETE_CART_BY_ID = "DELETE FROM cart WHERE cart_id = ?";


    @Override
    public Map<Integer, DiscountCard> getCarts() {
        Map<Integer, DiscountCard> carts = new HashMap<>();

        try {
            PreparedStatement statement = cn.prepareStatement(GET_ALL_CARTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DiscountCard cart = new DiscountCard();
                cart.setNumber(resultSet.getInt(1));
                cart.setDiscount(resultSet.getDouble(2));
                carts.put(cart.getNumber(), cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public DiscountCard getCartById(int id) {
        DiscountCard cart = new DiscountCard();

        try {
            PreparedStatement statement = cn.prepareStatement(GET_CART_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                cart.setNumber(resultSet.getInt(1));
                cart.setDiscount(resultSet.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public boolean add(DiscountCard cart) {
        try {
            PreparedStatement statement = cn.prepareStatement(ADD_CART);
            statement.setDouble(1, cart.getDiscount());
            int result = statement.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement statement = cn.prepareStatement(DELETE_CART_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
