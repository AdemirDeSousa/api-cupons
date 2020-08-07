package com.ademir.cupons.controllers;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ademir.cupons.controllers.util.URL;
import com.ademir.cupons.domain.Cupom;
import com.ademir.cupons.domain.dto.CupomDTO;
import com.ademir.cupons.services.CupomService;

@RestController
@RequestMapping("/api/cupons")
public class CupomController {
	
	@Autowired
	private CupomService cupomService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cupom> find(@PathVariable Integer id) {
		Cupom obj = cupomService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@PostMapping
	public ResponseEntity<Void>insert(@Valid @RequestBody CupomDTO objDTO){
		Cupom obj = cupomService.converterDTO(objDTO);
		obj = cupomService.criar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CupomDTO objDTO, @PathVariable Integer id){
		Cupom obj = cupomService.converterDTO(objDTO);
		obj.setId(id);
		obj = cupomService.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		cupomService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<CupomDTO>> findAll() {
		List<Cupom> list = cupomService.exibirTodos();
		List<CupomDTO> listDTO = list.stream().map(obj -> new CupomDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CupomDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina, 
			@RequestParam(value = "orderBy", defaultValue = "dataCadastro") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Cupom> list = cupomService.exibirPagina(page, linhasPorPagina, orderBy, direction);
		Page<CupomDTO> listDTO = list.map(obj -> new CupomDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<Page<CupomDTO>> searchBySituation(
			@RequestParam(value = "situacao", defaultValue = "") String situacao,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina, 
			@RequestParam(value = "orderBy", defaultValue = "dataCadastro") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		String situacaoDecoded = URL.decodeParam(situacao);
		Page<Cupom> list = cupomService.pesquisarPorSituacao(situacaoDecoded, page, linhasPorPagina, orderBy, direction);
		Page<CupomDTO> listDTO = list.map(obj -> new CupomDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
	
	@GetMapping("/buscar/expiracao")
	public ResponseEntity<Page<CupomDTO>> searchByExpiration(
			@RequestParam(value = "data1", defaultValue = "") String data1,
			@RequestParam(value = "data2", defaultValue = "") String data2,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina, 
			@RequestParam(value = "orderBy", defaultValue = "dataCadastro") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//String dataStart = data1;
		//String dateEnd = "16/08/2016";

		//convert String to LocalDate
		LocalDate localDate1 = LocalDate.parse(data1, formatter);
		LocalDate localDate2 = LocalDate.parse(data2, formatter);
		
		Page<Cupom> list = cupomService.pesquisarPorIntervaloDeDatas(localDate1, localDate2, page, linhasPorPagina, orderBy, direction);
		Page<CupomDTO> listDTO = list.map(obj -> new CupomDTO(obj));
		return ResponseEntity.ok().body(listDTO);
		
	}
}
