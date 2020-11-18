package FarmerToCustomerManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product {


    public void deleteProduct(String userName, String price) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = " delete  from product where userId = ? and productPrice = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, price);
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }

    }

}
