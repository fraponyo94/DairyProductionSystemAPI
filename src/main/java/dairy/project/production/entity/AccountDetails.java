package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "account")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class AccountDetails {
    @Id
    @Column(name = "account_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "enabled",nullable = false)
    @JsonView(View.Summary.class)
    private boolean enabled;

    @OneToMany(fetch= FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinTable(name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonView(View.Summary.class)

    private Set<Role> roles = new HashSet<>();

//    @MapsId
//    @OneToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "employeeId")
    @JsonIgnore
    private Employee employee;

    @Column(name = "password_reset_token")
    @JsonView(View.Summary.class)
    private UUID resetToken;

    public AccountDetails() {
    }

    public AccountDetails(boolean enabled, String password, Set<Role> roles) {
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public UUID getResetToken() {
        return resetToken;
    }

    public void setResetToken(UUID resetToken) {
        this.resetToken = resetToken;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();

        for(Role role:roles) {
            String name=role.getName().toString();
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

}
