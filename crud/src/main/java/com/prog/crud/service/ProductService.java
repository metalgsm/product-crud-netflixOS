package com.prog.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prog.crud.data.vo.ProductVO;
import com.prog.crud.entity.Product;
import com.prog.crud.exception.NotFoundException;
import com.prog.crud.repository.ProductRepository;

import lombok.Data;

@Service
@Data
public class ProductService {

	private final ProductRepository productRepository;
	
	private static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "The product(%s) could not be found";
	
	public ProductVO create(ProductVO productVO) {
		return ProductVO.create(productRepository.save(Product.create(productVO)));
	}
	
	public Page<ProductVO> findAll(Pageable pageable){
		var page = productRepository.findAll(pageable);
		return page.map(this::convertToProductVO);
	}
	
	private ProductVO convertToProductVO(Product product) {
		return ProductVO.create(product);
	}
	
	public ProductVO findById(Long id) {
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
		
		return ProductVO.create(entity);
	}
	
	public ProductVO update(ProductVO productVO) {
		final ProductVO currentProduct = findById(productVO.getId());
		
		Optional.ofNullable(productVO.getName()).ifPresent(val -> currentProduct.setName(val));
		Optional.ofNullable(productVO.getPrice()).ifPresent(val -> currentProduct.setPrice(val));
		Optional.ofNullable(productVO.getStock()).ifPresent(val -> currentProduct.setStock(val));
		
		return ProductVO.create(productRepository.save(Product.create(currentProduct)));
	}
	
	public void delete(Long id) {
		final ProductVO currentProduct = findById(id);
		productRepository.delete(Product.create(currentProduct));
	}
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
