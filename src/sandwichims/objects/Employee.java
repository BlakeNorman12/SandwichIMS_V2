/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author bnorm
 */
public class Employee {
    
    private String firstName;
    private String lastName;
    private boolean isManager;
    
    public Employee(String firstName, String lastName, boolean isManager){
        this.firstName = firstName;
        this.lastName = lastName;
        this.isManager = isManager;
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    public boolean isManager() { 
        return isManager; 
    }
    
}
