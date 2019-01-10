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
//		updateToDatabase(student, id);
		ss.updateStudent(student, id);
		saveToDatabase();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/students/{id}")
	public void deleteStudent(@PathVariable long id) {
//		deleteFromDatabase(id);
		ss.deleteStudent(id);
		saveToDatabase();
	}
	
	@RequestMapping("/save")
	public String saveToDatabase() {
		List<Student> students = getAllStudents();
		sr.deleteAll();
		for(Student sd : students) {
//			if(sr.existsById(sd.getId())) {
				// pass
//			}else {
				sr.save(sd);	
//			}
		}
		return "Done Saving";
	}
	
	// not used
	@RequestMapping("/update")
	public String updateToDatabase(Student newstd, long id) {
		Student oldstd = getStudent(id);
		sr.delete(oldstd);
		sr.save(newstd);
		return "Done Updating";
	}
	
	// not used
	@RequestMapping("/delete")
	public String deleteFromDatabase(long id) {
		Student st = getStudent(id);
		sr.delete(st);
		return "Done Deleting";
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
