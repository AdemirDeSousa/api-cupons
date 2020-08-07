package com.ademir.cupons.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ademir.cupons.domain.Cupom;
import com.ademir.cupons.domain.dto.CupomDTO;
import com.ademir.cupons.repositories.CupomRepository;
import com.ademir.cupons.services.exceptions.ObjectNotFoundException;
	
@Service
public class CupomService {
	
	@Autowired
	private CupomRepository cupomRepository;
	
	public Cupom buscarPorId(Integer id) {
		 Optional<Cupom> obj = cupomRepository.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cupom.class.getName())); 
	}
	
	public Cupom criar(Cupom obj) {
		obj.setId(null);
		obj.setDataCadastro(LocalDate.now());
		return cupomRepository.save(obj);
	}
	
	//public Cupom atualizar(Cupom obj) {
	//	buscarPorId(obj.getId());
	//	return cupomRepository.save(obj);
	//}
	
	public Cupom atualizar(Cupom obj) {
		Cupom newObj = buscarPorId(obj.getId());
		atualizarDados(newObj, obj);
		return cupomRepository.save(newObj);
		
	}
	
	public void deletar(Integer id) {
		buscarPorId(id);
		cupomRepository.deleteById(id);
	}
	
	public List<Cupom> exibirTodos(){
		return cupomRepository.findAll();
	}
	
	public Page<Cupom> exibirPagina(Integer page, Integer linhasPorPagina, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return cupomRepository.findAll(pageRequest);
	}
	
	public Cupom converterDTO(CupomDTO objDTO) {
		
		return new Cupom(objDTO.getId(), 
				objDTO.getCodigo(), 
				objDTO.getDescricao(), 
				objDTO.getValor(), 
				objDTO.getSituacao(), 
				objDTO.getDataExpiracao(), 
				objDTO.getDataCadastro(), 
				objDTO.getDataUso());
		
	}
	
	private void atualizarDados(Cupom newObj, Cupom obj) {
		newObj.setCodigo(obj.getCodigo());
		newObj.setDescricao(obj.getDescricao());
		newObj.setValor(obj.getValor());
		newObj.setSituacao(obj.getSituacao());
		newObj.setDataExpiracao(obj.getDataExpiracao());
		newObj.setDataUso(obj.getDataUso());
		
	}
	
	public Page<Cupom> pesquisarPorSituacao(String situacao, Integer page, Integer linhasPorPagina, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return cupomRepository.findBySituacaoContainingIgnoreCase(situacao, pageRequest);
	}
	
	public Page<Cupom> pesquisarPorIntervaloDeDatas(LocalDate data1, LocalDate data2, Integer page, Integer linhasPorPagina, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina, Direction.valueOf(direction), orderBy);
		return cupomRepository.findByDataExpiracaoBetween(data1, data2, pageRequest);
	}
}
