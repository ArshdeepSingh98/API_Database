package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.repo.StudentRepository;
import com.springjpa.service.StudentService;
import com.springjpa.student.Student;


@RestController
public class WebStudentController {
	@Autowired
	private StudentRepository sr; 
	
	@Autowired
	private StudentService ss;

	
	@RequestMapping("/hello")
	public String sayHi() {
		return "Hi";
	}
	
	@RequestMapping("/students")
	public List<Student> getAllStudents() {
		return ss.getAllStudents();
	}
	
	@RequestMapping("/students/{id}")
	public Student getStudent(@PathVariable long id) {
		return ss.getStudent(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/students")
	public void addStudent(@RequestBody Student student) {
		ss.addStudent(student);
		saveToDatabase();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/students/{id}")
	public void updateStudent(@RequestBody Student student,@PathVariable long id) {
		ss.updateStudent(student, id);
		updateToDatabase(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/students/{id}")
	public void deleteStudent(@PathVariable long id) {
		ss.deleteStudent(id);
	}
	
	@RequestMapping("/save")
	public String saveToDatabase() {
		List<Student> students = getAllStudents();
		for(Student sd : students) {
			sr.save(sd);
		}
		return "Done Saving";
	}
	
	@RequestMapping("/update")
	public String updateToDatabase(long id) {
		Student st = getStudent(id);
		sr.delete(st);
		sr.save(st);
		return "Done Updating";
	}
	
	@RequestMapping("/findall")
	public String findallinDatabase() {
		String result = "<html>";
		for(Student sd : sr.findAll()) {
			result += "<div>" + sd.toString() + "</div>";
		}
		return result + "</html>";
	}
	
	@RequestMapping("/findbyid")
	public String findbyidinDatabase(@RequestParam("id") long id) {
		String res = "";
		res = sr.findById(id).toString();
		return res;
	}
}
