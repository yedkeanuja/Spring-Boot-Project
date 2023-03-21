package com.psms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.psms.entity.Product;
import com.psms.model.ProductDTO;
import com.psms.repository.ProductRepository;
import com.psms.service.ProductService;
import com.psms.util.ProductConverter;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter productConverter;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	@Order(1)
	void testCreateProduct()
	{
		Product product= Product.builder().productName("Dog Food").price(150).quantity(1).build();
		
		Mockito.when(productRepository.save(product)).thenReturn(product);
//		assertEquals(" Product details added successfully",
//				productService.createProduct(product));
		
		assertThat(productService.createProduct(product)).isEqualTo("Product details added successfully");
	}
	
	
	@Test
	@Order(2)
	void testGetAllProducts()
	{
		Product product= Product.builder().productName("Dog Food").price(150).quantity(1).build();
		Product Product1=Product.builder().productName("Cat Food").price(200).quantity(1).build();
		
		List<Product>list= new ArrayList<>();
		list.add(Product1);
		list.add(product);
		
		Mockito.when(productRepository.findAll()).thenReturn(list);
		
		List<ProductDTO> dto= productService.getAllProducts();
		List<Product> products= new ArrayList<Product>();
		dto.forEach(productDto->
		products.add(productConverter.convertDTOToProduct(productDto))
		);
		
		assertThat(products.get(0).getProductName()).isEqualTo(list.get(0).getProductName());
		
	}
	
	
	@Test
	@Order(3)
	void testUpdateProduct()
	{
		Product product=Product.builder().productName("Dog Food").price(150).quantity(1).build();
			
		Optional<Product> opProduct = Optional.of(product);
			
		Mockito.when(productRepository.findById(product.getProductId())).thenReturn(opProduct);
			
		Product p = opProduct.get();
		product.setProductName("Dog");
			
		Mockito.when(productRepository.save(product)).thenReturn(p);
			
		ProductDTO dto= productService.updateProduct(product.getProductId(),product);
			
		assertEquals(dto.getProductName(),p.getProductName());
	}
}
