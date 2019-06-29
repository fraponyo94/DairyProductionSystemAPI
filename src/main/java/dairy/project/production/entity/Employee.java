package dairy.project.production.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.UniqueElements;


import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Employee {
    @Id
    @Column(name = "employeeId",length = 15)
    @JsonView(View.Summary.class)
    private int employeeId;

    @Column(name = "name",nullable = false,length = 60)
    @JsonView(View.Summary.class)
    private String name;

    @Column(name = "email",unique = true)
    @JsonView(View.Summary.class)
    private String email;

    @Column(name = "phoneNumber",length = 15)
    @JsonView(View.Summary.class)
    private int phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfEmployment")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfEmployment;

    @Temporal(TemporalType.DATE)
    @Column(name = "dateOfDismissal")
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonView(View.Summary.class)
    private Date dateOfDismissal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonView(View.Summary.class)
    private AccountDetails account;

    public Employee() {
    }

    public Employee(int employeeId, String name, int phoneNumber, String email, AccountDetails account,
                    Date dateOfEmployment, Date dateOfDismissal) {
        this.employeeId = employeeId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.account = account;
        this.dateOfEmployment = dateOfEmployment;
        this.dateOfDismissal = dateOfDismissal;

    }

    public Employee(int employeeId, String name,
                    String email) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;

    }

    public AccountDetails getAccount() {
        return account;
    }

    public void setAccount(AccountDetails account) {
        this.account = account;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public Date getDateOfDismissal() {
        return dateOfDismissal;
    }

    public void setDateOfDismissal(Date dateOfDismissal) {
        this.dateOfDismissal = dateOfDismissal;
    }




}
