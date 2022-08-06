package com.forum.controller;

import com.forum.controller.form.TopicForm;
import com.forum.controller.form.UpdateTopicForm;
import com.forum.model.Topic;
import com.forum.model.dto.TopicDetailDto;
import com.forum.model.dto.TopicDto;
import com.forum.repository.CourseRepository;
import com.forum.repository.TopicRepository;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicController
{
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public Page<TopicDto> list(@RequestParam(required = false) String nameCourse, @RequestParam int page, @RequestParam int size, @RequestParam String ordenation) {
		Page<Topic> topics;
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, ordenation);
		if (nameCourse == null) {
			topics = topicRepository.findAll(pageable);
		} else {
			topics = topicRepository.findByCourse(nameCourse , pageable);
		}
		return TopicDto.converter(topics);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailDto> search(@PathVariable Long id){
		Optional<Topic> topic = topicRepository.findById(id);
		return topic.map(value -> ResponseEntity.ok(new TopicDetailDto(value)))
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent())
		{
			Topic topic = form.update(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent())
		{
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}







