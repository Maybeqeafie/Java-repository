package ru.maybeqeafie.jpalessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.maybeqeafie.jpalessons.Entity.Personal;
import ru.maybeqeafie.jpalessons.repository.DepartmentRepository;
import ru.maybeqeafie.jpalessons.repository.PersonalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Scanner;

//@SpringBootApplication
public class JpaLessonsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(JpaLessonsApplication.class, args);
		ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.maybeqeafie");
		DepartmentRepository departmentRepository = (DepartmentRepository) ctx.getBean("departmentRepository");
		PersonalRepository personalRepository = (PersonalRepository) ctx.getBean("personalRepository");


		Personal personal = new Personal();

//		System.out.println(departmentRepository.findAll());
//		System.out.println(personalRepository.findAll());

		System.out.println("Choose an action: \n-Get values from database\n-Add value from database\n-Update value from database\n-Delete value from database");

		Scanner scanner = new Scanner(System.in);
		System.out.println("- ");
		String operation = scanner.next();
		if(Objects.equals(operation, "get")){
			System.out.println(personalRepository.findAll());
		}
		if(Objects.equals(operation,"add")){
			System.out.println("-Insert id");
			personal.setId(scanner.nextInt());
			personal.setName(scanner.next());
			personal.setSalary(scanner.nextInt());
			personal.setAge(scanner.nextInt());
			personalRepository.save(personal);

			System.out.println(personalRepository.findById(personal.getId()));
		}
		if(Objects.equals(operation,"delete")){
			System.out.println("-Insert id");
			personalRepository.deleteById(scanner.nextInt());
			System.out.println(personalRepository.findAll());
		}
		if(Objects.equals(operation,"update")){
			System.out.println("-Insert id");
			Personal personalUpdate = new Personal();
			personalUpdate.setId(scanner.nextInt());
			personalUpdate.setName(scanner.next());

			System.out.println(personalRepository.findAll());
		}
	}


}
