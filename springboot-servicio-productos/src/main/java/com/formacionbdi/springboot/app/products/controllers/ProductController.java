package com.formacionbdi.springboot.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.products.models.entity.Product;
import com.formacionbdi.springboot.app.products.models.service.IProductService;

@RestController
public class ProductController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/list")
	public List<Product> list(){
		return productService.findAll().stream().map(product -> {
//			product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/lookup/{id}")
	public Product detail(@PathVariable Long id) {
		Product product = productService.findById(id);
		product.setPort(port);

/* Generate an exception to check how Hystrix copes with it in client service-items */
//		boolean ok = false;
//		if (ok == false) {
//			throw new Exception("could not load product");
//		}
		
/* Generate a timeout to check how Hystrix copes with it in client service-items */
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return product;
	}
}
