package dairy.project.production.controller;

import dairy.project.production.Service.EmployeeService;
import dairy.project.production.component.JwtResponse;
import dairy.project.production.entity.Employee;
import dairy.project.production.entity.Login;
import dairy.project.production.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtProvider;

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/auth/token")
    public ResponseEntity authenticate(@RequestBody Login loginUser) throws AuthenticationException {



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Employee employee = employeeService.findByEmail(userDetails.getUsername());


        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),employee.getName(), userDetails.getAuthorities()));
    }


}
