package com.gilsonbraggion.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PessoaBean {
	
	private String uuid;
	private String nome;
	private Date dataNascimento;
}
