/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author Jordan
 */
public class PerishableProductFactory implements ProductFactory {
    
    @Override
    public Product createProduct(String productName, String shelfLife) {
        
        return new PerishableProduct(productName, shelfLife);
    }
        
    @Override
    public Product createProduct(String productName) {
        
        throw new UnsupportedOperationException("Perishable products must have a shelf life.");
    }
}
