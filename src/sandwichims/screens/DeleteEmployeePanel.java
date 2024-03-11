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
import methods.EmployeeMethods;

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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(3, 0, 3, 0); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        //Frame Components
        
        //Label
        JLabel label = new JLabel("Delete Employee");
        label.setHorizontalAlignment(JLabel.CENTER);
        Font labelFont = label.getFont();
        float newSize = 20f;
        label.setFont(labelFont.deriveFont(newSize));
        add(label, gbc);
        
        //Text Fields
        JTextField employeeIdField = new JTextField(20);
        addLabeledField("First Name:", employeeIdField, gbc);
        
        JTextField firstNameField = new JTextField(20);
        addLabeledField("First Name:", firstNameField, gbc);
        
        JTextField lastNameField = new JTextField(20);
        addLabeledField("Last Name:", lastNameField, gbc);
        
        //Delete Employee Button
        JButton deleteEmployeeButton = new JButton("Delete Employee");
        Font buttonFont = new Font(deleteEmployeeButton.getFont().getName(), Font.BOLD, 24);
        deleteEmployeeButton.setFont(buttonFont);
        add(deleteEmployeeButton, gbc);
        
        deleteEmployeeButton.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure? This cannot be undone.", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmed == JOptionPane.YES_OPTION){
                try {
                    int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    EmployeeMethods.deleteEmployee(employeeId, firstName, lastName);
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully");
                    
                    employeeIdField.setText("");
                    firstNameField.setText("");
                    lastNameField.setText("");                  
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(this, "Invalid Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(this, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);                    
                }
            }
        });
        
        //Back Button
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
    
    private void addLabeledField(String labelText, JTextField textField, GridBagConstraints gbc){
        JLabel label = new JLabel(labelText);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(label);
        panel.add(textField);
        add(panel, gbc);
    }
    
}
