package dairy.project.production;

import dairy.project.production.Enumerated.RoleName;
import dairy.project.production.Repository.EmployeeRepository;
import dairy.project.production.entity.AccountDetails;
import dairy.project.production.entity.Employee;
import dairy.project.production.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProductionApplication {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {

        SpringApplication.run(ProductionApplication.class, args);
    }


//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//        @Bean
//        CommandLineRunner runner() {
//            return (String... args) -> {
//                Set<Role> roleList = new HashSet<>();
//                roleList.add(new Role(RoleName.ROLE_ADMIN));
//                String pass = new BCryptPasswordEncoder().encode("123456");
//                AccountDetails accountDetails =new AccountDetails(true,pass,roleList);
//
//
//                Employee employee = new Employee(32259334, "Fredrick Aponyo", "fraponyo@gmail.com");
//                employee.setAccount(accountDetails);
//                accountDetails.setEmployee(employee);
//
//                employeeRepository.save(employee);
//
//            };
//        }

}
