/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author Jordan
 */
public class PerishableProduct extends Product {
    
    private String shelfLife;

    public PerishableProduct(String productName, String shelfLife) {
        super(productName);
        this.shelfLife = shelfLife;
    }
    
    @Override
    public String getShelfLife() {
        
        return this.shelfLife;
    }
    
    
}
