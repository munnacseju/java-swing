package FarmerToCustomerManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Profile extends JFrame implements ActionListener {
    private Container container;
    private JLabel nameJL, userNameJL, passwordJL,balanceJL;
    private JButton orderJB, sellJB, transportJB, storageJB;
    private Font font;
    private user myUser;

    Profile(user user){
        myUser = user;
        container = this.getContentPane();
        this.setBounds(500,150,300,520);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Your Profile");
        font = new Font("Arial", Font.BOLD,20);
        nameJL = new JLabel("Name");
        userNameJL = new JLabel("User Name");
        nameJL.setBounds(50, 90,300,20);
        nameJL.setFont(font);
        nameJL.setForeground(Color.blue);
        container.add(nameJL);
        userNameJL.setBounds(50,140,200,20);
        container.add(userNameJL);
        nameJL.setText(user.getName());
        userNameJL.setText("User Name: "+user.getUserName());

        passwordJL = new JLabel("Password: "+user.getPassword());
        passwordJL.setBounds(50,165,200,20);
        container.add(passwordJL);

        balanceJL = new JLabel("Balance: "+user.getBalance(user.getUserName()));
        balanceJL.setBounds(50,195,200,20);
        container.add(balanceJL);


        orderJB = new JButton("Order");
        sellJB = new JButton("Sell");
        orderJB.setBounds(30,50,70,25);
        container.add(orderJB);
        sellJB.setBounds(100, 50, 70, 25);
        container.add(sellJB);

        transportJB = new JButton("Transport");
        transportJB.setBounds(170,50, 90,25);
        container.add(transportJB);

        storageJB = new JButton("Store");
        storageJB.setBounds(90, 300, 100,25);
        container.add(storageJB);

        myUser = user;
        sellJB.addActionListener(this);
        orderJB.addActionListener(this);
        transportJB.addActionListener(this);
        storageJB.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==sellJB){
            this.setVisible(false);
            Sell sell = new Sell(myUser);
            sell.setVisible(true);
        }
        else if(actionEvent.getSource()==orderJB){
            this.setVisible(false);
            Order order = new Order(myUser);
            order.setVisible(true);
        }
    }
}
