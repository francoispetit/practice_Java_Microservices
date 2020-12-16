package com.formacionbdi.springboot.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.formacionbdi.springboot.app.commons.users.models.entity"})
@SpringBootApplication
public class SpringbootServicioUsariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUsariosApplication.class, args);
	}

}
