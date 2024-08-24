package com.unir.products.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.products.data.ElasticsearchRepository;
import com.unir.products.model.pojo.ElasticProduct;
import com.unir.products.model.request.CreateProductRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ElasticProductServiceImpl implements ElasticProductsService {
	
	private final ElasticsearchRepository repo;
	
	@Override
	public ElasticProduct getProductById(String productId) {
		return repo.getById(productId);
	}
	
	@Override
	public ElasticProduct getProductByName(String productName) {
		return repo.getByName(productName);
	}
	
	@Override
	public List<ElasticProduct> getProductsByCountry(String country) {
		return repo.getByCountry(country);
	}
	
	@Override
	public List<ElasticProduct> searchByDescription(String productDescription) {
		return repo.searchByDescription(productDescription);
	}

	@Override
	public List<ElasticProduct> searchByName(String productName) {
		return repo.searchByName(productName);
	}

	@Override
	public List<ElasticProduct> getAvailableProducts() {
		return repo.getVisible();
	}
	
	@Override
	public ElasticProduct createProduct(CreateProductRequest request) {
		
		if(request != null && StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getDescription().trim())
				&& StringUtils.hasLength(request.getCountry().trim()) && request.getVisible() != null) {
			ElasticProduct product = 
					ElasticProduct.builder().id(String.valueOf(request.getName().hashCode()))
					.name(request.getName()).description(request.getDescription())
					.country(request.getCountry()).visible(request.getVisible()).build();
			
			return repo.saveProduct(product);
		} else {
			return null;
		}
		
	}

}
