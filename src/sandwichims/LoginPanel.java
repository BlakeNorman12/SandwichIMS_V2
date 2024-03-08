/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author bnorm
 * 
 * This is the login screen for our application. The purpose is to validate users 
 * prior to allowing them to perform any functions within the system.
 * 
 */

//TODO:
//Add login functionality. Verify with the database that the user is a valid user
//Beautify the GUI
//Make sure the user is notified when login is failed or successful. Use a JPane to display a message

public class LoginPanel extends JPanel {
    
    private MainFrame mainFrame;
    
    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); 
        
        JLabel label = new JLabel("Please login to access the system.");
        label.setHorizontalAlignment(JLabel.CENTER);
        
        // Username components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTextField = new JTextField(20);
        
        // Password components
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticateUser(usernameTextField.getText(), new String(passwordField.getPassword()))){
                    System.out.println("Authentication successful");
                    mainFrame.navigateTo("MainMenu");
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Invalid username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Adding components with GridBagConstraints
        add(label, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        add(usernameLabel, gbc);
        add(usernameTextField, gbc);
        add(passwordLabel, gbc);
        add(passwordField, gbc);
        gbc.insets = new Insets(10, 0, 0, 0); 
        add(loginButton, gbc);
        
      
        setAlignmentX(Component.CENTER_ALIGNMENT);
        
}
    
    private boolean authenticateUser(String username, String password){
            String url = "jdbc:mysql://localhost:3306/SandwichIMS";
            String dbUsername = "root";
            String dbPassword = "root";
            
            String hashedPassword = SimpleHash.hashPassword(password);
            
            String query = "SELECT * FROM Employee WHERE username = ? AND password = ?";
            
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                    
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
}