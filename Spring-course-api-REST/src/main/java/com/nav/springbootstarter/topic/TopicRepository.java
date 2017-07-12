package com.nav.springbootstarter.topic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String>{
	//<T, idType>
	public Optional<List<Topic>> findById(String Id);


}
