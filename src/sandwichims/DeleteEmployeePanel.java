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
    
    public DeleteEmployeePanel(MainFrame mainFrame) {
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Delete Employee Panel");
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        
        deleteEmployeeButton.addActionListener(e -> {
            
            System.out.println("Delete Employee Button Clicked");
            
                });
        
        add(label);
        add(deleteEmployeeButton);
    }
}
