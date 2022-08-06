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
	private String title;
	private String message;
	private LocalDateTime createDate;
	private String nameAuthor;
	private TopicState state;
	private List<AnswerDto> answers;

	public TopicDetailDto(Topic topic){
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.createDate = topic.getCreateDate();
		this.nameAuthor = topic.getAuthor().getName();
		this.state = topic.getState();
		this.answers = new ArrayList<>();
		this.answers.addAll(topic.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public LocalDateTime getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate)
	{
		this.createDate = createDate;
	}

	public String getNameAuthor()
	{
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor)
	{
		this.nameAuthor = nameAuthor;
	}

	public TopicState getState()
	{
		return state;
	}

	public void setState(TopicState state)
	{
		this.state = state;
	}

	public List<AnswerDto> getAnswers()
	{
		return answers;
	}

	public void setAnswers(List<AnswerDto> answers)
	{
		this.answers = answers;
	}
}
