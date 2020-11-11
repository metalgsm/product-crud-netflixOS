package com.prog.crud.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.prog.crud.data.vo.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "PRODUCT")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3156278693068634845L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROD_ID")
	private Long id;
	
	@Column(name = "PROD_NAME", nullable = false, length = 50)
	private String name;
	
	@Column(name = "PROD_STOCK", nullable = false)
	private Integer stock;
	
	@Column(name = "PROD_PRICE", nullable = false)
	private BigDecimal price;
	
	public static Product create(ProductVO productVO) {
		return new ModelMapper().map(productVO, Product.class);
	}
	
}
