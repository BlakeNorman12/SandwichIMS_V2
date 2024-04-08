/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    
    public static void updateProduct(int itemID, String productName, int quantity){
    
    };
    
    public static void populateDataset(){
    
    };
}
