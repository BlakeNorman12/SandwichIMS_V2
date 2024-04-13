/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author bnorm
 */
public abstract class Product {
    
    protected String productName;

    public Product(String productName) {
        this.productName = productName;
    }
    
    public String getName() {
        
        return this.productName;
    }
    
    public String getShelfLife() {
        
        return "None";
    }
}
