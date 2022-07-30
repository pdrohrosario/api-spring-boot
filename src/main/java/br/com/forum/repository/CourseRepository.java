package br.com.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.model.Curso;

public interface CourseRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
