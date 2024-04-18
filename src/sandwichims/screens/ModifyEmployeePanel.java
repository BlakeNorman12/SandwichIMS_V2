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

        setLayout(new BorderLayout());

        JScrollPane scrollPane = createTable();
        JPanel managementInputPanel = initManagementInput();

        add(scrollPane, BorderLayout.CENTER);
        add(managementInputPanel, BorderLayout.WEST);
        addPreviousScreenButton();
    }

    private JScrollPane createTable() {
        String[] columns = {"Employee ID", "First Name", "Last Name", "Username", "Permissions"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Color.DARK_GRAY);
        return scrollPane;
    }

    private void populateTable() {
        if (model != null) {
            model.setRowCount(0);
        }

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

    private JPanel initManagementInput() {
        JTextField employeeIdField = new JTextField(10);
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JTextField userNameField = new JTextField(10);
        JPasswordField currentPasswordField = new JPasswordField(10);
        JPasswordField newPasswordField = new JPasswordField(10);

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel userNameLabel = new JLabel("User Name:");
        JLabel currentPasswordLabel = new JLabel("Current Password:");
        JLabel newPasswordLabel = new JLabel("New Password:");
        JLabel isManagerLabel = new JLabel("Permissions:");

        JRadioButton sandwichArtistRadioButton = new JRadioButton("Sandwich Artist");
        JRadioButton managerRadioButton = new JRadioButton("Manager");

        sandwichArtistRadioButton.setSelected(true);
        managerRadioButton.setBackground(Color.DARK_GRAY);
        managerRadioButton.setOpaque(true);
        sandwichArtistRadioButton.setBackground(Color.DARK_GRAY);
        sandwichArtistRadioButton.setOpaque(true);
        managerRadioButton.setForeground(Color.LIGHT_GRAY);
        sandwichArtistRadioButton.setForeground(Color.LIGHT_GRAY);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(sandwichArtistRadioButton);
        buttonGroup.add(managerRadioButton);

        JButton updateButton = new JButton("Update");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(employeeIdLabel);
        inputPanel.add(employeeIdField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(userNameLabel);
        inputPanel.add(userNameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(currentPasswordLabel);
        inputPanel.add(currentPasswordField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(newPasswordLabel);
        inputPanel.add(newPasswordField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(isManagerLabel);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(sandwichArtistRadioButton);
        inputPanel.add(managerRadioButton);
        inputPanel.add(Box.createVerticalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);

        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(inputPanel, BorderLayout.NORTH);
        inputButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String username = userNameField.getText();
                String currPassword = SimpleHash.hashPassword(currentPasswordField.getText().trim());
                String newPassword = newPasswordField.getText().trim();

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
                
                // Convert ID string to integer after verifying that the field is not empty.
                int employeeID = Integer.parseInt(employeeIdField.getText());
                
                if (!EmployeeMethods.verifyNewPassword(newPassword)) {
                
                    DarkTheme.showCustomDialog(mainFrame, "Password requirements not met. Password must contain at least 8 characters and at least one special character.");
                    return;
                }
                
                // Hash password before insertion into database.
                newPassword = SimpleHash.hashPassword(newPasswordField.getText().trim());

                // Check input permissions, call modifyEmployee, and display output in dialog box.
                if (permissions.equals("Sandwich Artist")) {
                    
                    DarkTheme.showCustomDialog(mainFrame, EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, username, currPassword, newPassword, false));
                    populateTable();
                    return;
                } else if (permissions.equals("Manager")) {
                    
                    DarkTheme.showCustomDialog(mainFrame, EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, username, currPassword, newPassword, true));
                    populateTable();
                    return;
                }

                employeeIdField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
                userNameField.setText("");
                currentPasswordField.setText("");
                newPasswordField.setText("");
                buttonGroup.clearSelection();
                populateTable(); // Refresh table after update
            }
        });

        return inputButtonPanel;
    }

    private void addPreviousScreenButton() {
        JButton previousScreenButton = new JButton("Previous Screen");
        previousScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.navigateTo("ManageEmployees", employee);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(previousScreenButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            populateTable(); // Refresh table data when the panel becomes visible
        }
    }
}
