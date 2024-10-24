package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum CategoriaEnum {
	
	MOLETOM, CAMISETA, SHORTS, CALCA, AGASALHO, ACESSORIOS, BRINDES;
	
	@JsonCreator
	public static CategoriaEnum verifica(String valor) {
		for(CategoriaEnum c :  CategoriaEnum.values()) {
			if (c.name().equals(valor)) {
				return c;
			}
		}
		throw new EnumException("Categoria inválida ou não existente");
	}
}
