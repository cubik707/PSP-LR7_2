package org.shop.service;

import org.shop.dao.ProductDAO;
import org.shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void saveProduct(Product product) {
        validateProduct(product);
        productDAO.save(product);
    }

    public Product getProductById(Long id) {
        return productDAO.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value.");
        }

        if (product.getDescription() != null && product.getDescription().length() > 1000) {
            throw new IllegalArgumentException("Product description is too long (max 1000 characters).");
        }
    }
}