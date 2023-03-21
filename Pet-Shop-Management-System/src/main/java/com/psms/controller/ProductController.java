package com.psms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psms.entity.Product;
import com.psms.model.ProductDTO;
import com.psms.service.ProductService;
import com.psms.util.ProductConverter;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter productConverter;
	
	@PostMapping("/createProduct")
	public String createProduct(@Valid @RequestBody ProductDTO productDTO)
	{
		final Product products = productConverter.convertDTOToProduct(productDTO);
		return productService.createProduct(products);
	}
	
	@GetMapping("/getProductById/{productId}")
	public ProductDTO getProductById(@PathVariable("productId") int productId)
	{
		return productService.getProductById(productId);
	}
	
	@GetMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts()
	{
		return productService.getAllProducts();
	}
	
	@PutMapping("/updateProduct/{producId}")
	public ProductDTO updateProduct(@PathVariable("productId") int productId,
			@Valid @RequestBody ProductDTO productDTO)	
	{
		Product prducts = productConverter.convertDTOToProduct(productDTO);
		return productService.updateProduct(productId, prducts);
	}
	
	@DeleteMapping("/deleteProductById/{productId}")
	public String deleteProductById(@PathVariable("productId") int productId)
	{
		return productService.deleteProductById(productId);
	}
	
	@DeleteMapping("/deleteAllProducts")
	public ResponseEntity<String> deleteAllProducts()
	{
		productService.deleteAllProducts();
		return new ResponseEntity<String>("All product details got deleted!",
				HttpStatus.OK);
	}
	
	@GetMapping("/getProductByName/{productName}")
	public List<ProductDTO> getProductByName(@PathVariable("productName") String productName)
	{
		return productService.getProductByName(productName);
	}
}
