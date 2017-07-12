package com.nav.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	
	protected List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		 topicRepository.findAll()
		 .forEach(topics::add);
		 return topics;
	}

	public Topic getTopic(String id) {
		validateTopic(id);
		return topicRepository.findOne(id);
	}

	public void addTopics(List<Topic> topics) {
		
		topicRepository.save(topics);
	}
	
	public void addTopic(Topic topic) {
		
		topicRepository.save(topic);
	}

	public void updateTopic(Topic topic) {
		validateTopic(topic.getId());
		topicRepository.save(topic);
		
	}

	public void deleteTopic(String id) {
		topicRepository.delete(id);
	}
	
	private void validateTopic(String id){
		topicRepository.findById(id).orElseThrow(()->new TopicNotFoundException("tttt"));
	}
}
