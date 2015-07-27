package com.demo.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.product.Product;

/**
 * Interface to handle mapping between model and database
 * 
 * @author admin
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
