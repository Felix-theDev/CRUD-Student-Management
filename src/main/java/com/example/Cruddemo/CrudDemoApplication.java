package com.example.Cruddemo;

import com.example.Cruddemo.dao.StudentDAO;
import com.example.Cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);

			createMultipleStudent(studentDAO);

			readStudent(studentDAO);

			queryForStudents(studentDAO);
//
			queryForStudentsByLastName(studentDAO);

			updateStudent(studentDAO);

//			deleteStudent(studentDAO);

//			deleteAllStudents(studentDAO);

			getOldestStudent(studentDAO);
//
			getYoungestStudent(studentDAO);

			countStudents(studentDAO);

			findAgeRange(studentDAO);


		};
	}

	private void findAgeRange(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByAgeRange(30, 40);

		for (Student student: students) {
			System.out.println(student.getFirstName() + ": " + student.getAge());
		}
	}

	private void countStudents(StudentDAO studentDAO) {
		Long studentCount = studentDAO.count();

		System.out.println("There are " + studentCount + " number of students");
	}

	private void getYoungestStudent(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findOldestStudent();

		System.out.println("List of youngest students sorted by name");

		for (Student student: students) {
			System.out.println(student);
		}
	}

	private void getOldestStudent(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findYoungestStudent();

		System.out.println("List of oldest students sorted by name");

		for (Student student: students) {
			System.out.println(student);
		}
	}

	private void deleteAllStudents(StudentDAO studentDAO) {

		System.out.println("Deleting all students");

		int numRows = studentDAO.deleteAllStudents();

		System.out.println("Delete row count: " + numRows);
	}

	//Delete students whose last name is Lionel
	private void deleteStudent(StudentDAO studentDAO) {

		String name = "Lionel";

		List<Student> students = studentDAO.findByLastName(name);

		for (Student student: students) {
			int id = student.getId();
			System.out.println("Deleting Students with id: "+ id);
			studentDAO.delete(id);
		}
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 2;

		System.out.println("Getting student with id: "+ studentId);
		Student student = studentDAO.findById(studentId);

		System.out.println("Updating student");
		student.setLastName("Scooby");

		studentDAO.update(student);

		System.out.println("Updated student: " + student);


	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Ogbonnaya");

		for (Student student: students ) {
			System.out.println(student);
		}
	}

	private void queryForStudentsByFirstName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByFirstName("Felix");

		for (Student student: students ) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		List<Student> students = studentDAO.findAll();

		for (Student student: students ) {
			System.out.println(student);
		}

	}

	private void readStudent(StudentDAO studentDAO) {
		// Create student object
		System.out.println("Creating new student object");
		Student newStudent = new Student("Messi", "Lionel", "messi.best@tahoo.com", 33);

		System.out.println("Saving the student");
		studentDAO.save(newStudent);


		int theId = newStudent.getId();
		System.out.println("Saved student . Generated id: " + theId);


		System.out.println("Retrieving student with id: " + theId);
		Student student = studentDAO.findById(theId);

		System.out.println("Found the student: " + student);

	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		System.out.println("Creating new Student object...");
		Student student1 = new Student("Felix", "Ogbonnaya", "felix.ogbonnaya@gmail.com", 30);
		Student student2 = new Student("Divine", "Diego", "divine.diego@gmail.com", 22);
		Student student3 = new Student("Johnson", "Nnanna", "john.boy@gmail.com", 35);
		Student student4 = new Student("Precious", "Sapphire", "sapphire.presh@gmail.com", 28);
		Student student5 = new Student("Emma", "Smith", "emma.smith@example.com", 25);
		Student student6 = new Student("Oliver", "Jones", "oliver.jones@example.com", 30);
		Student student7 = new Student("Ava", "Johnson", "ava.johnson@example.com", 22);
		Student student8 = new Student("Liam", "Williams", "liam.williams@example.com", 28);
		Student student9 = new Student("Sophia", "Brown", "sophia.brown@example.com", 33);
		Student student10 = new Student("Noah", "Davis", "noah.davis@example.com", 30);
		Student student11 = new Student("Isabella", "Miller", "isabella.miller@example.com", 24);
		Student student12 = new Student("Ethan", "Wilson", "ethan.wilson@example.com", 29);
		Student student13 = new Student("Andela", "Johnson", "jennifer.johnson@example.com", 40);
		Student student14 = new Student("Yoga", "Williams", "david.williams@example.com", 40);



		System.out.println("Saving the students....");

		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);
		studentDAO.save(student4);
		studentDAO.save(student5);
		studentDAO.save(student6);
		studentDAO.save(student7);
		studentDAO.save(student8);
		studentDAO.save(student9);
		studentDAO.save(student10);
		studentDAO.save(student11);
		studentDAO.save(student12);
		studentDAO.save(student13);
		studentDAO.save(student14);


	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new Student object...");

		Student student = new Student("Cristiano", "Ronaldo", "ronaldo@yahoo.com", 40);

		System.out.println("Saving the student");
		studentDAO.save(student);

		System.out.println("Save student. Generated id " + student.getId());
	}

}