package com.forum.model.dto;

import com.forum.model.Answer;
import java.time.LocalDateTime;

public class AnswerDto
{
	private Long id;
	private String message;
	private LocalDateTime createDate;
	private String nameAuthor;

	public AnswerDto(Answer answer){
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.createDate = answer.getCreateDate();
		this.nameAuthor = answer.getAuthor().getName();
	}

	public Long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public LocalDateTime getCreateDate()
	{
		return createDate;
	}

	public String getNameAuthor()
	{
		return nameAuthor;
	}
}
