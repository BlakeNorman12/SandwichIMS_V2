/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.screens;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import sandwichims.objects.Employee;

/**
 *
 * @author bnorm
 */
public class MainFrame extends JFrame {
    
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    Map<String, JPanel> panels = new HashMap<>();
    Employee currentEmployee;

    public MainFrame() {
        setTitle("Sandwich Restaurant Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(cardPanel);
        navigateTo("Login", null); // Initially show the login panel
    }
    
    public void navigateTo(String panelName, Employee employee){
        if (employee != null) {
            this.currentEmployee = employee; // Update the current employee
        }
        
        if (!panels.containsKey(panelName)) {
            // Instantiate the panel as needed and add it to the cardPanel and the map
            JPanel panel = createPanel(panelName, currentEmployee);
            panels.put(panelName, panel);
            cardPanel.add(panel, panelName);
        } else {
            // If panel exists, update it with the current employee
            updatePanel(panelName, currentEmployee);
        }
        
        cardLayout.show(cardPanel, panelName);
    }
    
    private JPanel createPanel(String panelName, Employee employee) {
        switch (panelName) {
            case "Login":
                return new LoginPanel(this);
            case "MainMenu":
                return new MainMenuPanel(this, employee);
            case "ManageEmployees":
                return new ManageEmployeesPanel(this, employee);
            case "ManageInventory":
                return new ManageInventoryPanelNew(this, employee);
            case "ModifyEmployee":
                return new ModifyEmployeePanel(this, employee);
            case "AddEmployee":
                return new AddEmployeePanel(this, employee);
            case "DeleteEmployee":
                return new DeleteEmployeePanel(this, employee);
            default:
                throw new IllegalArgumentException("Unknown panel: " + panelName);
        }
}

    private void updatePanel(String panelName, Employee employee) {
        
        JPanel panel = panels.get(panelName);
    
        if (panel != null) {
            switch (panelName) {
                case "MainMenu":
                    if (panel instanceof MainMenuPanel) {
                        ((MainMenuPanel) panel).setEmployee(employee);
                    }
                    break;
                case "ManageEmployees":
                    if (panel instanceof ManageEmployeesPanel) {
                        ((ManageEmployeesPanel) panel).setEmployee(employee);
                    }
                    break;
                case "ManageInventory":
                    if (panel instanceof ManageInventoryPanel) {
                        ((ManageInventoryPanel) panel).setEmployee(employee);
                    }
                    break;
                case "ModifyEmployee":
                    if (panel instanceof ModifyEmployeePanel) {
                        ((ModifyEmployeePanel) panel).setEmployee(employee);
                    }
                    break;
                case "AddEmployee":
                    if (panel instanceof AddEmployeePanel) {
                        ((AddEmployeePanel) panel).setEmployee(employee);
                    }
                    break;
                case "DeleteEmployee":
                    if (panel instanceof DeleteEmployeePanel) {
                        ((DeleteEmployeePanel) panel).setEmployee(employee);
                    }
                    break;
    }}}
    
    public void showMainMenu(Employee employee) {
        cardPanel.removeAll();

        MainMenuPanel mainMenuPanel = new MainMenuPanel(this, employee);
        LoginPanel loginPanel = new LoginPanel(this);
        
        cardPanel.add(mainMenuPanel, "MainMenu");
        cardPanel.add(loginPanel, "Login");
        
        cardLayout.show(cardPanel, "MainMenu");

        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    public void logout() {
        
        panels.clear();
        currentEmployee = null;
        
        cardPanel.removeAll();
        LoginPanel loginPanel = new LoginPanel(this);
        cardPanel.add(loginPanel, "Login");
        cardLayout.show(cardPanel, "Login");
        
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
