package com.nav.springbootstarter.course;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nav.springbootstarter.topic.Topic;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@RequestMapping(method=RequestMethod.GET, value="/topics/{topicid}/courses")
	public List<Course> getAllTopics(@PathVariable("topicid") String id){
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{topicid}/courses/{id}")
	public Course getTopic(@PathVariable("id") String id){
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicid}/courses")
	public void addTopic(@RequestBody Course course, @PathVariable("topicid") String topicid){
		 course.setTopic(new Topic(topicid, "", ""));
		 courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicid}/course/{id}")
	public void updateTopic(@RequestBody Course course,  @PathParam("topicid") String topicid){
		 course.setTopic(new Topic(topicid, "", ""));
		 courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicid}/course/{id}")
	public void deleteTopic(@PathVariable String id){
		 courseService.deleteCourse(id);
	}
	
}
