package com.webflux.srvwebflux.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webflux.srvwebflux.contracts.IEmployeeService;
import com.webflux.srvwebflux.dao.EmployeeRepository;
import com.webflux.srvwebflux.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public void create(Employee employee) {
		employeeRepository.save(employee);	
	}
	@Override
	public Mono<Employee> findById(int id) {
		return employeeRepository.findById(id)
				.map(emp -> {
					emp.setSalary(1000);
					return emp;
				})
				.doOnSuccess(success -> System.out.println("Salário alterado durante a consulta!"))
				.onErrorMap(e -> new RuntimeException("Erro consultar o empregado" + e.getMessage()));
				
	}
	
	@Override
	public Flux<Employee> findByName(String name) {
		return employeeRepository.findByName(name)
				.map(emp -> {
					emp.setName(name.toUpperCase());
					return emp;
				})
				.doOnError(e -> System.out.println("Error"))
				.onErrorMap(e -> new RuntimeException("Erro ao consultar lista de empregados"))
				.onBackpressureBuffer()
				.onBackpressureDrop();
				
	}
	
	
	@Override
	public Flux<Employee> findAll() {
		return employeeRepository.findAll();
	}
	@Override
	public Mono<Employee> update(Employee e) {
		return employeeRepository.save(e);
	}
	@Override
	public Mono<Void> delete(Integer id) {
		return employeeRepository.deleteById(id);
	}
}
