/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bnorm
 */

import java.sql.*;
import static org.junit.Assert.*;
import org.junit.*;
import methods.*;

public class TestEmployeeMethods {
    
    
    /* P  = (firstName != "") ^ (lastName != "") ^ (username != "") ^ (oldPassword != "") ^ (employeeID is in database) ^ (password is correct) ^ (firstName.length() <= 50) ^ (lastName.length() <= 50) ^ (usernameame.length() <= 50)
    *  C1 = firstName != ""
    *  C2 = lastName != ""
    *  C3 = username != ""
    *  C4 = old{assword != ""
    *  C5 = employeeID is in database
    *  C6 = password is correct
    *  C7 = firstName.length() <= 50
    *  C8 = lastName.length() <= 50
    *  C9 = userame.length() <= 50
    */
    
    // Modifying without a first name returns error message. C1 = F
    @Test
    public void modifyEmployeeNoFirst() {
        
        String firstName = "";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Please enter a first name.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));
    }
    
    // Modifying without a last name returns error message. C2 = F
    @Test
    public void modifyEmployeeNoLast() {
        
        String firstName = "test";
        String lastName = "";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Please enter a last name.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));
    }
    
    // Modifying without a username returns error message. C3 = F
    @Test
    public void modifyEmployeeNoUser() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Please enter a username.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));

    }
    
    // Modifying without the current password returns error message. C4 = F
    @Test
    public void modifyEmployeeNoPass() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Please enter the current password.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));

    }
    
    // Modifying an ID that doesn't exist in the database returns error message. C5 = F
    @Test
    public void modifyEmployeeInvalidID() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 2;
            
        assertEquals("Employee not updated. Old password is incorrect for provided Employee ID.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));

    }
    
    // Modifying an employee using the incorrect password returns error message. C6 = F
    @Test
    public void modifyEmployeeInvalidPassword() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "Invalid password";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Employee not updated. Old password is incorrect for provided Employee ID.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));

    }
    
    // Modifying with first name longer than 50 characters returns error message. C7 = F
    @Test
    public void modifyEmployeeLongFirst() {
        
        String firstName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'firstName' at row 1", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));
    }
    
    // Modifying with first name longer than 50 characters returns error message. C8 = F
    @Test
    public void modifyEmployeeLongLast() {
        
        String firstName = "test";
        String lastName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'lastName' at row 1", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));
    }
    
    // Modifying with first name longer than 50 characters returns error message. C9 = F
    @Test
    public void modifyEmployeeLongUser() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'username' at row 1", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));
    }
    
    // Modifying with valid ID and password returns success message. All characteristics true.
    @Test
    public void modifyEmployeeValid() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 1;
            
        assertEquals("Employee successfully updated.", EmployeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager));

    }
    
    /* P  = (firstName != "") ^ (lastName != "") ^ (username != "") ^ (password != "") ^ (username not in database) ^ (firstName.length() <= 50) ^ (lastName.length() <= 50) ^ (usernameame.length() <= 50)
    *  C1 = firstName != ""
    *  C2 = lastName != ""
    *  C3 = username != ""
    *  C4 = password != ""
    *  C5 = username not in database
    *  C6 = firstName.length() <= 50
    *  C7 = lastName.length() <= 50
    *  C8 = username.length() <= 50
    */
    
    // Adding an employee with all characteristics true returns success message. All characteristics true.
    @Test
    public void addEmployeeValid() {
        
        String firstName = "add";
        String lastName = "test";
        String userName = "testGuyNew";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
     
        // Delete test employee if already exists
        SQLConnection connect = new SQLConnection();
        String sql = "SELECT employeeid FROM employee WHERE firstName = ? AND lastName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, "add");
            pstmt.setString(2, "test");
            
            ResultSet resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                
                int ID = resultSet.getInt("employeeid");
                // Delete works with all characteristics true. See characteristics below.
                assertEquals("Employee deleted successfully.", EmployeeMethods.deleteEmployee(ID, "add", "test"));
            }
            
        } catch (SQLException e){
            
            System.out.println("SQL Error: " + e.getMessage());
        }
        
        String result = EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager);
        assertEquals("Employee added successfully.", result);
    }
    
    
    // Adding an employee with no first name returns error message. C1 = F
    @Test
    public void addEmployeeNoFirst() {
        
        String firstName = "";
        String lastName = "guy";
        String userName = "testGuyNew";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("Please enter a first name.", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with no last name returns error message. C2 = F
    @Test
    public void addEmployeeNoLast() {
        
        String firstName = "test";
        String lastName = "";
        String userName = "testGuyNew";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("Please enter a last name.", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with no first name returns error message. C3 = F
    @Test
    public void addEmployeeNoUser() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals( "Please enter a username.", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with no first name returns error message. C4 = F
    @Test
    public void addEmployeeNoPass() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuyNew";
        String password = "";
        boolean isManager = true;
        
        assertEquals("Please enter a password.", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with a username that already exists in the database returns error message. C5 = F
    @Test
    public void addEmployeeInvalidUsername() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("SQL ERROR: Duplicate entry 'testGuy' for key 'employee.unique_username'", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));

    }
    
    // Adding an employee with first name length > 50 returns error message. C6 = F
    @Test
    public void addEmployeeLongFirst() {
        
        String firstName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String lastName = "guy";
        String userName = "testGuyNew";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'firstName' at row 1", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with last name length > 50 returns error message. C7 = F
    @Test
    public void addEmployeeLongLast() {
        
        String firstName = "test";
        String lastName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String userName = "testGuyNew";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'lastName' at row 1", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    // Adding an employee with username length > 50 returns error message. C8 = F
    @Test
    public void addEmployeeLongUser() {
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'username' at row 1", EmployeeMethods.addEmployee(firstName, lastName, userName, password, isManager));
    }
    
    /* P  = (firstName != "") ^ (lastName != "") ^ (employeeID is in database) ^ (firstName is correct) ^ (lastName is correct)
    *  C1 = firstName != ""
    *  C2 = lastName != ""
    *  C3 = employeeID is in database
    *  C4 = firstName is correct
    *  C5 = lastName is correct 
    */
    
    // Deleting an employee with no first name returns error message. C1 = F
    @Test
    public void deleteEmployeeNoFirst() {
        
        String firstName = "";
        String lastName = "guy";
        int employeeID = 1;
        
        assertEquals("Please enter a first name.", EmployeeMethods.deleteEmployee(employeeID, firstName, lastName));
    }
    
    // Deleting an employee with no first name returns error message. C2 = F
    @Test
    public void deleteEmployeeNoLast() {
        
        String firstName = "test";
        String lastName = "";
        int employeeID = 1;
        
        assertEquals("Please enter a last name.", EmployeeMethods.deleteEmployee(employeeID, firstName, lastName));
    }
    
    // Deleting an employee with an ID that doesn't exist returns error message. C3 = F
    @Test
    public void deleteEmployeeInvalidID() {
        
        String firstName = "test";
        String lastName = "guy";
        int employeeID = 2;
        
        assertEquals("No employee found with the specified criteria.", EmployeeMethods.deleteEmployee(employeeID, firstName, lastName));

    }
    
    // Deleting an employee when the first name doesn't match the ID returns error message. C4 = F
    @Test
    public void deleteEmployeeInvalidFirst() {
        
        String firstName = "wrong";
        String lastName = "guy";
        int employeeID = 1;
        
        assertEquals("No employee found with the specified criteria.", EmployeeMethods.deleteEmployee(employeeID, firstName, lastName));

    }
    
    // Deleting an employee when the last name doesn't match the ID returns error message. C5 = F
    @Test
    public void deleteEmployeeInvalidLast() {
        
        String firstName = "test";
        String lastName = "failure";
        int employeeID = 1;
        
        assertEquals("No employee found with the specified criteria.", EmployeeMethods.deleteEmployee(employeeID, firstName, lastName));

    }
    
    /* P  = (password.length >= 8) ^ (password contains special character)
    *  C1 = password.length >= 8
    *  C2 = password contains special character
    */
    
    // verifyNewPassword() returns false when password length is less than 8. C1 = F
    @Test
    public void verifyNewPasswordShort() {
        
        String password = "";
        
        assertFalse(EmployeeMethods.verifyNewPassword(password));
    }
    
    // verifyNewPassword() returns false when password does not contain a special character. C1 = F
    @Test
    public void verifyNewPasswordNoSpecial() {
        
        String password = "12345678";
        
        assertFalse(EmployeeMethods.verifyNewPassword(password));
    }
    
    // verifyNewPassword() returns true when all characteristics are true.
    @Test
    public void verifyNewPasswordValid() {
        
        String password = "validpass!";
        
        assertTrue(EmployeeMethods.verifyNewPassword(password));
    }
    
    /* P  = (oldPassword != "") ^ (password is correct)
    *  C1 = oldPassword != ""
    *  C2 = password is correct
    */
    
    // verifyOldPassword() returns false when no oldPassword is entered. C1 = F
    @Test
    public void verifyPasswordNoPass() {
        
        String password = "";
        int employeeID = 1;
        
        assertFalse(EmployeeMethods.verifyOldPassword(employeeID, password));
    }
    
    // verifyOldPassword() returns false when password doesn't match the password for the given ID in the database. C2 = F
    @Test
    public void verifyPasswordInvalid() {
        
        String password = "Invalid password";
        int employeeID = 1;
        
        assertFalse(EmployeeMethods.verifyOldPassword(employeeID, password));
    }
    
    // verifyOldPassword() returns true when password matches password in database for given ID. All characteristics true.
    @Test
    public void verifyPasswordValid() {
        
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        int employeeID = 1;
        
        assertTrue(EmployeeMethods.verifyOldPassword(employeeID, password));
    }
}

