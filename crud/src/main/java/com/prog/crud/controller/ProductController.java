package com.prog.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prog.crud.data.vo.ProductVO;
import com.prog.crud.service.ProductService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	private final PagedResourcesAssembler<ProductVO> assembler;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public ProductVO findById(@PathVariable("id") Long id) {
		ProductVO productVO = productService.findById(id);
		productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		return productVO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size,
			@RequestParam(value = "direction", defaultValue = "desc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
		
		Page<ProductVO> products = productService.findAll(pageable);
		products.stream()
			.forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ProductVO>> pageModel = assembler.toModel(products);
		
		return new ResponseEntity<>(pageModel,HttpStatus.OK);
	}
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ProductVO create(@RequestBody ProductVO productVO) {
		ProductVO vo = productService.create(productVO);
		vo.add(linkTo(methodOn(ProductController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public ProductVO update(@RequestBody ProductVO productVO) {
		ProductVO vo = productService.update(productVO);
		vo.add(linkTo(methodOn(ProductController.class).findById(vo.getId())).withSelfRel());
		return vo;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id){
		productService.delete(id);
	}
	
	@Autowired
	public ProductController(ProductService productService, PagedResourcesAssembler<ProductVO> assembler) {
		super();
		this.productService = productService;
		this.assembler = assembler;
	}
	
	
}
