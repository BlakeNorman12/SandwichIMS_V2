/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package methods;

import java.sql.*;
import org.jfree.data.category.DefaultCategoryDataset;
import sandwichims.objects.Employee.*;

/**
 *
 * @author bnorm
 */
public class ProductMethods {
    
    public static void addProduct(String productName, int quantity, String employee){
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "INSERT INTO Product (ProductName, LastUpdated, quantity, UpdatedBy) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, productName);
            pstmt.setString(2, employee);
            pstmt.setInt(3, quantity);

            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("An error occurred. No product was added.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    };
    
    public static void deleteProduct(int itemID, String productName){
        SQLConnection connect = new SQLConnection();
        
        String sql = "DELETE FROM Employee WHERE ProductID = ? AND ProductName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, itemID);
            pstmt.setString(2, productName);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("No product found with the specified criteria");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    };
    
    public static void updateProduct(int itemID, String productName, int quantity, String employee){
        
        SQLConnection connect = new SQLConnection();

        String sql = "UPDATE Product SET itemID = ?, ProductName = ?, quantity = ?, UpdatedBy = ?";

        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, itemID);
            pstmt.setString(2, productName);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, employee);
            

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0){
                System.out.println("Product successfully updated");
            } else {
                System.out.println("An error occurred while updating the product.");
            }

        } catch (SQLException e){
            System.out.println("SQL ERROR: " + e.getMessage());
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
}
