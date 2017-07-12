package com.nav.springbootstarter.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	
	protected List<Course> getAllCourses(String id){
		List<Course> courses = new ArrayList<>();
		 courseRepository.findByTopicId(id)
		 .forEach(courses::add); //  forEach(topic -> courses.add(topic));
		 return courses;
	}

	public Course getCourse(String id) {
		return courseRepository.findOne(id);
	}

	public void addCourse(Course course) {
		
		courseRepository.save(course);
	}

	public void updateCourse(Course course) {
		
		courseRepository.save(course);
		
	}

	public void deleteCourse(String id) {
		courseRepository.delete(id);
	}
}
