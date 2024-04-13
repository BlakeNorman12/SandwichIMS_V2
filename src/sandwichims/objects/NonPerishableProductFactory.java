/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author Jordan
 */
public class NonPerishableProductFactory implements ProductFactory {
    
    @Override
    public Product createProduct(String productName, String shelfLife) {
        
        throw new UnsupportedOperationException("Non-perishable products do not have a shelf life.");
    }
        
    @Override
    public Product createProduct(String productName) {
        
        return new NonPerishableProduct(productName);
    }
}
