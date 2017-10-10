package edu.fiap.estudos.casadocodigo.greendogdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class GreendogdeliveryApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GreendogdeliveryApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GreendogdeliveryApplication.class, args);
	}
}
