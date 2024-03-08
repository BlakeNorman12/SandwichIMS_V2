/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    
    public MainMenuPanel(MainFrame mainFrame) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Main Menu");
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton manageInventoryButton = new JButton("Manage Inventory");
        JButton downloadReportButton = new JButton("Download Report");
        
        manageEmployeesButton.addActionListener(e -> mainFrame.navigateTo("ManageEmployees"));
        manageInventoryButton.addActionListener(e -> mainFrame.navigateTo("ManageInventory"));
        
        add(label);
        add(manageEmployeesButton);
        add(manageInventoryButton);
        add(downloadReportButton);
    }
}