package com.forum.model.dto;

import com.forum.model.Answer;
import java.time.LocalDateTime;

public class AnswerDto
{
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;

	public AnswerDto(Answer answer){
		this.id = answer.getId();
		this.mensagem = answer.getMensagem();
		this.dataCriacao = answer.getDataCriacao();
		this.nomeAutor = answer.getAutor().getNome();
	}

	public Long getId()
	{
		return id;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public LocalDateTime getDataCriacao()
	{
		return dataCriacao;
	}

	public String getNomeAutor()
	{
		return nomeAutor;
	}
}
