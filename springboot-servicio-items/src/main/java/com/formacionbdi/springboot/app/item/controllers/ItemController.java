package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Product;
import com.formacionbdi.springboot.app.item.models.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> list(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "alternativeMethod")
	@GetMapping("/lookup/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public Item alternativeMethod(Long id, Integer quantity) {
		Item item = new Item();
		Product product = new Product();
		item.setQuantity(quantity);
		product.setId(id);
		product.setName("Mars");
		product.setPrice(500.00);
		item.setProduct(product);
		return item;
	}
}
