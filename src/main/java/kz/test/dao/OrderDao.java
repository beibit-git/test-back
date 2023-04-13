package kz.test.dao;

import kz.test.dto.requestDto.OrderRequest;
import kz.test.model.Order;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDao {

    private static String URL = "jdbc:postgresql://localhost:5432/testforjob";

    private static String username = "testforjob";

    private static String password = "testforjob";

    private static Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> index() {
        List<Order> orderList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM orders";

            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Order order = new Order();

                order.setId(Long.valueOf(resultSet.getInt("id")));
                order.setTitle(resultSet.getString("title"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setAmount(resultSet.getFloat("amount"));

                orderList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }

    public void createOrder(OrderRequest orderRequest) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (title, quantity, amount) VALUES(? , ? , ?)");
            if(orderRequest.getTitle().matches(".*[\\\\%&].*")) {
                throw new RuntimeException("Invalid character");
            }
            preparedStatement.setString(1, orderRequest.getTitle());
            preparedStatement.setInt(2, orderRequest.getQuantity());
            preparedStatement.setFloat(3, orderRequest.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
