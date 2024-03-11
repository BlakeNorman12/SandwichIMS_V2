/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;

/**
 *
 * @author bnorm
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeInfoDialog extends JDialog{
    private JTextField employeeIdField, firstNameField, lastNameField;
    private JButton submitButton;
    private boolean submitted = false;
    
    public ChangeInfoDialog(Frame owner){
        super(owner, "Change Employee Info", true);
        
        setLayout(new GridLayout(4, 2));
        
        add(new JLabel("Employee ID:"));
        employeeIdField = new JTextField(20);
        add(employeeIdField);
        
        add(new JLabel("First Name:"));
        firstNameField = new JTextField(20);
        add(firstNameField);
        
        add(new JLabel("Last Name:"));
        lastNameField = new JTextField(20);
        add(lastNameField);
        
        submitButton = new JButton("Submit");
        add(submitButton);
        submitButton.addActionListener(e -> {
            submitted = true;
            setVisible(false);
        });
        
        setSize(300, 200);
    }
    
    public boolean isSubmitted(){
        return submitted;
    }
    
    public String getEmployeeId(){
        return employeeIdField.getText().trim();
    }
    
    public String getFirstName(){
        return firstNameField.getText().trim();
    }
    
    public String getLastName(){
        return lastNameField.getText().trim();
    }
    
    public void reset(){
        employeeIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        submitted = false;
    }
}
