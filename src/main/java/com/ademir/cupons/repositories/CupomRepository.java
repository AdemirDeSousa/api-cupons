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
	
	//@Query("SELECT obj FROM Cupom obj WHERE obj.situacao LIKE %:situacao%")
	//Page<Cupom> pesquisarPorSituacaoIgnoreCase(@Param("situacao") String situacao, Pageable pageRequest);
	Page<Cupom> findBySituacaoContainingIgnoreCase(String situacao, Pageable pageRequest);
	
	@Query("SELECT obj FROM Cupom obj WHERE obj.dataExpiracao BETWEEN :startDate AND :endDate")
	Page<Cupom> findByDataExpiracaoBetween(@Param("startDate")LocalDate data1, @Param("endDate")LocalDate data2, Pageable pageRequest);
	
	//@Query(value = "from EntityClassTable t where yourDate BETWEEN :startDate AND :endDate")
	//public List<EntityClassTable> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
