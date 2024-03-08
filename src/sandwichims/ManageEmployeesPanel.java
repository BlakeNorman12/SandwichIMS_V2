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
 * 
 * This screen is to allow users to perform different functions regarding employees.
 * The functions include Modify Existing, Add New, and Delete.
 * 
 */

/*

TODO:

BACK BUTTON this should navigate the user back to the Main Menu screen.

This one is almost done. I'd like the buttons stacked on top of each other, 
with enough space between the buttons to take up a reasonable part of the screen.

*/
public class ManageEmployeesPanel extends JPanel {
    
    private MainFrame mainFrame;
    
    public ManageEmployeesPanel(MainFrame mainFrame) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Manage Employees");
        
        JButton modifyExisting = new JButton("Modify Existing");
        JButton addNewEmployee = new JButton("Add New Employee");
        JButton deleteEmployee = new JButton("Delete Employee");
        
        modifyExisting.addActionListener(e -> {
            
            System.out.println("Modify Employee Button Clicked");
            mainFrame.navigateTo("modifyEmployee");
                });
        
        addNewEmployee.addActionListener(e -> {
            
            System.out.println("Add Employee Button Clicked");
            mainFrame.navigateTo("addEmployee");
                });
        
        deleteEmployee.addActionListener(e -> {
            
            System.out.println("Delete Employee Button Clicked");
            mainFrame.navigateTo("deleteEmployee");
                });
        
        
        add(modifyExisting);
        add(addNewEmployee);
        add(deleteEmployee);
        add(label);
        
    }
}