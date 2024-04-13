/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bnorm
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import methods.*;

public class TestEmployeeMethods {
    
    @Test
    public void modifyEmployeeInvalidID() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 2;
            
        assertEquals(employeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager), "Employee not updated. Old password is incorrect for provided Employee ID." );

    }
    
    @Test
    public void modifyEmployeeInvalidPassword() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String oldPassword = "Invalid password";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        int employeeID = 2;
            
        assertEquals(employeeMethods.modifyEmployee(employeeID, firstName, lastName, userName, oldPassword, password, isManager), "Employee not updated. Old password is incorrect for provided Employee ID." );

    }
    
    @Test
    public void addEmployeeInvalidUsername() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "test";
        String lastName = "guy";
        String userName = "testGuy";
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean isManager = true;
        
        assertEquals(employeeMethods.addEmployee(firstName, lastName, userName, password, isManager), "SQL ERROR: Duplicate entry 'testGuy' for key 'employee.unique_username'");

    }
    
    @Test
    public void deleteEmployeeInvalidID() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "test";
        String lastName = "guy";
        int employeeID = 2;
        
        assertEquals(employeeMethods.deleteEmployee(employeeID, firstName, lastName), "No employee found with the specified criteria.");

    }
    
    @Test
    public void deleteEmployeeInvalidFirst() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "wrong";
        String lastName = "guy";
        int employeeID = 1;
        
        assertEquals(employeeMethods.deleteEmployee(employeeID, firstName, lastName), "No employee found with the specified criteria.");

    }
    
    @Test
    public void deleteEmployeeInvalidLast() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String firstName = "test";
        String lastName = "failure";
        int employeeID = 1;
        
        assertEquals(employeeMethods.deleteEmployee(employeeID, firstName, lastName), "No employee found with the specified criteria.");

    }
    
    @Test
    public void verifyPasswordInvalid() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String password = "Invalid password";
        int employeeID = 1;
        
        assertFalse(employeeMethods.verifyOldPassword(employeeID, password));
    }
    
    @Test
    public void verifyPasswordValid() {
        
        EmployeeMethods employeeMethods = new EmployeeMethods();
        
        String password = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        int employeeID = 1;
        
        assertTrue(employeeMethods.verifyOldPassword(employeeID, password));
    }
}

