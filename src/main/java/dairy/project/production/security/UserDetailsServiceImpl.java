package dairy.project.production.security;

import dairy.project.production.Repository.EmployeeRepository;
import dairy.project.production.component.DairyUser;
import dairy.project.production.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = employeeRepo.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );

        System.out.println(employee.getEmail()+ employee.getAccount().getRoles());
        return DairyUser.build(employee.getAccount());
    }
}
