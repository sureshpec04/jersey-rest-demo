package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Person p1 = new Person();
		p1.setFirstName("Peter");
		p1.setLastName("Mark");
		p1.setCompany("cts");
		
		personRepo.save(p1);
		
		log.info("Display all persons: ");
		personRepo.findAll().stream().forEach(System.out :: println);
		
	}
}
