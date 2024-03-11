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

Possibly make more visually appealing

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
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        add(Box.createGlue(), gbc);
        
        //Frame Components
        
        JLabel label = new JLabel("Welcome " + employee.getFirstName());
        label.setHorizontalAlignment(JLabel.CENTER);
        
        Font labelFont = label.getFont();
        float newSize = 20f;
        label.setFont(labelFont.deriveFont(newSize));
        
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton manageInventoryButton = new JButton("Manage Inventory");
        JButton downloadReportButton = new JButton("Download Report");
        
        Font buttonFont = new Font(manageEmployeesButton.getFont().getName(), Font.BOLD, 24);
        manageEmployeesButton.setFont(buttonFont);
        manageInventoryButton.setFont(buttonFont);
        downloadReportButton.setFont(buttonFont);
        
        manageEmployeesButton.addActionListener(e -> mainFrame.navigateTo("ManageEmployees", employee));
        manageInventoryButton.addActionListener(e -> mainFrame.navigateTo("ManageInventory", employee));
        downloadReportButton.addActionListener(e -> JOptionPane.showMessageDialog(mainFrame, "Report Successfully Downloaded"));
        
        add(label, gbc);
        add(manageEmployeesButton, gbc);
        add(manageInventoryButton, gbc);
        add(downloadReportButton, gbc);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            mainFrame.logout();
            this.employee = null;
            mainFrame.navigateTo("Login", null);
        });
        
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 10);
        
        add(logoutButton, gbc);
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}