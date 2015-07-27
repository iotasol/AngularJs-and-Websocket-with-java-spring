package com.demo.controller.product;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.product.Product;
import com.demo.service.product.ProductService;

/**
 * Controller to Handle user Request
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

	private static final Logger log = LogManager
			.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	/**
	 * Mapping to handle User Get all Product request
	 * 
	 * @return List<Product>
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Product> getProducts() {
		log.debug("Getting All Product");
		return productService.getProducts();
	}

	/**
	 * Mapping to handle User Get Product by Id request
	 * 
	 * @param productId
	 * @return Product
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public Product getProductByid(@PathVariable Long productId) {
		log.debug("Getting Product by Id");
		return productService.getProductById(productId);
	}

	/**
	 * Mapping to handle User Save Product request
	 * 
	 * @param product
	 * @return Product
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product) {
		log.debug("Going to Save Product");
		return productService.saveProduct(product);
	}

	/**
	 * Mapping to handle User Update Product request
	 * 
	 * @param product
	 * @param productId
	 * @return Product
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public Product saveProduct(@RequestBody Product product,
			@PathVariable Long productId) {
		log.debug("Going to Update Product");
		return productService.updateProduct(product);
	}

	/**
	 * Mapping to handle User Delete Product request
	 * 
	 * @param productId
	 * @return Product
	 */
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public Product saveProduct(@PathVariable Long productId) {
		log.debug("Going to Delete Product");
		return productService.deleteProduct(productId);
	}
}
