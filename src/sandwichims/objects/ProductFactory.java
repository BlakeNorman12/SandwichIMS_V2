/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sandwichims.objects;

/**
 *
 * @author Jordan
 */
interface ProductFactory {
    
    Product createProduct(String productName, String shelfLife);
    
    Product createProduct(String productName);
}
