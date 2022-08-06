package com.forum.model.dto;

import java.time.LocalDateTime;

import com.forum.model.Topic;
import org.springframework.data.domain.Page;

public class TopicDto
{

	private Long id;
	private String title;
	private String message;
	private LocalDateTime createDate;

	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.createDate = topic.getCreateDate();
	}

	public Long getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getMessage()
	{
		return message;
	}

	public LocalDateTime getCreateDate()
	{
		return createDate;
	}

	public static Page<TopicDto> converter(Page<Topic> topics) {
		return topics.map(TopicDto::new);
	}

}
