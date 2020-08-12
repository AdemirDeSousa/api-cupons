package com.ademir.cupons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ademir.cupons.domain.Cupom;
import com.ademir.cupons.repositories.CupomRepository;

@EnableWebMvc
@SpringBootApplication
public class CuponsApplication implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CuponsApplication.class, args);
	}

}
