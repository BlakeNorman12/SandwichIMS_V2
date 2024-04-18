/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sandwichims.screens;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import methods.ProductMethods;
import methods.SQLConnection;
import sandwichims.DarkTheme;
import sandwichims.objects.Employee;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import sandwichims.objects.*;

/**
 *
 * @author Jordan
 */
public class ManageInventoryPanel extends javax.swing.JPanel {

    private MainFrame mainFrame;
    private Employee employee;
    private JTable table;
    private DefaultCategoryDataset dataset;

    public ManageInventoryPanel(MainFrame mainFrame, Employee employee) {

        DarkTheme.applyTheme();
        this.mainFrame = mainFrame;
        this.employee = employee;

        setLayout(new BorderLayout());

        JPanel topHalf = new JPanel(new BorderLayout());
        JScrollPane scrollPane = createTable();
        topHalf.add(scrollPane, BorderLayout.CENTER);

        JPanel managementInputPanel = initManagementInput();
        topHalf.add(managementInputPanel, BorderLayout.WEST);

        add(topHalf, BorderLayout.NORTH);

        JPanel bottomHalf = new JPanel(new BorderLayout());
        createChartPanel(bottomHalf);
        add(bottomHalf, BorderLayout.CENTER);
        addPreviousScreenButton();

    }

    private JScrollPane createTable() {
        table = new JTable();
        updateTable();
        JScrollPane scrollPane = new JScrollPane(table);
        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Color.DARK_GRAY);
        return scrollPane;
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Quantity");
        model.addColumn("Shelf-Life");
        model.addColumn("Last Updated");
        model.addColumn("Last Modified By");

        try {
            SQLConnection connect = new SQLConnection();
            Connection connection = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String productName = resultSet.getString("productname");
                int quantity = resultSet.getInt("quantity");
                String shelfLife = resultSet.getString("shelflife");
                String lastUpdated = resultSet.getString("lastupdated");
                String updatedBy = resultSet.getString("updatedby");

                model.addRow(new Object[]{productId, productName, quantity, shelfLife, lastUpdated, updatedBy});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }

    private void createChartPanel(JPanel bottomHalf) {
        dataset = new DefaultCategoryDataset();
        updateChart();
        JFreeChart chart = ChartFactory.createBarChart(
                "Inventory Count",
                "Product",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 500));

        bottomHalf.add(chartPanel, BorderLayout.CENTER);
    }

    private void updateChart() {
        dataset.clear();

        try {
            SQLConnection connect = new SQLConnection();
            Connection connection = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                String productName = resultSet.getString("productname");
                int quantity = resultSet.getInt("quantity");
                dataset.setValue(quantity, "Quantity", productName);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addPreviousScreenButton() {
        JButton previousScreenButton = new JButton("Previous Screen");
        previousScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.navigateTo("MainMenu", employee);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(previousScreenButton);
        buttonPanel.setOpaque(false);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private JPanel initManagementInput() {
        JTextField productIdField = new JTextField(10);
        JTextField productNameField = new JTextField(10);
        JTextField productShelfField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JLabel productIdLabel = new JLabel("Product ID:");
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel productShelfLabel = new JLabel("Product Shelf-Life:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(productIdLabel);
        inputPanel.add(productIdField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(productShelfLabel);
        inputPanel.add(productShelfField);
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);
        inputPanel.add(Box.createVerticalStrut(10));

        addButton.addActionListener(e -> {
            if (productNameField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty()) {
                DarkTheme.showCustomDialog(mainFrame, "Please fill out Product Name and Quantity fields.");
                return;
            }
            String productName = productNameField.getText();
            String productShelf = productShelfField.getText();
            Product product;
            
            if (productShelf.equals("")) {
                
                NonPerishableProductFactory nonPerishableFactory = new NonPerishableProductFactory();
                product = nonPerishableFactory.createProduct(productName);
            } else {
                
                PerishableProductFactory perishableFactory = new PerishableProductFactory();
                product = perishableFactory.createProduct(productName, productShelf);
            }
            
            int quantity = Integer.parseInt(quantityField.getText());
            ProductMethods.addProduct(product, quantity, employee.getFirstName() + " " + employee.getLastName());
            productIdField.setText("");
            productNameField.setText("");
            quantityField.setText("");
            updateTable();
            updateChart();
        });

        deleteButton.addActionListener(e -> {
            if (productIdField.getText().trim().isEmpty() || productNameField.getText().trim().isEmpty()) {
                DarkTheme.showCustomDialog(mainFrame, "Please fill out ProductID and Product Name fields.");
                return;
            }
            String productName = productNameField.getText();
            int productId = Integer.parseInt(productIdField.getText());
            ProductMethods.deleteProduct(productId, productName);
            productIdField.setText("");
            productNameField.setText("");
            quantityField.setText("");
            updateTable();
            updateChart();
        });

        updateButton.addActionListener(e -> {
            if (productIdField.getText().trim().isEmpty() || productNameField.getText().trim().isEmpty() || quantityField.getText().trim().isEmpty()) {
                DarkTheme.showCustomDialog(mainFrame, "Please fill out all fields.");
                return;
            }
            String productName = productNameField.getText();
            int productId = Integer.parseInt(productIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            ProductMethods.updateProduct(productId, productName, quantity, employee.getFirstName() + " " + employee.getLastName());
            productIdField.setText("");
            productNameField.setText("");
            productShelfField.setText("");
            quantityField.setText("");
            updateTable();
            updateChart();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(inputPanel, BorderLayout.NORTH);
        inputButtonPanel.add(buttonPanel, BorderLayout.CENTER);

        return inputButtonPanel;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
