package ru.maybeqeafie.jpalessons;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.maybeqeafie.jpalessons.Entity.Department;
import ru.maybeqeafie.jpalessons.Entity.Employee;
import ru.maybeqeafie.jpalessons.service.DepartmentService;
import ru.maybeqeafie.jpalessons.service.EmployeeService;

import java.util.Objects;
import java.util.Scanner;

//@SpringBootApplication
public class JpaLessonsApplication {

    public static void main(String[] args) {
        //SpringApplication.run(JpaLessonsApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.maybeqeafie");

        DepartmentService departmentService = (DepartmentService) ctx.getBean("departmentService");
        EmployeeService employeeService = (EmployeeService) ctx.getBean("employeeService");

        Employee employee = new Employee();

//		System.out.println(departmentRepository.findAll());
//		System.out.println(personalRepository.findAll());

        while (true) {
            System.out.println("Choose an action: \n-Get values from database\n-Add value from database\n-Update value from database\n-Delete value from database");

            Scanner scanner = new Scanner(System.in);
            String operation = scanner.next();
            if (Objects.equals(operation, "get")) {
                System.out.println(employeeService.getAll());
            }
            if (Objects.equals(operation, "add")) {
                System.out.println("-Insert name:");
                String name = scanner.next();
                employeeService.save(Employee.builder()
                        .name(name)
                        .department(departmentService.getById(1))
                        .build());
            }
            if (Objects.equals(operation, "update")) {
                System.out.println("-Insert id:");
                int id = scanner.nextInt();
                Employee employee1 = employeeService.getById(id);
                System.out.println(employee1);
                System.out.println("enter new name:");
                String newName = scanner.next();
                employee1.setName(newName);
                employeeService.save(employee1);
            }
            if (Objects.equals(operation, "delete")) {
                System.out.println("-Insert id:");
                int id1 = scanner.nextInt();
                Employee deleteEmployee = employeeService.getById(id1);
                System.out.println(deleteEmployee);
                employeeService.delete(deleteEmployee);
            }
        }
    }


}
