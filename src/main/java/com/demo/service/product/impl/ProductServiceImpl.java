package com.demo.service.product.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.product.Product;
import com.demo.repository.product.ProductRepository;
import com.demo.service.product.ProductService;

/**
 * Service Class to Implement all Product Service Method
 * 
 * @author admin
 *
 */
@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Method to save New Product in our system
	 * 
	 * @param product
	 * @return Product
	 */
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Method to update existing Product in our system
	 * 
	 * @param product
	 * @return Product
	 */
	@Transactional
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Method to Get All Products
	 * 
	 * @return List<Product>
	 */
	@Transactional
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	/**
	 * Method to get product by id
	 * 
	 * @param productId
	 * @return Product
	 */
	@Transactional
	public Product getProductById(Long productId) {
		return productRepository.findOne(productId);
	}

	/**
	 * Method to Delete Product by Id
	 * 
	 * @param productId
	 * @return Product
	 */
	@Transactional
	public Product deleteProduct(Long productId) {
		productRepository.delete(productId);
		return new Product();
	}

}
