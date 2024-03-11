/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sandwichims.DarkTheme;
import sandwichims.objects.Employee;

/**
 *
 * @author bnorm
 * 
 * This is the AddEmployee screen of the application.
 * The purpose of this screen is to allow users to add new users to the system.
 * There should be many input fields.
 * 
 */

/*

TODO:

BACK BUTTON should navigate back to the "Manage Employees" screen

We need four input boxes on this screen:
    1. First Name
    2. Last Name
    3. Login
    4. Password

We also need a yes/no option for the question:
    "Is this employee a manager?"

Finally, we need a "Submit" button at the bottom of this screen.
This button should make sure each field is full. If possible by the deadline,
we should require the user to create a password following certain requirements.
When the user presses submit, and if all fields are valid, all of this information
needs to be uploaded to the Employee table in our database. This is for verification
if the user decides to use the system themselves.

Beautify the GUI, organize the input boxes in an attractive manner.

*/
public class AddEmployeePanel extends JPanel {
    
    private MainFrame mainFrame;
    private Employee employee;
    
    public AddEmployeePanel(MainFrame mainFrame, Employee employee) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        //Frame Components
        JLabel label = new JLabel("Add New Employee");
        
        Font labelFont = label.getFont();
        float newSize = 20f;
        label.setFont(labelFont.deriveFont(newSize));
        
        add(label, gbc);
        
        //Input Fields
        
        JTextField firstNameField = new JTextField(20);
        addLabeledField("First Name:", firstNameField, gbc);
        
        JTextField lastNameField = new JTextField(20);
        addLabeledField("Last Name:", lastNameField, gbc);
        
        JTextField usernameField = new JTextField(20);
        addLabeledField("Username:", usernameField, gbc);
        
        JTextField passwordField = new JTextField(20);
        addLabeledField("Password:", passwordField, gbc);
        
        // isManager question
        
        JLabel isManagerLabel = new JLabel("Is this employee a manager?");
        add(isManagerLabel, gbc);
        
        JRadioButton yesButton = new JRadioButton("Yes");
        yesButton.setBackground(Color.DARK_GRAY);
        yesButton.setForeground(Color.WHITE);
        
        JRadioButton noButton = new JRadioButton("No", true);
        noButton.setBackground(Color.DARK_GRAY);
        noButton.setForeground(Color.WHITE);
        
        ButtonGroup group = new ButtonGroup();
        group.add(yesButton);
        group.add(noButton);
        
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.setBackground(Color.DARK_GRAY);
        radioPanel.add(yesButton);
        radioPanel.add(noButton);
        add(radioPanel, gbc);
        
        JButton submitButton = new JButton("Submit");
        add(submitButton, gbc);
        
        
        JButton backButton = new JButton("Previous Screen");
        backButton.addActionListener(e -> {
            mainFrame.navigateTo("ManageEmployees", employee);
            this.employee = employee;
        });
        
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 10);
        
        add(backButton, gbc);
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    private void addLabeledField(String labelText, JTextField textField, GridBagConstraints gbc){
        JLabel label = new JLabel(labelText);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(label);
        panel.add(textField);
        add(panel, gbc);
    }
}
