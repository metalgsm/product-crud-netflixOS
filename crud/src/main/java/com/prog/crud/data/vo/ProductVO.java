package com.prog.crud.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.prog.crud.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonPropertyOrder({"id", "name", "price", "stock"})
public class ProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3214355935459141707L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("stock")
	private Integer stock;
	
	@JsonProperty("price")
	private BigDecimal price;
	
	public static ProductVO create(Product product) {
		return new ModelMapper().map(product, ProductVO.class);
	}
}
