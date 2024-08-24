package com.unir.products.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.unir.products.model.pojo.ElasticProduct;

@Repository
public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProduct, String> {
	
	Optional<ElasticProduct> findByName(String name);
	
	Optional<ElasticProduct> findById(String id);
	
	List<ElasticProduct> findByCountry(String country);
	
	List<ElasticProduct> findByVisible(Boolean visible);

	ElasticProduct save(ElasticProduct product);
	
}
