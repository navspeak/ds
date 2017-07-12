package com.nav.springbootstarter.topic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
class TopicNotFoundException extends RuntimeException {

	public TopicNotFoundException(String topicId) {
		super("could not find Topic '" + topicId + "'.");
	}
}