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
 * This screen is for when we need to delete an employee.
 * This could be due to demotion, being fired, being laid off, not wanting
 * to be an inventory employee anymore, etc.
 * 
 */

/**
* TODO:
* 
* BACK BUTTON should navigate back to the "Manage Employees" Panel
* 
* We need three input boxes:
*   1. Employee First Name
*   2. Employee Last Name
*   3. EmployeeID
* 
* We need a "Delete Employee" Button
*   When this button is clicked, we should have a popup that says
*   "Are you sure? This action cannot be undone." The user should have the option to cancel.
* 
* If the user presses "Yes", delete all information regarding the deleted employee
* from the "Employees" table in the database. Also, notify the user with a popup
* that says "User successfully deleted". 
* 
* Beautify the GUI, stack the input  boxes, etc.
*
*/
public class DeleteEmployeePanel extends JPanel {
    
    private MainFrame mainFrame;
    private Employee employee;
    
    public DeleteEmployeePanel(MainFrame mainFrame, Employee employee) {
        
        //Applying dark mode before adding GUI components
        DarkTheme.applyTheme();
        setBackground(Color.DARK_GRAY);
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Frame Components
        
        JLabel label = new JLabel("Delete Employee Panel");
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        
        deleteEmployeeButton.addActionListener(e -> {
            
            System.out.println("Delete Employee Button Clicked");
            
                });
        
        add(label);
        add(deleteEmployeeButton);
        
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
    
}
