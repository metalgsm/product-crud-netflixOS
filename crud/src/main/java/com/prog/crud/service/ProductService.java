package com.prog.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.crud.data.vo.ProductVO;
import com.prog.crud.repository.ProductRepository;

import lombok.Data;

@Service
@Data
public class ProductService {

	private final ProductRepository productRepository;
	
	public ProductVO create(ProductVO productVO) {
		return productVO;
	}
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
