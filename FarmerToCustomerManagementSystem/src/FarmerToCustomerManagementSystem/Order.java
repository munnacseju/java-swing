package FarmerToCustomerManagementSystem;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Order extends JFrame implements ActionListener {
    private int nuberOfProduct=0;
    private JButton jButton[] = new JButton[100];
    private JLabel jLabel[] = new JLabel[100],price[]=new JLabel[100];
    private JTextArea jTextArea[] = new JTextArea[100], jTextAreas1[] = new JTextArea[100];;
    private JPanel jPanel;
    private Container container;
    private JScrollPane jScrollPane;
    private BoxLayout boxLayout;
    private user myUser,sellerUser;
    String vUser[] = new String[100], vPrice[] = new String[100];
    Order(user user){
        myUser = user;
        this.setDefaultCloseOperation(3);
        this.setBounds(500,150,300,520);
        this.setTitle("Order");
        container = this.getContentPane();
        container.setLayout(null);


        jPanel = new JPanel();
        order();

        jScrollPane = new JScrollPane(jPanel);
        boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        jScrollPane.setBounds(5,5,270,470);
        container.add(jScrollPane);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        for(int i=0; i<=nuberOfProduct; i++){
            if(actionEvent.getSource()==jButton[i]){
                int choise = JOptionPane.showConfirmDialog(null, "Are You sure to bye this product?", "Alart!",JOptionPane.YES_NO_OPTION);
                if(choise==JOptionPane.YES_OPTION){
                    boolean capability = myUser.updateBalance(-1,vPrice[i].trim());
                    if(capability==true){
                        sellerUser = new user(vUser[i].trim());
                        sellerUser.updateBalance(1, vPrice[i].trim());
                        Profile profile = new Profile(myUser);
                        profile.setVisible(true);
                    }
                }

            }
        }
    }

    void order() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = " select * from product";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            nuberOfProduct = -1;
            while (resultSet.next()){
                nuberOfProduct++;
                jTextAreas1[nuberOfProduct] = new JTextArea("..........................................................................................................");
                jTextAreas1[nuberOfProduct].setEditable(false);
                jPanel.add(jTextAreas1[nuberOfProduct]);
                    user test = new user(resultSet.getString(1));
                    jLabel[nuberOfProduct] = new JLabel(test.getName()+" want to sell");
                    price[nuberOfProduct] = new JLabel("Price: "+resultSet.getString(4)+"tk");
                    vUser[nuberOfProduct] = resultSet.getString(1);
                    vPrice[nuberOfProduct] = resultSet.getString(4);

                    jTextArea[nuberOfProduct] = new JTextArea("Product Name : "+resultSet.getString(2)+"\nProduct Details : "+resultSet.getString(3));
                    jTextArea[nuberOfProduct].setEditable(false);
                    jButton[nuberOfProduct] = new JButton("Order");
                    jButton[nuberOfProduct].addActionListener(this);

                    jPanel.add(jLabel[nuberOfProduct]);
                    jPanel.add(price[nuberOfProduct]);
                    jPanel.add(jTextArea[nuberOfProduct]);
                    jPanel.add(jButton[nuberOfProduct]);

            }
            ps.execute();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Exception Prepared: " + e);
        }
    }
}
