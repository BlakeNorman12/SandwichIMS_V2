
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import methods.ProductMethods;
import methods.SQLConnection;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import sandwichims.objects.PerishableProductFactory;
import sandwichims.objects.Product;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bnorm
 */
public class TestProductMethods {
    
    /* P  = (employee != "") ^ (quantity >= 0) ^ (employee.length() <= 50)
    *  C1 = employee != ""
    *  C2 = quantity >= 0
    *  C3 = employee.length() <= 50
    */
    
    // Adding product without employee returns error message. C1 = F
    @Test
    public void addProductNoEmployee() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "Test Shelf Life");
        int quantity = 1;
        String employee = "";
        
        assertEquals("Please enter an employee.", ProductMethods.addProduct(product, quantity, employee));
    }
    
    // Adding product with negative quantity returns error message. C2 = F
    @Test
    public void addProductNegativeQuantity() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "Test Shelf Life");
        int quantity = -1;
        String employee = "Test Employee";
        
        assertEquals("Please enter a positive integer or 0.", ProductMethods.addProduct(product, quantity, employee));
    }
    
    // Adding product with employee name length greater than 50 returns error message. C3 = F
    @Test
    public void addProductLongEmployee() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "Test Shelf Life");
        int quantity = 1;
        String employee = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        
        assertEquals("Data truncation: Data too long for column 'UpdatedBy' at row 1", ProductMethods.addProduct(product, quantity, employee));
    }
    
    // Adding a product with all characteristics true returns success message.
    @Test
    public void addProductValid() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Add Product", "Test Shelf Life");
        int quantity = 1;
        String employee = "Test Employee";
     
        // Delete test product if already exists
        SQLConnection connect = new SQLConnection();
        String sql = "SELECT productID FROM product WHERE productName = ?";
        
        try (Connection conn = DriverManager.getConnection(connect.getURL(), connect.getUser(), connect.getPass());
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, "Test Add Product");
            
            ResultSet resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                
                int ID = resultSet.getInt("productid");
                // deleteProduct() works with all characteristics true. See characteristics below.
                assertEquals("Product deleted successfully.", ProductMethods.deleteProduct(ID, "Test Add Product"));
            }
            
        } catch (SQLException e){
            
            System.out.println("SQL Error: " + e.getMessage());
        }
        
        String result = ProductMethods.addProduct(product, quantity, employee);
        assertEquals("Product added successfully.", result);
    }
    
    /* P  = (productName != "") ^ (itemID is in database) ^ (productName is correct)
    *  C1 = productName != ""
    *  C2 = itemID is in database
    *  C3 = productName is correct
    */
    
    // Deleting product with no product name returns error message. C1 = F
    @Test
    public void deleteProductNoName() {
        
        int ID = 1;
        String productName = "";
        
        assertEquals("Please enter a product name.", ProductMethods.deleteProduct(ID, productName));
    }
    
    // Deleting product with invalid ID returns error message. C2 = F
    @Test
    public void deleteProductInvalidID() {
        
        int ID = -1;
        String productName = "Test Product";
        
        assertEquals("No product found with the specified criteria.", ProductMethods.deleteProduct(ID, productName));
    }
    
    // Deleting product with invalid ID returns error message. C3 = F
    @Test
    public void deleteProductInvalidName() {
        
        int ID = 1;
        String productName = "Wrong Test Product";
        
        assertEquals("No product found with the specified criteria.", ProductMethods.deleteProduct(ID, productName));
    }
    
    /* P  = (productName != "") ^ (employee != "") ^ (itemID is in database) ^ (quantity >= 0) ^ (productName.length <= 50) ^ (employee.length <= 50) ^ (shelfLife.length <= 50)
    *  C1 = productName != ""
    *  C2 = employee != ""
    *  C3 = itemID is in database
    *  C4 = quantity >= 0
    *  C5 = productName.length <= 50
    *  C6 = employee.length <= 50
    *  C7 = shelfLife.length <= 50
    */
    
    // Updating product without a product name returns error message. C1 = F.
    @Test
    public void updateProductNoProduct() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("", "");
        int itemID = 1;
        int quantity = 1;
        String employee = "Test Employee";
        
        assertEquals("Please enter a product name.", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product without an employee name returns error message. C2 = F.
    @Test
    public void updateProductNoEmployee() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "");
        int itemID = 1;
        int quantity = 1;
        String employee = "";

        
        assertEquals("Please enter an employee.", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product with invalid ID returns error message. C3 = F.
    @Test
    public void updateProductInvalidID() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "");
        int itemID = -1;
        int quantity = 1;
        String employee = "Test Employee";
        
        assertEquals("No product found for given ID.", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product with invalid quantity returns error message. C4 = F
    @Test
    public void updateProductInvalidQuantity() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "");
        int itemID = 1;
        int quantity = -1;
        String employee = "Test Employee";
        
        assertEquals("Cannot add negative values to the database. Database operation canceled.", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product with product name longer than 50 returns error message. C5 = F.
    @Test
    public void updateProductLongProduct() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "");
        int itemID = 1;
        int quantity = 1;
        String employee = "Test Employee";
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'ProductName' at row 1", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }

    // Updating product with employee name longer than 50 returns error message. C6 = F
    @Test
    public void updateProductLongEmployee() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "");
        int itemID = 1;
        int quantity = 1;
        String employee = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'UpdatedBy' at row 1", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product with shelf life longer than 50 returns error message. C7 = F
    @Test
    public void updateProductLongShelf() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        int itemID = 1;
        int quantity = 1;
        String employee = "Test Employee";
        
        assertEquals("SQL ERROR: Data truncation: Data too long for column 'ShelfLife' at row 1", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
    
    // Updating product with all characteristics true returns success message. P = T
    @Test
    public void updateProductValid() {
        
        PerishableProductFactory productFactory = new PerishableProductFactory();
        
        Product product = productFactory.createProduct("Test Product", "");
        int itemID = 1;
        int quantity = 1;
        String employee = "Test Employee";
        
        assertEquals("Product successfully updated.", ProductMethods.updateProduct(itemID, product, quantity, employee));
    }
}
