package com.ademir.cupons.repositories;


import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ademir.cupons.domain.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Integer>{
	
	Page<Cupom> findBySituacaoContainingIgnoreCase(String situacao, Pageable pageRequest);
	
	@Query("SELECT obj FROM Cupom obj WHERE obj.dataExpiracao BETWEEN :startDate AND :endDate")
	Page<Cupom> findByDataExpiracao(@Param("startDate")LocalDate data1, @Param("endDate")LocalDate data2, Pageable pageRequest);
	

}
