package com.forum.model.dto;

import com.forum.model.TopicState;
import com.forum.model.Topic;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailDto
{
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private TopicState status;
	private List<AnswerDto> respostas;

	public TopicDetailDto(Topic topic){
		this.id = topic.getId();
		this.titulo = topic.getTitulo();
		this.mensagem = topic.getMensagem();
		this.dataCriacao = topic.getDataCriacao();
		this.nomeAutor = topic.getAutor().getNome();
		this.status = topic.getStatus();
		this.respostas = new ArrayList<>();
		this.respostas.addAll(topic.getRespostas().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}

	public Long getId()
	{
		return id;
	}

	public String getTitulo()
	{
		return titulo;
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

	public TopicState getStatus()
	{
		return status;
	}

	public List<AnswerDto> getRespostas()
	{
		return respostas;
	}
}
