package com.unir.products.data;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import com.unir.products.model.pojo.ElasticProduct;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ElasticsearchRepository {
	
	private final String[] nameSearchFields = 
		{"name.search", "name.search._2gram", "name.search._3gram"};
	
	private final ElasticProductRepository productRepository;
	private final ElasticsearchOperations elasticClient;
	
	public ElasticProduct getById(String id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public ElasticProduct getByName(String name) {
		return productRepository.findByName(name).orElse(null);
	}
	
	public List<ElasticProduct> getByCountry(String country) {
		return productRepository.findByCountry(country);
	}
	
	public List<ElasticProduct> getVisible() {
		return productRepository.findByVisible(true);
	}
	
	public List<ElasticProduct> searchByName(String namePart) {
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.multiMatchQuery(namePart, nameSearchFields)
			.type(MultiMatchQueryBuilder.Type.BOOL_PREFIX));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = 
				new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		
		SearchHits<ElasticProduct> result = elasticClient.search(query, ElasticProduct.class);
		
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}
	
	public List<ElasticProduct> searchByDescription(String description) {
		
		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
		
		boolQuery.must(QueryBuilders.matchQuery("description", description));
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = 
				new NativeSearchQueryBuilder().withQuery(boolQuery);
		Query query = nativeSearchQueryBuilder.build();
		
		SearchHits<ElasticProduct> result = elasticClient.search(query, ElasticProduct.class);
		
		return result.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
	}
	
	public ElasticProduct saveProduct(ElasticProduct product) {
		return productRepository.save(product);
	}
	
}
