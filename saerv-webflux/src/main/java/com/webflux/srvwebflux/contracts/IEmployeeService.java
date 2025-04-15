package com.webflux.srvwebflux.contracts;

import com.webflux.srvwebflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeService {
	
	void create(Employee employee);
	Mono<Employee> findById(int id);
	Flux<Employee> findByName(String name);
	Flux<Employee> findAll();
	Mono<Employee> update(Employee e);
	Mono<Void> delete(Integer id);

}
