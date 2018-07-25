package com.example.demo.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepository;

@Component
@Path("/api/v1")
public class PersonResource {

	private final PersonRepository personRepo;
	
	@Autowired
	public PersonResource(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	
	@GET
	@Path( "/persons")
    @Produces("application/json")
	
	public List<Person> getAllPerson(){
		List<Person> persons = personRepo.findAll();
		return persons;
		
	}
	
	@GET
	@Path( "/persons/{id}")
    @Produces("application/json")
	public Response findPersonById(@PathParam("id") String id) throws NumberFormatException, Exception{
		Person person = personRepo.findById(Long.valueOf(id)).orElseThrow( () -> new Exception("Unable to find a person with id: " + id));
		return Response.ok(person, MediaType.APPLICATION_JSON).build();
		
	}
	
	@POST
	@Path( "/persons")
    @Produces("application/json")
	public Response addPerson(Person person) throws URISyntaxException{
		
		if(person.getId() != null && person.getId() > 0) {
			Optional<Person> p1 = personRepo.findById(person.getId());
			if(p1.isPresent()) return Response.notModified().build();
		}
		
		Person addedPerson = personRepo.save(person);
		return Response.created(new URI("/persons/id")).build();
		
	}
}
