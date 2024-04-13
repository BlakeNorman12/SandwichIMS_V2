/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import sandwichims.DarkTheme;
import sandwichims.objects.Employee;
import sandwichims.SimpleHash;
import methods.*;

/**
 *
 * @author bnorm
 * 
 * This panel is for modifying information regarding an employee.
 * Users should be able to modify:
 *  1. Employee Personal Info
 *  2. Employee Login info
 *  3. Change Permissions
 * 
 */

/*
TODO:

Implement functionalities for Employee Modifications


*/

public class ModifyEmployeePanel extends JPanel {
    
    private MainFrame mainFrame;
    private Employee employee;
    private JTable table;
    private DefaultTableModel model;
    
    public ModifyEmployeePanel(MainFrame mainFrame, Employee employee) {
        
        DarkTheme.applyTheme();
        this.mainFrame = mainFrame;
        this.employee = employee;
        
        initTable();
        initManagementInput();
        addPreviousScreenButton();
        
        populateTable();
    }
    
    private void initTable() {
        
        // Define column names
        String[] columns = {"Employee ID", "First Name", "Last Name", "Username", "Permissions"};

        // Create a table model with the data and columns
        model = new DefaultTableModel(columns, 0);

        // Create the table using the table model
        table = new JTable(model);

        // Set table theme
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Color.DARK_GRAY);

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
        
        
    }
    
    private void populateTable() {

        if (model != null) {
            model.setRowCount(0); // Clear existing table data
        }

        // Retrieve data from the database and populate the table
        try {
            SQLConnection connect = new SQLConnection();
            Connection connection = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employeeid");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String userName = resultSet.getString("username");
                String permissions = resultSet.getInt("isManager") == 1 ? "Manager" : "Sandwich Artist";

                model.addRow(new Object[]{employeeId, firstName, lastName, userName, permissions});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void addPreviousScreenButton() {
        JButton previousScreenButton = new JButton("Previous Screen");
        previousScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                mainFrame.navigateTo("ManageEmployees", employee);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned button panel
        buttonPanel.add(previousScreenButton);
        buttonPanel.setOpaque(false); // Make the panel transparent

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the panel
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    private void initManagementInput() {
        
        // Create text fields
        JTextField employeeIdField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JTextField userNameField = new JTextField(10);
        JTextField currentPasswordField = new JTextField(10);
        JTextField newPasswordField = new JTextField(10);

        
        // Create labels for text fields
        JLabel employeeIdLabel = new JLabel("Employee ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel userNameLabel = new JLabel("User Name:");
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        JLabel newPasswordLabel = new JLabel("New Password:");
        JLabel isManagerLabel = new JLabel("Permissions:");
        
        // Create radio buttons
        JRadioButton sandwichArtistRadioButton = new JRadioButton("Sandwich Artist");
        JRadioButton managerRadioButton = new JRadioButton("Manager");
        
        managerRadioButton.setBackground(Color.DARK_GRAY);
        managerRadioButton.setOpaque(true);
        
        sandwichArtistRadioButton.setBackground(Color.DARK_GRAY);
        sandwichArtistRadioButton.setOpaque(true);
        
        managerRadioButton.setForeground(Color.LIGHT_GRAY);
        sandwichArtistRadioButton.setForeground(Color.LIGHT_GRAY);

        // Group the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(sandwichArtistRadioButton);
        buttonGroup.add(managerRadioButton);

        // Create buttons
        JButton updateButton = new JButton("Update");

        // Create panel for text fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(employeeIdLabel);
        inputPanel.add(employeeIdField);
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(userNameLabel);
        inputPanel.add(userNameField);
        inputPanel.add(currentPasswordLabel);
        inputPanel.add(currentPasswordField);
        inputPanel.add(newPasswordLabel);
        inputPanel.add(newPasswordField);
        inputPanel.add(isManagerLabel);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(sandwichArtistRadioButton);
        inputPanel.add(managerRadioButton);
        

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);

        // Create panel for input fields and buttons
        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(inputPanel, BorderLayout.NORTH);
        inputButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the input fields and buttons panel to the panel in the WEST region
        add(inputButtonPanel, BorderLayout.WEST);
        
        
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int employeeID = Integer.parseInt(employeeIdField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = userNameField.getText();
                String currPassword = SimpleHash.hashPassword(currentPasswordField.getText().trim());
                String newPassword = SimpleHash.hashPassword(newPasswordField.getText().trim());
                
                
                String permissions;
                if (sandwichArtistRadioButton.isSelected()) {
                    permissions = sandwichArtistRadioButton.getText();
                } else if (managerRadioButton.isSelected()) {
                    permissions = managerRadioButton.getText();
                } else {
                    permissions = "";
                }
                
                if (employeeIdField.getText().trim().isEmpty() || firstName.trim().isEmpty() ||
                    lastName.trim().isEmpty() || username.trim().isEmpty() || 
                    currPassword.trim().isEmpty() || newPassword.trim().isEmpty() || 
                    permissions.isEmpty()) {


                    DarkTheme.showCustomDialog(mainFrame, "Please fill out all input fields.");
                    return;
                }
                
                if (permissions.equals("Sandwich Artist")){
                    
                    EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, username, currPassword, newPassword, false);
                } else if (permissions.equals("Manager")){
                    
                    EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, username, currPassword, newPassword, true);                   
                }
                
                employeeIdField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
                userNameField.setText("");
                currentPasswordField.setText("");
                newPasswordField.setText("");
                buttonGroup.clearSelection();
        

            }
        });
    }
    
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            populateTable(); // Refresh table data when the panel becomes visible
        }
    }
}
