package dairy.project.production.controller;

import dairy.project.production.Service.EmployeeService;
import dairy.project.production.entity.Employee;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	//Find all employess
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return employeeService.findAll();
	}


	//Find employee given ID
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.findById(employeeId);
		return ResponseEntity.ok().body(employee);
	}


	//Find employee given email
	@GetMapping("/employees/user/{email}")
	public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable(value = "email") String email)
			 {
		Employee employee = employeeService.findByEmail(email);
		return ResponseEntity.ok().body(employee);
	}


	//Save employee
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {

		return employeeService.saveEmployee(employee);
	}


	//Update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") int employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

		final Employee updatedEmployee = employeeService.updateEmployee(employeeId,employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}


   //delete employee
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.findById(employeeId);

		employeeService.deleteEmployee(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


//	//@Secured("ROLE_USER")
//	@PreAuthorize("hasRole('USER')")
//	////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
}
