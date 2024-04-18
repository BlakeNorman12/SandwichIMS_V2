/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package methods;

import java.sql.*;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.Date;
import sandwichims.objects.PerishableProduct;
import sandwichims.objects.Product;

/**
 *
 * @author bnorm
 */
public class ProductMethods {
    
    public static String addProduct(Product product, int quantity, String employee){
        
        // Verify input
        if (employee.equals("")) {
            
            return "Please enter an employee.";
        }
        else if (quantity < 0) {
            
            return "Please enter a positive integer or 0.";
        }
        
        SQLConnection connect = new SQLConnection();
        String productName = product.getName();
        Date lastUpdated = getDate();
        String shelfLife = product.getShelfLife();
        
        String sql = "INSERT INTO Product (ProductName, LastUpdated, quantity, UpdatedBy, shelfLife) VALUES (?, ?, ?, ?, ?)";
        
        if (quantity >= 0){
            try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, productName);
                pstmt.setDate(2, lastUpdated);
                pstmt.setInt(3, quantity);
                pstmt.setString(4, employee);
                pstmt.setString(5, shelfLife);


                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    return "Product added successfully.";
                } else {
                    return "An error occurred. No product was added.";
                }
            } catch (SQLException e){
                return e.getMessage();
            }
        } else {
            return "Cannot add negative product to the database. Operation cancelled.";
        }
    };
    
    public static String deleteProduct(int itemID, String productName){
        
        // Verify input
        if (productName.equals("")) {
            
            return "Please enter a product name.";
        }
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "DELETE FROM Product WHERE ProductID = ? AND ProductName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, itemID);
            pstmt.setString(2, productName);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                
                return "Product deleted successfully.";
            } else {
                
                return "No product found with the specified criteria.";
            }
        } catch (SQLException e){
            
            return e.getMessage();
        }
    };
    
    public static String updateProduct(int itemID, String productName, int quantity, String employee){
        
        //Verify input
        if (productName.equals("")) {
            
            return "Please enter a product name.";
        }
        else if (employee.equals("")) {
            
            return "Please enter an employee.";
        }
        
        SQLConnection connect = new SQLConnection();
        Date LastUpdated = getDate();

        String sql = "UPDATE Product SET ProductName = ?, LastUpdated = ?, quantity = ?, UpdatedBy = ? WHERE ProductID = ?";

        if (quantity >= 0){
            try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setString(1, productName);
                pstmt.setDate(2, LastUpdated);
                pstmt.setInt(3, quantity);
                pstmt.setString(4, employee);
                pstmt.setInt(5, itemID);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0){
                    
                    return "Product successfully updated.";
                } else {
                    
                    return "An error occurred while updating the product.";
                }

            } catch (SQLException e){
                
                return "SQL ERROR: " + e.getMessage();
            }
        } else {
                return "Cannot add negative values to the database. Database operation canceled.";
        }
    };
    
    public static DefaultCategoryDataset populateDataset(){
        
        SQLConnection connect = new SQLConnection();
        
        String query = "SELECT ProductName, Quantity FROM Product";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                dataset.addValue(quantity, "Quantity", productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        
        return dataset;
    }
    
    public static Date getDate(){
        long millis = System.currentTimeMillis();
        Date currentDate = new Date(millis);
        
        return currentDate;
    }
}
