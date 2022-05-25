package com.codingdojo.controllersviews.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.controllersviews.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

}
