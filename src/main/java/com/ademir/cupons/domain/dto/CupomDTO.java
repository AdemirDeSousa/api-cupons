package com.ademir.cupons.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.ademir.cupons.domain.Cupom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CupomDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo Codigo Obrigatorio")
	private String codigo;
	
	@NotEmpty(message = "Campo Descriçao Obrigatorio")
	private String descricao;
	
	@NotEmpty(message = "Campo Valor Obrigatorio")
	private String valor;
	
	@NotEmpty(message = "Campo Situaçao Obrigatorio")
	private String situacao;
	
	private LocalDate dataExpiracao;
	private LocalDate dataCadastro;
	private LocalDate dataUso;
	
	public CupomDTO(Cupom obj) {
		id = obj.getId();
		codigo = obj.getCodigo();
		descricao = obj.getDescricao();
		valor = obj.getValor();
		situacao = obj.getSituacao();
		dataExpiracao = obj.getDataExpiracao();
		dataUso = obj.getDataUso();
		dataCadastro = obj.getDataCadastro();
	}
}


