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
    
    public ModifyEmployeePanel(MainFrame mainFrame, Employee employee) {
        
        //DARK MODE
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //FORMATTING FOR PANEL COMPONENTS
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        
        //MAIN LABEL
        JLabel label = new JLabel("Modify Employee");
        
        Font labelFont = label.getFont();
        float newSize = 20f;
        label.setFont(labelFont.deriveFont(newSize));
        
        add(label, gbc);
        
        
        //ADDING BUTTONS
        JButton changeInfoButton = new JButton("Change Info");
        changeInfoButton.addActionListener(e -> {
            
        });
        
        JButton changeLoginButton = new JButton("Change Login");
        changeInfoButton.addActionListener(e -> {
            
        });
        
        JButton changePermissionsButton = new JButton("Change Permissions");
        changeInfoButton.addActionListener(e -> {
            
        });
        
        //CHANGING BUTTON FONTS
        Font buttonFont = new Font(changeInfoButton.getFont().getName(), Font.BOLD, 24);
        changeInfoButton.setFont(buttonFont);
        changeLoginButton.setFont(buttonFont);
        changePermissionsButton.setFont(buttonFont);
        
        add(changeInfoButton, gbc);
        add(changeLoginButton, gbc);
        add(changePermissionsButton, gbc);
        
        JButton backButton = new JButton("Previous Screen");
        backButton.addActionListener(e -> {
            mainFrame.navigateTo("ManageEmployees", employee);
            this.employee = employee;
        });
        
        //FORMATTING SPECIFIC TO THE BACK BUTTON
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
}
