package com.psms.service;

import java.util.List;

import com.psms.entity.Product;
import com.psms.model.ProductDTO;

public interface ProductService {

String createProduct(Product products);
	
	ProductDTO getProductById(long productId);
	List<ProductDTO> getAllProducts();
	
	ProductDTO updateProduct(long productId, Product products);
	
	String deleteProductById(long productId);
	void deleteAllProducts();
	
	List<ProductDTO> getProductByName(String productName);
	
	
}
