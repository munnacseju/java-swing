package FarmerToCustomerManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sell extends JFrame implements ActionListener {
    private Container container;
    private JTextField productNameJTF, productPriceJTF;
    private JLabel productNameJL, productPriceJL, productDetailsJL;
    private JTextArea productDetailsJTA;
    private JButton conformJB;
    private user myUser;
    private JScrollPane jScrollPane;
    Sell(user user){
        myUser = user;
        container = this.getContentPane();
        this.setBounds(500,150,300,520);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Registration");

        productNameJL = new JLabel("Product Name : ");
        productPriceJL = new JLabel("Price : ");
        productNameJL.setBounds(50, 90,100,20);
        container.add(productNameJL);
        productPriceJL.setBounds(50,120,100,20);
        container.add(productPriceJL);

        productNameJTF = new JTextField();
        productPriceJTF = new JTextField();
        productNameJTF.setBounds(150, 90,100,20);
        container.add(productNameJTF);
        productPriceJTF.setBounds(150,120,100,20);
        container.add(productPriceJTF);

        productDetailsJL = new JLabel("Details : ");
        productDetailsJL.setBounds(50,140,200,25);
        container.add(productDetailsJL);

        productDetailsJTA = new JTextArea();
        jScrollPane = new JScrollPane(productDetailsJTA);
        jScrollPane.setBounds(50,165,200,200);
        container.add(jScrollPane);
        //productDetailsJTA.setBounds(50,165,200,200);
        //container.add(productDetailsJTA);

        conformJB = new JButton("Conform");
        conformJB.setBounds(50,375,200,20);
        container.add(conformJB);

        conformJB.addActionListener(this);
    }


    void userRagistration() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            String productName, productPrice, productDetails;
            productName = productNameJTF.getText();
            productPrice = productPriceJTF.getText();
            productDetails = productDetailsJTA.getText();



            String query = " insert into product (userId, productName, productDetails, productPrice)"
                    + " values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, myUser.getUserName());
            ps.setString(2, productName);
            ps.setString(3,productDetails);
            ps.setString(4, productPrice);
            ps.execute();
            connection.close();
            JOptionPane.showMessageDialog(null, "Your Product is ready for sell", "Done", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);

        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==conformJB){
            userRagistration();
            this.setVisible(false);
            Profile profile = new Profile(myUser);
            profile.setVisible(true);
        }
    }
}
