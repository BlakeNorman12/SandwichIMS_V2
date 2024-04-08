/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sandwichims.screens;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import sandwichims.DarkTheme;
import sandwichims.objects.Employee;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author Jordan
 */
public class ManageInventoryPanelNew extends javax.swing.JPanel {

    /**
     * Creates new form ManageInventoryPanelNew
     */
    private MainFrame mainFrame;
    private Employee employee;
    
    public ManageInventoryPanelNew(MainFrame mainFrame, Employee employee) {
        
        DarkTheme.applyTheme();
        this.mainFrame = mainFrame;
        this.employee = employee;
        
        setLayout(new BorderLayout());
        
        JPanel topHalf = new JPanel(new BorderLayout());
        JScrollPane scrollPane = populateTable();
        topHalf.add(scrollPane, BorderLayout.CENTER);
        
        JPanel managementInputPanel = initManagementInput();
        topHalf.add(managementInputPanel, BorderLayout.WEST);
        
        add(topHalf, BorderLayout.NORTH);
        
        JPanel bottomHalf = new JPanel(new BorderLayout());
        addChartPanel(bottomHalf);
        add(bottomHalf, BorderLayout.CENTER);
        addPreviousScreenButton();
       
    }
    
    private void addChartPanel(JPanel bottomHalf) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(80, "Quantity", "Product A");
        dataset.addValue(50, "Quantity", "Product B");
        dataset.addValue(30, "Quantity", "Product C");
        dataset.addValue(60, "Quantity", "Product D");
        dataset.addValue(75, "Quantity", "Product E");
        dataset.addValue(80, "Quantity", "Product F");
        dataset.addValue(50, "Quantity", "Product G");
        dataset.addValue(30, "Quantity", "Product H");
        dataset.addValue(60, "Quantity", "Product I");
        dataset.addValue(75, "Quantity", "Product J");
        
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
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 500));
        
        bottomHalf.add(chartPanel, BorderLayout.CENTER);
    }
    
    private JScrollPane populateTable() {

        // Define column names
        String[] columns = {"Product ID", "Product Name", "Quantity", "Modified By"};

        // Define data for the table
        Object[][] data = null; // Initialize as null for now

        // Retrieve data from the database and populate the data array
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sandwichims", "root", "root");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            // Get the row count from the ResultSet
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Initialize data array with the correct size
            data = new Object[rowCount][4];

            // Populate data array with ResultSet values
            int row = 0;
            while (resultSet.next()) {
                data[row][0] = resultSet.getInt("productid");
                data[row][1] = resultSet.getString("productname");
                data[row][2] = resultSet.getInt("quantity");
                data[row][3] = resultSet.getString("updatedby");
                row++;
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create a table model with the data and columns
        DefaultTableModel model = new DefaultTableModel(data, columns);

        // Create the table using the table model
        JTable table = new JTable(model);
        
        // Set table theme
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setForeground(Color.BLACK);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        JViewport viewport = scrollPane.getViewport();
        viewport.setBackground(Color.DARK_GRAY);
        
        // Set the layout for the panel
        setLayout(new BorderLayout());

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
        
        return scrollPane;
    }
    
    private void addPreviousScreenButton() {
        JButton previousScreenButton = new JButton("Previous Screen");
        previousScreenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                mainFrame.navigateTo("MainMenu", employee);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Right-aligned button panel
        buttonPanel.add(previousScreenButton);
        buttonPanel.setOpaque(false); // Make the panel transparent

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the panel
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    private JPanel initManagementInput() {
    
        // Create text fields
        JTextField productIdField = new JTextField(10);
        JTextField productNameField = new JTextField(10);
        JTextField quantityField = new JTextField(10);

        // Create labels for text fields
        JLabel productIdLabel = new JLabel("Product ID:");
        JLabel productNameLabel = new JLabel("Product Name:");
        JLabel quantityLabel = new JLabel("Quantity:");

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        // Create panel for text fields and labels
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(productIdLabel);
        inputPanel.add(productIdField);
        inputPanel.add(productNameLabel);
        inputPanel.add(productNameField);
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // Create panel for input fields and buttons
        JPanel inputButtonPanel = new JPanel(new BorderLayout());
        inputButtonPanel.add(inputPanel, BorderLayout.NORTH);
        inputButtonPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return inputButtonPanel;
        // Add the input fields and buttons panel to the passed topPanel in the WEST region
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
