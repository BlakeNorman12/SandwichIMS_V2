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

BACK BUTTON should navigate back to the Manage Employees Screen

Add three buttons:
    1. Change Info
    2. Change Login
    3. Change Permissions

I didn't think it'd be necessary to add whole new screens for these simple operations.
I'm thinking instead we could just have a pop-up with some input fields, as well as a "Cancel" 
and "Submit" button. When "Submit" is clicked, all relevant information must be updated accordingly
in the "Employees" table of the database.


*/

public class ModifyEmployeePanel extends JPanel {
    
    private MainFrame mainFrame;
    private Employee employee;
    
    public ModifyEmployeePanel(MainFrame mainFrame, Employee employee) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Modify Employee Panel");
        JButton modifyEmployeeButton = new JButton("Modify Employee");
        
        modifyEmployeeButton.addActionListener(e -> {
            
            System.out.println("Modify Employee Button Clicked");
            
                });
        
        add(label);
        add(modifyEmployeeButton);
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
