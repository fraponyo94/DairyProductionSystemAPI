package dairy.project.production.Service;

import dairy.project.production.Enumerated.RoleName;
import dairy.project.production.Repository.EmployeeRepository;
import dairy.project.production.Repository.RoleRepository;
import dairy.project.production.entity.AccountDetails;
import dairy.project.production.entity.Employee;
import dairy.project.production.entity.Role;
import dairy.project.production.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeesRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepository roleRepository;



    //Find employee by Id
    public Employee findById(int employeeId) throws ResourceNotFoundException {
        return employeesRepository.findByEmployeeId(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    }


    //Update employee
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employeeExist = employeesRepository.findByEmployeeId(employee.getEmployeeId());
        if(!employeeExist.isPresent()) {
            AccountDetails details = new AccountDetails();

            //Generate passwword
            String password = encoder.encode(Integer.toString(employee.getEmployeeId()));
            details.setEnabled(true);
            details.setPassword(password);

            //Assign role employee to User
            Role role = roleRepository.findByName(RoleName.ROLE_EMPLOYEE);
            Set<Role> roles = new HashSet<>();
            if (role != null) {
                roles.add(role);
                details.setRoles(roles);
            } else {
                Role role1 = new Role(RoleName.ROLE_EMPLOYEE);
                roles.add(role1);
                details.setRoles(roles);
            }


            details.setEmployee(employee);

            //Create account
            employee.setAccount(details);
            return employeesRepository.save(employee);
        }else{
            return employeeExist.get();
        }

    }


    //Update employee
    public Employee updateEmployee(int employeeId,Employee employeeDetails) throws ResourceNotFoundException{
     Employee employee = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

            employee.setDateOfEmployment(employeeDetails.getDateOfEmployment());
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            employee.setDateOfDismissal(employeeDetails.getDateOfDismissal());

            return employeesRepository.save(employee);

    }





    //Delete employee
    public void deleteEmployee(Employee employee){
        employeesRepository.delete(employee);

    }


    //Find by email
    public Employee findByEmail(String email){
        return  employeesRepository.findByEmail(email).get();
    }


    //Find all employees
    public List<Employee> findAll() {
        return employeesRepository.findAll();
    }
}
