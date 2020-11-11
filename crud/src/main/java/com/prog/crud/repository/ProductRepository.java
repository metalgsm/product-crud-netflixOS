package com.prog.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.crud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
