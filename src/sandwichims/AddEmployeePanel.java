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
    
    public AddEmployeePanel(MainFrame mainFrame) {
        
        this.mainFrame = mainFrame;
        setLayout(new FlowLayout());
        
        //Frame Components
        
        JLabel label = new JLabel("Add Employee Panel");
        JButton addEmployeeButton = new JButton("Add Employee");
        
        addEmployeeButton.addActionListener(e -> {
            
            System.out.println("Add Employee Button Clicked");
            
                });
        
        add(label);
        add(addEmployeeButton);
    }
}
