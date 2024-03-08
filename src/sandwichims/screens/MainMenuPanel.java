/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;

import sandwichims.objects.Employee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sandwichims.DarkTheme;

/**
 *
 * @author bnorm
 */

/*

TODO:

This screen is almost done. I'd really just like the buttons to be stacked on top
of each other, as well as another button for "Close Application" and "Change User"

Close application should close the application. "Change User" should navigate back to the Login Screen,
where the user can login to their account.
*/

public class MainMenuPanel extends JPanel {
    
    private MainFrame mainFrame;
    private Employee employee;
    
    public MainMenuPanel(MainFrame mainFrame, Employee employee) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); 
        
        //Frame Components
        
        JLabel label = new JLabel("Welcome " + employee.getFirstName());
        label.setHorizontalAlignment(JLabel.CENTER);
        
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton manageInventoryButton = new JButton("Manage Inventory");
        JButton downloadReportButton = new JButton("Download Report");
        
        manageEmployeesButton.addActionListener(e -> mainFrame.navigateTo("ManageEmployees", employee));
        manageInventoryButton.addActionListener(e -> mainFrame.navigateTo("ManageInventory", employee));
        downloadReportButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Report Successfully Downloaded"));
        
        add(label, gbc);
        add(manageEmployeesButton, gbc);
        add(manageInventoryButton, gbc);
        add(downloadReportButton, gbc);
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}