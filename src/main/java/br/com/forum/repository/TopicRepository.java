package br.com.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	List<Topic> findByCursoNome(String nomeCurso);

}
