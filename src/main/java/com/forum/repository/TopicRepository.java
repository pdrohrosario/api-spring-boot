package com.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.forum.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findByCourse(String nameCourse, Pageable pageable);

}
