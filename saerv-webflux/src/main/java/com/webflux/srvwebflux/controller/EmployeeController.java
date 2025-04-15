package com.webflux.srvwebflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.srvwebflux.model.Employee;
import com.webflux.srvwebflux.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public void create(@RequestBody Employee e) {
		employeeService.create(e);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id){
		Mono<Employee> e = employeeService.findById(id);
		return new ResponseEntity<Mono<Employee>>(e, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> findByName(@PathVariable("name") String name){
		return employeeService.findByName(name);		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Mono<Employee> update(@RequestBody Employee e){
		return employeeService.update(e);
	}
	
	@RequestMapping(value = "/delete/{id}")
	public void delete(@PathVariable Integer id) {
		employeeService.delete(id).subscribe();
	}
	
	
	
}
