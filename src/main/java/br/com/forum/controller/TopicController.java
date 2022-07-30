package br.com.forum.controller;

import br.com.forum.model.dto.TopicDetailDto;
import br.com.forum.controller.form.UpdateTopicForm;
import br.com.forum.repository.CourseRepository;
import java.net.URI;
import java.util.List;

import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.model.dto.TopicDto;
import br.com.forum.controller.form.TopicoForm;
import br.com.forum.model.Topic;
import br.com.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topicos")
public class TopicController
{
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping
	public List<TopicDto> lista(String nomeCurso) {
		if (nomeCurso == null) {
			List<Topic> topics = topicRepository.findAll();
			return TopicDto.converter(topics);
		} else {
			List<Topic> topics = topicRepository.findByCursoNome(nomeCurso);
			return TopicDto.converter(topics);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = form.converter(courseRepository);
		topicRepository.save(topic);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDto(topic));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDetailDto> detalhar(@PathVariable Long id){
		Optional<Topic> topico = topicRepository.findById(id);
		if(topico.isPresent())
		{
			return ResponseEntity.ok(new TopicDetailDto(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent())
		{
			Topic topic = form.atualizar(id, topicRepository);
			return ResponseEntity.ok(new TopicDto(topic));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Topic> optional = topicRepository.findById(id);
		if(optional.isPresent())
		{
			topicRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}







