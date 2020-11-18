package FarmerToCustomerManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class Main extends JFrame implements ActionListener {
    private Container container;
    private JTextField userIdJTextField, passwordJTextField;
    private JLabel userIdJLebel, passwordJLebel;
    private JButton logInButton, signUpButton;
    Main(){
        container = this.getContentPane();
        this.setBounds(500,150,300,520);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Log In");

        userIdJTextField = new JTextField();
        passwordJTextField = new JTextField();
        userIdJTextField.setBounds(150,100,100,20);
        container.add(userIdJTextField);
        passwordJTextField.setBounds(150,130,100,20);
        container.add(passwordJTextField);

        userIdJLebel = new JLabel("User Id : ");
        passwordJLebel = new JLabel("Password : ");
        userIdJLebel.setBounds(50, 100, 100, 20);
        passwordJLebel.setBounds(50,130,100,20);
        container.add(userIdJLebel);
        container.add(passwordJLebel);

        logInButton = new JButton("Log In");
        logInButton.setBounds(50, 160, 200, 20);
        container.add(logInButton);

        signUpButton = new JButton("Not Registered? Sign Up!");
        signUpButton.setBounds(50,190,200,20);
        container.add(signUpButton);
        signUpButton.addActionListener(this);
        logInButton.addActionListener(this);
    }


    void userLogIn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            String userId, password1;
            userId = userIdJTextField.getText();
            password1 = passwordJTextField.getText();


            String query = " select * from logIn";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            boolean isRegistered = false;
            while (resultSet.next()){

                if(resultSet.getString(4).equals(userId)&&resultSet.getString(5).equals(password1)){
                    isRegistered = true;
                    this.setVisible(false);
                    user user = new user(userId);
                    Profile profile = new Profile(user);
                    profile.setVisible(true);
                }
            }
            if(isRegistered==false){
                JOptionPane.showMessageDialog(null, "Wrong Password or User Id","Sorry!",JOptionPane.ERROR_MESSAGE);
            }
            ps.execute();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);
        }
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==signUpButton){
            this.setVisible(false);
            Registration registration = new Registration();
            registration.setVisible(true);
        }
        if(actionEvent.getSource()==logInButton){
            System.out.println("You Clicked Login Button");
            userLogIn();
        }
    }
}

