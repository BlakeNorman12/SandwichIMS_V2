/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;

import sandwichims.objects.Employee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import sandwichims.DarkTheme;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import methods.SQLConnection;
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
        
        manageEmployeesButton.addActionListener(e -> {
            
            if (employee.isManager()){
                mainFrame.navigateTo("ManageEmployees", employee);
            } else {
                DarkTheme.showCustomDialog(mainFrame, "You must be a manager to modify employee information.");
            }
        });
        manageInventoryButton.addActionListener(e -> mainFrame.navigateTo("ManageInventory", employee));
        downloadReportButton.addActionListener(e -> {
            generateReport();
        });
        
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
    
    public void generateReport(){
        
        SQLConnection connect = new SQLConnection();
        
        try {
            File reportsDir = new File("reports");
            
            if (!reportsDir.exists()){
                reportsDir.mkdir();
            }
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String formattedDateTime = now.format(formatter);
            
            String fileName = "reports/Report_" + formattedDateTime + ".txt";
            
            String productQuery = "SELECT * FROM Product";
            String employeeQuery = "SELECT * FROM Employee";
            
            try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass())){
                PreparedStatement pstmtProduct = conn.prepareStatement(productQuery);
                ResultSet rsProduct = pstmtProduct.executeQuery();
                PreparedStatement pstmtEmployee = conn.prepareStatement(employeeQuery);
                ResultSet rsEmployee = pstmtEmployee.executeQuery();
                PrintWriter writer = new PrintWriter(new FileWriter(fileName));
                
                writer.println("Products Report:");
                while (rsProduct.next()){
                    writer.println("Product ID: " + rsProduct.getInt("ProductID") +
                            ", Product Name: " + rsProduct.getString("ProductName") + 
                            ", Quantity: " + rsProduct.getInt("Quantity") + 
                            ", Last Updated: " + rsProduct.getDate("LastUpdated") +
                            ", Updated By: " + rsProduct.getString("UpdatedBy"));
                }
                
                writer.println("\nEmployees Report:");
                while (rsEmployee.next()){
                    writer.println("Employee ID: " + rsEmployee.getInt("employeeID") +
                            ", Name: " + rsEmployee.getString("firstName") + " " + rsEmployee.getString("lastName") + 
                            ", Username: " + rsEmployee.getString("username") +
                            ", Manager: " + rsEmployee.getBoolean("isManager"));
                }
                
                writer.flush();
                
                    DarkTheme.showCustomDialog(mainFrame, "Report successfully downloaded.");
            } catch (Exception e) {
                e.printStackTrace();
                    DarkTheme.showCustomDialog(mainFrame, "Failed to generate report.");
            }
        } catch (Exception e){
            System.err.println("An error occurred while generating the Report.");
        }
    }
}