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
    
    public static String modifyEmployee(int employeeID, String firstName, String lastName, String username, String oldPassword, String password, boolean isManager){
        
        // Verify inputs
        if (firstName.equals("")) {
            
            return "Please enter a first name.";
        }
        else if (lastName.equals("")) {
            
            return "Please enter a last name.";
        }
        else if (username.equals("")) {
            
            return "Please enter a username.";
        }
        else if (oldPassword.equals("")) {
            
            return "Please enter the current password.";
        }
        
        boolean passwordVerified = verifyOldPassword(employeeID, oldPassword);
        
        if (passwordVerified){
            SQLConnection connect = new SQLConnection();

            String sql = "UPDATE Employee SET firstName = ?, lastName = ?, username = ?, password = ?, isManager = ? WHERE employeeID = ?";

            try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){

                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, username);
                pstmt.setString(4, password);
                pstmt.setBoolean(5, isManager);
                pstmt.setInt(6, employeeID);

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0){
                    
                    return "Employee successfully updated.";
                } else {
                    
                    return "An error occurred while updating the employee.";
                }
            } catch (SQLException e){
                return "SQL ERROR: " + e.getMessage();
            }
        } else {
            return "Employee not updated. Old password is incorrect for provided Employee ID.";
        }
    }
    
    
    public static String addEmployee(String firstName, String lastName, String username, String password, boolean isManager){
        
        // Verify inputs
        if (firstName.equals("")) {
            
            return "Please enter a first name.";
        }
        else if (lastName.equals("")) {
            
            return "Please enter a last name.";
        }
        else if (username.equals("")) {
            
            return "Please enter a username.";
        }
        else if (password.equals("")) {
            
            return "Please enter a password.";
        }
        
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
                
                return "Employee added successfully.";
            } else {
                
                return "An error occurred. No employee was added.";
            }
        } catch (SQLException e){
            
            return "SQL ERROR: " + e.getMessage();
        }
    }    
    
    public static String deleteEmployee(int EmployeeID, String firstName, String lastName){
        
        // Verify inputs
        if (firstName.equals("")) {
            
            return "Please enter a first name.";
        }
        else if (lastName.equals("")) {
            
            return "Please enter a last name.";
        }
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "DELETE FROM Employee WHERE employeeID = ? AND firstName = ? AND lastName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, EmployeeID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0){
                
                return "Employee deleted successfully.";
            } else {
                
                return "No employee found with the specified criteria.";
            }
        } catch (SQLException e){
            
            return "SQL ERROR: " + e.getMessage();
        }
    }
    
    public static boolean verifyNewPassword(String password) {
        
        if (password.length() < 8) {
            
            return false;
        }
        
        boolean hasSpecialCharacters = false;
        for (char c : password.toCharArray()) {
            
            if (!Character.isLetterOrDigit(c)) {
                
                hasSpecialCharacters = true;
                break;
            }
        }
        
        return hasSpecialCharacters;
    }
    
    public static boolean verifyOldPassword(int employeeID, String oldPassword){
        
        // Verify input
        if (oldPassword.equals("")) {
            
            return false;
        }
        
        SQLConnection connect = new SQLConnection();
        
        String sql = "SELECT employeeID FROM Employee WHERE employeeID = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, employeeID);
            pstmt.setString(2, oldPassword);
            System.out.println(oldPassword);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            System.out.println("SQL Error: " + e.getMessage());
        }
        
        return false;
    }
}
