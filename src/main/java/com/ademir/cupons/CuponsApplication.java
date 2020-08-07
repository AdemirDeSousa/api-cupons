package com.ademir.cupons;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ademir.cupons.domain.Cupom;
import com.ademir.cupons.repositories.CupomRepository;

@SpringBootApplication
public class CuponsApplication implements CommandLineRunner{
	
	@Autowired
	private CupomRepository cupomRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CuponsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LocalDate localDate = LocalDate.parse("28/05/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		Cupom cupom_1 = new Cupom(null,"IAU", "Cupom Iau", "20 Reais", "Expirado", localDate, localDate, LocalDate.now());
		
		cupomRepository.save(cupom_1);
	}

}
