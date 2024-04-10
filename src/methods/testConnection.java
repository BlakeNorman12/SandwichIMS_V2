package methods;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jordan
 */
public class testConnection {
    
    // Change these variables to connect to your SQL server.
    private String url = "jdbc:mysql://localhost:3306/TestSandwichIMS";
    private String user = "root";
    private String pass = "root";
    
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
