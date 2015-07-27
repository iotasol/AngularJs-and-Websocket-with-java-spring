package com.demo.service.product;

import java.util.List;

import com.demo.model.product.Product;

/**
 * Interface to Declare all method of Product
 * 
 * @author admin
 *
 */
public interface ProductService {

	/**
	 * Method to save New Product in our system
	 * 
	 * @param product
	 * @return Product
	 */
	Product saveProduct(Product product);

	/**
	 * Method to update existing Product in our system
	 * 
	 * @param product
	 * @return Product
	 */
	Product updateProduct(Product product);

	/**
	 * Method to Get All Products
	 * 
	 * @return List<Product>
	 */
	List<Product> getProducts();

	/**
	 * Method to get product by id
	 * 
	 * @param productId
	 * @return Product
	 */
	Product getProductById(Long productId);

	/**
	 * Method to Delete Product by Id
	 * 
	 * @param productId
	 * @return Product
	 */
	Product deleteProduct(Long productId);
}
