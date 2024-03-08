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
public class MainFrame extends JFrame {
    
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel();
    
    public MainFrame() {
        
        setTitle("***Store Name*** Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardPanel.setLayout(cardLayout);
        
        //screens
        
        LoginPanel loginPanel = new LoginPanel(this);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        ManageEmployeesPanel manageEmployeesPanel = new ManageEmployeesPanel(this);
        ManageInventoryPanel manageInventoryPanel = new ManageInventoryPanel(this);
        ModifyEmployeePanel modifyEmployeePanel = new ModifyEmployeePanel(this);
        AddEmployeePanel addEmployeePanel = new AddEmployeePanel(this);
        DeleteEmployeePanel deleteEmployeePanel = new DeleteEmployeePanel(this);
        
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(manageEmployeesPanel, "ManageEmployees");
        cardPanel.add(manageInventoryPanel, "ManageInventory");
        cardPanel.add(modifyEmployeePanel, "modifyEmployee");
        cardPanel.add(addEmployeePanel, "addEmployee");
        cardPanel.add(deleteEmployeePanel, "deleteEmployee");
        
        add(cardPanel);
        
        cardLayout.show(cardPanel, "Login");
        
    }
    
    public void navigateTo(String panelName){
        
        cardLayout.show(cardPanel, panelName);
        
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
