package com.unir.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;
import com.unir.products.service.ElasticProductsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ElasticProductController {

	private final ElasticProductsService service;
	
	@GetMapping("/elastic/products/{productId}")
	public ResponseEntity<ElasticProduct> getProductById(@PathVariable String productId) {
		
		ElasticProduct product = service.getProductById(productId);
		
		if(product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/elastic/products")
	public ResponseEntity<List<ElasticProduct>> getProducts() {
		
		List<ElasticProduct> product = service.getAvailableProducts();
		
		if(product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/elastic/products/match/{value}")
	public ResponseEntity<ElasticProduct> getProductByName(@PathVariable String value) {
		
		ElasticProduct product = service.getProductByName(value);
		
		if(product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/elastic/products/search/as-you-type/{value}")
	public ResponseEntity<List<ElasticProduct>> searchByName(@PathVariable String value) {
		
		List<ElasticProduct> product = service.searchByName(value);
		
		if(product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/elastic/products/search/full-text/{value}")
	public ResponseEntity<List<ElasticProduct>> searchByDescription(@PathVariable String value) {
		
		List<ElasticProduct> product = service.searchByDescription(value);
		
		if(product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/elastic/products")
	public ResponseEntity<ElasticProduct> getProduct(@RequestBody CreateProductRequest request) {
		
		ElasticProduct createdProduct = service.createProduct(request);
		
		if(createdProduct != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
 
}
