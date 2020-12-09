package com.formacionbdi.springboot.app.products.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Product;


public interface ProductDao extends CrudRepository<Product, Long>{

}
