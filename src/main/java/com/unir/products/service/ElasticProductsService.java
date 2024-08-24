package com.unir.products.service;

import java.util.List;

import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;

public interface ElasticProductsService {
	
	ElasticProduct createProduct(CreateProductRequest request);
	
	ElasticProduct getProductById(String productId);
	
	ElasticProduct getProductByName(String productName);
	
	List<ElasticProduct> getProductsByCountry(String country);
	
	List<ElasticProduct> searchByDescription(String productDescription);
	
	List<ElasticProduct> searchByName(String productName);
	
	List<ElasticProduct> getAvailableProducts();

}
