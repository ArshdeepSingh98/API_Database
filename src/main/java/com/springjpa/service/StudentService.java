package com.springjpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springjpa.student.Student;

@Service
public class StudentService {

	private List<Student> students = new ArrayList<>(Arrays.asList(new Student("Arshdeep", "Singh", 20)));
	
	public List<Student> getAllStudents() {
		return students;
	}
	
	public Student getStudent(long id) {
		return students.stream().filter(t -> t.getId().equals(id)).findFirst().get();	
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	
	public void updateStudent(Student student, long id) {
		 for(int i=0;i<students.size();i++) {
			 Student t = students.get(i);
			 if(t.getId().equals(id)) {
				  students.set(i, student);
				  return;
			 }
		 }
	}
	
	public void deleteStudent(long id) {
		students.removeIf(t -> t.getId().equals(id)); 
	}
}
