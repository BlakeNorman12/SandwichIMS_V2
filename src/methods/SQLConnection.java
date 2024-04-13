/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package methods;

/**
 *
 * @author Jordan
 */
public class SQLConnection {
    
    // Change these variables to connect to your SQL server.
    // Change url to "...localhost:3306/testsandwichims" for testing
    private String url = "jdbc:mysql://localhost:3306/sandwichims";
    private String user = "root";
    private String pass = "Abster001";
    
    public String getURL() {
        
        return url;
    }
    
    public String getUser() {
        
        return user;
    }
    
    public String getPass() {
        
        return pass;
    }
}
