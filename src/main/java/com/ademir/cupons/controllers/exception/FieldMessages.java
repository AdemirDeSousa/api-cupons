package com.ademir.cupons.controllers.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FieldMessages implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;

}
