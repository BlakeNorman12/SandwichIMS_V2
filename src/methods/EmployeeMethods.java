/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package methods;

import java.sql.*;

/**
 *
 * @author bnorm
 */
public class EmployeeMethods {
    
    public static void changeInfo(int employeeID, String firstName, String lastName){
        
    }
    
    public static void changeLogin(int employeeID, String newLogin, String newPassword){
        
    }
    
    public static void changePermissions(int employeeID, boolean isManager){
        
    }
    
    public static void addEmployee(String firstName, String lastName, String username, String password, boolean isManager){
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "INSERT INTO Employee (firstName, lastName, username, password, isManager) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.setBoolean(5, isManager);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("An error occurred. No employee was added.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }    
    
    public static void deleteEmployee(int EmployeeID, String FirstName, String LastName){
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "DELETE FROM Employee WHERE employeeID = ? AND firstName = ? AND lastName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, EmployeeID);
            pstmt.setString(2, FirstName);
            pstmt.setString(3, LastName);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                System.out.println("Employee deleted successfully");
            } else {
                System.out.println("No employee found with the specified criteria");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
