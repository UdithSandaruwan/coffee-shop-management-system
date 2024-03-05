package Controller;

import Model.OrderModel;

import java.sql.*;
import java.util.ArrayList;

public class OrderController {
    public double calculateTotalPrice(ArrayList<OrderModel> productList) {
        double totalPrice = 0.0;

        for (OrderModel product : productList) {
            totalPrice += (product.getPrice())*(product.getQuantity());
        }

        return totalPrice;
    }

    public Double getProductPrice(int p_id) {
        Double price = 0.0;
        try {
            Connection con = ConnectionClass.getCon();
            String sql = "SELECT price FROM products WHERE p_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, p_id);

            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        } finally {
            ConnectionClass.closeConnection();
        }
        return price;
    }
}
