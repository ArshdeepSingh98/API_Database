package com.springjpa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springjpa.student.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
//	List<Student> findByLastName(String lastname);
}
