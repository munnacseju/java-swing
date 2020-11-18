package FarmerToCustomerManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration extends JFrame implements ActionListener {
    private Container container;
    private JTextField firstNameJTF, lastNameJTF, userIdJTF, passwordJTF, balanceJTF;
    private JLabel userIdJL, passwordJL,firstNameJL,lastNameJL,balanceJL;
    private JButton logInButton, signUpButton;
    Registration(){
        container = this.getContentPane();
        this.setBounds(500,150,300,520);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Registration");

        firstNameJL = new JLabel("First Name : ");
        lastNameJL = new JLabel("Last Name : ");
        firstNameJL.setBounds(50, 90,100,20);
        container.add(firstNameJL);
        lastNameJL.setBounds(50,120,100,20);
        container.add(lastNameJL);

        firstNameJTF = new JTextField();
        lastNameJTF = new JTextField();
        firstNameJTF.setBounds(150, 90,100,20);
        container.add(firstNameJTF);
        lastNameJTF.setBounds(150,120,100,20);
        container.add(lastNameJTF);

        userIdJL = new JLabel("User Id : ");
        passwordJL = new JLabel("Password : ");
        userIdJL.setBounds(50, 150, 100, 20);
        passwordJL.setBounds(50,180,100,20);
        container.add(userIdJL);
        container.add(passwordJL);

        userIdJTF = new JTextField();
        passwordJTF = new JTextField();
        userIdJTF.setBounds(150,150,100,20);
        container.add(userIdJTF);
        passwordJTF.setBounds(150,180,100,20);
        container.add(passwordJTF);

        balanceJL = new JLabel("Balance : ");
        balanceJL.setBounds(50, 210,100,20);
        container.add(balanceJL);
        balanceJTF = new JTextField();
        balanceJTF.setBounds(150,210,100,20);
        container.add(balanceJTF);

        signUpButton = new JButton("Sign Up!");
        signUpButton.setBounds(50,240,200,20);
        container.add(signUpButton);

        logInButton = new JButton("Already Registered? Log In");
        logInButton.setBounds(50, 270, 200, 20);
        container.add(logInButton);

        signUpButton.addActionListener(this);
        logInButton.addActionListener(this);
    }


    void userRagistration() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            String firstName,lastName,userId,password1,balance;
            firstName = firstNameJTF.getText();
            lastName = lastNameJTF.getText();
            userId = userIdJTF.getText();
            password1 = passwordJTF.getText();
            balance = balanceJTF.getText();


            String query = " insert into logIn (firstName, lastName, balance, userId, password)"
                    + " values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, balance);
            ps.setString(4, userId);
            ps.setString(5, password1);
            ps.execute();
            connection.close();
            JOptionPane.showMessageDialog(null, "Registration Successfull!", "Congress", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==signUpButton){
            userRagistration();
        }
        else if(actionEvent.getSource()==logInButton){
            this.setVisible(false);
            Main main = new Main();
            main.setVisible(true);
        }
    }
}
