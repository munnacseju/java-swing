package FarmerToCustomerManagementSystem;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.sql.*;

public class user{
    public String name,balance, userName, password1;

    user(String userName ){
        this.name =getName(userName);
        this.balance = getBalance(userName);
        this.userName = userName;
        this.password1 = getPassword(userName);
    }


    public String getName() {
        return name;
    }

    public String getBalance(){
        return balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password1;
    }


    public String getName(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = " select * from login where userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString(1)+" "+resultSet.getString(2);
            }
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
        return name;
    }

    public String getBalance(String userName){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = " select * from login where userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                balance = resultSet.getString(3);
            }
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
        return balance;
    }


    public String getPassword(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = " select * from login where userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                password1 = resultSet.getString(5);
            }
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
        return password1;
    }


    boolean updateBalance(int test, String x){
        try {
            System.out.println("Come");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            int myBalance, productPrice;
            myBalance = Integer.parseInt(balance);
            productPrice = Integer.parseInt(x);
            if(test<0){
                if(productPrice>myBalance){
                    JOptionPane.showMessageDialog(null, "Sorry! You have not enouth balance, Please load your balance or try for another product","Error!", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                else {
                    myBalance = myBalance - productPrice;
                    JOptionPane.showMessageDialog(null, "After buying Yur current balance : "+myBalance,"Congress!",JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else {
                myBalance = myBalance + productPrice;
                String b = productPrice+"";
                Product product = new Product();
                product.deleteProduct(userName,b.trim());
                System.out.println(userName+" "+b.trim());



            }


            String b = ""+myBalance;
            System.out.println(myBalance);
            System.out.println(b);


            Connection connection = DriverManager.getConnection(url, username, password);

            String query = " update login set balance = ? where userId = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, b.trim());
            ps.setString(2, userName.toString().trim());
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
        return true;
    }
}
