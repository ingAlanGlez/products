package com.unir.products.model.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

// import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName = "products", createIndex = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticProduct {
	
	@Id
	private String id;
	
	@MultiField(mainField = @Field(type = FieldType.Keyword, name = "name"),
			otherFields = @InnerField(suffix = "search", type = FieldType.Search_As_You_Type))
	private String name;
	
	@Field(type = FieldType.Keyword, name = "country")
	private String country;
	
	@Field(type = FieldType.Text, name = "description")
	private String description;
	
	@Field(type = FieldType.Boolean, name = "visible")
	private Boolean visible;

}
