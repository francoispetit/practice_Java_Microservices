package com.formacionbdi.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	private static final String BASE_URL = "http://service-products/";
	
	@Autowired
	private RestTemplate restClient;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(restClient.getForObject(BASE_URL+"/list", Product[].class));
		return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Product product = restClient.getForObject(BASE_URL+"/lookup/{id}", Product.class, pathVariables);
		return new Item(product, quantity);
	}

}
