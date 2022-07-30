package br.com.forum.controller.form;

import br.com.forum.model.Topic;
import br.com.forum.repository.TopicRepository;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UpdateTopicForm
{

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public Topic atualizar(Long id, TopicRepository topicRepository)
	{
		Topic topic = topicRepository.getOne(id);
		topic.setTitulo(this.titulo);
		topic.setMensagem(this.mensagem);
		return topic;
	}
}
