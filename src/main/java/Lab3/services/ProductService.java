package Lab3.services;

import Lab3.entities.*;
import Lab3.repositories.Repository;

import java.util.List;
import java.util.Optional;


/**
 * The Service Layer for handling product-related business logic.
 * This class is completely decoupled from the data storage mechanism. It operates
 * exclusively on the Repository<ProductEntity> interface, which is provided to it
 * via its constructor (Dependency Injection).
 */
public class ProductService {


    // The service depends on the repository abstraction, not a concrete implementation.
    private final Repository<ProductEntity> productRepository;


    /**
     * Constructs the ProductService with a specific repository implementation.
     * @param productRepository The data repository to be used (e.g., MySQL, H2, InMemory).
     */
    public ProductService(Repository<ProductEntity> productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Saves a given product entity.
     * @param entity The entity to save.
     * @return The saved entity.
     */
    public ProductEntity saveProduct(ProductEntity entity) {
        // The service's job is to orchestrate. It asks the repository to save the object.
        return productRepository.save(entity);
    }


    /**
     * Retrieves all products.
     * @return A list of all products.
     */
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }


    /**
     * Finds a product by its ID.
     * @param id The ID of the product.
     * @return An Optional containing the product if found.
     */
    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }


    /**
     * Business logic to delete a product.
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}