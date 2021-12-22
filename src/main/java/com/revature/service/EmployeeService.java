package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDAO;
import com.revature.models.Employee;

/**
 * The Service layer is responsible for business (or application) logic,
 * whereas the DAO layer is responsible for persistence logic (anything to do with the Session)
 * and Hibernate's Session methods.
 * @author michael
 *
 */
public class EmployeeService {

	private EmployeeDAO edao;
	
	public EmployeeService(EmployeeDAO edao) {
		super();
		this.edao = edao;
	}
	
	public Employee confirmLogin(String username, String password) {
		
		// call the findAll method, stream() through the records, and find the first whose username and password matches
		Optional<Employee> possibleEmp = edao.findAll()
				.stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPassword().equals(password)))
				.findFirst();
				
		return (possibleEmp.isPresent() ? possibleEmp.get() : null);
	}
	
	public Employee doubleCheckLogin(String username, String password) {
		
		Employee e = new Employee(2, "firstname", "lastname", username, password);
		
		Optional<Employee> poss = Optional.ofNullable(confirmLogin(e.getUsername(),e.getPassword()));
		
		return null;
		
	}
	
	public List<Employee> findAll() {
		return edao.findAll();				
	}
	
	public int insert(Employee e) {
		return edao.insert(e);
	}
	
	public boolean update(Employee e) {
		return edao.update(e);
	}
	
	public boolean delete(Employee e) {
		return edao.delete(e);
	}
	
}
