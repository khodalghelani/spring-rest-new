package io.egen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.UUID;

@Entity
@NamedQueries({
                      @NamedQuery(name = "Employee.findAll",
                                  query = "SELECT emp FROM Employee emp ORDER BY emp.email DESC"),
                      @NamedQuery(name = "Employee.findByEmail",
                                  query = "SELECT emp FROM Employee emp WHERE emp.email=:paramEmail")
              })
public class Employee {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    public Employee() {
        this.id = UUID.randomUUID()
                      .toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}