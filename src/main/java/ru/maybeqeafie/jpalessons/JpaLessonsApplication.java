package ru.maybeqeafie.jpalessons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.maybeqeafie.jpalessons.service.UsersService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

@SpringBootApplication
public class JpaLessonsApplication {
    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args) throws RemoteException, NotBoundException {
        SpringApplication.run(JpaLessonsApplication.class, args);
        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.maybeqeafie");


//        final String value = "file://c:/calculator";
//        System.setProperty("java.rmi.server.codebase", value);
//        final Registry registry = LocateRegistry.getRegistry(2020);
//        CardRemote cardRemote = (CardRemote) registry.lookup(UNIQUE_BINDING_NAME);
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter number");
//        int number = scanner.nextInt();
//
//        System.out.println("Enter name");
//        String name = scanner.next();
//
//        System.out.println("Enter term");
//        int term = scanner.nextInt();
//
//        System.out.println("Enter code");
//        int code = scanner.nextInt();
//
//        if(cardRemote.donate(name,number,term,code)){
//            System.out.println("Card connection");
//        }
//        else {
//            System.out.println("Connection card error");
//        }
    }


}
