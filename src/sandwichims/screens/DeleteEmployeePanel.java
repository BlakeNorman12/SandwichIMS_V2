/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;


import javax.swing.*;
import java.awt.*;
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
* Beautify the GUI.
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
        addLabeledField("Employee ID:", employeeIdField, gbc);
        
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
            if (employeeIdField.getText().trim().isEmpty() ||
                    firstNameField.getText().trim().isEmpty() ||
                    lastNameField.getText().trim().isEmpty()) {
                DarkTheme.showCustomDialog(mainFrame, "Please fill out all input fields.");
            } else {
                UIManager.put("Panel.background", Color.WHITE);
                UIManager.put("OptionPane.background", Color.WHITE);
                UIManager.put("Button.background", Color.WHITE);
                UIManager.put("Button.foreground", Color.BLACK);
                UIManager.put("Label.foreground", Color.BLACK);

                int confirmed = JOptionPane.showConfirmDialog(mainFrame, "Are you sure? This cannot be undone.", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                DarkTheme.applyTheme();

                if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                        int employeeId = Integer.parseInt(employeeIdField.getText().trim());
                        String firstName = firstNameField.getText().trim();
                        String lastName = lastNameField.getText().trim();
                        DarkTheme.showCustomDialog(mainFrame, EmployeeMethods.deleteEmployee(employeeId, firstName, lastName));

                        employeeIdField.setText("");
                        firstNameField.setText("");
                        lastNameField.setText("");
                    } catch (NumberFormatException ex) {
                        DarkTheme.showCustomDialog(mainFrame, "Employee not added. An error occurred.");
                    } catch (Exception ex) {
                        DarkTheme.showCustomDialog(mainFrame, "Employee not added. An error occurred.");
                    }
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
